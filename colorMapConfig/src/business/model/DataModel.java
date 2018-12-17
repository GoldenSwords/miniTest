package business.model;

import business.util.DataUtil;

import java.util.List;
import java.util.Map;

public class DataModel {

	String KeyLon;
	String KeyLat;
	String KeyValue;
	public DataModel(){

	}
	public DataModel(String lonK,String latK,String valueK){
		this.KeyLon = lonK;
		this.KeyLat = latK;
		this.KeyValue = valueK;
	}
	public String getKeyLon() {
		return KeyLon==null? DataUtil.lon:KeyLon;
	}

	public void setKeyLon(String keyLon) {
		KeyLon = keyLon;
	}

	public String getKeyLat() {
		return KeyLat==null? DataUtil.lat:KeyLat;
	}

	public void setKeyLat(String keyLat) {
		KeyLat = keyLat;
	}

	public String getKeyValue() {
		return KeyValue==null? DataUtil.value:KeyValue;
	}

	public void setKeyValue(String keyValue) {
		KeyValue = keyValue;
	}
}
