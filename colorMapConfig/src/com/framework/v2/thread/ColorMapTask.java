package com.framework.v2.thread;

import com.framework.v2.ColorMapUtil;
import com.framework.v2.util.GridDataConfig;
import com.framework.v2.util.PolygonConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 色斑图线程
 */
public class ColorMapTask implements Runnable{
    private GridDataConfig gridDataConfig;
    private PolygonConfig polygonConfig;

    public ColorMapTask(GridDataConfig gridDataConfig) {
        this.gridDataConfig = gridDataConfig;
    }

    public ColorMapTask(PolygonConfig polygonConfig) {
        this.polygonConfig = polygonConfig;
    }

    @Override
    public synchronized void run() {
       try {
           if(gridDataConfig!=null){
               System.out.println(String.format("taskStart::%s  %s","GRID",LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
               System.out.println(ColorMapUtil.colorMap(gridDataConfig));
           }
           if(polygonConfig!=null){
               System.out.println(ColorMapUtil.colorMap(polygonConfig));
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
