package business;

import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.mapdata.MapDataManage;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.geoprocess.analysis.InterpolationMethods;
import org.meteoinfo.geoprocess.analysis.InterpolationSetting;
import org.meteoinfo.layer.VectorLayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟数据类
 * @author lh q 2432556082
 */
public class DataMaker {

    /**
     * 测试数据生成器
     * @return
     */
    public static StationData simpleStationDataMaker(){
        StationData data = new StationData();
        for (int i = 0; i < 100; i++) {
            data.addData(""+i,Math.random()+120.28,Math.random()+29.71,Math.random()*100);
        }
        return data;
    }

    /**
     * 测试数据生成器
     * @param numbers 数量
     * @param latMin 最小纬度
     * @param latMax 最大纬度
     * @param lonMin 最小经度
     * @param lonMax 最大经度
     * @param valueMax 最小值范围
     * @param valueMin 最大值范围
     * @return
     */
    public static StationData simpleStationDataMaker(int numbers,double latMin,double latMax,double lonMin,double lonMax,double valueMax,double valueMin){
        StationData data = new StationData();
        for (int i = 0; i < numbers; i++) {
            data.addData(""+i,numberMaker(lonMax,lonMin),numberMaker(latMax,latMin),numberMaker(valueMax,valueMin));
        }
        return data;
    }

    /**
     * 测试数据生成器
     * @return
     */
    public static List<Map<String,Object>> simpleListDataMaker(){
        List<Map<String,Object>> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",String.valueOf(i));
            map.put("name",String.valueOf(i));
//            map.put("lon",Math.random()+120.29);
//            map.put("lat",Math.random()+29.7);
            map.put("lon",Math.random()*(0.09)+104.11);
            map.put("lat",Math.random()*(0.1)+30.42);
            map.put("value",Math.random()*100);
            map.put("fvalue",Math.random()*10);
            double xMax = 104.20;
            double xMin = 104.11;
            double yMin = 30.42;
            double yMax =  30.51;
            data.add(map);
        }
        return data;
    }

    /**
     * 测试数据生成器
     * @param numbers 数量
     * @param latMin 最小纬度
     * @param latMax 最大纬度
     * @param lonMin 最小经度
     * @param lonMax 最大经度
     * @param valueMax 最小值范围
     * @param valueMin 最大值范围
     * @return
     */
    public static List<Map<String,Object>> simpleListDataMaker(int numbers,double latMin,double latMax,double lonMin,double lonMax,double valueMax,double valueMin){
        List<Map<String,Object>> data = new ArrayList<>();
        for (int i = 0; i < numbers; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",String.valueOf(i));
            map.put("name",String.valueOf(i));
            map.put("lon",numberMaker(lonMax,lonMin));
            map.put("lat",numberMaker(latMax,latMin));
            map.put("value",numberMaker(valueMax,valueMin));
            data.add(map);
        }
        return data;
    }

    private static double numberMaker(double max,double min){
        double fs = Math.random()*max-min;
        if(fs<min){
            fs = numberMaker(max,min);
        }
        return fs;
    }

}
