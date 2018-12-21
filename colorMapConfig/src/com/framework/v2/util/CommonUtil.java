package com.framework.v2.util;

import com.framework.v2.model.PolygonModel;
import com.framework.v2.model.PolylineModel;
import org.apache.commons.codec.binary.Base64;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.shape.Polygon;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {
    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String imgToBase64(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr 图片数据
     * @param imgFilePath 保存图片全路径地址
     * @return
     */
    public static boolean base64ToImg(String imgStr,String imgFilePath){
        //
        if (imgStr == null) //图像数据为空
            return false;

        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    /**
     * 实例化Map
     * @param obj
     * @return
     */
    public static Map<String,Object> initMap(Object[]... obj){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < obj.length; i++) {
            map.put(obj[i][0].toString(),obj[i][1]);
        }
        return map;
    }
    public static PolylineModel polyline(Color color){
        color = color == null ? Color.black : color;
        List<Polyline> list1 = new ArrayList<>();
        String pointString = "103.730991,32.471087,101.500767,32.127491,100.852573,30.881734,101.632603,29.447505,102.775181,29.294318,103.082798,28.968033,103.500278,29.006474,103.665073,29.533571,104.236362,29.5718,104.280308,29.294318,104.785679,29.5718,105.334995,29.610013,104.96146,30.333309,104.258335,30.57008,103.500278,30.124476,103.038853,29.619564,102.489536,29.800864,101.962192,30.200467,102.137974,30.806274,103.22562,30.957134,103.8848,31.013646,104.071567,31.295699,104.401157,31.088942,104.763706,30.740198,105.400913,30.72131,105.510776,30.314342,105.906284,29.972319,106.554478,31.389531,106.24686,31.922575,105.609653,32.304095,104.719761,32.600756,104.247349,32.610011";
        String[] points = pointString.split(",");
        List<PointD> list = new ArrayList<>();
        for (int i = 0; i < points.length/2; i++) {
            list.add(new PointD(Double.valueOf(points[i*2]),Double.valueOf(points[i*2+1])));
        }
        list.add(new PointD(Double.valueOf(points[0]),Double.valueOf(points[1])));
        Polyline polyline = new Polyline();
        polyline.setPointList(list);
        list1.add(polyline);
        return new PolylineModel(list1,color);
    }
    /**
     * 实例化线条模型
     * @param extent
     * @param color
     * @return
     */
    public static PolylineModel polyline(Extent extent, Color color, int number){
        List<Polyline> list1 = new ArrayList<>();
        color = color == null ? Color.black : color;
        for (int i = 0; i < number; i++) {
            Polyline polyline = new Polyline();
            List<PointD> list = new ArrayList<>();
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
            polyline.setPointList(list);
            list1.add(polyline);
        }
        return new PolylineModel(list1,color);
    }

    public static PolygonModel polygon(Color color){
        color = color == null ? Color.black : color;
        Polygon polygon = new Polygon();
        String pointString = "103.730991,32.471087,101.500767,32.127491,100.852573,30.881734,101.632603,29.447505,102.775181,29.294318,103.082798,28.968033,103.500278,29.006474,103.665073,29.533571,104.236362,29.5718,104.280308,29.294318,104.785679,29.5718,105.334995,29.610013,104.96146,30.333309,104.258335,30.57008,103.500278,30.124476,103.038853,29.619564,102.489536,29.800864,101.962192,30.200467,102.137974,30.806274,103.22562,30.957134,103.8848,31.013646,104.071567,31.295699,104.401157,31.088942,104.763706,30.740198,105.400913,30.72131,105.510776,30.314342,105.906284,29.972319,106.554478,31.389531,106.24686,31.922575,105.609653,32.304095,104.719761,32.600756,104.247349,32.610011";
        String[] points = pointString.split(",");
        List<PointD> list = new ArrayList<>();
        for (int i = 0; i < points.length/2; i++) {
            list.add(new PointD(Double.valueOf(points[i*2]),Double.valueOf(points[i*2+1])));
        }
        list.add(new PointD(Double.valueOf(points[0]),Double.valueOf(points[1])));

        polygon.setOutLine(list);
        return new PolygonModel(polygon,color);
    }

    /**
     * 实例化区块模型
     * @param extent
     * @param color
     * @param number
     * @return
     */
    public static PolygonModel polygon(Extent extent, Color color, int number){
        color = color == null ? Color.black : color;
        Polygon polygon = new Polygon();
        List<PointD> list = new ArrayList<>();
        PointD pointD = new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY);
        list.add(pointD);
        for (int i = 0; i < number-2; i++) {
            list.add(new PointD((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY));
        }
        list.add(pointD);
        polygon.setOutLine(list);
        return new PolygonModel(polygon,color);
    }
}
