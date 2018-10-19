package com.sugar.leke.mapper;

import com.sugar.leke.framework.mybatis.MyMapper;
import com.sugar.leke.pojo.OrderTask;

import java.util.List;

public interface OrderTaskMapper extends MyMapper<OrderTask> {

    List<OrderTask> listUserOrderByStatus(OrderTask param);
}
