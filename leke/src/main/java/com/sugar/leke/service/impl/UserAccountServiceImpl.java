package com.sugar.leke.service.impl;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.mapper.OrderTaskMapper;
import com.sugar.leke.mapper.UserAccountMapper;
import com.sugar.leke.pojo.OrderTask;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.util.CollectionUtils;
import com.sugar.leke.util.DateUtils;
import com.sugar.leke.util.OrderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private OrderTaskMapper orderTaskMapper;

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

    @Override
    public ResponseStatus getAccountStatus(String mobile) {
        OrderTask orderParam = new OrderTask();
        orderParam.setMobile(mobile);
        orderParam.setToday(DateUtils.today());
        List<OrderTask> orderTaskList = orderTaskMapper.listUserOrderByStatus(orderParam);
        if(orderTaskList.isEmpty()) {
            return ResponseStatus.ORDER_CAN_BE_START;
        }
        //60分钟内未完成的订单
        List<OrderTask> unFinishOrderList = orderTaskList.stream().filter(OrderUtils::hasUnFinishOrder).collect(toList());
        if (CollectionUtils.isNotEmpty(unFinishOrderList)) {
            return ResponseStatus.ORDER_UNFINISH;
        }
        //最近一次接单情况
        OrderTask orderTask = orderTaskList.get(0);
        Integer status = orderTask.getStatus();
        if (status == AccountStatus.接单中.getCode()) {
            return ResponseStatus.ORDER_RECEIPTING;
        } else if (status == AccountStatus.接单成功.getCode()) {
            return ResponseStatus.ORDER_RECEIPT_SUCCESS;
        } else if (status == AccountStatus.接单失败.getCode()) {
            return ResponseStatus.OREDER_RECEIPT_FAIL;
        } else if (status == AccountStatus.已上限.getCode()) {
            return ResponseStatus.ORDER_FULL;
        }
        return null;
    }

    @Override
    public void updateAccoutActive(UserAccount userAccount, Integer isActive) {
        userAccount.setIsActive(isActive);
        userAccountMapper.updateByPrimaryKey(userAccount);
    }
}
