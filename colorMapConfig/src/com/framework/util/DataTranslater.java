package com.framework.util;

import com.framework.model.DataModel;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;

import java.util.List;
import java.util.Map;

/**
 * 数据生成类
 */
public class DataTranslater {

    /**
     * listToStationData
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
