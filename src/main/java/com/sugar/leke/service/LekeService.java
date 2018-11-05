package com.sugar.leke.service;

import com.sugar.leke.framework.web.ResponseStatus;

public interface LekeService {

    String login(String userName, String password, String sessionId);

    void receipt(String userName, String sessionId);

    String getSessionId();
}
