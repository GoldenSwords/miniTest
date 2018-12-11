package business.model;

public class GridNetModel {
    double xMin;
    double xMax;
    double yMin;
    double yMax;
    String color;

    public double getxMin() {
        return xMin;
    }

    public GridNetModel setxMin(double xMin) {
        this.xMin = xMin;
        return this;
    }

    public double getxMax() {
        return xMax;
    }

    public GridNetModel setxMax(double xMax) {
        this.xMax = xMax;
        return this;
    }

    public double getyMin() {
        return yMin;
    }

    public GridNetModel setyMin(double yMin) {
        this.yMin = yMin;
        return this;
    }

    public double getyMax() {
        return yMax;
    }

    public GridNetModel setyMax(double yMax) {
        this.yMax = yMax;
        return this;
    }

    public String getColor() {
        return color;
    }

    public GridNetModel setColor(String color) {
        this.color = color;
        return this;
    }
}
