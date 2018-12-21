package com.framework.v2;

import com.framework.v2.model.DataModel;
import com.framework.v2.model.MapTextModel;
import com.framework.v2.model.PolygonModel;
import com.framework.v2.model.PolylineModel;
import com.framework.v2.thread.ColorMapTask;
import com.framework.v2.util.*;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 色斑图生成 V2
 * 格点数据生成图片
 * 自定义区块/线条/站点图片绘制
 * 可定义边界宽度 可设置保存开关
 */
public class DoMain {

    public static int x = 500;
    public static int y = 500;
    public static int n = 10;
    public static int w = 1000;
    public static int h = 800;
    public static Extent extent = LayerReaderUtil.readOutExtent("E:\\demo\\miniTest\\colorMapConfig\\shp\\县边界.shp");//自定义展示区域

    public static void main(String[] args) {
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

        //创建线程 执行色斑图
        Thread gridThread = new Thread(new ColorMapTask( new GridDataConfig(DataTranslater.getData(dataModels),x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,2,true,false)));
        gridThread.start();

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

        Thread gridThread2 = new Thread(new ColorMapTask( new GridDataConfig(DataTranslater.getData(dataModels2),x,y,n,w,h,extent,"e:/tmp/非全屏过滤_格点数据.png",colorList2,valueList2,vectorLayer2,list2,2,false,true)));
        gridThread2.start();

        //配置项
        List<PolylineModel> polylinesList = new ArrayList<>();
        polylinesList.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
        List<PolygonModel> polygonsList = new ArrayList<>();
        polygonsList.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
        List<MapTextModel> points = new ArrayList<>();
        points.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
        points.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
        points.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));

        //创建线程 执行色斑图
        Thread polyThread = new Thread(new ColorMapTask(new PolygonConfig(extent,w,h,"e:/tmp/全屏_自定义.png",polylinesList,polygonsList,points,0,15,true,false)));
        polyThread.start();

        List<PolylineModel> polylinesList2 = new ArrayList<>();
        polylinesList2.addAll(Arrays.asList(CommonUtil.polyline(Color.pink)));
        List<PolygonModel> polygonsList2 = new ArrayList<>();
        polygonsList2.addAll(Arrays.asList(CommonUtil.polygon(Color.lightGray)));
        List<MapTextModel> points2 = new ArrayList<>();
        points2.add(new MapTextModel(105,28,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
        points2.add(new MapTextModel(102,30,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
        points2.add(new MapTextModel(99,32,CommonUtil.initMap(new Object[]{"SHOWDATA","tetete"}),"SHOWDATA"));

        //创建线程 执行色斑图
        Thread polyThread2 = new Thread(new ColorMapTask(new PolygonConfig(extent,w,h,"e:/tmp/非全屏_自定义.png",polylinesList2,polygonsList2,points2,0,15,false,true)));
        polyThread2.start();
    }

}
