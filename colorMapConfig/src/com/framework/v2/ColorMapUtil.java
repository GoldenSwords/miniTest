package com.framework.v2;

import com.alibaba.fastjson.JSONObject;
import com.framework.v2.model.MapTextModel;
import com.framework.v2.model.PolygonModel;
import com.framework.v2.model.PolylineModel;
import com.framework.v2.util.CommonUtil;
import com.framework.v2.util.GridDataConfig;
import com.framework.v2.util.PolygonConfig;
import org.meteoinfo.data.DataTypes;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.mapdata.AttributeTable;
import org.meteoinfo.data.mapdata.Field;
import org.meteoinfo.data.meteodata.DrawMeteoData;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.geoprocess.analysis.InterpolationMethods;
import org.meteoinfo.geoprocess.analysis.InterpolationSetting;
import org.meteoinfo.global.PointD;
import org.meteoinfo.layer.MapLayer;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.layout.LayoutLegend;
import org.meteoinfo.layout.LegendStyles;
import org.meteoinfo.layout.MapLayout;
import org.meteoinfo.legend.LegendManage;
import org.meteoinfo.legend.LegendScheme;
import org.meteoinfo.map.MapView;
import org.meteoinfo.shape.PointShape;
import org.meteoinfo.shape.PolygonShape;
import org.meteoinfo.shape.PolylineShape;
import org.meteoinfo.shape.ShapeTypes;
import org.meteoinfo.table.DataColumn;
import org.meteoinfo.table.DataColumnCollection;
import org.meteoinfo.table.DataRow;
import org.meteoinfo.table.DataTable;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 色斑图生成工具类 v1
 */
