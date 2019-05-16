package com.framework.v3;

import business.util.JDBCUtil;
import com.framework.v3.model.DataModel;
import com.framework.v3.util.DataConverter;
import com.framework.v3.util.DataTranslater;
import com.framework.v3.util.GridDataConfig;
import com.framework.v3.util.LayerReaderUtil;
import org.meteoinfo.data.StationData;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.MapLayer;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polyline;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public static int w = 930; // 图片宽
    public static int h = 685; // 图片高
    public static String extent_SiChuan = "E:\\压缩包\\arcgisSHP\\省市县\\四川省界.shp";//"E:\\shp\\全省界.shp";
    public static String shpPath = "E:\\压缩包\\arcgisSHP\\四川省农气地图181023\\县边界.shp";
    public static String shoPath_All = "E:\\压缩包\\arcgisSHP\\全国\\china.shp";
    public static Extent extent = LayerReaderUtil.readOutExtent(extent_SiChuan);//自定义展示区域
    public static VectorLayer vectorLayer = LayerReaderUtil.readOutLayer(extent_SiChuan);

    public static String[] names = new String[]{"炉霍县","邛崃市","道孚县","金川县","都江堰市","崇州市","大邑县","古蔺县","木里","盐源县"};

    public static void main(String[] args) throws Exception{
        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
//                List<Map<String,Object>> list = JDBCUtil.queryForList(JDBCUtil.getDbConnect("com.mysql.jdbc.Driver",
//                "jdbc:mysql://178.16.30.181:2306/environment?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true",
//                "root",
//                "htdf123456"),"SELECT * from SURF_CHN_MUL_HOR where D_DATETIME='2019-03-22 09:00:00'");
        List<DataModel> dataModels = DataConverter.staticDataForModel(1000,vectorLayer.getExtent());
        List<Color> colorList = Arrays.asList(
                Color.decode("#0000ff"),Color.decode("#0014ff"),Color.decode("#0045ff"),Color.decode("#0065ff"),Color.decode("#0085ff"),
//                Color.decode("#00a6ff"),Color.decode("#00cbff"),Color.decode("#00ebff"),Color.decode("#00fff7"),Color.decode("#00ffd6"),
//                Color.decode("#00ffb5"),Color.decode("#00ff94"),Color.decode("#00ff73"),Color.decode("#00ff52"),Color.decode("#00ff29"),
//                Color.decode("#00ff08"),Color.decode("#10ff00"),Color.decode("#31ff00"),Color.decode("#52ff00"),Color.decode("#73ff00"),
//                Color.decode("#94ff00"),Color.decode("#b5ff00"),Color.decode("#deff00"),Color.decode("#ffff00"),Color.decode("#ffe700"),
//                Color.decode("#ffc300"),Color.decode("#ffa200"),Color.decode("#ff8200"),Color.decode("#ff6100"),Color.decode("#ff4100"),
                Color.decode("#ff2c00"),Color.decode("#ff2000"),Color.decode("#ff1400"),Color.decode("#ff0000")
        );//自定义色标
        List<Double> valueList = Arrays.asList(
//                -26d, -24d, -22d, -20d, -18d,
//                -16d, -14d, -12d, -10d,-8d,
//                -6d, -4d, -2d, 0d, 2d,
//                4d, 6d, 8d, 10d, 12d,
//                14d, 16d, 18d,20d, 22d,
                24d, 26d, 28d, 30d, 32d,
                34d, 60d, 80d
        );//自定义色阶
        System.out.println(extent.maxX+"::"+extent.minX+"::"+extent.maxY+"::"+extent.minY);
        List<Polyline> borderline = new ArrayList<>();
        for (int i = 0; i < vectorLayer.getShapes().size(); i++) {
            Polyline polyline = new Polyline();
            polyline.setPointList(vectorLayer.getShapes().get(i).getPoints());
            borderline.add(polyline);
        }
        int limit = 1000;
        int douS = doubles(extent.maxX,extent.minX,limit)>doubles(extent.maxY,extent.minY,limit)?doubles(extent.maxX,extent.minX,limit):doubles(extent.maxY,extent.minY,limit);
        StationData s = DataTranslater.getData(dataModels);
//        s = DataTranslater.getData(list,"V06001","V05001","V12001");
        GridDataConfig gridDataConfig = new GridDataConfig(s, x,y, n,
//                (int)(Math.round((extent.maxX-extent.minX)*douS)),
//                (int)(Math.round((extent.maxY-extent.minY)*douS)),
                w,h,
                extent,"e:/tmp/png.png",colorList,valueList,vectorLayer,borderline,2,true,false);
        try {
            ColorMapUtil.colorMap(gridDataConfig);
            System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
            gridDataConfig.setLine(true);
            gridDataConfig.setFilePath("e:/tmp/png.png.png");
            ColorMapUtil.colorMap(gridDataConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        for (String area:names) {
//            VectorLayer maskLayer = LayerReaderUtil.readOutLayer(shpPath,area,"NAME","TARGET_FID");
//            Extent cextent = maskLayer.getExtent();
//            douS = doubles(cextent.maxX,cextent.minX,limit)>doubles(cextent.maxY,cextent.minY,limit)?doubles(cextent.maxX,cextent.minX,limit):doubles(cextent.maxY,cextent.minY,limit);
//
//            gridDataConfig = new GridDataConfig(DataTranslater.getData(list,"V06001","V05001","V12001"), x,y, n,
//                    (int)(Math.round((cextent.maxX-cextent.minX)*douS)),
//                    (int)(Math.round((cextent.maxY-cextent.minY)*douS)),
//                    extent,"e:/tmp/" + area + ".png",colorList,valueList,maskLayer,new ArrayList<>(),0,true,false);
//            try {
//                ColorMapUtil.colorMap(gridDataConfig);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        Extent extent = LayerReaderUtil.readOutLayer("E:\\压缩包\\arcgisSHP\\成都市县\\成都市带县界复制3.shp").getExtent();
        System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
    }

    private static int doubles(double max,double min,int limit){
        int dou = 1;
        if((max-min)*dou<limit){
            dou ++ ;
            return doubles(max,min,limit,dou);
        }
        return dou;
    }
    private static int doubles(double max,double min,int limit,int dou){
        if((max-min)*dou<limit){
            dou ++ ;
            return doubles(max,min,limit,dou);
        }
        return dou;
    }
}
