package com.framework.util;

import com.framework.model.PolygonModel;
import com.framework.model.PolylineModel;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.shape.Polygon;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {
    /**
     * 实例化Map
     * @param obj
     * @return
     */
    public static Map<String,Object> initMap(Object[]... obj){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < obj.length; i++) {
            map.put(obj[i][0].toString(),obj[i][1]);
        }
        return map;
    }

    /**
     * 实例化线条模型
     * @param extent
     * @param color
     * @return
     */
    public static PolylineModel polyline(Extent extent, Color color,int number){
        java.util.List<Polyline> list1 = new ArrayList<>();
        color = color == null ? Color.black : color;
        for (int i = 0; i < number; i++) {
            Polyline polyline = new Polyline();
            List<PointD> list = new ArrayList<>();
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
            polyline.setPointList(list);
            list1.add(polyline);
        }
        return new PolylineModel(list1,color);
    }

    /**
     * 实例化区块模型
     * @param extent
     * @param color
     * @param number
     * @return
     */
    public static PolygonModel polygon(Extent extent, Color color, int number){
        color = color == null ? Color.black : color;
        org.meteoinfo.shape.Polygon polygon = new Polygon();
        List<PointD> list = new ArrayList<>();
        PointD pointD = new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY);
        list.add(pointD);
        for (int i = 0; i < number-2; i++) {
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        }
        list.add(pointD);
        polygon.setOutLine(list);
        return new PolygonModel(polygon,color);
    }
}
