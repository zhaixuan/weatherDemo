package com.weatherdemo.db;

import org.litepal.crud.LitePalSupport;

/**
 * 县信息类
 */
public class County extends LitePalSupport {
    private int id;
    /** 县的名称 */
    private String countName;
    /** 县所对应天气的id */
    private int weatherId;
    /** 所属城市的代号 */
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountName() {
        return countName;
    }

    public void setCountName(String countName) {
        this.countName = countName;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
