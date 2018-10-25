package com.sugar.leke.controller;

import com.sugar.leke.framework.web.ResponseMessage;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.framework.web.WebUtils;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/status/{mobile}")
    public ResponseMessage status(@PathVariable String mobile) {
        ResponseStatus responseStatus = userAccountService.getAccountStatus(mobile);
        return WebUtils.buildResponseMessage(responseStatus);
    }

    @PostMapping("/login")
    public ResponseMessage login(String userName, String password) {
        userAccountService.login(userName, password);
        return WebUtils.buildSuccessResponseMessage();
    }


    @GetMapping("/active/{isActive}/{mobile}")
    public ResponseMessage stop(@PathVariable Integer isActive, @PathVariable String mobile){
        UserAccount userAccount = userAccountService.getByUserName(mobile);
        if (userAccount == null) {
            return WebUtils.buildResponseMessage(ResponseStatus.USER_NOT_FOUND);
        }
        userAccountService.updateAccoutActive(userAccount, isActive);
        return WebUtils.buildSuccessResponseMessage();
    }
}
