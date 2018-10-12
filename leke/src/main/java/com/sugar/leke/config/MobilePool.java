package com.sugar.leke.config;

import com.sugar.leke.pojo.ReceiptInfo;
import com.sugar.leke.util.BeanCopierUtil;

import java.util.List;
import static java.util.stream.Collectors.*;

public class MobilePool {

    private static ThreadLocal<List<ReceiptInfo>> list = new ThreadLocal<>();
    //private static List<ReceiptInfo> receiptInfoList = new ArrayList<>();

    public static ReceiptInfo get(String mobile) {
        return list.get().stream().filter(e -> e.getMobile().equals(mobile)).findFirst().get();
    }

    public static void save(ReceiptInfo receiptInfo) {
        ReceiptInfo ri = get(receiptInfo.getMobile());
        if(ri == null) {
            list.get().add(receiptInfo);
        } else {
            list.get().stream().filter(e -> e.getMobile().equals(receiptInfo.getMobile())).peek(e -> BeanCopierUtil.copy(e, receiptInfo)).collect(toList());
        }
    }
}
