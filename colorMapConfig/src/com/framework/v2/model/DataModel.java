package com.framework.v2.model;

/**
 * 数据模型
 */
public class DataModel {
    double lon;
    double lat;
    double value;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DataModel(double lon, double lat, double value) {
        this.lon = lon;
        this.lat = lat;
        this.value = value;
    }
}
