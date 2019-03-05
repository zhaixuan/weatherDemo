package com.weatherdemo.db;

import org.litepal.crud.LitePalSupport;

/**
 * 城市信息类
 * @author yejinmo
 */
public class City extends LitePalSupport {
    private int id;
    /** 城市名称 */
    private String cityName;
    /** 城市代号 */
    private int cityCode;
    /** 当前城市的省份id */
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
