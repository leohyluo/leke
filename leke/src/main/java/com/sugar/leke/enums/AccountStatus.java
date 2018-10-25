package com.sugar.leke.enums;

public enum AccountStatus {

    登录失败(-1, "登录失败"),
    已登录(1, "已登录"),
    接单中(2, "接单中"),
    接单成功(3, "接单成功"),
    待完成(4, "您还有进行中的任务没完成，请先完成任务"),
    已上限(5, "今天接手机会为3，已上限"),
    接单失败(6, "接单失败"),
    订单已失效(7, "订单已失效"),
    已暂停(8, "已暂停");

    private Integer code;
    private String text;

    private AccountStatus(Integer code, String text){
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
