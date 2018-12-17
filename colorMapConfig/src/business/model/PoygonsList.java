package business.model;

public class PoygonsList {
	Double[][] point;
	String color;

	public Double[][] getPoint() {
		return point;
	}

	public PoygonsList setPoint(Double[][] point) {
		this.point = point;
		return this;
	}

	public String getColor() {
		return color;
	}

	public PoygonsList setColor(String color) {
		this.color = color;
		return this;
	}
}
