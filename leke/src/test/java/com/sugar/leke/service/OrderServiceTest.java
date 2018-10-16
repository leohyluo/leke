package com.sugar.leke.service;

import com.sugar.leke.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.Scanner;

public class OrderServiceTest {

    @Test
    public void test1() {
        String userName = "15818518021";
        String password = "zxcvbnm123";

        OrderService orderService = new OrderServiceImpl();
        String sessionId = orderService.getSessionId();
        System.out.println("sessionId=" + sessionId);

        orderService.login(userName, password, sessionId);

        orderService.receipt(userName, sessionId);
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        System.out.println("系统停止");
    }
}
