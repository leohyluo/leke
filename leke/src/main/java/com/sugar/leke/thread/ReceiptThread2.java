package com.sugar.leke.thread;

import com.alibaba.fastjson.JSONObject;
import com.sugar.leke.common.MobilePool;
import com.sugar.leke.pojo.ReceiptInfo;
import com.sugar.leke.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class ReceiptThread2 implements Runnable {

    private ReceiptInfo receiptInfo;
    private String sessionId;

    public ReceiptThread2(ReceiptInfo receiptInfo, String sessionId) {
        this.receiptInfo = receiptInfo;
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        String url = "http://s.58leke.com/index.php?s=/Indexajax/taskset.html";
        String param = "task_type=1&app=1&pc=2&maxmoney=&hasCaptcha=0&captcha_code=";
        String mobile = receiptInfo.getMobile();

        String cookie = "PHPSESSID="+this.sessionId+"; buyers_mobile="+mobile+"; _ga=GA1.2.2048970887.1539176044; _gid=GA1.2.1635056857.1539446050; _gat_gtag_UA_117103124_1=1";
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("accept", "application/json, text/javascript, */*; q=0.01");
        requestHeader.put("accept-encoding", "gzip, deflate");
        requestHeader.put("accept-languageg", "zh-CN,zh;q=0.9");
        requestHeader.put("connection", "Keep-Alive");
        requestHeader.put("content-length", "59");
        requestHeader.put("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
        requestHeader.put("Cookie",cookie);
        System.out.println(mobile + "开始接单");
        while(true) {
            boolean end = false;
            String result = HttpUtils.doPost(url, param, requestHeader);
            try {
                Thread.sleep(5000);
                //更新接单次数
                Integer totalCount = this.receiptInfo.getTotalCount() + 1;
                this.receiptInfo.setTotalCount(totalCount);

                JSONObject json = JSONObject.parseObject(result);
                System.out.println(json.toJSONString());
                String msgs = json.getString("msgs");
                if (msgs == null || "".equals(msgs)) {
                    end = true;
                    Integer errorCount = this.receiptInfo.getErrorCount() + 1;
                    this.receiptInfo.setErrorCount(errorCount);
                    this.receiptInfo.setErrorMsg(json.toJSONString());
                    System.out.println(mobile + "出现异常");
                }
                if(msgs.contains("成功")){
                    end = true;
                    Integer successCount = this.receiptInfo.getSuccessCount() + 1;
                    this.receiptInfo.setSuccessCount(successCount);
                    System.out.println(mobile + "接单成功！");
                }
                MobilePool.update(this.receiptInfo);
                if (end) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