public class ColorMapUtil {
    /**
     * 格点数据生成
     * @param config
     * @throws Exception
     */
    public synchronized static JSONObject colorMap(GridDataConfig config) throws Exception{
        JSONObject result = new JSONObject();
        File file = new File(config.getFilePath());
        if (!file.exists()){
            file.mkdirs();
        }
        MapLayout layout = new MapLayout(); // 设置图片面板
        layout.setPageBounds(new Rectangle((int)(config.getWidth()*1), (int)(config.getHeight()*1))); // 设置图片页面尺寸
        MapView mapView = layout.getActiveMapFrame().getMapView(); // 获取图片显示页面
        //全屏设置
        if(config.isFullScreen()){
            mapView = new MapView();
            mapView.zoomToExtent(config.getExtent());
            mapView.setBounds(new Rectangle(config.getWidth(), config.getHeight()));
        }else{
            layout.getMapFrames().get(0).setLayoutBounds(new Rectangle((int)(config.getWidth()*0.1), (int)(config.getHeight()*0.1),(int)(config.getWidth()*0.8), (int)(config.getHeight()*0.8)));
        }
        //网格参数设置
        GridDataSetting setting = new GridDataSetting();
        setting.dataExtent = config.getExtent();//网格范围
        setting.xNum = config.getX();//网格横向数量
        setting.yNum = config.getY();//网格纵向数量
        InterpolationSetting interSet = new InterpolationSetting();//转换参数设置
        interSet.setGridDataSetting(setting);
        interSet.setInterpolationMethod(InterpolationMethods.IDW_Neighbors);//临近点插值法
        interSet.setMinPointNum(config.getN());//设置临近点计算数量
        GridData gridData = config.getStationData().interpolateData(interSet);//插值流程
        System.out.println(String.format("max::%f,min::%f  %s",gridData.getMaxValue(),gridData.getMinValue(),LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        VectorLayer vectorLayer = getLegendScheme(config,gridData);//数据绘制为图层
        System.out.println(String.format("makeLayer::%s",LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        //设置遮罩层
        if(config.getMaskLayer()!=null){
            MapLayer layer = config.getMaskLayer();
            layer.setMaskout(true);
            mapView.addLayer(layer);
        }
        mapView.addLayer(vectorLayer);
        //设置边界线
        if(config.getBorderLine()!=null){
            VectorLayer borderLayer = new VectorLayer(ShapeTypes.Polyline);
            PolylineShape shape = new PolylineShape();
            shape.setPolylines(config.getBorderLine());
            borderLayer.addShape(shape);
            LegendScheme legendScheme = LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polyline,Color.BLACK,config.getBorderWidth());
            borderLayer.setLegendScheme(legendScheme);
            mapView.addLayer(borderLayer);
        }
        //开启遮罩
        if(config.getMaskLayer()!=null){
            vectorLayer.setMaskout(true);
            mapView.getMaskOut().setMask(true);
            mapView.getMaskOut().setMaskLayer(config.getMaskLayer().getLayerName());
        }
        //色标设置
        LayoutLegend layoutLegend = layout.addLegend((int)(config.getWidth()*0.8),(int)(config.getHeight()*0.5));
        layoutLegend.setLegendLayer(vectorLayer);
        layoutLegend.setLegendStyle(LegendStyles.Normal);
        layoutLegend.setTitle("titles");
        layout.getMapFrames().get(0).setGridXDelt(0.1);
        layout.getMapFrames().get(0).setGridYDelt(0.1);
        //输出图片
        if(config.isFullScreen()){
            mapView.exportToPicture(config.getFilePath());
        }else{
            layout.exportToPicture(config.getFilePath());
        }
        //文件删除操作
        if(config.isDelete()){
            result.put("pngBase64", CommonUtil.imgToBase64(config.getFilePath()));
            new File(config.getFilePath()).delete();
        }else {
            result.put("path",config.getFilePath());
        }
        return result;
    }

    /**
     * 自定义数据生成
     * @param config
     * @throws Exception synchronized
     */
    public synchronized static JSONObject colorMap(PolygonConfig config) throws Exception{
        System.out.println(String.format("taskStart::%s  %s","CUSTOM", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        JSONObject result = new JSONObject();
        File file = new File(config.getFilePath());
        if (!file.exists()){
            file.mkdirs();
        }
        MapLayout layout = new MapLayout(); // 设置图片面板
        layout.setPageBounds(new Rectangle((int)(config.getWidth()*1), (int)(config.getHeight()*1))); // 设置图片页面尺寸
        MapView mapView = layout.getActiveMapFrame().getMapView(); // 获取图片显示页面
        //全屏设置
        if(config.isFullScreen()){
            mapView = new MapView();
            mapView.zoomToExtent(config.getExtent());
            mapView.setBounds(new Rectangle(config.getWidth(), config.getHeight()));
        }else{
            layout.getMapFrames().get(0).setLayoutBounds(new Rectangle((int)(config.getWidth()*0.1), (int)(config.getHeight()*0.1),(int)(config.getWidth()*0.8), (int)(config.getHeight()*0.8)));
        }
        //设置显示区域
        VectorLayer layer = new VectorLayer(ShapeTypes.Polygon);
        layer.setExtent(config.getExtent());
        mapView.addLayer(layer);
        //添加自定义区块
        if(config.getPolygons()!=null){
            List<PolygonModel> list = config.getPolygons();
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                VectorLayer polygonModelLayer = new VectorLayer(ShapeTypes.Polygon);
                PolygonShape polygonShape = new PolygonShape();
                List<org.meteoinfo.shape.Polygon> polygons = new ArrayList<>();
                Color color = list.get(i).getColor();
                for (int j = 0; j < list.size(); j++) {
                    if(list.get(j).getColor().equals(color)){
                        polygons.add(list.get(j).getPolygon());
                        list.remove(j);
                        j--;
                        flag = true;
                    }
                }
                if(flag){
                    i--;
                    flag = false;
                }
                LegendScheme legendSchemes = LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polygon,color,1);//设置区块颜色及边界线宽度
                polygonShape.setPolygons(polygons);
                polygonModelLayer.addShape(polygonShape);
                polygonModelLayer.setLegendScheme(legendSchemes);
                mapView.addLayer(polygonModelLayer);
            }
        }
        //添加自定义线条
        if(config.getPolylines()!=null){
            for (PolylineModel polylineModel:config.getPolylines()) {
                VectorLayer polylineModelLayer = new VectorLayer(ShapeTypes.Polyline);
                PolylineShape polylineShape = new PolylineShape();
                polylineShape.setPolylines(polylineModel.getPolyline());
                polylineModelLayer.addShape(polylineShape);
                polylineModelLayer.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polyline,polylineModel.getColor(),1));
                mapView.addLayer(polylineModelLayer);
            }
        }
        //添加站点文本
        if(config.getTexts()!=null){
            VectorLayer pointLayer = new VectorLayer(ShapeTypes.Point);
            List<MapTextModel> tests = config.getTexts();
            String showColumn  = "SHOWDATA";
            AttributeTable attributeTable = new AttributeTable();
            DataTable dataTable = new DataTable();
            dataTable.addColumn(showColumn,DataTypes.String);
            for (int i = 0; i < tests.size(); i++) {
                PointShape pointShape = new PointShape();
                pointShape.setPoint(new PointD(tests.get(i).getLon(),tests.get(i).getLat()));
                pointLayer.addShape(pointShape);
                Field field = new Field(showColumn,DataTypes.String);
                DataColumnCollection dataColumnCollection = new DataColumnCollection();
                dataColumnCollection.add(new DataColumn(showColumn,DataTypes.String));
                DataRow dataRow = new DataRow();
                dataRow.setColumns(dataColumnCollection);
                if(tests.get(i).getText().containsKey(tests.get(i).getShowColumn())){
                    dataRow.setValue(showColumn,tests.get(i).getText().get(tests.get(i).getShowColumn()));
                }
                dataTable.addColumn(field);
                dataTable.addRow(dataRow);
            }
            attributeTable.setTable(dataTable);

            pointLayer.setAttributeTable(attributeTable);
            for (int i = 0; i < tests.size(); i++) {
                pointLayer.editCellValue(showColumn, i,tests.get(i).getText().get(tests.get(i).getShowColumn()));
            }
            pointLayer.getLabelSet().setFieldName(showColumn);
            pointLayer.getLabelSet().setYOffset(config.getOffsetY());
            pointLayer.getLabelSet().setXOffset(config.getOffsetX());
            pointLayer.getLabelSet().setLabelColor(Color.black);//文本颜色
            pointLayer.addLabels();
            mapView.addLayer(pointLayer);
        }
        //全屏显示
        if(config.isFullScreen()){
            mapView.exportToPicture(config.getFilePath());
        }else {
            layout.getMapFrames().get(0).setGridXDelt(0.1);
            layout.getMapFrames().get(0).setGridYDelt(0.1);
            layout.exportToPicture(config.getFilePath());
        }
        //文件删除操作
        if(config.isDelete()){
            result.put("pngBase64", CommonUtil.imgToBase64(config.getFilePath()));
            new File(config.getFilePath()).delete();
        }else {
            result.put("path",config.getFilePath());
        }
        System.out.println(String.format("taskEnd::%s  %s","CUSTOM", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        return result;
    }

    /**
     * 图层制作
     * @param config
     * @param gridData
     * @return
     */
    private static VectorLayer getLegendScheme(GridDataConfig config, GridData gridData) {
        Double[] valuesB =config.getValue().stream().sorted(Comparator.naturalOrder()).toArray(Double[]::new);
        Color[] colors = config.getColor().stream().toArray(Color[]::new);
        double[] values = new double[valuesB.length];
        for (int i = 0; i < valuesB.length; i++) {
            values[i] = valuesB[i].doubleValue();
        }
        //创建色标
        LegendScheme als =  LegendManage.createGraduatedLegendScheme(values, colors, ShapeTypes.Polygon, gridData.getMinValue(), gridData.getMaxValue());
        //根据色标和数据生成色斑图层
        return DrawMeteoData.createShadedLayer(gridData, als, "GridData", "Data", true);
    }
}
