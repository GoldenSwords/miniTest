package business.util;

import business.model.DataModel;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.geoprocess.analysis.InterpolationMethods;
import org.meteoinfo.geoprocess.analysis.InterpolationSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {
	public static final String lon = "lon";
	public static final String lat = "lat";
	public static final String value = "value";

	public static List<Map<String, Object>> getData(DataModel dataModel, List<Map<String, Object>> data) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (Map<String, Object> map : data) {
			if(map.containsKey(dataModel.getKeyLat())&&
					map.containsKey(dataModel.getKeyLon())&&
					map.containsKey(dataModel.getKeyValue())){
				Map<String,Object> dMap = new HashMap<>();
				dMap.put(lon,map.get(dataModel.getKeyLon()));
				dMap.put(lat,map.get(dataModel.getKeyLat()));
				dMap.put(value,map.get(dataModel.getKeyValue()));
				list.add(dMap);
			}
		}
		return list;
	}

	public static StationData convertListToStationData(List<Map<String,Object>> list,DataModel dataModel){
		StationData stationData = new StationData();
		for (Map<String,Object> map:list) {
			if(dataModel!=null){
				if(map.containsKey(dataModel.getKeyLat())&&
						map.containsKey(dataModel.getKeyLon())&&
						map.containsKey(dataModel.getKeyValue())){
					if(isNumer(map.get(dataModel.getKeyLon()).toString())&&
							isNumer(map.get(dataModel.getKeyLat()).toString())&&
							isNumer(map.get(dataModel.getKeyValue()).toString())){
						stationData.addData("", Double.parseDouble(map.get(dataModel.getKeyLon()) + ""), Double.parseDouble(map.get(dataModel.getKeyLat()) + ""), Double.parseDouble(map.get(dataModel.getKeyValue()) + ""));
					}
				}
			}else{
				dataModel = new DataModel();
				if(isNumer(map.get(dataModel.getKeyLon()).toString())&&
						isNumer(map.get(dataModel.getKeyLat()).toString())&&
						isNumer(map.get(dataModel.getKeyValue()).toString())){
					stationData.addData("", Double.parseDouble(map.get(dataModel.getKeyLon()) + ""), Double.parseDouble(map.get(dataModel.getKeyLat()) + ""), Double.parseDouble(map.get(dataModel.getKeyValue()) + ""));
				}
			}
		}
		return stationData;
	}

	public static GridData convertStationDataToGridData(StationData stationData, String LayerPath, int xNumber, int yNumber, int nearNumber) {
		try {
			GridDataSetting gridDataSetting = new GridDataSetting();
			gridDataSetting.dataExtent = MeteoInfoUtil.getClipLayer(LayerPath).getExtent();
			gridDataSetting.xNum = xNumber;
			gridDataSetting.yNum = yNumber;
			InterpolationSetting interSet = new InterpolationSetting();
			interSet.setGridDataSetting(gridDataSetting);
			interSet.setInterpolationMethod(InterpolationMethods.IDW_Neighbors);
			if(nearNumber==-1){
				interSet.setMinPointNum(stationData.data.length);//临近点
			}else{
				interSet.setMinPointNum(nearNumber);//临近点
			}
			return stationData.interpolateData(interSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isNumer(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
