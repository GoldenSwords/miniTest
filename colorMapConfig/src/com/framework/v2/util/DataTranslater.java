package com.framework.v2.util;

import com.framework.v2.model.DataModel;
import org.meteoinfo.data.StationData;

import java.util.List;
import java.util.Map;

/**
 * 数据生成类
 */
public class DataTranslater {

    /**
     * listToStationData
     * List数据解析为站点数据
     * @param list
     * @param lonKey
     * @param latKey
     * @param valueKey
     * @return
     */
    public static StationData getData(List<Map<String,Object>> list,String lonKey,String latKey, String valueKey){
        StationData stationData = new StationData();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).containsKey(lonKey)&&list.get(i).containsKey(latKey)&&list.get(i).containsKey(valueKey)){
                stationData.addData("",Double.valueOf(list.get(i).get(lonKey).toString()),Double.valueOf(list.get(i).get(latKey).toString()),Double.valueOf(list.get(i).get(valueKey).toString()));
            }
        }
        return stationData;
    }

    /**
     * listToStationData
     * List数据解析为站点数据
     * @param list
     * @param missValue 无效值
     * @param limit_max 最大值限制
     * @param limit_min 最小值限制
     * @return
     */
    public static StationData getData(List<DataModel> list, double missValue, double limit_max, double limit_min){
        StationData stationData = new StationData();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getValue()-missValue!=0&&list.get(i).getValue()>=limit_min&&list.get(i).getValue()<=limit_max){
                stationData.addData("",list.get(i).getLon(),list.get(i).getLat(),list.get(i).getValue());
            }
        }
        return stationData;
    }

    /**
     * listToStationData
     * List数据解析为站点数据
     * @param list
     * @return
     */
    public static StationData getData(List<DataModel> list){
        StationData stationData = new StationData();
        for (int i = 0; i < list.size(); i++) {
            stationData.addData("",list.get(i).getLon(),list.get(i).getLat(),list.get(i).getValue());
        }
        return stationData;
    }
}
