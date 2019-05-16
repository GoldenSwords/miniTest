package com.framework.v3;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import com.framework.v3.model.DataModel;
import com.framework.v3.util.DataConverter;
import com.framework.v3.util.DataTranslater;
import com.framework.v3.util.GridDataConfig;
import com.framework.v3.util.LayerReaderUtil;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.shape.Polyline;

import java.awt.Color;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GDAL {
    public static int x = 100; //横向格点数量
    public static int y = 100; //纵向格点数据
    public static int n = 10;   //需计算的临近点数量
    public static int w = 930; // 图片宽
    public static int h = 685; // 图片高
    //minX = 103.55636118991
    // maxX = 104.890870574332
    // minY = 30.4859012616972
    //maxY = 31.690983229399
    public static String extent_SiChuan = "E:\\压缩包\\arcgisSHP\\省市县\\四川省界.shp";
    public static String shoPath_All = "E:\\压缩包\\arcgisSHP\\全国\\china.shp";
    public static Extent extent = new Extent(100, 110, 30, 40);//自定义展示区域

    public static void doGridDataConfig() {
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
        VectorLayer vectorLayer = LayerReaderUtil.readOutLayer(shoPath_All);
        //过滤层
        extent = vectorLayer.getExtent();
        //边界线
        List<Polyline> list = new ArrayList<>();
        List<DataModel> dataModels = DataConverter.staticDataForModel(100, extent);
        System.out.println(LocalDateTime.now() + " Config Complete");
        try {
            String filePath = "e:/tmp/V13003.png";
//            //横向格点数,纵向格点数,临近点数量,图片宽,图片高，区域，生成图片路径，色标配置，色域配置，过滤层，边界线，是否全屏
            GridDataConfig config = new GridDataConfig(DataTranslater.getData(dataModels),x, y, n, w, h, extent, filePath, colorList, valueList, vectorLayer, list,0, true,false);
//
//            System.out.println(LocalDateTime.now() + " Grid Complete");
//
//            ColorMapUtil.colorMap(config);
//
//            System.out.println(LocalDateTime.now() + " Making Complete");

            // 转换投影
            transImgMecProjByGDAL(config.getExtent(), filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(LocalDateTime.now() + " Grid  End");
    }

    private static void transImgMecProjByGDAL(Extent extent, String outUrl) {
        if (!FileUtil.exist(outUrl)) {
            return;
        }
        String pdir = FileUtil.file(outUrl).getParent();
        //中间过程图片名称变量生成
        String temFileName = UUID.randomUUID().toString() + "";
        String temFilePathAndFName = pdir+ "\\" + temFileName;
        //获取地理范围
        Extent viewExtent = extent;
        String extentStr = String.format("%s %s %s %s", viewExtent.minX, viewExtent.maxY, viewExtent.maxX, viewExtent.minY);
        //GDAL第一步  .png to .img
        //gdal_translate -a_srs EPSG:4326 "E:\\AOD\\20181210000000_20181210001459.png" "E:\\AOD\\20181210000000_20181210001459.png.img" -of HFA -a_ullr 73.44586543252842 53.553741455078125 134.76846313476562 18.16888427734375
        String one = String.format(
                "gdal_translate -a_srs EPSG:4326 %s %s.img -of HFA -a_ullr %s",
                outUrl,
                temFilePathAndFName,
                extentStr);
//        one = "gdal_translate -a_srs EPSG:4326 \"E:/tmp/png.png\" \"E:/tmp/png.png.img\" -of HFA -a_ullr 73.44586543252842 53.553741455078125 134.76846313476562 18.16888427734375";
        //gdalwarp -s_srs EPSG:4326 -t_srs EPSG:3857 "E:\\AOD\\20181210000000_20181210001459.png.img" "E:\\AOD\\20181210000000_20181210001459.png.m.img" -of HFA
        String two = String.format(
                "gdalwarp -s_srs EPSG:4326 -t_srs EPSG:3857 %s.img %sm.img -of HFA",
                temFilePathAndFName,
                temFilePathAndFName);
        //gdal_translate -of PNG -a_srs EPSG:3857 H:\\123m.img H:\\123m.png
        String there = String.format(
                "gdal_translate -of PNG -a_srs EPSG:3857 %sm.img %s",
                temFilePathAndFName,
                outUrl);
        //执行命令
        System.out.println(one);
        System.out.println(two);
        System.out.println(there);
        System.out.println(RuntimeUtil.execForStr(one));
        System.out.println(RuntimeUtil.execForStr(two));
        System.out.println(RuntimeUtil.execForStr(there));
        //清理掉中间文件
        for (File file : FileUtil.ls(pdir)) {
            if (file.getName().startsWith(temFileName) || file.getName().endsWith(".aux.xml")) {
                file.delete();
            }
        }
    }

    public static void main(String[] args) {
        GDAL.doGridDataConfig();

    }
}
