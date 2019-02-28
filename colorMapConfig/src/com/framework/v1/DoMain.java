package com.framework.v1;

import com.framework.v1.model.DataModel;
import com.framework.v1.model.MapTextModel;
import com.framework.v1.model.PolygonModel;
import com.framework.v1.model.PolylineModel;
import com.framework.v1.util.*;
import com.framework.v2.thread.ColorMapTask;
import org.apache.batik.svggen.SVGGraphics2D;
import org.meteoinfo.chart.plot.Plot;
import org.meteoinfo.chart.plot.Plot2D;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polyline;
import wContour.Contour;

import javax.swing.text.BoxView;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * 色斑图生成 V1
 * 格点数据生成图片
 * 自定义区块/线条/站点图片绘制
 */
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
        List<DataModel> dataModels = DataConverter.staticDataForModel(1000,extent);
       try {
           ColorMapUtil.colorMap(new GridDataConfig(x,y,n,w,h,extent,"e:/tmp/全屏过滤_格点数据.png",colorList,valueList,vectorLayer,null,true,DataTranslater.getData(dataModels)));
       }catch (Exception e){
           e.printStackTrace();
       }
        System.out.println(String.format("End::%s",LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
    }

}
