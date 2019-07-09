package com.team.house.util;

public class HouseParam extends PageUtil {
    private String title;
    private Integer min_price;
    private Integer max_price;
    private Integer did;
    private Integer streetId;
    private Integer typeId;


    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMin_price() {
        return min_price;
    }

    public void setMin_price(Integer min_price) {
        this.min_price = min_price;
    }

    public Integer getMax_price() {
        return max_price;
    }

    public void setMax_price(Integer max_price) {
        this.max_price = max_price;
    }
}
