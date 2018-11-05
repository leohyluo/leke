package com.sugar.leke.thread;

import com.alibaba.fastjson.JSONObject;
import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.framework.spring.SpringContextHolder;
import com.sugar.leke.pojo.OrderTask;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.service.impl.OrderTaskService;
import com.sugar.leke.service.impl.OrderTaskServiceImpl;
import com.sugar.leke.service.impl.UserAccountServiceImpl;
import com.sugar.leke.util.HttpUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ReceiptThread implements Runnable {

    private OrderTask orderTask;
    private String sessionId;

    public ReceiptThread(OrderTask orderTask, String sessionId) {
        this.orderTask = orderTask;
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(orderTask.getMobile());
        OrderTaskService orderTaskService = SpringContextHolder.getBean(OrderTaskServiceImpl.class);

        String url = "http://s.58leke.com/index.php?s=/Indexajax/taskset.html";
        String param = "task_type=1&app=1&pc=2&maxmoney=&hasCaptcha=0&captcha_code=";
        String mobile = orderTask.getMobile();

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
        UserAccountService userAccountService = SpringContextHolder.getBean(UserAccountServiceImpl.class);
        while(true) {
            UserAccount userAccount = userAccountService.getByUserName(mobile);
            if (userAccount != null && userAccount.getIsActive() == 0) {
                break;
            }
            String result = HttpUtils.doPost(url, param, requestHeader);
            try {
                Thread.sleep(5000);
                JSONObject json = JSONObject.parseObject(result);
                System.out.println(json.toJSONString());
                String msgs = json.getString("msgs");
                boolean end = false;
                if (msgs == null || "".equals(msgs)) {
                    end = true;
                    orderTask.setStatus(AccountStatus.接单失败.getCode());
                    orderTask.setErrorMsg(json.toJSONString());
                    orderTask.setErrorCount(orderTask.getErrorCount() + 1);
                }
                if(msgs.contains("成功")){
                    end = true;
                    this.orderTask.setStatus(AccountStatus.接单成功.getCode());
                    System.out.println(mobile + "接单成功！");
                }
                orderTask.setTotalCount(orderTask.getTotalCount() + 1);
                orderTaskService.update(orderTask);
                if(end) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject exec(OrderTask orderTask) {
        String url = "http://s.58leke.com/index.php?s=/Indexajax/taskset.html";
        String param = "task_type=1&app=1&pc=2&maxmoney=&hasCaptcha=0&captcha_code=";
        String mobile = orderTask.getMobile();

        String cookie = "PHPSESSID="+this.sessionId+"; buyers_mobile="+mobile+"; _ga=GA1.2.2048970887.1539176044; _gid=GA1.2.1635056857.1539446050; _gat_gtag_UA_117103124_1=1";
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("accept", "application/json, text/javascript, */*; q=0.01");
        requestHeader.put("accept-encoding", "gzip, deflate");
        requestHeader.put("accept-languageg", "zh-CN,zh;q=0.9");
        requestHeader.put("connection", "Keep-Alive");
        requestHeader.put("content-length", "59");
        requestHeader.put("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
        requestHeader.put("Cookie",cookie);
        String result = HttpUtils.doPost(url, param, requestHeader);
        JSONObject json = JSONObject.parseObject(result);
        return json;
    }

    private void tryUpdateStatusAsFinish(OrderTask orderTask) {

    }
}
