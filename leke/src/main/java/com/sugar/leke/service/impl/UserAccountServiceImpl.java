package com.sugar.leke.service.impl;

import com.sugar.leke.mapper.UserAccountMapper;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public UserAccount getByUserName(String userName) {
        UserAccount param = new UserAccount();
        param.setUserName(userName);
        List<UserAccount> userAccountList = userAccountMapper.select(param);
        UserAccount userAccount = null;
        if (CollectionUtils.isNotEmpty(userAccountList)) {
            userAccount = userAccountList.get(0);
        }
        return userAccount;
    }
}
