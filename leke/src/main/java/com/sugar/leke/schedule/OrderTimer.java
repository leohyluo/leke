package com.sugar.leke.schedule;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.mapper.OrderTaskMapper;
import com.sugar.leke.mapper.UserAccountMapper;
import com.sugar.leke.pojo.OrderTask;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.OrderService;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.service.impl.OrderTaskService;
import com.sugar.leke.util.DateUtils;
import com.sugar.leke.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class OrderTimer {

    @Resource
    private OrderTaskService orderTaskService;
    @Resource
    private OrderTaskMapper orderTaskMapper;
    @Resource
    private OrderService orderService;
    @Resource
    private UserAccountService userAccountService;


    public void updateOrderStatus() {
        OrderTask orderParam = new OrderTask();
        orderParam.setToday(DateUtils.today());
        orderParam.setStatus(AccountStatus.接单成功.getCode());
        List<OrderTask> orderTaskList = orderTaskMapper.listUserOrderByStatus(orderParam);
        for (OrderTask orderTask : orderTaskList) {

        }
    }

    private Boolean orderCompleted(OrderTask orderTask) {
        UserAccount userAccount = userAccountService.getByUserName(orderTask.getMobile());
        String sessionId = orderTask.getSessionId();
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = orderService.getSessionId();
            orderService.login(userAccount.getUserName(), userAccount.getPassword(), sessionId);
        }
        orderService.receipt(orderTask.getMobile(), sessionId);
        return true;
    }
}
