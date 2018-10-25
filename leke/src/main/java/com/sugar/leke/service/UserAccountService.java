package com.sugar.leke.service;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.framework.exception.ServiceException;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.pojo.UserAccount;

public interface UserAccountService {

    UserAccount getByUserName(String userName);

    ResponseStatus getAccountStatus(String mobile);

    void updateAccoutActive(UserAccount userAccount, Integer isActive);

    String login(String userName, String password) throws ServiceException;
}
