package com.framework.v2;

import com.framework.v2.model.DataModel;
import com.framework.v2.model.MapTextModel;
import com.framework.v2.model.PolygonModel;
import com.framework.v2.model.PolylineModel;
import com.framework.v2.thread.ColorMapTask;
import com.framework.v2.util.*;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.geoprocess.analysis.InterpolationMethods;
import org.meteoinfo.geoprocess.analysis.InterpolationSetting;
import org.meteoinfo.global.DataConvert;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 色斑图生成 V2
 * 格点数据生成图片
 * 自定义区块/线条/站点图片绘制
 * 可定义边界宽度 可设置保存开关
 */
public class DoMain {

    public static int x = 500; //横向格点数量
    public static int y = 500; //纵向格点数据
    public static int n = 10;   //需计算的临近点数量
    public static int w = 1000; // 图片宽
    public static int h = 800; // 图片高
    public static Extent extent = LayerReaderUtil.readOutExtent("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");//自定义展示区域

    public static void main(String[] args) {

        List<Map<String,Object>> dataList = new ArrayList<>();//数据
        org.meteoinfo.data.StationData stationData = com.framework.v2.util.DataTranslater.getData(dataList,"lon","lat","value");

        GridDataSetting setting = new GridDataSetting();
        setting.dataExtent = extent;//网格范围
        setting.xNum = x;//网格横向数量
        setting.yNum = y;//网格纵向数量
        InterpolationSetting interSet = new InterpolationSetting();//转换参数设置
        interSet.setGridDataSetting(setting);
        interSet.setInterpolationMethod(InterpolationMethods.IDW_Neighbors);//临近点插值法
        interSet.setMinPointNum(n);//设置临近点计算数量
        GridData gridData = stationData.interpolateData(interSet);//格点插值流程


        //配置项
        List<Color> colorList = Arrays.asList(Color.decode("#11003E"),Color.decode("#1B0060"),Color.decode("#240082"),Color.decode("#2C009F"),Color.decode("#3500BF"),Color.decode("#6002BD"),Color.decode("#6F00E8"),Color.decode("#8A02AC"),Color.decode("#E1025B"),Color.decode("#DE0515"));//自定义色标
        List<Double> valueList = Arrays.asList(10d,20d,30d,40d,50d,60d,70d,80d,90d);//自定义色阶
        VectorLayer vectorLayer = LayerReaderUtil.readOutLayer("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");
        List<Polyline> list = new ArrayList<>();
        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
            Polyline polyline = new Polyline();
            polyline.setPointList(vectorLayer.getShapes().get(i).getPoints());
            list.add(polyline);
        }
        List<DataModel> dataModels = DataConverter.staticDataForModel(100,extent);
        GridDataConfig gridDataConfig = new GridDataConfig(DataTranslater.getData(dataModels),x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,2,true,false);

        List<Color> colorList2 = Arrays.asList(Color.decode("#11003E"),Color.decode("#1B0060"),Color.decode("#240082"),Color.decode("#2C009F"),Color.decode("#3500BF"),Color.decode("#6002BD"),Color.decode("#6F00E8"),Color.decode("#8A02AC"),Color.decode("#E1025B"),Color.decode("#DE0515"));//自定义色标
        List<Double> valueList2 = Arrays.asList(10d,20d,30d,40d,50d,60d,70d,80d,90d);//自定义色阶
        VectorLayer vectorLayer2 = LayerReaderUtil.readOutLayer("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");
        List<Polyline> list2 = new ArrayList<>();
        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
            Polyline polyline = new Polyline();
            polyline.setPointList(vectorLayer2.getShapes().get(i).getPoints());
            list2.add(polyline);
        }
        List<DataModel> dataModels2 = DataConverter.staticDataForModel(100,extent);
        GridDataConfig gridDataConfig1 = new GridDataConfig(DataTranslater.getData(dataModels2),x,y,n,w,h,extent,"e:/tmp/非全屏过滤_格点数据.png",colorList2,valueList2,vectorLayer2,list2,2,false,false);

        //配置项
        List<PolylineModel> polylinesList = new ArrayList<>();
        polylinesList.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
        List<PolygonModel> polygonsList = new ArrayList<>();
        polygonsList.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
        List<MapTextModel> points = new ArrayList<>();
        points.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
        points.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
        points.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));
        PolygonConfig polygonConfig = new PolygonConfig(extent,w,h,"e:/tmp/全屏_自定义.png",polylinesList,polygonsList,points,0,15,true,false);

        List<PolylineModel> polylinesList2 = new ArrayList<>();
        polylinesList2.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
        List<PolygonModel> polygonsList2 = new ArrayList<>();
        polygonsList2.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
        List<MapTextModel> points2 = new ArrayList<>();
        points2.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
        points2.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
        points2.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));
        PolygonConfig polygonConfig1 = new PolygonConfig(extent,w,h,"e:/tmp/非全屏_自定义.png",polylinesList2,polygonsList2,points2,0,15,false,false);

        try {
            ColorMapUtil.colorMap(gridDataConfig);
            ColorMapUtil.colorMap(gridDataConfig1);
            ColorMapUtil.colorMap(polygonConfig);
            ColorMapUtil.colorMap(polygonConfig1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
