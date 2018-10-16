package com.sugar.leke.pojo;

import java.util.List;

public class ReceiptInfo {

    private String mobile;
    private Integer totalCount;
    private Integer successCount;
    private Integer errorCount;
    private String errorMsg;
    private Integer status;

    public ReceiptInfo() {}

    public ReceiptInfo(String mobile) {
        this.mobile = mobile;
        this.totalCount = 0;
        this.successCount = 0;
        this.errorCount = 0;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
