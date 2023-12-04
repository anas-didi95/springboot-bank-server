package com.anasdidi.bank.domain.customer;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class CustomerUtils {

  public static final String generateCustomerNo() {
    SimpleDateFormat sdf = new SimpleDateFormat(CustomerConstants.CUSTOMER_NO_FORMAT);
    return String.format("%s%s", CustomerConstants.CUSTOMER_NO_PREFIX, sdf.format(new Date()));
  }
}
