package com.framework.util;

import org.meteoinfo.data.mapdata.MapDataManage;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.PolygonShape;
import org.meteoinfo.shape.Shape;

import java.util.List;

public class LayerReaderUtil {
    private static VectorLayer getClipLayer(String layerEnums) {
        try {
            return MapDataManage.readMapFile_ShapeFile(layerEnums);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<PolygonShape> readOutPolygons(String layerFilePath){
        VectorLayer v = getClipLayer(layerFilePath);
        List<PolygonShape> polygonShapeList = (List<PolygonShape>)v.getShapes();
        return polygonShapeList;
    }
    public static Extent readOutExtent(String layerFilePath){
        VectorLayer v = getClipLayer(layerFilePath);
        return v.getExtent();
    }
    public static VectorLayer readOutLayer(String layerFilePath){
        return getClipLayer(layerFilePath);
    }
}
