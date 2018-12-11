package com.framework.model;

import org.meteoinfo.shape.Polygon;

import java.awt.*;

public class PolygonModel {
    Polygon polygon;
    Color color;

    public PolygonModel(Polygon polygon, Color color) {
        this.polygon = polygon;
        this.color = color;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
