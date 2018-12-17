package com.framework;

import com.framework.model.DataModel;
import com.framework.model.MapTextModel;
import com.framework.model.PolygonModel;
import com.framework.model.PolylineModel;
import com.framework.util.*;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polygon;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println(LocalDateTime.now() + " Grid Start");
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
        System.out.println(LocalDateTime.now()+ " Config Complete");
        try {
            GridDataConfig config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,true);

            System.out.println(LocalDateTime.now() + " Grid Complete");

            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));

            System.out.println(LocalDateTime.now() + " Making Complete");

            config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏非过滤_格点数据.png",colorList,valueList,null,list,true);

            System.out.println(LocalDateTime.now() + " Grid2 Complete");

            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));

            System.out.println(LocalDateTime.now() + " Making2 Complete");

            config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/非全屏过滤_格点数据.png",colorList,valueList,vectorLayer,list,false);

            System.out.println(LocalDateTime.now() + " Grid3 Complete");

            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));

            System.out.println(LocalDateTime.now() + " Making3 Complete");

            config = new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/非全屏非过滤_格点数据.png",colorList,valueList,null,list,false);

            System.out.println(LocalDateTime.now() + " Grid4 Complete");

            ColorMapUtil.colorMap(config, DataTranslater.getData(dataModels));

            System.out.println(LocalDateTime.now() + " Making4 Complete");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(LocalDateTime.now() + " Grid All End");
        System.out.println(LocalDateTime.now() + " Custom START");

        List<PolylineModel> polylinesList = new ArrayList<>();
        polylinesList.addAll( Arrays.asList(CommonUtil.polyline(extent,Color.red,3),CommonUtil.polyline(extent,Color.blue,5),CommonUtil.polyline(extent,Color.green,7)));

        List<PolygonModel> polygonsList = new ArrayList<>();
        polygonsList.addAll( Arrays.asList(CommonUtil.polygon(extent,Color.orange,10),CommonUtil.polygon(extent,Color.pink,10),CommonUtil.polygon(extent,Color.lightGray,10)));

        List<MapTextModel> points = new ArrayList<>();
        points.add(new MapTextModel(110,35,CommonUtil.initMap(new Object[]{"text","qqq"}),"text"));
        points.add(new MapTextModel(113,37,CommonUtil.initMap(new Object[]{"qf","fff"}),"qf"));
        points.add(new MapTextModel(109,31,CommonUtil.initMap(new Object[]{"qf","tetete"}),"qf"));

        try {
            PolygonConfig polygonConfig = new PolygonConfig(extent,w,h,"e:/tmp/全屏_自定义.png",polylinesList,polygonsList,points,0,15,true);

            System.out.println(LocalDateTime.now() + " Custom Config Complete");

            ColorMapUtil.colorMap(polygonConfig);

            System.out.println(LocalDateTime.now() + " Custom Making Complete");

            polygonConfig = new PolygonConfig(extent,w,h,"e:/tmp/非全屏_自定义.png",polylinesList,polygonsList,points,0,15,false);

            System.out.println(LocalDateTime.now() + " Custom Config2 Complete");

            ColorMapUtil.colorMap(polygonConfig);

            System.out.println(LocalDateTime.now() + " Custom Making2 Complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DoMain.doGridDataConfig();
    }

}
