package com.framework;

import com.framework.model.DataModel;
import com.framework.model.MapTextModel;
import com.framework.model.PolygonModel;
import com.framework.util.*;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polygon;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DoMain {

    public static int x = 100;
    public static int y = 100;
    public static int n = 10;
    public static int w = 700;
    public static int h = 500;
    public static Extent extent = new Extent(100,120,30,40);

    public static void doGridDataConfig(){
        List<Color> colorList = new ArrayList<>();
        List<Double> valueList = new ArrayList<>();
        colorList.add(Color.decode("#11003E"));
        colorList.add(Color.decode("#1B0060"));
        colorList.add(Color.decode("#240082"));
        colorList.add(Color.decode("#2C009F"));
        colorList.add(Color.decode("#3500BF"));
        colorList.add(Color.decode("#6002BD"));
        colorList.add(Color.decode("#6F00E8"));
        colorList.add(Color.decode("#8A02AC"));
        colorList.add(Color.decode("#E1025B"));
        colorList.add(Color.decode("#DE0515"));
        valueList.add(10d);
        valueList.add(20d);
        valueList.add(30d);
        valueList.add(40d);
        valueList.add(50d);
        valueList.add(60d);
        valueList.add(70d);
        valueList.add(80d);
        valueList.add(90d);

        VectorLayer vectorLayer = LayerReaderUtil.readOutLayer("E:\\压缩包\\arcgisSHP\\四川省农气地图181023\\县边界.shp");
        extent = vectorLayer.getExtent();
        List<Polyline> list = new ArrayList<>();
        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
            Polyline polyline = new Polyline();
            polyline.setPointList(vectorLayer.getShapes().get(i).getPoints());
            list.add(polyline);
        }
        List<DataModel> dataModels = DataConverter.staticDataForModel(100,extent);

        try {
            GridDataConfig config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,true);
//            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));
//
//            config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏非过滤_格点数据.png",colorList,valueList,null,list,true);
//            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));
//
            config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/非全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,false);
            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));
//
//            config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/非全屏非过滤_格点数据.png",colorList,valueList,null,list,false);
//            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Polyline> polylines = new ArrayList<>();
        polylines.add(polyline(extent));
        polylines.add(polyline(extent));
        polylines.add(polyline(extent));

        List<PolygonModel> polygons = new ArrayList<>();
        polygons.add(new PolygonModel(polygon(extent),Color.red));
        polygons.add(new PolygonModel(polygon(extent),Color.blue));
        polygons.add(new PolygonModel(polygon(extent),Color.orange));


        List<MapTextModel> point = new ArrayList<>();
        point.add(new MapTextModel(110,35,initMap(new Object[]{"text","qqq"}),"text"));
        point.add(new MapTextModel(113,37,initMap(new Object[]{"qf","fff"}),"qf"));
        point.add(new MapTextModel(109,31,initMap(new Object[]{"qf","tetete"}),"qf"));

        try {
//            PolygonConfig polygonConfig = new PolygonConfig(extent,w,h,"e:/tmp/全屏_自定义.png",polylines,polygons,texts,0,15,true);
//            ColorMapUtil.colorMap(polygonConfig);

            PolygonConfig polygonConfig = new PolygonConfig(extent,w,h,"e:/tmp/非全屏_自定义.png",polylines,polygons,point,0,15,false);
            ColorMapUtil.colorMap(polygonConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Polygon polygon(Extent extent){
        Polygon polygon = new Polygon();
        List<PointD> list = new ArrayList<>();
        PointD pointD = new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY);
        list.add(pointD);
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(pointD);
        polygon.setOutLine(list);
        return polygon;
    }
    public static Polyline polyline(Extent extent){
        Polyline polyline = new Polyline();
        List<PointD> list = new ArrayList<>();
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        polyline.setPointList(list);
        return polyline;
    }

    public static void doPolygonConfig(){
        Extent extent = new Extent(100,120,30,40);

        List<Polyline> polylines = new ArrayList<>();
        polylines.add(polyline(extent));
        polylines.add(polyline(extent));
        polylines.add(polyline(extent));

        List<PolygonModel> polygons = new ArrayList<>();
        polygons.add(new PolygonModel(polygon(extent),Color.red));
        polygons.add(new PolygonModel(polygon(extent),Color.blue));
        polygons.add(new PolygonModel(polygon(extent),Color.orange));


        List<MapTextModel> texts = new ArrayList<>();
        texts.add(new MapTextModel(110,35,initMap(new Object[]{"text","qqq"}),"text"));
        texts.add(new MapTextModel(113,37,initMap(new Object[]{"qf","fff"}),"qf"));
        texts.add(new MapTextModel(109,31,initMap(new Object[]{"qf","tetete"}),"tetete"));

        PolygonConfig polygonConfig = new PolygonConfig(extent,500,500,"e:/tmp/text.png",polylines,polygons,texts,25,30,false);
        try {
            ColorMapUtil.colorMap(polygonConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DoMain.doGridDataConfig();
    }

    public static Map<String,Object> initMap(Object[]... obj){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < obj.length; i++) {
            map.put(obj[i][0].toString(),obj[i][1]);
        }
        return map;
    }

    /**
     * @Title:main
     * @Description:生成随机颜色
     * @param:@param args
     * @return: void
     * @throws
     */
    public static String randomColor()
    {
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length()==1 ? "0" + red : red ;
        //判断绿色代码的位数
        green = green.length()==1 ? "0" + green : green ;
        //判断蓝色代码的位数
        blue = blue.length()==1 ? "0" + blue : blue ;
        //生成十六进制颜色值
        String color = "#"+red+green+blue;
        return color;
    }
}
