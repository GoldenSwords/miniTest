package com.framework.v2.model;

import java.util.Map;

public class MapTextModel {
    double lon;
    double lat;
    Map<String,Object> text;
    String showColumn;

    public MapTextModel(double lon, double lat, Map<String, Object> text, String showColumn) {
        this.lon = lon;
        this.lat = lat;
        this.text = text;
        this.showColumn = showColumn;
    }

    public String getShowColumn() {
        return showColumn;
    }

    public void setShowColumn(String showColumn) {
        this.showColumn = showColumn;
    }

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

    public Map<String,Object> getText() {
        return text;
    }

    public void setText(Map<String,Object> text) {
        this.text = text;
    }
}
