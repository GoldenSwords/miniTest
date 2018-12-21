package com.framework.v1;

import com.framework.v1.model.DataModel;
import com.framework.v1.model.MapTextModel;
import com.framework.v1.model.PolygonModel;
import com.framework.v1.model.PolylineModel;
import com.framework.v1.util.*;
import com.framework.v2.thread.ColorMapTask;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class DoMain {

    public static int x = 500; //横向格点数量
    public static int y = 500; //纵向格点数据
    public static int n = 10;   //需计算的临近点数量
    public static int w = 1000; // 图片宽
    public static int h = 800; // 图片高
    public static Extent extent = LayerReaderUtil.readOutExtent("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");//自定义展示区域
    public static double missValue = -999;
    public static double limit_Max = 50;//最大值限制
    public static double limit_Min = 0;//最小值限制

    public static void main(String[] args) {
        System.out.println(String.format("Start::%s",LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        List<Color> colorList = Arrays.asList(Color.decode("#11003E"),Color.decode("#1B0060"),Color.decode("#240082"),Color.decode("#2C009F"),Color.decode("#3500BF"),Color.decode("#6002BD"),Color.decode("#6F00E8"),Color.decode("#8A02AC"),Color.decode("#E1025B"),Color.decode("#DE0515"));//自定义色标
        List<Double> valueList = Arrays.asList(10d,20d,30d,40d,50d,60d,70d,80d,90d);//自定义色阶
        VectorLayer vectorLayer = LayerReaderUtil.readOutLayer("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");
        List<Polyline> list = new ArrayList<>();
        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
            Polyline polyline = new Polyline();
            polyline.setPointList(vectorLayer.getShapes().get(i).getPoints());
            list.add(polyline);
        }
        List<DataModel> dataModels = DataConverter.staticDataForModel(1000,extent);
       try {
           ColorMapUtil.colorMap(new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,null,true,DataTranslater.getData(dataModels)));
       }catch (Exception e){
           e.printStackTrace();
       }
        System.out.println(String.format("End::%s",LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
//        Thread gridThread = new Thread(new ColorMapTask( new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,true,DataTranslater.getData(dataModels))));
//        Thread gridThread2 = new Thread(new ColorMapTask( new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/非全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,false,DataTranslater.getData(dataModels))));
//
//        gridThread.start();
//        gridThread2.start();
//
//        List<PolylineModel> polylinesList = new ArrayList<>();
//        polylinesList.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
//        List<PolygonModel> polygonsList = new ArrayList<>();
//        polygonsList.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
//        List<MapTextModel> points = new ArrayList<>();
//        points.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
//        points.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
//        points.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));
//
//        Thread polyThread = new Thread(new ColorMapTask(new PolygonConfig(extent,w,h,"e:/tmp/全屏_自定义.png",polylinesList,polygonsList,points,0,15,true)));
//        polyThread.start();
//
//        List<PolylineModel> polylinesList2 = new ArrayList<>();
//        polylinesList2.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
//        List<PolygonModel> polygonsList2 = new ArrayList<>();
//        polygonsList2.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
//        List<MapTextModel> points2 = new ArrayList<>();
//        points2.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
//        points2.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
//        points2.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));
//        Thread polyThread2 = new Thread(new ColorMapTask(new PolygonConfig(extent,w,h,"e:/tmp/非全屏_自定义.png",polylinesList2,polygonsList2,points2,0,15,false)));
//        polyThread2.start();
    }

}
