package business.model;

//数据计算模型
public class ExtremumModel {

	double lat;//纬度
	double lon;//经度
	double value;//值
	ExtremumEnum type;//模型类型 MAX,MIN,AVG

	public double getLat() {
		return lat;
	}

	public ExtremumModel setLat(double lat) {
		this.lat = lat;
		return this;
	}

	public double getLon() {
		return lon;
	}

	public ExtremumModel setLon(double lon) {
		this.lon = lon;
		return this;
	}

	public double getValue() {
		return value;
	}

	public ExtremumModel setValue(double value) {
		this.value = value;
		return this;
	}

	public ExtremumEnum getType() {
		return type;
	}

	public ExtremumModel setType(ExtremumEnum type) {
		this.type = type;
		return this;
	}
}
