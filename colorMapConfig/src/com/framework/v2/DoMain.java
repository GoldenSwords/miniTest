package com.framework.v2;

import business.util.JDBCUtil;
import com.alibaba.fastjson.JSONArray;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 色斑图生成 V2
 * 格点数据生成图片
 * 自定义区块/线条/站点图片绘制
 * 可定义边界宽度 可设置保存开关
 */
public class DoMain {

    public static int x = 100; //横向格点数量
    public static int y = 100; //纵向格点数据
    public static int n = 3;   //需计算的临近点数量
    public static int w = 1000; // 图片宽
    public static int h = 800; // 图片高
    public static Extent extent = LayerReaderUtil.readOutExtent("E:\\shp\\china.shp");//自定义展示区域
    public static VectorLayer vectorLayer = LayerReaderUtil.readOutLayer("E:\\shp\\china.shp");

    public static void main(String[] args) throws Exception{
        String[] qf = new String[]{"1d","2d","3d"};
        JSONArray arr = new JSONArray();
        for (int i = 0; i < qf.length; i++) {
            arr.add(qf[i]);

        }
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.getDouble(i));

        }
//        List<Map<String,Object>> list = JDBCUtil.queryForList(JDBCUtil.getDbConnect("com.mysql.jdbc.Driver",
//                "jdbc:mysql://178.16.30.181:2306/environment?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true",
//                "root",
//                "htdf123456"),"SELECT * from SURF_CHN_MUL_HOR where D_DATETIME='2019-03-07 09:00:00'");
//        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
////        extent = new Extent(96.716008,113.468023,23.781539,36.233668);
////        List<DataModel> dataModels = new ArrayList<>();//DataConverter.staticDataForModel(2000,extent);
////        dataModels.add(new DataModel((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY,0));
////        dataModels.add(new DataModel((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY,5));
////        dataModels.add(new DataModel((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY,220));
//        //配置项
//        List<Color> colorList = Arrays.asList(Color.decode("#000000"),Color.decode("#440062"),Color.decode("#7E0000"),Color.decode("#FE2102"),Color.decode("#FE8919"),
//                Color.decode("#FEFC04"),Color.decode("#51D51D"),Color.decode("#93E0F4"),Color.decode("#FFFFFF"));//自定义色标
//        List<Double> valueList = Arrays.asList(0D, 200D, 500D, 1000D, 2000D, 3000D, 5000D, 10000D);//自定义色阶
////        List<Polyline> list = new ArrayList<>();
////        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
////            Polyline polyline = new Polyline();
////            polyline.setPointList(vectorLayer.getShapes().get(i).getPoints());
////            list.add(polyline);
////        }
////        List<DataModel> dataModels = DataConverter.staticDataForModel(100,extent);
//        GridDataConfig gridDataConfig = new GridDataConfig(DataTranslater.getData(list,"V06001","V05001","V20001"),x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,new ArrayList<>(),0,true,false);
//
//        try {
//            ColorMapUtil.colorMap(gridDataConfig);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        org.meteoinfo.data.StationData stationData = DataTranslater.getData(dataModels);//com.framework.v2.util.DataTranslater.getData(dataList,"lon","lat","value");

//        GridDataSetting setting = new GridDataSetting();
//        setting.dataExtent = extent;//网格范围
//        setting.xNum = x;//网格横向数量
//        setting.yNum = y;//网格纵向数量
//        InterpolationSetting interSet = new InterpolationSetting();//转换参数设置
//        interSet.setGridDataSetting(setting);
//        interSet.setInterpolationMethod(InterpolationMethods.IDW_Neighbors);//临近点插值法
//        interSet.setMinPointNum(n);//设置临近点计算数量
//        GridData gridData = stationData.interpolateData(interSet);//格点插值流程


//        List<Color> colorList2 = Arrays.asList(Color.decode("#11003E"),Color.decode("#1B0060"),Color.decode("#240082"),Color.decode("#2C009F"),Color.decode("#3500BF"),Color.decode("#6002BD"),Color.decode("#6F00E8"),Color.decode("#8A02AC"),Color.decode("#E1025B"),Color.decode("#DE0515"));//自定义色标
//        List<Double> valueList2 = Arrays.asList(10d,20d,30d,40d,50d,60d,70d,80d,90d);//自定义色阶
//        VectorLayer vectorLayer2 = LayerReaderUtil.readOutLayer("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");
//        List<Polyline> list2 = new ArrayList<>();
//        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
//            Polyline polyline = new Polyline();
//            polyline.setPointList(vectorLayer2.getShapes().get(i).getPoints());
//            list2.add(polyline);
//        }
//        List<DataModel> dataModels2 = DataConverter.staticDataForModel(100,extent);
//        GridDataConfig gridDataConfig1 = new GridDataConfig(DataTranslater.getData(dataModels2),x,y,n,w,h,extent,"e:/tmp/非全屏过滤_格点数据.png",colorList2,valueList2,vectorLayer2,list2,2,false,false);
//
//        //配置项
//        List<PolylineModel> polylinesList = new ArrayList<>();
//        polylinesList.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
//        List<PolygonModel> polygonsList = new ArrayList<>();
//        polygonsList.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
//        List<MapTextModel> points = new ArrayList<>();
//        points.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
//        points.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
//        points.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));
//        PolygonConfig polygonConfig = new PolygonConfig(extent,w,h,"e:/tmp/全屏_自定义.png",polylinesList,polygonsList,points,0,15,true,false);
//
//        List<PolylineModel> polylinesList2 = new ArrayList<>();
//        polylinesList2.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
//        List<PolygonModel> polygonsList2 = new ArrayList<>();
//        polygonsList2.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
//        List<MapTextModel> points2 = new ArrayList<>();
//        points2.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
//        points2.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
//        points2.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));
//        PolygonConfig polygonConfig1 = new PolygonConfig(extent,w,h,"e:/tmp/非全屏_自定义.png",polylinesList2,polygonsList2,points2,0,15,false,false);

//        try {
//            ColorMapUtil.colorMap(gridDataConfig);
//            ColorMapUtil.colorMap(gridDataConfig1);
//            ColorMapUtil.colorMap(polygonConfig);
//            ColorMapUtil.colorMap(polygonConfig1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
    }

}
