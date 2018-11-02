package com.sugar.leke.pojo.vo;

import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.util.BeanCopierUtil;

import java.util.HashMap;
import java.util.Map;

public class AccountStatusVo {

    private String mobile;
    private ResponseStatus responseStatus;
    private String image;
    private String btnId;
    private String btnText;
    private String comment;

    private static Map<ResponseStatus, AccountStatusVo> statusVoMap;

    static {
        statusVoMap = new HashMap<>();
        initStatusVo(ResponseStatus.USER_STOP_TASK, "stop.jpg", "btn_restart", "开始接单", "客官，请让我帮你快快接单吧...");
        initStatusVo(ResponseStatus.ORDER_CAN_BE_START, "timg.jpg", "btn_start", "开始接单", "客官，请让我帮你快快接单吧...");
        initStatusVo(ResponseStatus.ORDER_RECEIPTING, "working.jpg", "btn_working", "接单中", "正在努力接单中...");
        initStatusVo(ResponseStatus.ORDER_RECEIPT_SUCCESS, "happy2.jpg", "btn_working", "已接到单", "客官，接到单了; 请前去完成吧。");
    }

    public AccountStatusVo() {}

    public AccountStatusVo(String mobile, ResponseStatus responseStatus) {
        AccountStatusVo vo = statusVoMap.get(responseStatus);
        BeanCopierUtil.copy(vo, this);
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private static void initStatusVo(ResponseStatus responseStatus, String image, String btnId, String btnText, String comment) {
        AccountStatusVo vo = new AccountStatusVo();
        vo.setImage(image);
        vo.setBtnId(btnId);
        vo.setBtnText(btnText);
        vo.setResponseStatus(responseStatus);
        vo.setComment(comment);
        statusVoMap.put(responseStatus, vo);
    }
}
