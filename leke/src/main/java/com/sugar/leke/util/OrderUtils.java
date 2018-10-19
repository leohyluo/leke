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
            }
        }
        return false;
    }
}
