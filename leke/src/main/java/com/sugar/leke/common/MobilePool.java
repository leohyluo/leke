package com.sugar.leke.common;

import com.sugar.leke.pojo.ReceiptInfo;

import java.util.HashMap;
import java.util.Map;

public class MobilePool {

    private static Map<String, ReceiptInfo> receiptPool = new HashMap<>();

    public static ReceiptInfo get(String inputMobile) {
        ReceiptInfo receiptInfo = null;
        for(String mobile : receiptPool.keySet()) {
            if(inputMobile.equals(mobile)) {
                receiptInfo = receiptPool.get(mobile);
                break;
            }
        }
        return receiptInfo;
    }

    public static void update(ReceiptInfo receiptInfo) {
        ReceiptInfo cacheReceiptInfo = get(receiptInfo.getMobile());
        receiptPool.put(receiptInfo.getMobile(), receiptInfo);
    }
}
