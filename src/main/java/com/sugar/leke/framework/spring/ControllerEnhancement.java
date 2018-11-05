package com.sugar.leke.framework.spring;

import com.sugar.leke.framework.exception.ServiceException;
import com.sugar.leke.framework.web.ResponseMessage;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.framework.web.WebUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerEnhancement {

    @ExceptionHandler({Exception.class})
    public ResponseMessage processException(HttpServletRequest request, Exception ex) {
        return WebUtils.buildResponseMessage(ResponseStatus.EXCEPTION);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseMessage processServiceException(HttpServletRequest request, ServiceException ex) {
        return WebUtils.buildResponseMessage(ex.getResultEnum());
    }
}
