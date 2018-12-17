package business.util;

import business.model.*;
import business.util.ConvertUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sleepycat.je.utilint.StatGroup;
import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.mapdata.AttributeTable;
import org.meteoinfo.data.mapdata.MapDataManage;
import org.meteoinfo.data.meteodata.DrawMeteoData;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.geoprocess.analysis.InterpolationMethods;
import org.meteoinfo.geoprocess.analysis.InterpolationSetting;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.legend.ColorBreak;
import org.meteoinfo.legend.LegendManage;
import org.meteoinfo.legend.LegendScheme;
import org.meteoinfo.shape.PolygonShape;
import org.meteoinfo.shape.Shape;
import org.meteoinfo.shape.ShapeTypes;
import org.meteoinfo.table.ColumnData;
import org.meteoinfo.table.DataTable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * 色斑图工具类
 *
 * @author lh q 2432556082
 */
public class MeteoInfoUtil {
	private static final String kmlSuffix = ".kml";
	private static final String pngSuffix = ".png";
	private static final String jsonSuffix = ".json";

//	//实例化坐标轴
//	public static List<double[]> instanceAxis(String layer,int xNum,int yNum){
//		VectorLayer layers = getClipLayer(layer);
//		GridDataSetting gridDataSetting = new GridDataSetting();
//		gridDataSetting.dataExtent = layers.getExtent();
//		gridDataSetting.xNum = xNum;
//		gridDataSetting.yNum = yNum;
//		return new StationData().createGridXY(gridDataSetting);
//	}
//	//实例化坐标轴
//	public static List<double[]> instanceAxis(double minX,double maxX,double minY,double maxY,int xNum,int yNum){
//		double XDelt = (maxX - minX) / (double) (xNum - 1);
//		int Xnum = (int)((maxX - minX) / XDelt + 1.0D);
//		double[] X = new double[Xnum];
//		int i;
//		for(i = 0; i < Xnum; ++i) {
//			X[i] = minX + (double)i * XDelt;
//		}
//
//		double YDelt = (maxY - minY) / (double) (yNum - 1);
//		int Ynum = (int)((maxY - minY) / YDelt + 1.0D);
//		double[] Y = new double[Ynum];
//		int k;
//		for(k = 0; k < Ynum; ++k) {
//			Y[k] = minY + (double)k * YDelt;
//		}
//		List<double[]> values = new ArrayList();
//		values.add(X);
//		values.add(Y);
//		return values;
//	}

//	/**
//	 * 查询临近格点编码
//	 * @param xAxis
//	 * @param yAxis
//	 * @param lon
//	 * @param lat
//	 * @return
//	 */
//	public static Object[] findNearData(double[] xAxis, double[] yAxis, double lon,double lat) {
//		double temp = -1;
//		Object[] tempIndex = new Object[4];
//		for (int i = 0; i < xAxis.length; i++) {
//			if(temp==-1){
//				temp = (xAxis[i]-lon) * (xAxis[i]-lon);
//				tempIndex[0] = i;
//				tempIndex[2] = xAxis[i];
//			}else{
//				if(temp>(xAxis[i]-lon) * (xAxis[i]-lon)){
//					temp = (xAxis[i]-lon) * (xAxis[i]-lon);
//					tempIndex[0] = i;
//					tempIndex[2] = xAxis[i];
//				}
//			}
//		}
//		temp = -1;
//		for (int i = 0; i < yAxis.length; i++) {
//			if(temp==-1){
//				temp = (yAxis[i]-lat) * (yAxis[i]-lat);
//				tempIndex[1] = i;
//				tempIndex[3] = yAxis[i];
//			}else{
//				if(temp>(yAxis[i]-lat) * (yAxis[i]-lat)){
//					temp = (yAxis[i]-lat) * (yAxis[i]-lat);
//					tempIndex[1] = i;
//					tempIndex[3] = yAxis[i];
//				}
//			}
//		}
//		return tempIndex;
//	}
//
//	/**
//	 * 查询临近点数据
//	 *
//	 * @param lon
//	 * @param lat
//	 * @param gridData 数据组
//	 * @return
//	 */
//	public static Object[] findNearData(double lon, double lat, GridData gridData) {
//		double temp = -1;
//		Object[] tempIndex = new Object[5];
//		double[] xAxis = gridData.xArray;
//		double[] yAxis = gridData.yArray;
//
//		for (int i = 0; i < xAxis.length; i++) {
//			if(temp==-1){
//				temp = (xAxis[i]-lon) * (xAxis[i]-lon);
//				tempIndex[0] = i;
//				tempIndex[2] = xAxis[i];
//			}else{
//				if(temp>(xAxis[i]-lon) * (xAxis[i]-lon)){
//					temp = (xAxis[i]-lon) * (xAxis[i]-lon);
//					tempIndex[0] = i;
//					tempIndex[2] = xAxis[i];
//				}
//			}
//		}
//		temp = -1;
//		for (int i = 0; i < yAxis.length; i++) {
//			if(temp==-1){
//				temp = (yAxis[i]-lat) * (yAxis[i]-lat);
//				tempIndex[1] = i;
//				tempIndex[3] = yAxis[i];
//			}else{
//				if(temp>(yAxis[i]-lat) * (yAxis[i]-lat)){
//					temp = (yAxis[i]-lat) * (yAxis[i]-lat);
//					tempIndex[1] = i;
//					tempIndex[3] = yAxis[i];
//				}
//			}
//		}
//		tempIndex[4] = gridData.data[Integer.valueOf(tempIndex[1]+"")][Integer.valueOf(tempIndex[0]+"")];
//		return tempIndex;
//	}

//	/**
//	 * 数据极值计算
//	 *
//	 * @param stationData
//	 * @param extremumEnums MAX,MIN,AVG
//	 * @return
//	 */
//	public static List<ExtremumModel> DataExtremum(StationData stationData, List<ExtremumEnum> extremumEnums) {
//		List<ExtremumModel> list = new ArrayList<>();
//		double[][] value = stationData.data;
//		ExtremumModel maxModel = new ExtremumModel();
//		ExtremumModel minModel = new ExtremumModel();
//		ExtremumModel avgModel = new ExtremumModel();
//		double avg = 0;
//		int avgIndex = 0;
//		for (int j = 0; j < value.length; j++) {
//			if (extremumEnums.contains(ExtremumEnum.MAX)) {
//				if (value[j][2] == stationData.getMaxValue()) {
//					maxModel.setLat(value[j][1]).setLon(value[j][0]).setValue(value[j][2]).setType(ExtremumEnum.MAX);
//				}
//			}
//			if (extremumEnums.contains(ExtremumEnum.MIN)) {
//				if (value[j][2] == stationData.getMinValue()) {
//					minModel.setLat(value[j][1]).setLon(value[j][0]).setValue(value[j][2]).setType(ExtremumEnum.MIN);
//				}
//			}
//			if (extremumEnums.contains(ExtremumEnum.AVG)) {
//				if (value[j][2] != stationData.missingValue) {
//					avg += value[j][2];
//					avgIndex++;
//				}
//			}
//		}
//		if (extremumEnums.contains(ExtremumEnum.MAX)) {
//			list.add(maxModel);
//		}
//		if (extremumEnums.contains(ExtremumEnum.MIN)) {
//			list.add(minModel);
//		}
//		if (extremumEnums.contains(ExtremumEnum.AVG)) {
//			avgModel.setType(ExtremumEnum.AVG).setValue(avg / avgIndex);
//			list.add(avgModel);
//		}
//		return list;
//	}
//
//	/**
//	 * 数据极值计算
//	 *
//	 * @param gridData
//	 * @param extremumEnums MAX,MIN,AVG
//	 * @return
//	 */
//	public static List<ExtremumModel> DataExtremum(GridData gridData, List<ExtremumEnum> extremumEnums) {
//		List<ExtremumModel> list = new ArrayList<>();
//		double[] x = gridData.xArray;
//		double[] y = gridData.yArray;
//		double[][] value = gridData.data;
//		ExtremumModel maxModel = new ExtremumModel();
//		ExtremumModel minModel = new ExtremumModel();
//		ExtremumModel avgModel = new ExtremumModel();
//		double avg = 0;
//		int avgIndex = 0;
//		for (int i = 0; i < x.length; i++) {
//			for (int j = 0; j < y.length; j++) {
//				if (extremumEnums.contains(ExtremumEnum.MAX)) {
//					if (value[i][j] == gridData.getMaxValue()) {
//						maxModel.setLat(y[j]).setLon(x[i]).setValue(value[i][j]).setType(ExtremumEnum.MAX);
//					}
//				}
//				if (extremumEnums.contains(ExtremumEnum.MIN)) {
//					if (value[i][j] == gridData.getMinValue()) {
//						minModel.setLat(y[j]).setLon(x[i]).setValue(value[i][j]).setType(ExtremumEnum.MIN);
//					}
//				}
//				if (extremumEnums.contains(ExtremumEnum.AVG)) {
//					if (value[i][j] != gridData.missingValue) {
//						avg += value[i][j];
//						avgIndex++;
//					}
//				}
//			}
//		}
//		if (extremumEnums.contains(ExtremumEnum.MAX)) {
//			list.add(maxModel);
//		}
//		if (extremumEnums.contains(ExtremumEnum.MIN)) {
//			list.add(minModel);
//		}
//		if (extremumEnums.contains(ExtremumEnum.AVG)) {
//			avgModel.setType(ExtremumEnum.AVG).setValue(avg / avgIndex);
//			list.add(avgModel);
//		}
//		return list;
//	}

