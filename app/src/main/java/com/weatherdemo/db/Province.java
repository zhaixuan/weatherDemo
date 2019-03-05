package com.weatherdemo.db;

import org.litepal.crud.LitePalSupport;

/**
 * 省份信息类
 * @author yejinmo
 */
public class Province extends LitePalSupport {
    private int id;
    /** 省份名称 */
    private String provinceName;
    /** 省份代号 */
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
