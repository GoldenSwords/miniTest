package com.framework.v3.model;


import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.util.List;

public class PolylineModel {
    List<Polyline> polyline;
    Color color;

    public PolylineModel(List<Polyline> polyline, Color color) {
        this.polyline = polyline;
        this.color = color;
    }

    public List<Polyline> getPolyline() {
        return polyline;
    }

    public void setPolyline(List<Polyline> polyline) {
        this.polyline = polyline;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
