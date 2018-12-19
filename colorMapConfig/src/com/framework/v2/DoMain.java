package com.framework.v2;

import com.framework.v1.ColorMapUtil;
import com.framework.v1.model.DataModel;
import com.framework.v1.model.MapTextModel;
import com.framework.v1.model.PolygonModel;
import com.framework.v1.model.PolylineModel;
import com.framework.v1.util.*;
import org.meteoinfo.data.DataTypes;
import org.meteoinfo.data.mapdata.AttributeTable;
import org.meteoinfo.data.mapdata.Field;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.global.colors.ColorMap;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.layout.MapLayout;
import org.meteoinfo.legend.*;
import org.meteoinfo.map.MapView;
import org.meteoinfo.map.MaskOut;
import org.meteoinfo.shape.*;
import org.meteoinfo.shape.Shape;
import org.meteoinfo.table.DataColumn;
import org.meteoinfo.table.DataColumnCollection;
import org.meteoinfo.table.DataRow;
import org.meteoinfo.table.DataTable;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoMain {

    public static int x = 100;
    public static int y = 100;
    public static int n = 10;
    public static int w = 700;
    public static int h = 500;
    public static Extent extent = new Extent(100,120,30,40);

    public static void doGridDataConfig(){
        String filePaht = "e:/tmp/demo.png";
        System.out.println(LocalDateTime.now() + " Grid Start");
        VectorLayer vectorLayer = LayerReaderUtil.readOutLayer("E:\\压缩包\\arcgisSHP\\四川省农气地图181023\\县边界.shp");
        VectorLayer rootLayer = LayerReaderUtil.readOutLayer("E:\\压缩包\\arcgisSHP\\四川省农气地图181023\\县边界.shp");

        List<Color> colorLists = new ArrayList<>();
        colorLists.add(Color.decode("#9EE07F"));//land
        colorLists.add(Color.decode("#A5CBF0"));//river
        MapLayout layout = new MapLayout();
        layout.setPageBounds(new Rectangle((int)(w*1), (int)(h*1)));
        MapView mapView = layout.getActiveMapFrame().getMapView();
        mapView.zoomToExtent(extent);
        mapView.setBounds(new Rectangle(w, h));

        for (ColorBreak colorBreak:vectorLayer.getLegendScheme().getLegendBreaks()) {
            colorBreak.setColor(Color.decode("#9EE07F"));//
        }

        extent = vectorLayer.getExtent();
        try {
            File file = new File(filePaht);
            if (!file.exists()){
                file.mkdirs();
            }
            mapView.setBackground(Color.decode("#A5CBF0"));
            mapView.addLayer(rootLayer);

            mapView.addLayer(vectorLayer);
            mapView.getMaskOut().setMask(true);
            mapView.getMaskOut().setMaskLayer(rootLayer.getLayerName());
            mapView.exportToPicture(filePaht);

            System.out.println(LocalDateTime.now() + " Custom Making2 Complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DoMain.doGridDataConfig();
    }

}
