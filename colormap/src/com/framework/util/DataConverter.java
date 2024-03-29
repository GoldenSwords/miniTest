package com.framework.util;

import com.framework.model.DataModel;
import org.meteoinfo.global.Extent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据转换类
 */
public class DataConverter {
    public static Map<String,Object> instanceMap(Object[]... obj){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < obj.length; i++) {
            map.put(obj[i][0].toString(),obj[i][1]);
        }
        return map;
    }
    public static List<Map<String,Object>> staticDataForList(int number){
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(instanceMap(new Object[]{"lon",131.1},new Object[]{"lat",31.1},new Object[]{"value",131.1}));
        }
        return list;
    }

    public static List<DataModel> staticDataForModel(int number,Extent extent){
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(new DataModel((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY,Math.random()*100));
        }
        return list;
    }
}
