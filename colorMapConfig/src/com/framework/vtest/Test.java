package com.framework.vtest;

import com.framework.v2.util.DataConverter;
import org.meteoinfo.data.GridData;
import org.meteoinfo.global.DataConvert;
import ucar.ma2.Array;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static GridData getData(String lonKey, String latKey, String valKey, List<Map<String,Object>> data){
        List<Double> xAxis = new ArrayList<>();
        List<Double> yAxis = new ArrayList<>();
        List<List<Double>> allData = new ArrayList<>();
        data.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return Double.valueOf(o1.get("lat").toString())-Double.valueOf(o2.get("lat").toString())<0?-1:
                        Double.valueOf(o1.get("lat").toString())-Double.valueOf(o2.get("lat").toString())==0&&Double.valueOf(o1.get("lon").toString())-Double.valueOf(o2.get("lon").toString())<0?-1:1;
            }
        });
        List<Double> lineData = new ArrayList<>();
        for (Map<String,Object> map:data) {
            if(!xAxis.contains(Double.valueOf(map.get(lonKey).toString()))){
                xAxis.add(Double.valueOf(map.get(lonKey).toString()));
                lineData.add(Double.valueOf(map.get(valKey).toString()));
            }else{
                allData.add(lineData);
                lineData = new ArrayList<>();
                xAxis = new ArrayList<>();
                xAxis.add(Double.valueOf(map.get(lonKey).toString()));
                lineData.add(Double.valueOf(map.get(valKey).toString()));
            }
            if(!yAxis.contains(Double.valueOf(map.get(latKey).toString()))){
                yAxis.add(Double.valueOf(map.get(latKey).toString()));
            }
        }
        allData.add(lineData);
        xAxis.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1-o2>=0?1:-1;
            }
        });
        yAxis.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1-o2>=0?1:-1;
            }
        });
        double[] xX = new double[xAxis.size()];
        double[] yY = new double[yAxis.size()];
        Double[] x = xAxis.toArray(new Double[0]);
        Double[] y = yAxis.toArray(new Double[0]);
        double[][] d = new double[y.length][x.length];
        for (int i = 0; i < allData.size(); i++) {
            double[] temp = new double[allData.get(i).size()];
            for (int j = 0; j < allData.get(i).size(); j++) {
                temp[j] = allData.get(i).get(j);
            }
            d[i] = temp;
        }
        for (int i = 0; i < x.length; i++) {
            xX[i] = x[i];
        }
        for (int i = 0; i < y.length; i++) {
            yY[i] = y[i];
        }
        GridData gridData = new GridData();
        gridData.data = d;
        gridData.xArray = xX;
        gridData.yArray = yY;
        return gridData;
    }
    public static void main(String[] args) throws Exception{
//        List<List<Double>> xAxis = new ArrayList<>();
//        List<Double> sd = new ArrayList<>();
//        sd.add(3d);
//        sd.add(6d);
//        sd.add(1d);
//        xAxis.add(sd);
//        xAxis.add(sd);
        List<Map<String,Object>> xAxis = new ArrayList<>();
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",10},new Object[]{"lat",10},new Object[]{"val",1010}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",20},new Object[]{"lat",20},new Object[]{"val",2020}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",30},new Object[]{"lat",30},new Object[]{"val",3030}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",10},new Object[]{"lat",30},new Object[]{"val",1030}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",30},new Object[]{"lat",20},new Object[]{"val",3020}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",20},new Object[]{"lat",30},new Object[]{"val",2030}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",30},new Object[]{"lat",10},new Object[]{"val",3010}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",20},new Object[]{"lat",10},new Object[]{"val",2010}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",10},new Object[]{"lat",20},new Object[]{"val",1020}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",10},new Object[]{"lat",40},new Object[]{"val",1040}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",20},new Object[]{"lat",40},new Object[]{"val",2040}));
        xAxis.add(DataConverter.instanceMap(new Object[]{"lon",30},new Object[]{"lat",40},new Object[]{"val",3040}));
//        xAxis.sort(new Comparator<Map<String, Object>>() {
//            @Override
//            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//
//                return Double.valueOf(o1.get("lat").toString())-Double.valueOf(o2.get("lat").toString())>0?-1:
//                        Double.valueOf(o1.get("lat").toString())-Double.valueOf(o2.get("lat").toString())==0&&Double.valueOf(o1.get("lon").toString())-Double.valueOf(o2.get("lon").toString())<0?-1:1;
//            }
//        });
//        Double[] s = sd.toArray(new Double[0]);
//        GridData data = getData("lon","lat","val",xAxis);
        String param = "111";
        String[] times = param.split(",");
        System.out.println("=========");
        for (int i = 0; i < times.length; i++) {
            System.out.println(times[i]);
        }
        System.out.println("=========");
    }
}
