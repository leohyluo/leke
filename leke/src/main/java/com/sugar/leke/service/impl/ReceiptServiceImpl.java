package com.sugar.leke.service.impl;

import com.sugar.leke.config.MobilePool;
import com.sugar.leke.pojo.ReceiptInfo;
import com.sugar.leke.service.ReceiptService;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Override
    public void start(String mobile) {
        ReceiptInfo receiptInfo = new ReceiptInfo();
        receiptInfo.setMobile(mobile);
        MobilePool.save(receiptInfo);
    }

    @Override
    public void run(ReceiptInfo receiptInfo) {
        System.out.println("开始接单");
        MobilePool.save(receiptInfo);
    }
}
