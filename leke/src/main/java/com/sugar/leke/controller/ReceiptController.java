package com.sugar.leke.controller;

import com.sugar.leke.framework.web.ResponseMessage;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.framework.web.WebUtils;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.LekeService;
import com.sugar.leke.service.UserAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Resource
    private LekeService orderService;
    @Resource
    private UserAccountService userAccountService;

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/start/{mobile}")
    public ResponseMessage start(@PathVariable String mobile) {
        UserAccount userAccount = userAccountService.getByUserName(mobile);
        if (userAccount == null) {
            return WebUtils.buildResponseMessage(ResponseStatus.USER_NOT_FOUND);
        }
        String password = userAccount.getPassword();
        String sessionId = orderService.getSessionId();
        orderService.login(mobile, password, sessionId);
        orderService.receipt(mobile, sessionId);
        return WebUtils.buildSuccessResponseMessage();
    }
}
