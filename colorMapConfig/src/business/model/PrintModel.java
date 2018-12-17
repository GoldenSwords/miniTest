package business.model;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//参数模型
public class PrintModel {
	PrintTypeEnum printTypeEnum;//绘制模型
	String fileName;//文件名称 必填

	String fileOutPath;//文件路径 必填
	String layerRootPath;//色斑图路径 必填
	List<GridNetModel> grid;//绘制网格横纵轴 [10,10]
	String pngTitle;//图片标题
	String pngSubTitle;//图片标题

//	String element;
	boolean fileSwitch;//保存文件开关
	boolean borderSwitch;//图片轮廓开关
//	boolean lengendSwitch;//色标绘制开关
//	boolean titleSwitch;//标题绘制开关
//	boolean dataCutSwitch;//数据单裁剪开关
//	boolean dataCutManySwitch;//数据多文件裁剪开关

	int x;int y;int n;
	List<TextModel> textModels;
	List<String> colors;
	List<Double> values;
	List<String> customValues;
//	String pngSymbolTitle;//图例标题

	List<PoygonsList> clipsValue;//裁剪值
//	List<Double[][]> clipsValue;//裁剪值
//	String clipsKey;//裁剪键

	List<Map<String,Object>> data;//数据集
	DataModel dataModel;//数据键值对

	PrintEnum print ;

	public List<TextModel> getTextModels() {
		return textModels;
	}

	public PrintModel setTextModels(List<TextModel> textModels) {
		this.textModels = textModels;
		return this;
	}

	public List<GridNetModel> getGrid() {
		return grid;
	}

	public PrintModel setGrid(List<GridNetModel> grid) {
		this.grid = grid;
		return this;
	}

	public int getX() {
		return x;
	}

	public PrintModel setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public PrintModel setY(int y) {
		this.y = y;
		return this;
	}

	public PrintTypeEnum getPrintTypeEnum() {
		return printTypeEnum;
	}

	public PrintModel setPrintTypeEnum(PrintTypeEnum printTypeEnum) {
		this.printTypeEnum = printTypeEnum;
		return this;
	}

	public int getN() {
		return n;
	}

	public PrintModel setN(int n) {
		this.n = n;
		return this;
	}

//	public String getElement() {
//		return element;
//	}
//
//	public PrintModel setElement(String element) {
//		this.element = element;
//		return this;
//	}

	public List<String> getCustomValues() {
		return customValues;
	}

	public PrintModel setCustomValues(List<String> customValues) {
		this.customValues = customValues;
		return this;
	}

//	public boolean isDataCutManySwitch() {
//		return dataCutManySwitch;
//	}
//
//	public PrintModel setDataCutManySwitch(boolean dataCutManySwitch) {
//		this.dataCutManySwitch = dataCutManySwitch;
//		return this;
//	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public PrintModel setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
		return this;
	}

	public String getFileName() {
		if(fileName==null){
			fileName = "colorMap";
		}
		return fileName;
	}

	public PrintModel setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public String getFileOutPath() {
		return fileOutPath;
	}

	public PrintModel setFileOutPath(String fileOutPath) {
		this.fileOutPath = fileOutPath;
		return this;
	}

	public String getLayerRootPath() {
		return layerRootPath;
	}

	public PrintModel setLayerRootPath(String layerRootPath) {
		this.layerRootPath = layerRootPath;
		return this;
	}

	public String getPngTitle() {
		return pngTitle;
	}

	public PrintModel setPngTitle(String pngTitle) {
		this.pngTitle = pngTitle;
		return this;
	}

	public String getPngSubTitle() {
		return pngSubTitle;
	}

	public PrintModel setPngSubTitle(String pngSubTitle) {
		this.pngSubTitle = pngSubTitle;
		return this;
	}

	public boolean isFileSwitch() {
		return fileSwitch;
	}

	public PrintModel setFileSwitch(boolean fileSwitch) {
		this.fileSwitch = fileSwitch;
		return this;
	}

	public boolean isBorderSwitch() {
		return borderSwitch;
	}

	public PrintModel setBorderSwitch(boolean borderSwitch) {
		this.borderSwitch = borderSwitch;
		return this;
	}

//	public boolean isLengendSwitch() {
//		return lengendSwitch;
//	}
//
//	public PrintModel setLengendSwitch(boolean lengendSwitch) {
//		this.lengendSwitch = lengendSwitch;
//		return this;
//	}
//
//	public boolean isTitleSwitch() {
//		return titleSwitch;
//	}
//
//	public PrintModel setTitleSwitch(boolean titleSwitch) {
//		this.titleSwitch = titleSwitch;
//		return this;
//	}

//	public boolean isDataCutSwitch() {
//		return dataCutSwitch;
//	}
//
//	public PrintModel setDataCutSwitch(boolean dataCutSwitch) {
//		this.dataCutSwitch = dataCutSwitch;
//		return this;
//	}

	public List<String> getColors() {
		return colors;
	}

	public PrintModel setColors(List<String> colors) {
		this.colors = colors;
		return this;
	}

	public List<Double> getValues() {
		return values;
	}

	public PrintModel setValues(List<Double> values) {
		this.values = values;
		return this;
	}

//	public String getPngSymbolTitle() {
//		return pngSymbolTitle;
//	}
//
//	public PrintModel setPngSymbolTitle(String pngSymbolTitle) {
//		this.pngSymbolTitle = pngSymbolTitle;
//		return this;
//	}

	public List<PoygonsList> getClipsValue() {
		return clipsValue;
	}

	public PrintModel setClipsValue(List<PoygonsList> clipsValue) {
		this.clipsValue = clipsValue;
		return this;
	}

//	public String getClipsKey() {
//		return clipsKey;
//	}
//
//	public PrintModel setClipsKey(String clipsKey) {
//		this.clipsKey = clipsKey;
//		return this;
//	}

	public List<Map<String, Object>> getData() {
		if(data==null){
			return new ArrayList<>();
		}
		return data;
	}

	public PrintModel setData(List<Map<String, Object>> data) {
		this.data = data;
		return this;
	}

	public PrintEnum getPrint() {
		if(print==null){
			return PrintEnum.PNG;
		}
		return print;
	}

	public PrintModel setPrint(PrintEnum print) {
		this.print = print;
		return this;
	}
}
