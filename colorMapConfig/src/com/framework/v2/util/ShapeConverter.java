package com.framework.v2.util;

import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.shape.PolygonShape;

import java.util.ArrayList;
import java.util.List;


public class ShapeConverter {
    public static PolygonShape maker(List<Double[]> list){
        PolygonShape pShape = new PolygonShape();
        List<PointD> clip = new ArrayList<>();
        for (Double[] f:list) {
            clip.add(new PointD(f[0], f[1]));
        }
        pShape.setPoints(clip);
        return pShape;
    }
    public static List<Double[]> staticInit(int number,Extent extent){
        List<Double[]> list = new ArrayList<>();
        Double[] start = new Double[]{(extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY};
        list.add(start);
        for (int i = 0; i < number; i++) {
            list.add(new Double[]{(extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY});
        }
        list.add(start);
        return list;
    }
}
