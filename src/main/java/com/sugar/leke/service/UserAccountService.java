package com.sugar.leke.service;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.framework.exception.ServiceException;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.pojo.vo.AccountStatusVo;

public interface UserAccountService {

    UserAccount getByUserName(String userName);

    AccountStatusVo getAccountStatus(String mobile);

    void updateAccoutActive(UserAccount userAccount, Integer isActive);

    String login(String userName, String password) throws ServiceException;
}