	/**
	 * 数据插值
	 *
	 * @return
	 */
	public static JSONObject colorMap(PrintModel printModel) {
		JSONObject json = new JSONObject();
		StationData stationData = null;
		GridData gridData = null;
		switch (printModel.getPrintTypeEnum()){
			case DATA_MANY_CUT:
				stationData = DataUtil.convertListToStationData(printModel.getData(),printModel.getDataModel());
				gridData = DataUtil.convertStationDataToGridData(stationData,printModel.getLayerRootPath(),printModel.getX(),printModel.getY(),printModel.getN());
				json = ConvertUtil.converter_LayerToPng( getLegendScheme(gridData,printModel),printModel);
				break;
			case DATA_SINGLE_CUT:
				stationData = DataUtil.convertListToStationData(printModel.getData(),printModel.getDataModel());
				gridData = DataUtil.convertStationDataToGridData(stationData,printModel.getLayerRootPath(),printModel.getX(),printModel.getY(),printModel.getN());
				json = ConvertUtil.converter_LayerToPng( getLegendScheme(gridData,printModel),printModel);
				break;
			case CUSTOM_MANY_CUT:
				json = ConvertUtil.converter_ClipDataToPng(printModel);
				break;
			case CUSTOM_SINGLE_CUT:
				json = ConvertUtil.converter_ClipDataToPng(printModel);
				break;
		}
		clearFile(printModel);
		return json;
	}

