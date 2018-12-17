package com.framework.v1.model;

import org.meteoinfo.shape.PolygonShape;

public class PolygonSty {
    PolygonShape polygonShape;
    int lengendIndex;

    public PolygonSty(PolygonShape polygonShape, int lengendIndex) {
        this.polygonShape = polygonShape;
        this.lengendIndex = lengendIndex;
    }

    public PolygonShape getPolygonShape() {
        return polygonShape;
    }

    public void setPolygonShape(PolygonShape polygonShape) {
        this.polygonShape = polygonShape;
    }

    public int getLengendIndex() {
        return lengendIndex;
    }

    public void setLengendIndex(int lengendIndex) {
        this.lengendIndex = lengendIndex;
    }
}
