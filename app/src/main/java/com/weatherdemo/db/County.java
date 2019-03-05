package com.weatherdemo.db;

import org.litepal.crud.LitePalSupport;

/**
 * 县信息类
 * @author yejinmo
 */
public class County extends LitePalSupport {
    private int id;
    /** 县的名称 */
    private String countyName;
    /** 县所对应天气的id */
    private String weatherId;
    /** 所属城市的代号 */
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