	private static VectorLayer getLegendScheme(GridData gridData,PrintModel printModel) {
		LegendScheme als = null;
		if(printModel.getColors()!=null&&printModel.getValues()!=null){
			Double[] values = printModel.getValues().toArray(new Double[]{});
			String[] colors = printModel.getColors().toArray(new String[]{});
			Color[] color = new Color[colors.length];
			for (int i = 0; i < colors.length; i++) {
				color[i] = Color.decode(colors[i]);
			}
			if(values.length==0){
				values = getValues(gridData.getMaxValue(),gridData.getMinValue(),colors.length-1,0);
			}
			double[] value = new double[values.length];
			for (int i = 0; i < values.length; i++) {
				value[i]=values[i].doubleValue();
			}
			als = LegendManage.createGraduatedLegendScheme(value, color, ShapeTypes.Polygon, gridData.getMinValue(), gridData.getMaxValue());
		}else{
			als = LegendManage.createLegendScheme(gridData.getMinValue(), gridData.getMaxValue());
			List<String> colors = new ArrayList<>();
			List<Double> values = new ArrayList<>();
			for (Color color:als.getColors()) {
				colors.add(toHexFromColor(color));
			}
			List<ColorBreak> colorBreaks = als.getLegendBreaks();
			for (int i = 0; i < colorBreaks.size(); i++) {
				if(i!=als.getLegendBreaks().size()-1){
					String vv = colorBreaks.get(i).getEndValue().toString();
					if(vv.substring(vv.length()-2,vv.length()).equals(".0")){
						values.add(Double.valueOf(vv.substring(0,vv.length()-2)));
					}else if(vv.substring(vv.length()-1,vv.length()).equals(".0")){
						values.add(Double.valueOf(vv.substring(0,vv.length()-1)));
					}else{
						values.add(Double.valueOf(vv));
					}
				}
			}
			printModel.setColors(colors).setValues(values);
		}
		return DrawMeteoData.createShadedLayer(gridData, als, "", "Data", true);
	}

	private static String toHexFromColor(Color color){
		String r,g,b;
		StringBuilder su = new StringBuilder();
		r = Integer.toHexString(color.getRed());
		g = Integer.toHexString(color.getGreen());
		b = Integer.toHexString(color.getBlue());
		r = r.length() == 1 ? "0" + r : r;
		g = g.length() ==1 ? "0" +g : g;
		b = b.length() == 1 ? "0" + b : b;
		r = r.toUpperCase();
		g = g.toUpperCase();
		b = b.toUpperCase();
		su.append("#");
		su.append(r);
		su.append(g);
		su.append(b);
		//0xFF0000FF
		return su.toString();
	}

