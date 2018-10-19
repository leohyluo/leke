package com.sugar.leke.pojo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "order_task")
public class OrderTask {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "error_count")
    private Integer errorCount;

    @Column(name = "error_msg")
    private String errorMsg;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "status")
    private Integer status;

    @Transient
    private String today;

    public OrderTask() {}

    public OrderTask(String mobile) {
        this.mobile = mobile;
        this.totalCount = 0;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }
}
