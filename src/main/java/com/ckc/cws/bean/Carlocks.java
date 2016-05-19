package com.ckc.cws.bean;

public class Carlocks {
    private Integer sId;

    private Integer uId;

    private Integer pId;

    private Boolean isRent;

    private Boolean useStat;

    private Byte faultStat;

    private String blueId;

    private String bulePwd;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Boolean getIsRent() {
        return isRent;
    }

    public void setIsRent(Boolean isRent) {
        this.isRent = isRent;
    }

    public Boolean getUseStat() {
        return useStat;
    }

    public void setUseStat(Boolean useStat) {
        this.useStat = useStat;
    }

    public Byte getFaultStat() {
        return faultStat;
    }

    public void setFaultStat(Byte faultStat) {
        this.faultStat = faultStat;
    }

    public String getBlueId() {
        return blueId;
    }

    public void setBlueId(String blueId) {
        this.blueId = blueId == null ? null : blueId.trim();
    }

    public String getBulePwd() {
        return bulePwd;
    }

    public void setBulePwd(String bulePwd) {
        this.bulePwd = bulePwd == null ? null : bulePwd.trim();
    }
}