	/**
	 * 获取图层
	 *
	 * @param layerEnums 图层路径
	 * @return
	 */
	public static VectorLayer getClipLayer(String layerEnums) {
		try {
			return MapDataManage.readMapFile_ShapeFile(layerEnums);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	private static int getIndexByColumns(PrintModel model,String key,String value) throws Exception{
//		VectorLayer vectorLayer = getClipLayer(model.getLayerRoot());
//		AttributeTable attributeTable = vectorLayer.getAttributeTable();
//		DataTable dataTable = attributeTable.getTable();
//		ColumnData columnData = dataTable.getColumnData(key);
//		List<String> dataString = columnData.getDataStrings();
//		int index = 0;
//		for (int j = 0; j < dataString.size(); j++) {
//			if(value.equals(dataString.get(j))){
//				index = j;
//			}
//		}
//		return index;
//	}

	public static Double[] getValues(double max, double min, int number, int limit) {
		Double[] arr = new Double[number];
		double decimal = 0;
		if (formatDecimal((max - min) / (number + 1), limit).equals("0")) {
			decimal = 0.1;
		} else {
			decimal = Double.valueOf(formatDecimal((max - min) / (number + 1), limit));
		}
		if (decimal == 0) {
			decimal = 1;
		}
		for (int i = 0; i < number ; i++) {
			arr[i]=Double.valueOf(formatDecimal((i == 0 ? (min + decimal) : (arr[i - 1] + decimal)), limit));
		}
		return arr;
	}

	/**
	 * 按四舍五入保留指定小数位数，小数点后仅保留有效位数
	 *
	 * @param d        格式化前的小数
	 * @param newScale 保留小数位数
	 * @return 格式化后的小数
	 */
	public static String formatDecimal(double d, int newScale) {
		String pattern = "#.";
		for (int i = 0; i < newScale; i++) {
			pattern += "#";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(d);
	}

	private static void clearFile( PrintModel printModel){
		if (printModel.isFileSwitch()) {
			File file = new File(printModel.getFileOutPath() + printModel.getFileName() + pngSuffix);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * getBorder
	 */
	public static List<Point2D.Double> getBorder(VectorLayer clipLayer) {
		// 获取当前裁剪的polygon
		List<Object> list1 = new ArrayList<Object>();// .getPoints();
		List<? extends Shape> shape = clipLayer.getShapes();// .getShape(0);
		for (int i = 0; i < shape.size(); i++) {
			list1.addAll(shape.get(i).getPoints());
		}
		List<Point2D.Double> list2 = new ArrayList<Point2D.Double>();

		for (Object l : list1) {
			PointD o = (PointD) l;
			Point2D.Double pd = new Point2D.Double();
			pd.x = o.X;
			pd.y = o.Y;
			list2.add(pd);
		}
		return list2;
	}
	/**
	 * 数据裁剪[数据是否在图层区域内]
	 *
	 * @param polygon
	 * @param list      必须包含 lon,lat
	 * @return
	 */
	public static List<Map<String, Object>> isPolygonPoint(List<Point2D.Double> polygon, List<Map<String, Object>> list) {
		//绘制区域
		java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
		Point2D.Double first = polygon.get(0);
		p.moveTo(first.x, first.y);
		for (Point2D.Double d : polygon) {
			p.lineTo(d.x, d.y);
		}
		p.lineTo(first.x, first.y);
		p.closePath();
		for (int i = 0; i < list.size(); i++) {
			if (!p.contains(new Point2D.Double(Double.valueOf(list.get(i).get("lon") + ""), Double.valueOf(list.get(i).get("lat") + "")))) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}
	/**
	 * 数据裁剪[数据是否在图层区域内]
	 *
	 * @param clipLayer
	 * @param list      必须包含 lon,lat
	 * @return
	 */
	public static List<Map<String, Object>> isPolygonPoint(VectorLayer clipLayer, List<Map<String, Object>> list) {
		List<Point2D.Double> polygon = getBorder(clipLayer);
		//绘制区域
		java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
		Point2D.Double first = polygon.get(0);
		p.moveTo(first.x, first.y);
		for (Point2D.Double d : polygon) {
			p.lineTo(d.x, d.y);
		}
		p.lineTo(first.x, first.y);
		p.closePath();
		for (int i = 0; i < list.size(); i++) {
			if (!p.contains(new Point2D.Double(Double.valueOf(list.get(i).get("lon") + ""), Double.valueOf(list.get(i).get("lat") + "")))) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}

	/**
	 * 裁剪点位[点位是否在图层区域内]
	 *
	 * @param clipLayer
	 * @param lon
	 * @param lat
	 * @return
	 */
	public static boolean isPolygonPoint(VectorLayer clipLayer, Double lon, Double lat) {
		List<Point2D.Double> polygon = getBorder(clipLayer);
		if (polygon == null || polygon.size() == 0 || lon == null || lat == null)
			return false;
		java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
		Point2D.Double first = polygon.get(0);
		p.moveTo(first.x, first.y);
		for (Point2D.Double d : polygon) {
			p.lineTo(d.x, d.y);
		}
		p.lineTo(first.x, first.y);
		p.closePath();
		return p.contains(new Point2D.Double(lon, lat));
	}
}
