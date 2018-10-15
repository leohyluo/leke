package com.sugar.leke.controller;

import com.sugar.leke.framework.web.ResponseMessage;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.framework.web.WebUtils;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    @GetMapping("/get/{userName}")
    public ResponseMessage getAccount(@PathVariable String userName) {
        if (StringUtils.isEmpty(userName)) {
            return WebUtils.buildResponseMessage(ResponseStatus.REQUIRED_PARAMETER_MISSING);
        }
        UserAccount userAccount = userAccountService.getByUserName(userName);
        return WebUtils.buildSuccessResponseMessage(userAccount);
    }
}
