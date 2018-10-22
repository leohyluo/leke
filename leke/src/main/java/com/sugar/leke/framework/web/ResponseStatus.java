package com.sugar.leke.framework.web;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leohyluo 服务器响应请求状态码。
 */
public enum ResponseStatus {
    /**
     * 成功，服务器处理完成 20000, "success"
     */
//	SUCCESS(200, "操作成功"),
    SUCCESS(1000, "操作成功"),
    FAIL(1001,"操作失败"),
    NULLDATA(1002,"无数据"),
    /**
     * 缺少必要参数 30001, "Required parameter missing"
     */
    REQUIRED_PARAMETER_MISSING(201, "缺少必要参数或参数为空"),
    /**
     * 用户不存在 30002, "User not found"
     */
    USER_NOT_FOUND(202, "用户不存在"),
    /**
     * 无效的签名
     */
    INVALID_SIGN(203, "签名验证不通过"),
    /**
     * 用户密码不匹配 30003, "Incorrect password"
     */
    USER_PASSWORD_INCORRECT(204, "密码错误"),

    ORDER_UNFINISH(205, "客官，请先完成已接到的订单"),

    ORDER_CANCLED(206, "客官，订单1小时未完成,订单已失效"),

    ORDER_CAN_BE_START(206, "客官，请让本喵来帮你接单吧"),

    USER_UNLOGIN(207, "未登录"),
    USER_LOGINED(208, "已登录"),
    ORDER_RECEIPTING(209, "接单中"),
    ORDER_RECEIPT_SUCCESS(230, "接单成功"),
    ORDER_FULL(231, "今天接手机会为3，已上限"),
    OREDER_RECEIPT_FAIL(232, "接单失败"),
    ORDER_EXPIRED(233, "订单已失效"),
    USER_STOP_TASK(234, "任务已停止");

    private final int code;
    private final String message;

    private ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static ResponseStatus findByCode(int code) {
        for (ResponseStatus rs : values()) {
            if (rs.code == code) {
                return rs;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + code + " code!");
    }

    public static Map<Integer, String> all2Map() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (ResponseStatus t : ResponseStatus.values()) {
            map.put(t.code, t.message);
        }
        return map;
    }
}
