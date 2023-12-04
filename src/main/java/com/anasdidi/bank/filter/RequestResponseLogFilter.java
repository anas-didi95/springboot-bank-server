package com.anasdidi.bank.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestResponseLogFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    ContentCachingRequestWrapper requestWrapper = requestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = responseWrapper(response);
    String requestId = request.getRequestId();

    filterChain.doFilter(requestWrapper, responseWrapper);
    logRequest(requestWrapper, requestId);
    logResponse(responseWrapper, requestId);
  }

  private ContentCachingRequestWrapper requestWrapper(ServletRequest request) {
    if (request instanceof ContentCachingRequestWrapper requestWrapper) {
      return requestWrapper;
    }
    return new ContentCachingRequestWrapper((HttpServletRequest) request);
  }

  private ContentCachingResponseWrapper responseWrapper(ServletResponse response) {
    if (response instanceof ContentCachingResponseWrapper responseWrapper) {
      return responseWrapper;
    }
    return new ContentCachingResponseWrapper((HttpServletResponse) response);
  }

  private void logRequest(ContentCachingRequestWrapper request, String requestId) throws IOException {
    String tag = String.format("[logRequest:%s]", requestId);
    log.info("{} {} {}", tag, request.getMethod(), request.getRequestURI());
    log.info("{} Parameters: {}", tag,
        collectionToString(Collections.list(request.getParameterNames()), request::getParameter));
    log.info("{} Headers: {}", tag, collectionToString(Collections.list(request.getHeaderNames()), request::getHeader));
    log.info("{} Body: {}", tag, beautifyBody(new String(request.getContentAsByteArray())));
  }

  private void logResponse(ContentCachingResponseWrapper response, String requestId) throws IOException {
    String tag = String.format("[logResponse:%s]", requestId);
    log.info("{} Status Code: {}", tag, response.getStatus());
    log.info("{} Headers: {}", tag, collectionToString(response.getHeaderNames(), response::getHeader));
    log.info("{} Body: {}", tag, beautifyBody(new String(response.getContentAsByteArray())));
    response.copyBodyToResponse();
  }

  private String collectionToString(Collection<String> collection, Function<String, String> valueResolver) {
    StringBuilder builder = new StringBuilder();
    boolean putDelimiter = false;
    for (String item : collection) {
      if (putDelimiter) {
        builder.append("; ");
      }
      String value = valueResolver.apply(item);
      builder.append("%s=%s".formatted(item, value));
      putDelimiter = true;
    }
    return builder.toString();
  }

  private String beautifyBody(String str) throws JsonProcessingException {
    if (StringUtils.isBlank(str)) {
      return "";
    }
    ObjectMapper objectMapper = new ObjectMapper();
    Object body = objectMapper.readValue(str, Object.class);
    return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
  }
}
