package com.sugar.leke.pojo;

import java.util.List;

public class ReceiptInfo {

    private String mobile;
    private Integer totalCount;
    private Integer successOrderCount;
    private Integer errorCount;
    private List<String> errList;

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

    public Integer getSuccessOrderCount() {
        return successOrderCount;
    }

    public void setSuccessOrderCount(Integer successOrderCount) {
        this.successOrderCount = successOrderCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public List<String> getErrList() {
        return errList;
    }

    public void setErrList(List<String> errList) {
        this.errList = errList;
    }
}
