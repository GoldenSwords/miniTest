package com.framework.v3.util;

import org.meteoinfo.data.mapdata.AttributeTable;
import org.meteoinfo.data.mapdata.MapDataManage;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.PolygonShape;
import org.meteoinfo.shape.Shape;
import org.meteoinfo.shape.ShapeTypes;
import org.meteoinfo.table.DataColumnCollection;
import org.meteoinfo.table.DataRow;
import org.meteoinfo.table.DataRowCollection;
import org.meteoinfo.table.DataTable;

import java.util.Iterator;
import java.util.List;

/**
 * 图层读取工具类
 */
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
    public static Extent readOutExtent(String layerFilePath,String ElString, String ELkey, String IDkey){
        Extent extent = new Extent();
        VectorLayer vectorLayer = getClipLayer(layerFilePath);
        AttributeTable attributeTable = vectorLayer.getAttributeTable();
        DataTable dataTable = attributeTable.getTable();
        DataRowCollection dataRows = dataTable.getRows();
        Iterator<DataRow> iterable = dataRows.iterator();
        boolean has = false;
        boolean check = false;
        String ID = "";
        while (iterable.hasNext()){
            DataRow dataRow = iterable.next();
            DataColumnCollection dataColumns = dataRow.getColumns();
            for (int i = 0; i < dataColumns.size(); i++) {
                if(dataColumns.get(i).getColumnName().equals(ELkey)){
                    has = true;
                    break;
                }
            }
            if(has){
                for (String key:dataRow.getItemMap().keySet()) {
                    if(ELkey.toUpperCase().equals(key.toUpperCase()) && dataRow.getItemMap().get(key).toString().equals(ElString)){
                        check = true;
                    }
                    if(IDkey.toUpperCase().equals(key.toUpperCase())){
                        ID = dataRow.getItemMap().get(key).toString();
                    }
                }
                if(check){
                    break;
                }
            }
            has = false;
        }
        if(check && !"".equals(ID)){
            PolygonShape polygonShape = (PolygonShape) vectorLayer.getShapes().get(Integer.parseInt(ID));
            extent = polygonShape.getExtent();
            System.out.println("");
        }
        return extent;
    }
    public static VectorLayer readOutLayer(String layerFilePath){
        return getClipLayer(layerFilePath);
    }
    public static VectorLayer readOutLayer(String layerFilePath,String ElString, String ELkey,String IDkey){
        VectorLayer resultLayer = new VectorLayer(ShapeTypes.Polygon);
        VectorLayer vectorLayer = getClipLayer(layerFilePath);
        AttributeTable attributeTable = vectorLayer.getAttributeTable();
        DataTable dataTable = attributeTable.getTable();
        DataRowCollection dataRows = dataTable.getRows();
        Iterator<DataRow> iterable = dataRows.iterator();
        boolean has = false;
        boolean check = false;
        String ID = "";
        while (iterable.hasNext()){
            DataRow dataRow = iterable.next();
            DataColumnCollection dataColumns = dataRow.getColumns();
            for (int i = 0; i < dataColumns.size(); i++) {
                if(dataColumns.get(i).getColumnName().toUpperCase().equals(ELkey.toUpperCase())){
                    has = true;
                    break;
                }
            }
            if(has){
                for (String key:dataRow.getItemMap().keySet()) {
                    if(ELkey.toUpperCase().equals(key.toUpperCase()) && dataRow.getItemMap().get(key).toString().equals(ElString)){
                        check = true;
                    }
                    if(IDkey.toUpperCase().equals(key.toUpperCase())){
                        ID = dataRow.getItemMap().get(key).toString();
                    }
                }
                if(check){
                    break;
                }
            }
            has = false;
        }
        if(check && !"".equals(ID)){
            PolygonShape polygonShape = (PolygonShape) vectorLayer.getShapes().get(Integer.parseInt(ID));
            resultLayer.setExtent(polygonShape.getExtent());
            resultLayer.addShape(polygonShape);
        }
        return resultLayer;
    }
}
