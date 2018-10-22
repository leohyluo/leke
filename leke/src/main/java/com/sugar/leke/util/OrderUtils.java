package com.sugar.leke.util;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.pojo.OrderTask;

import java.time.Duration;
import java.time.LocalDateTime;

public class OrderUtils {

    public static boolean hasUnFinishOrder(OrderTask orderTask) {
        if(orderTask.getStatus() == AccountStatus.待完成.getCode()) {
            LocalDateTime endTime = orderTask.getEndTime();
            if(endTime != null) {
                LocalDateTime now = LocalDateTime.now();
                Duration duration = java.time.Duration.between(endTime,now);
                long minutes = duration.toMinutes();
                //60分钟内未完成，即失效
                if (minutes > 60) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 10, 20,9 , 8);
        OrderTask orderTask = new OrderTask();
        orderTask.setEndTime(localDateTime);
        orderTask.setStatus(AccountStatus.待完成.getCode());
        hasUnFinishOrder(orderTask);
    }
}
