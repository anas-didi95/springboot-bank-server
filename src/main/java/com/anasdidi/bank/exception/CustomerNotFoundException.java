package com.anasdidi.bank.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomerNotFoundException extends Exception {

	private final String customerId;
}
