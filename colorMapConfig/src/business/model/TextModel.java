package business.model;

public class TextModel {
    double lon;
    double lat;
    String text;

    public double getLon() {
        return lon;
    }

    public TextModel setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public TextModel setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public String getText() {
        return text;
    }

    public TextModel setText(String text) {
        this.text = text;
        return this;
    }
}
