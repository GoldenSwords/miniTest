package com.framework.util;

import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.MapLayer;
import org.meteoinfo.shape.Polyline;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 格点数据配置类
 */
public class GridDataConfig {
    int x = 500;//横向格点数
    int y = 500;//纵向格点数
    int n = 10;//临近点数量
    int width = 500;//宽
    int height = 500;//高
    Extent extent;//区域
    String filePath;//生成图片路径
    List<Color> color = new ArrayList<>();//色标配置
    List<Double> value = new ArrayList<>();//色域配置
    MapLayer maskLayer;//过滤层
    List<Polyline> borderLine;//边界线
    boolean fullScreen;//是否全屏

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public GridDataConfig(int x, int y, int n, int width, int height, Extent extent, String filePath, List<Color> color, List<Double> value, MapLayer maskLayer, List<Polyline> borderLine, boolean fullScreen) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.width = width;
        this.height = height;
        this.extent = extent;
        this.filePath = filePath;
        this.color = color;
        this.value = value;
        this.maskLayer = maskLayer;
        this.borderLine = borderLine;
        this.fullScreen = fullScreen;
    }

    public List<Polyline> getBorderLine() {
        return borderLine;
    }

    public void setBorderLine(List<Polyline> borderLine) {
        this.borderLine = borderLine;
    }

    public Extent getExtent() {
        return extent;
    }

    public MapLayer getMaskLayer() {
        return maskLayer;
    }

    public void setMaskLayer(MapLayer maskLayer) {
        this.maskLayer = maskLayer;
    }

    public void setExtent(Extent extent) {
        this.extent = extent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }
}
