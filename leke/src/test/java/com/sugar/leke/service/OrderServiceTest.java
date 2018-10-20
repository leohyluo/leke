package com.sugar.leke.service;

import com.sugar.leke.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderServiceTest {

    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        /*map.put("15818518021", "zxcvbnm123");
        map.put("13691645822", "zxcvbnm123");
        map.put("18200898720", "871111maomao");
        map.put("15018079780", "120408");*/
        map.put("13360537242", "zxcvbnm123");

        map.forEach((userName, password) -> {
            OrderService orderService = new OrderServiceImpl();
            String sessionId = orderService.getSessionId();
            System.out.println("sessionId=" + sessionId);

            orderService.login(userName, password, sessionId);

            orderService.receipt(userName, sessionId);
        });
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        System.out.println("系统停止");
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("15818518021", "zxcvbnm123");
//        map.put("13691645822", "zxcvbnm123");
//        map.put("18200898720", "871111maomao");
//        map.put("15018079780", "120408");
//        map.put("13360537242", "zxcvbnm123");

        map.forEach((userName, password) -> {
            OrderService orderService = new OrderServiceImpl();
            String sessionId = orderService.getSessionId();
            System.out.println("sessionId=" + sessionId);

            orderService.login(userName, password, sessionId);

            orderService.receipt(userName, sessionId);
        });
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        System.out.println("系统停止");
    }
}
