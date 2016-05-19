package com.ckc.cws.bean;

public class Parks {
    private Integer pId;

    private String pName;

    private Double lng;

    private Double lat;

    private String pic;

    private String city;

    private String area;

    private Float dayPrice;

    private Float nightPrice;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Float getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Float dayPrice) {
        this.dayPrice = dayPrice;
    }

    public Float getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(Float nightPrice) {
        this.nightPrice = nightPrice;
    }
}