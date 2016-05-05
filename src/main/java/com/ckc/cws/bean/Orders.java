package com.ckc.cws.bean;

import java.util.Date;

public class Orders {
    private String oId;

    private Date startTime;

    private Date endTime;

    private Integer totalDayTime;

    private Integer totalNightTime;

    private Double totalCost;

    private Float discount;

    private Byte stat;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId == null ? null : oId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalDayTime() {
        return totalDayTime;
    }

    public void setTotalDayTime(Integer totalDayTime) {
        this.totalDayTime = totalDayTime;
    }

    public Integer getTotalNightTime() {
        return totalNightTime;
    }

    public void setTotalNightTime(Integer totalNightTime) {
        this.totalNightTime = totalNightTime;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Byte getStat() {
        return stat;
    }

    public void setStat(Byte stat) {
        this.stat = stat;
    }
}