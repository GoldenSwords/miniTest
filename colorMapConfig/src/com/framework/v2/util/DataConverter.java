package com.framework.v2.util;

import com.framework.v2.model.DataModel;
import org.meteoinfo.global.Extent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据转换类
 */
public class DataConverter {


    /**
     * ToMap
     * @param obj
     * @return
     */
    public static Map<String,Object> instanceMap(Object[]... obj){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < obj.length; i++) {
            map.put(obj[i][0].toString(),obj[i][1]);
        }
        return map;
    }

    /**
     * ToList
     * @param number
     * @return
     */
    public static List<Map<String,Object>> staticDataForList(int number){
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(instanceMap(new Object[]{"lon",131.1},new Object[]{"lat",31.1},new Object[]{"value",131.1}));
        }
        return list;
    }

    /**
     * ToList
     * @param number 数据量
     * @param extent 数据随机经纬度范围
     * @return
     */
    public static List<DataModel> staticDataForModel(int number, Extent extent){
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(new DataModel((extent.maxX-extent.minX)*Math.random()+extent.minX,(extent.maxY-extent.minY)*Math.random()+extent.minY,Math.random()*100));
        }
//        String data = "[{\"lat\":30.697791901506186,\"lon\":104.9427615629471,\"value\":4.345305092497265},{\"lat\":31.621288447606204,\"lon\":107.10028795014253,\"value\":24.876188137399822},{\"lat\":27.48710712776665,\"lon\":107.64242276716263,\"value\":52.2666850768074},{\"lat\":27.944088361653275,\"lon\":101.59691854046517,\"value\":68.27401761936753},{\"lat\":32.09367345703608,\"lon\":98.91547144075058,\"value\":33.75929562556886},{\"lat\":31.37234411517187,\"lon\":100.16921266555346,\"value\":7.101808218284278},{\"lat\":29.09240172118802,\"lon\":98.18137376217071,\"value\":61.69742662383201},{\"lat\":28.734475722612252,\"lon\":107.28961846013063,\"value\":78.414134631462},{\"lat\":33.37650693297944,\"lon\":107.97442878270847,\"value\":90.65608714638842},{\"lat\":28.665507902360496,\"lon\":106.98281924163616,\"value\":20.106037043974588},{\"lat\":28.423129565352784,\"lon\":100.01624836948638,\"value\":64.4691678913178}]";
//        JSONArray arr = JSONArray.parseArray(data);
//        List<DataModel> list = new ArrayList<>();
//        for (int i = 0; i < arr.size(); i++) {
//            JSONObject jsonObject = arr.getJSONObject(i);
//            list.add(new DataModel(jsonObject.getDoubleValue("lon"),jsonObject.getDoubleValue("lat"),jsonObject.getDoubleValue("value")));
//        }
        return list;
    }
}
