package com.sugar.leke.service.impl;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.mapper.OrderTaskMapper;
import com.sugar.leke.pojo.OrderTask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class OrderTaskServiceImpl implements OrderTaskService {

    @Resource
    private OrderTaskMapper orderTaskMapper;

    @Override
    public void update(OrderTask orderTask) {
        Integer status = orderTask.getStatus();
        if (status == AccountStatus.接单成功.getCode() || status == AccountStatus.接单失败.getCode()) {
            orderTask.setEndTime(LocalDateTime.now());
        }
        orderTaskMapper.updateByPrimaryKeySelective(orderTask);
    }
}
