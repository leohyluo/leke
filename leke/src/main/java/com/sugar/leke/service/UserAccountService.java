package com.sugar.leke.service;

import com.sugar.leke.pojo.UserAccount;

public interface UserAccountService {

    UserAccount getByUserName(String userName);
}
