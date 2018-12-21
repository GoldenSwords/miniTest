package com.framework.v2.util;

import com.framework.v2.model.MapTextModel;
import com.framework.v2.model.PolygonModel;
import com.framework.v2.model.PolylineModel;
import org.meteoinfo.global.Extent;

import java.util.List;

public class PolygonConfig {
    Extent extent;
    int width = 500;
    int height = 500;
    String filePath;
    List<PolylineModel> polylines;
    List<PolygonModel> polygons;
    List<MapTextModel> texts;
    int offsetX;
    int offsetY;
    boolean fullScreen;
    boolean isDelete;

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public PolygonConfig(Extent extent, int width, int height, String filePath, List<PolylineModel> polylines, List<PolygonModel> polygons, List<MapTextModel> texts, int offsetX, int offsetY, boolean fullScreen,boolean isDelete) {
        this.extent = extent;
        this.isDelete = isDelete;
        this.width = width;
        this.height = height;
        this.filePath = filePath;
        this.polylines = polylines;
        this.polygons = polygons;
        this.texts = texts;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.fullScreen = fullScreen;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public Extent getExtent() {
        return extent;
    }

    public void setExtent(Extent extent) {
        this.extent = extent;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<PolylineModel> getPolylines() {
        return polylines;
    }

    public void setPolylines(List<PolylineModel> polylines) {
        this.polylines = polylines;
    }

    public List<PolygonModel> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<PolygonModel> polygons) {
        this.polygons = polygons;
    }

    public List<MapTextModel> getTexts() {
        return texts;
    }

    public void setTexts(List<MapTextModel> texts) {
        this.texts = texts;
    }
}
