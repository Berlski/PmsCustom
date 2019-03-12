package com.berlski.tool.custom.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * 城市区域商圈
 * Created by MSI on 2017/9/6.
 */
@Entity(//把 id 和 parentId 放在一起作为一个综合主键
        nameInDb = "LOCATION_ID",
        indexes = {
                @Index(value = "id DESC, parentId DESC", unique = true)
        })
public class Location {

    private String id;
    private String parentId;
    private String name;
    private String lng;
    private String lat;
    private String cityId;
    private String cityName;

    @Generated(hash = 1799133131)
    public Location(String id, String parentId, String name, String lng, String lat,
                    String cityId, String cityName) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    @Generated(hash = 375979639)
    public Location() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
