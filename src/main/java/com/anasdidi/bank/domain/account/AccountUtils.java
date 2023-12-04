package com.anasdidi.bank.domain.account;

import java.text.SimpleDateFormat;
import java.util.Date;

final class AccountUtils {

  static final String generateAccountNo() {
    SimpleDateFormat sdf = new SimpleDateFormat(AccountConstants.ACCOUNT_NO_FORMAT);
    return String.format("%s%s", AccountConstants.ACCOUNT_NO_PREFIX, sdf.format(new Date()));
  }
}
