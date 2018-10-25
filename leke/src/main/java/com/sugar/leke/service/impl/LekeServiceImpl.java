package com.sugar.leke.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.mapper.OrderTaskMapper;
import com.sugar.leke.pojo.OrderTask;
import com.sugar.leke.service.LekeService;
import com.sugar.leke.thread.ReceiptThread;
import com.sugar.leke.thread.pool.ThreadPoolScheduler;
import com.sugar.leke.util.CollectionUtils;
import com.sugar.leke.util.DateUtils;
import com.sugar.leke.util.HttpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LekeServiceImpl implements LekeService {

    private static Map<String, String> map = new HashMap<>();
    @Resource
    private OrderTaskMapper orderTaskMapper;

    @Override
    public String login(String userName, String password, String sessionId) {
        String url = "http://s.58leke.com/index.php?s=/Index/login.html";
        String param = "username="+userName+"&password="+password;

        String cookie = "_ga=GA1.2.1099391170.1539658537; _gid=GA1.2.1985067262.1539658537; PHPSESSID=" + sessionId;

        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("accept", "application/json, text/javascript, */*; q=0.01");
        requestHeader.put("accept-encoding", "gzip, deflate");
        requestHeader.put("accept-language", "zh-CN,zh;q=0.9");
        requestHeader.put("connection", "keep-alive");
        requestHeader.put("content-length", "25");
        requestHeader.put("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        requestHeader.put("cookie", cookie);
        requestHeader.put("host", "s.58leke.com");
        requestHeader.put("origin", "http://s.58leke.com");
        requestHeader.put("referer", "http://s.58leke.com");
        requestHeader.put("x-requested-with", "XMLHttpRequest");

        String result = HttpUtils.doPost(url, param, requestHeader);
        JSONObject json = JSONObject.parseObject(result);
        System.out.println(json.toJSONString());
        return json.toJSONString();
    }

    @Override
    public void receipt(String mobile, String sessionId) {

        String url = "http://s.58leke.com/index.php?s=/Indexajax/taskset.html";
        String param = "task_type=1&app=1&pc=2&maxmoney=&hasCaptcha=0&captcha_code=";

        OrderTask orderParam = new OrderTask();
        orderParam.setMobile(mobile);
        orderParam.setToday(DateUtils.today());
        orderParam.setStatus(AccountStatus.接单中.getCode());
        List<OrderTask> orderTaskList = orderTaskMapper.listUserOrderByStatus(orderParam);
        OrderTask orderTask;
        if(orderTaskList.isEmpty()) {
            orderTask = new OrderTask(mobile);
            orderTaskMapper.insert(orderTask);
        } else {
            orderTask = orderTaskList.get(0);
        }
        ThreadPoolScheduler.addTask(new ReceiptThread(orderTask, sessionId));
    }

    @Override
    public String getSessionId() {
        String url = "http://s.58leke.com/index.php";
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        requestHeader.put("accept-encoding", "deflate");
        requestHeader.put("accept-language", "zh-CN,zh;q=0.9");
        requestHeader.put("connection", "keep-alive");
        requestHeader.put("cookie", "_ga=GA1.2.1099391170.1539658537; _gid=GA1.2.1985067262.1539658537");
        requestHeader.put("host", "s.58leke.com");
        Map<String, List<String>> responseHeader = new HashMap<>();
        HttpUtils.doGet(url, requestHeader, responseHeader);
        String sessionId = "";
        for(String key : responseHeader.keySet()) {
            if("Set-Cookie".equals(key)) {
                List<String> itemList = responseHeader.get(key);
                if (CollectionUtils.isNotEmpty(itemList)) {
                    String line = itemList.get(0);
                    String[] arr = line.split(";");
                    sessionId = arr[0].split("=")[1];
                }
            }
        }
        return sessionId;
    }
}
