package com.sugar.leke.thread;

import com.alibaba.fastjson.JSONObject;
import com.sugar.leke.util.HttpUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ReceiptThread implements Runnable {

    private String mobile;
    private String sessionId;

    public ReceiptThread(String mobile, String sessionId) {
        this.mobile = mobile;
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        String url = "http://s.58leke.com/index.php?s=/Indexajax/taskset.html";
        String param = "task_type=1&app=1&pc=2&maxmoney=&hasCaptcha=0&captcha_code=";

        String cookie = "PHPSESSID="+this.sessionId+"; buyers_mobile="+this.mobile+"; _ga=GA1.2.2048970887.1539176044; _gid=GA1.2.1635056857.1539446050; _gat_gtag_UA_117103124_1=1";
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("accept", "application/json, text/javascript, */*; q=0.01");
        requestHeader.put("accept-encoding", "gzip, deflate");
        requestHeader.put("accept-languageg", "zh-CN,zh;q=0.9");
        requestHeader.put("connection", "Keep-Alive");
        requestHeader.put("content-length", "59");
        requestHeader.put("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
        requestHeader.put("Cookie",cookie);
        System.out.println(this.mobile + "开始接单");
        while(true) {
            String result = HttpUtils.doPost(url, param, requestHeader);
            try {
                Thread.sleep(5000);
                JSONObject json = JSONObject.parseObject(result);
                System.out.println(json.toJSONString());
                String msgs = json.getString("msgs");
                if (msgs == null || "".equals(msgs)) {
                    System.out.println(this.mobile + "出现异常");
                    continue;
                }
                if(msgs.contains("成功")){
                    System.out.println(this.mobile + "接单成功！");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
