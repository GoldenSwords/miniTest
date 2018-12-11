package business.model;

import org.meteoinfo.global.PointF;

import java.awt.*;

public class ArrayDataModel {
	PointF[] data;
	Color color;

	public PointF[] getData() {
		return data;
	}

	public ArrayDataModel setData(PointF[] data) {
		this.data = data;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public ArrayDataModel setColor(Color color) {
		this.color = color;
		return this;
	}
}
