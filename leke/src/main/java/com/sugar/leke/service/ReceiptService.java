package com.sugar.leke.service;

import com.sugar.leke.pojo.ReceiptInfo;

public interface ReceiptService {

    void start(String mobile);

    void run(ReceiptInfo receiptInfo);
}
