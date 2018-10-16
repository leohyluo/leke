package com.sugar.leke.service;

public interface OrderService {

    void login(String userName, String password, String sessionId);

    void receipt(String userName, String sessionId);

    String getSessionId();
}
