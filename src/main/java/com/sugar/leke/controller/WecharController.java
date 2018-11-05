package com.sugar.leke.controller;

import com.sugar.leke.service.OfficalAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wechar")
public class WecharController {

    @Resource
    private OfficalAccountService officalAccountService;

    @GetMapping("/menu/create")
    public String createMenu() {
        officalAccountService.createMenu();
        return "success";
    }
}
