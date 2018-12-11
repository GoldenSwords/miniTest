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

    public static StationData getData(List<DataModel> list){
        StationData stationData = new StationData();
        for (int i = 0; i < list.size(); i++) {
            stationData.addData("",list.get(i).getLon(),list.get(i).getLat(),list.get(i).getValue());
        }
        return stationData;
    }
}
