package com.anasdidi.bank.domain.account;

import java.text.SimpleDateFormat;
import java.util.Date;

final class AccountUtils {

  static final String generateAccountNo() {
    SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
    return sdf.format(new Date());
  }
}
