package business.util;

import business.model.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.meteoinfo.data.DataTypes;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.mapdata.MapDataManage;
import org.meteoinfo.global.PointD;
import org.meteoinfo.layer.MapLayer;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.layout.LayoutLegend;
import org.meteoinfo.layout.LayoutMap;
import org.meteoinfo.layout.LegendStyles;
import org.meteoinfo.layout.MapLayout;
import org.meteoinfo.legend.*;
import org.meteoinfo.map.MapView;
import org.meteoinfo.shape.*;
import org.meteoinfo.shape.Polygon;

import javax.imageio.ImageIO;
import javax.print.PrintException;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 色斑图文件转换工具
 */
public class ConvertUtil {

	//自定义区域色斑图片输出
	public static JSONObject converter_ClipDataToPng(PrintModel printModel) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", false);
		jsonObject.put("msg", "PNG输出流程异常");
		List<PoygonsList> list = printModel.getClipsValue();
		MapLayout layout = new MapLayout();
		layout.setPageBounds(new Rectangle(0, 0, PrintEnum.PNG.getX(), PrintEnum.PNG.getY()));
		MapFrame mapFrame = layout.getActiveMapFrame();
		mapFrame.setLayoutBounds(new Rectangle(0, 0, PrintEnum.PNG.getX(), PrintEnum.PNG.getY()));
		mapFrame.setDrawNeatLine(false);
		mapFrame.setDrawGridTickLine(false);
		mapFrame.setDrawGridLine(false);
		mapFrame.setDrawGridLabel(false);
		MapView mapView = mapFrame.getMapView();
		mapView.setBounds(0, 0, PrintEnum.PNG.getX(), PrintEnum.PNG.getY());

		switch (printModel.getPrintTypeEnum()){
			case CUSTOM_MANY_CUT:
				JSONArray arr = new JSONArray();
				for (int z = 0; z < list.size(); z++) {
					VectorLayer vec = new VectorLayer(ShapeTypes.Polygon);
					PolygonShape pShape = new PolygonShape();
					List<PointD> clip = new ArrayList<>();
					for (Double[] f : list.get(z).getPoint()) {
						clip.add(new PointD(f[0], f[1]));
					}
					pShape.setPoints(clip);
					vec.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polygon, Color.decode(list.get(z).getColor()), 1));
					vec.addShape(pShape);
					vec.setMaskout(true);
					mapView.addLayer(vec);
					VectorLayer rootLayer = MeteoInfoUtil.getClipLayer(printModel.getLayerRootPath());
					mapView.zoomToExtent(rootLayer.getExtent());

					try {
						String fileName = printModel.getFileOutPath() + UUID.randomUUID().toString() + ".png";
						mapView.exportToPicture(fileName);
						JSONObject data = new JSONObject();
						data.put("data", Base64Util.ConvertImageToStr(fileName));
						data.put("extent",rootLayer.getExtent());
						data.put("colors",printModel.getColors());
						data.put("values",printModel.getValues());
						data.put("customValues",printModel.getCustomValues());
						arr.add(data);
					} catch (PrintException e) {
						e.printStackTrace();
						arr.add(new JSONObject());
					} catch (IOException e) {
						e.printStackTrace();
						arr.add(new JSONObject());
					}
				}
				if(arr.size()!=0){
					jsonObject.put("flag", true);
					jsonObject.put("msg", "PNG输出流程正常");
					jsonObject.put("data",arr);
				}
				break;
			case CUSTOM_SINGLE_CUT:
				VectorLayer rootLayer = MeteoInfoUtil.getClipLayer(printModel.getLayerRootPath());
				if(list!=null){
					List<Color> colorsList = new ArrayList<>();
					for (int z = 0; z < list.size(); z++) {
						VectorLayer vec = new VectorLayer(ShapeTypes.Polygon);
						PolygonShape pShape = new PolygonShape();
						List<PointD> clip = new ArrayList<>();
						for (Double[] f : list.get(z).getPoint()) {
							clip.add(new PointD(f[0], f[1]));
						}
						pShape.setPoints(clip);
						Color color = Color.decode(list.get(z).getColor().length()!=7?"#ffffff":list.get(z).getColor());
						if(!colorsList.contains(color)){
							colorsList.add(color);

						}
						pShape.setLegendIndex(colorsList.indexOf(color));
						vec.addShape(pShape);
						vec.setMaskout(true);
						vec.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polygon, color, 1));
						mapView.addLayer(vec);
					}
				}

				mapView.zoomToExtent(rootLayer.getExtent());
				if (printModel.isBorderSwitch()) {
					VectorLayer borderLayer = (VectorLayer) rootLayer.clone();
					borderLayer.setTransparency(100);
					List<ColorBreak> blist = borderLayer.getLegendScheme().getLegendBreaks();
					for (int i = 0; i < blist.size(); i++) {
						PolygonBreak aPGB = (PolygonBreak) blist.get(i);
						aPGB.setOutlineColor(Color.BLACK);
						aPGB.setOutlineSize(2);
					}
					mapView.addLayer(borderLayer);
				}

				if(printModel.getTextModels()!=null){
					List<TextModel> textModels = printModel.getTextModels();
					for (TextModel textModel:textModels) {
						layout.addText(textModel.getText(),
								(int)((textModel.getLon()-rootLayer.getExtent().minX)/(rootLayer.getExtent().maxX-rootLayer.getExtent().minX)*PrintEnum.PNG.getX())-textModel.getText().length(),
								(int)((1-(textModel.getLat()-rootLayer.getExtent().minY)/(rootLayer.getExtent().maxY-rootLayer.getExtent().minY))*PrintEnum.PNG.getY()),
								"黑体",
								40);
					}
				}

				if(printModel.getGrid()!=null){
					List<GridNetModel> grid = printModel.getGrid();
					for (GridNetModel gridNetModel:grid) {
						VectorLayer gridLayer = new VectorLayer(ShapeTypes.Polygon);
						PolygonShape py = new PolygonShape();
						List<PointD> clip = new ArrayList<>();
						clip.add(new PointD(gridNetModel.getxMin(),gridNetModel.getyMin()));
						clip.add(new PointD(gridNetModel.getxMax(),gridNetModel.getyMin()));
						clip.add(new PointD(gridNetModel.getxMax(),gridNetModel.getyMax()));
						clip.add(new PointD(gridNetModel.getxMin(),gridNetModel.getyMax()));
						clip.add(new PointD(gridNetModel.getxMin(),gridNetModel.getyMin()));
						py.setPoints(clip);
						if(gridNetModel.getColor()!=null){
							gridLayer.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polygon, new Color(Color.decode(gridNetModel.getColor()).getRed(),Color.decode(gridNetModel.getColor()).getGreen(),Color.decode(gridNetModel.getColor()).getBlue(),70), 1));
						}
						gridLayer.addShape(py);
						mapView.addLayer(gridLayer);
					}
				}
				try {
					String fileName = printModel.getFileOutPath() + printModel.getFileName() + ".png";
					if(layout!=null){
						layout.exportToPicture(fileName);
					}else{
						mapView.exportToPicture(fileName);
					}
					jsonObject.put("data", Base64Util.ConvertImageToStr(fileName));
					jsonObject.put("extent",rootLayer.getExtent());
					jsonObject.put("colors",printModel.getColors());
					jsonObject.put("values",printModel.getValues());
					jsonObject.put("customValues",printModel.getCustomValues());
					jsonObject.put("flag", true);
					jsonObject.put("msg", "PNG输出流程正常");
				} catch (PrintException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
		return jsonObject;
	}

//	private static void paintTitle(String path, PrintModel printModel) {
//		try {
//			int height = 0;
//			int part = 10;
//			if (printModel.isTitleSwitch() || printModel.isLengendSwitch()) {
//				BufferedImage image = ImageIO.read(new File(path));
//				if (printModel.isTitleSwitch()) {
//					if (printModel.getPngTitle() != null || printModel.getPngSubTitle() != null) {
//						String title = printModel.getPngTitle();
//						String subtitle = printModel.getPngSubTitle();
//						if (title != null) {
//							height += 70;
//						}
//						if (subtitle != null) {
//							height += 30;
//						}
//					}
//				}
//				BufferedImage bufferedImage = new BufferedImage(
//						image.getWidth(),
//						image.getHeight() + height,
//						BufferedImage.TYPE_INT_ARGB
//				);
//				Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
//				if (printModel.isTitleSwitch()) {
//					if (printModel.getPngTitle() != null || printModel.getPngSubTitle() != null) {
//						String title = printModel.getPngTitle();
//						String subtitle = printModel.getPngSubTitle();
//						graphics2D.setBackground(Color.WHITE);
//						graphics2D.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
//						graphics2D.setColor(Color.BLACK);
//						if (title != null) {
//							Font font = new Font("宋体", Font.BOLD, 40);
//							graphics2D.setFont(font);
//							FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
//							Rectangle2D stringBounds = font.getStringBounds(title, fontRenderContext);
//							graphics2D.drawString(title, (float) (bufferedImage.getWidth() - stringBounds.getWidth()) / 2, (float) 55);
//						}
//						if (subtitle != null) {
//							Font font = new Font("宋体", Font.BOLD, 20);
//							graphics2D.setFont(font);
//							FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
//							Rectangle2D stringBounds = font.getStringBounds(subtitle, fontRenderContext);
//							graphics2D.drawString(subtitle, (float) (bufferedImage.getWidth() - stringBounds.getWidth()) / 2, (float) 95);
//						}
//					}
//				}
//				ImageIcon imageIcon = new ImageIcon(image);
//				graphics2D.drawImage(image, 0, height, imageIcon.getImageObserver());
//				if (printModel.isLengendSwitch()) {
//					List<String> colors = printModel.getColors();
//					List<Double> values = printModel.getValues();
//					List<String> cvalues = printModel.getCustomValues();
//					String symbolTitle = printModel.getPngSymbolTitle();
//					int laberX = 100;
//					int laberY = 50;
//					int laberDistance = 10;
//					for (int i = 0; i < colors.size(); i++) {
//						graphics2D.setBackground(Color.decode(colors.get(i)));
//						graphics2D.setColor(Color.decode(colors.get(i)));
//						graphics2D.fillRect((int) (image.getWidth() * 0.8), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance)), laberX, laberY);
//						graphics2D.setColor(Color.gray);
//						graphics2D.drawRect((int) (image.getWidth() * 0.8), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance)), laberX, laberY);
//					}
//					// 抗锯齿
//					graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//					// 计算文字长度，计算居中的x点坐标
//					Font font = new Font("宋体", Font.PLAIN, laberY - 5);
//					graphics2D.setFont(font);
//					graphics2D.setColor(Color.BLACK);
//					FontMetrics fm = graphics2D.getFontMetrics(font);
//					// 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
//					if (symbolTitle != null) {
//						graphics2D.drawString(symbolTitle, (int) (image.getWidth() * 0.8 + laberX * 0.2), (int) (image.getHeight() * 0.5 - laberDistance * 3));
//					} else {
//						graphics2D.drawString("图例", (int) (image.getWidth() * 0.8 + laberX * 0.2), (int) (image.getHeight() * 0.5 - laberDistance * 3));
//					}
//					if(cvalues!=null){
//						for (int i = 0; i < cvalues.size(); i++) {
//							if (cvalues.size() < colors.size()) {
//								graphics2D.drawString(cvalues.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance + laberY / 2));
//							} else {
//								graphics2D.drawString(cvalues.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//							}
//						}
//					}else{
//						for (int i = 0; i < values.size(); i++) {
//							if (values.size() < colors.size()) {
//								if (Double.valueOf(values.get(0)) < Double.valueOf(values.get(values.size() - 1))) {
//									if (i == 0) {
//										graphics2D.drawString("< " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//									} else if (i == values.size() - 1) {
//										graphics2D.drawString(values.get(i - 1) + " - " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//										graphics2D.drawString("> " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + (i + 1) * (laberY + laberDistance) + laberY - laberDistance));
//									} else {
//										graphics2D.drawString(values.get(i - 1) + " - " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//									}
//								} else {
//									if (i == 0) {
//										graphics2D.drawString("> " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//									} else if (i == values.size() - 1) {
//										graphics2D.drawString(values.get(i) + " - " + values.get(i - 1), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//										graphics2D.drawString("< " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + (i + 1) * (laberY + laberDistance) + laberY - laberDistance));
//									} else {
//										graphics2D.drawString(values.get(i - 1) + " - " + values.get(i), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//									}
//								}
//							} else {
//								graphics2D.drawString(String.valueOf(values.get(i)), (int) (image.getWidth() * 0.8 + laberX + 5), (int) (image.getHeight() * 0.5 + i * (laberY + laberDistance) + laberY - laberDistance));
//							}
//						}
//					}
//				}
//				// 释放对象
//				graphics2D.dispose();
//				ImageIO.write(bufferedImage, "png", new File(path));
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 转换图层到图片
	 *
	 * @return
	 */
	public static JSONObject converter_LayerToPng(VectorLayer vectorLayer, PrintModel printModel) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", false);
		jsonObject.put("msg", "PNG输出流程异常");
			switch (printModel.getPrintTypeEnum()){
				case DATA_SINGLE_CUT:
					VectorLayer rootLayer = MeteoInfoUtil.getClipLayer(printModel.getLayerRootPath());
					VectorLayer poinLayer = new VectorLayer(ShapeTypes.Point);
					int width = PrintEnum.PNG.getX();
					int height = PrintEnum.PNG.getY();
					MapView mapView = null;
					MapLayout layout = null;
					String fileName = printModel.getFileOutPath() + printModel.getFileName() + ".png";//文件名
					switch (printModel.getPrint()){
						case PNG:
							mapView = new MapView();
							mapView.setBounds(0, 0, PrintEnum.PNG.getX(), PrintEnum.PNG	.getY());
							break;
						default:
							layout = new MapLayout();
							// 研究图片分辨率
							layout.setSize(width, height);
							layout.setPageBounds(new Rectangle(0, 0, width, height));
							// 设置内容边距
							layout.getActiveLayoutMap().setLeft((int) (width * 0.1));
							layout.getActiveLayoutMap().setTop((int) (height * 0.1));
							layout.getActiveLayoutMap().setWidth((int) (width * 0.8));
							layout.getActiveLayoutMap().setHeight((int) (height * 0.8));
//							layout.getActiveLayoutMap().setLeft((int) (width ));
//							layout.getActiveLayoutMap().setTop((int) (height));
//							layout.getActiveLayoutMap().setWidth((int) (width ));
//							layout.getActiveLayoutMap().setHeight((int) (height));
							MapFrame mapFrame = layout.getActiveLayoutMap().getMapFrame();
							int titleHeight = (int)(height*0.1);
							if(printModel.getPngTitle()!=null&&printModel.getPngSubTitle()!=null){
								layout.addText(printModel.getPngTitle(),(int)(width*0.5),(int)(titleHeight*0.333+35),"黑体",70);
								layout.addText(printModel.getPngSubTitle(),(int)(width*0.5),(int)(titleHeight*0.75+20),"黑体",40);
							}else if(printModel.getPngTitle()!=null){
								layout.addText(printModel.getPngTitle(),(int)(width*0.5),(int)(titleHeight*0.5+35),"黑体",70);
							}

							mapFrame.setDrawGridLabel(true);//经纬度标注
							mapFrame.setDrawGridLine(true);//网格线
							mapFrame.setDrawNeatLine(true);//经纬度网格边框线
							mapFrame.setActive(true);
							mapFrame.setGridFont(new Font("微软雅黑",0,30));
							double y = Math.round(((rootLayer.getExtent().maxY-rootLayer.getExtent().minY)/5)*10)/10.0;
							double x = Math.round((rootLayer.getExtent().maxX-rootLayer.getExtent().minX)/5*10)/10.0;
							if(y<0.1){y=0.1;}
							if(x<0.1){x=0.1;}
							mapFrame.setGridXDelt(x);
							mapFrame.setGridYDelt(y);
							mapFrame.setText("999");
							mapFrame.getMapView().setDefPointBreak(new PointBreak());

							LayoutLegend alegend = layout.addLegend((int)(layout.getWidth()*0.25), (int) (layout.getHeight() * 0.93));
							alegend.setLegendLayer(vectorLayer);
							alegend.setFont(new Font("黑体", Font.PLAIN, 15));
							alegend.setLegendStyle(LegendStyles.Bar_Horizontal);
							alegend.setWidth((int)(layout.getWidth()*0.5));
							alegend.setHeight(80);
							alegend.setFont(new Font("黑体",Font.PLAIN,30));
							mapView = layout.getActiveMapFrame().getMapView();
							mapView.setDrawGridLine(true);
							mapView.setDrawGridTickLine(true);
							mapView.setGridLineStyle(LineStyles.SOLID);
							break;
					}
					mapView.addLayer(rootLayer);
					mapView.addLayer(vectorLayer);

//					for (int i = 0; i < printModel.getData().size(); i++) {
//						PointShape pointShape = new PointShape();
//						pointShape.setPoint(new PointD(
//								Double.valueOf(printModel.getData().get(i).get("lon").toString()),
//								Double.valueOf(printModel.getData().get(i).get("lat").toString())
//						));
//						poinLayer.addShape(pointShape);
//						if(Double.valueOf(printModel.getData().get(i).get("lon").toString())>rootLayer.getExtent().maxX||
//								Double.valueOf(printModel.getData().get(i).get("lon").toString())<rootLayer.getExtent().minX||
//								Double.valueOf(printModel.getData().get(i).get("lat").toString())>rootLayer.getExtent().maxY||
//								Double.valueOf(printModel.getData().get(i).get("lat").toString())<rootLayer.getExtent().minY){
//
//						}else{
//							layout.addText(printModel.getData().get(i).get("value").toString(),
//									(int)(layout.getWidth()*0.1+(Double.valueOf(printModel.getData().get(i).get("lon").toString())-rootLayer.getExtent().minX)/(rootLayer.getExtent().maxX-rootLayer.getExtent().minX)*(printModel.getPrint().getX()-layout.getWidth()*0.2)),
//									(int)(layout.getHeight()*0.1+(rootLayer.getExtent().maxY-Double.valueOf(printModel.getData().get(i).get("lat").toString()))/(rootLayer.getExtent().maxY-rootLayer.getExtent().minY)*(printModel.getPrint().getY()-layout.getHeight()*0.2)),
//									"黑体",
//									10);
//						}
//					}
					mapView.addLayer(poinLayer);


					if (printModel.isBorderSwitch()) {
						VectorLayer borderLayer = (VectorLayer) rootLayer.clone();
						borderLayer.setTransparency(100);
						List<ColorBreak> list = borderLayer.getLegendScheme().getLegendBreaks();
						for (int i = 0; i < list.size(); i++) {
							PolygonBreak aPGB = (PolygonBreak) list.get(i);
							aPGB.setOutlineColor(Color.BLACK);
							aPGB.setOutlineSize(2);
						}
						mapView.addLayer(borderLayer);
					}
					vectorLayer.setMaskout(true);
					mapView.getMaskOut().setMask(true);
					mapView.getMaskOut().setMaskLayer(rootLayer.getLayerName());
					mapView.zoomToExtent(rootLayer.getExtent());

					if(printModel.getGrid()!=null){
						List<GridNetModel> grid = printModel.getGrid();
						for (GridNetModel gridNetModel:grid) {
							VectorLayer gridLayer = new VectorLayer(ShapeTypes.Polygon);
							PolygonShape py = new PolygonShape();
							List<PointD> clip = new ArrayList<>();
							clip.add(new PointD(gridNetModel.getxMin(),gridNetModel.getyMin()));
							clip.add(new PointD(gridNetModel.getxMax(),gridNetModel.getyMin()));
							clip.add(new PointD(gridNetModel.getxMax(),gridNetModel.getyMax()));
							clip.add(new PointD(gridNetModel.getxMin(),gridNetModel.getyMax()));
							clip.add(new PointD(gridNetModel.getxMin(),gridNetModel.getyMin()));
							py.setPoints(clip);
							if(gridNetModel.getColor()!=null){
								gridLayer.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Polygon, new Color(Color.decode(gridNetModel.getColor()).getRed(),Color.decode(gridNetModel.getColor()).getGreen(),Color.decode(gridNetModel.getColor()).getBlue(),70), 1));
							}
							gridLayer.addShape(py);
							mapView.addLayer(gridLayer);
						}
					}
					//绘制标题
					try {
						switch (printModel.getPrint()) {
							case PNG:
								mapView.exportToPicture(fileName);
								break;
							default:
								layout.exportToPicture(fileName);
								break;
						}
						jsonObject.put("data", Base64Util.ConvertImageToStr(fileName));
						jsonObject.put("extent",rootLayer.getExtent());
						jsonObject.put("colors",printModel.getColors());
						jsonObject.put("values",printModel.getValues());
						jsonObject.put("customValues",printModel.getCustomValues());
						jsonObject.put("flag", true);
						jsonObject.put("msg", "PNG输出流程正常");
					} catch (Exception e) {
						System.out.println("保存矢量图发生异常!{" + e.getMessage() + "}");
						jsonObject.put("flag", false);
						jsonObject.put("msg", e.getMessage());
					}
					break;
				case DATA_MANY_CUT:
					JSONArray arr = new JSONArray();
					for (int i = 0; i < printModel.getClipsValue().size(); i++) {
						fileName = printModel.getFileOutPath() + UUID.randomUUID().toString() + ".png";//文件名
						mapView = new MapView();
						mapView.setBounds(0, 0, PrintEnum.PNG.getX(), PrintEnum.PNG.getY());
						rootLayer = MeteoInfoUtil.getClipLayer(printModel.getLayerRootPath());
//						mapView.addLayer(rootLayer);
						VectorLayer vec = new VectorLayer(ShapeTypes.Polygon);
						PolygonShape pShape = new PolygonShape();
						List<PointD> clip = new ArrayList<>();
						for (Double[] f : printModel.getClipsValue().get(i).getPoint()) {
							clip.add(new PointD(f[0], f[1]));
						}
						pShape.setPoints(clip);
						vec.addShape(pShape);
						vec.setMaskout(true);
						mapView.addLayer(vec);
						mapView.addLayer(vectorLayer);
						vectorLayer.setMaskout(true);
						mapView.getMaskOut().setMask(true);
						mapView.getMaskOut().setMaskLayer(vec.getLayerName());
						mapView.zoomToExtent(rootLayer.getExtent());
						//绘制标题
						try {
							mapView.exportToPicture(fileName);
							JSONObject data = new JSONObject();
							data.put("data", Base64Util.ConvertImageToStr(fileName));
							data.put("extent",rootLayer.getExtent());
							data.put("colors",printModel.getColors());
							data.put("values",printModel.getValues());
							data.put("customValues",printModel.getCustomValues());
							data.put("flag", true);
							data.put("msg", "PNG输出流程正常");
							arr.add(data);
						} catch (Exception e) {
							System.out.println("保存矢量图发生异常!{" + e.getMessage() + "}");
							jsonObject.put("flag", false);
							jsonObject.put("msg", e.getMessage());
							arr.add(new JSONObject());
						}
					}
					if(arr.size()!=0){
						jsonObject.put("flag",true);
						jsonObject.put("msg", "PNG输出流程正常");
						jsonObject.put("data",arr);
					}
					break;
			}
		return jsonObject;
	}

	/**
	 * 标注数据
	 *
	 * @param clipLayer
	 * @param stationData
	 * @param dataTypeEnums STATION_DATA,STATION_TEXT 标注类型
	 * @return
	 */
	private static VectorLayer point(VectorLayer clipLayer, StationData stationData, DataTypeEnum[] dataTypeEnums) {
		VectorLayer aLayer = new VectorLayer(ShapeTypes.Point);
		if (dataTypeEnums != null) {
			try {
				boolean process = false;
				aLayer.setLayerName("New_Point_Layer");
				aLayer.setLegendScheme(LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Point, Color.black, 1));
				aLayer.setVisible(true);
				for (DataTypeEnum dataTypeEnum : dataTypeEnums) {
					switch (dataTypeEnum) {
						case STATION_DATA:
							aLayer.editAddField("DATA", DataTypes.String);
							process = true;
							break;
						case STATION_TEXT:
							aLayer.editAddField("NAME", DataTypes.String);
							process = true;
							break;
						default:
							break;
					}
				}
				if (process) {
					for (int i = 0; i < stationData.data.length; i++) {
						double[] aData = stationData.data[i];
						PointShape aPS = new PointShape();
						PointD aPoint = new PointD();
						aPoint.X = aData[0];
						aPoint.Y = aData[1];
						aPS.setPoint(aPoint);
						// 判断点是否在区域内
						if (!MeteoInfoUtil.isPolygonPoint(clipLayer, aPoint.X, aPoint.Y)) {
							continue;
						}
						int shapeNum = aLayer.getShapeNum();
						if (aLayer.editInsertShape(aPS, shapeNum)) {
							for (DataTypeEnum dataTypeEnum : dataTypeEnums) {
								switch (dataTypeEnum) {
									case STATION_DATA:
										aLayer.editCellValue("DATA", shapeNum, aData[2]);
										break;
									case STATION_TEXT:
//										aLayer.editCellValue("NAME", shapeNum, stationData.stationsName.get(i));
										break;
									default:
										break;
								}
							}
						}
					}
					for (DataTypeEnum dataTypeEnum : dataTypeEnums) {
						switch (dataTypeEnum) {
							case STATION_DATA:
								aLayer.getLabelSet().setFieldName("DATA");
								aLayer.getLabelSet().setYOffset(-10);
								aLayer.getLabelSet().setLabelFont(new Font("黑体", Font.PLAIN, 10));
								aLayer.addLabels();
								break;
							case STATION_TEXT:
								aLayer.getLabelSet().setFieldName("NAME");
								aLayer.getLabelSet().setYOffset(10);
								aLayer.getLabelSet().setLabelFont(new Font("黑体", Font.PLAIN, 10));
								aLayer.addLabels();
								break;
							default:
								break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return aLayer;
	}

	private static void setLegendShow(LegendScheme legendScheme, MapLayout layout) {
		LayoutLegend alegend = layout.addLegend((int) (layout.getActiveLayoutMap().getWidth() * 0.9), (int) (layout.getActiveLayoutMap().getHeight() * 0.6));
		MapLayer mapLayer = new MapLayer();
		mapLayer.setLegendScheme(legendScheme);
		alegend.setLegendLayer(mapLayer);
		alegend.setFont(new Font("宋体", Font.PLAIN, 15));
		alegend.setTitle("图例");
		alegend.setDrawPieLabel(true);
		alegend.setSelected(true);
		alegend.setDrawNeatLine(false);
		alegend.setDrawBackColor(true);
		alegend.setLegendStyle(LegendStyles.Normal);
	}

	/**
	 * 显示图例
	 *
	 * @param contourLayer
	 */
	private static void setLegendShow(VectorLayer contourLayer, MapLayout layout) {
		LayoutLegend alegend = layout.addLegend((int) (layout.getActiveLayoutMap().getWidth() * 0.9), (int) (layout.getActiveLayoutMap().getHeight() * 0.6));
		alegend.setLegendLayer(contourLayer);
		alegend.setFont(new Font("宋体", Font.PLAIN, 15));
//		alegend.setWidth((int) (layout.getWidth() * 0.1));
//		alegend.setHeight((int) (layout.getHeight() * 0.4));
		alegend.setDrawPieLabel(true);
		alegend.setSelected(true);
		alegend.setDrawNeatLine(false);
		alegend.setDrawBackColor(true);
		alegend.setLegendStyle(LegendStyles.Normal);
	}

	/**
	 * 转换KML到JSON
	 *
	 * @param fileIn 文件名
	 * @return
	 */
	public static JSONObject converter_KmlToJson(String fileIn, String fileOut, Map<String, Object> param) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", true);
		jsonObject.put("msg", "JSON输出流程正常");
		try {
			//dom解析
			//创建SAXReader对象
			SAXReader reader = new SAXReader();
			//读取文件 转换成Document
			Document document = reader.read(new File(fileIn));
			//获取根节点元素对象
			Element root = document.getRootElement();
			Element document1 = root.element("Document");
			Element folder = document1.element("Folder");
			//所有的色斑
			List placemark = folder.elements("Placemark");
			//稀释点的步长
			int step = 1;
			//
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < placemark.size(); i++) {
				JSONObject jsonObject1 = new JSONObject();

				//色斑
				Element element = (Element) placemark.get(i);
				//polygon
				Element polygon = element.element("Polygon");
				//外边框线
				Element elementOutLine = polygon.element("outerBoundaryIs").element("LinearRing").element("coordinates");
				String data = (String) elementOutLine.getData();
				JSONArray outlineArray = new JSONArray();
				String[] split = data.split(" ");

				for (int j = 0; j < split.length; j++) {
					if (!(j == 0 || j == (split.length - 1) || j % step == 0)) continue;

					String s = split[j];
					String[] split1 = s.split(",");
					if (split1.length != 2) continue;
					Double lon = Double.valueOf(split1[0]);
					Double lat = Double.valueOf(split1[1]);
					JSONArray lonLat = new JSONArray();
					lonLat.add(lon);
					lonLat.add(lat);
					outlineArray.add(lonLat);
				}
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("outline", outlineArray);
				//内环 可能有多个，也可能没有
				List innerBoundaryIs = polygon.elements("innerBoundaryIs");
				JSONArray innerArray = new JSONArray();
				for (int j = 0; j < innerBoundaryIs.size(); j++) {
					JSONArray innerArray1 = new JSONArray();
					Element inner = (Element) innerBoundaryIs.get(j);
					Element element1 = inner.element("LinearRing").element("coordinates");
					String data1 = (String) element1.getData();
					String[] split2 = data1.split(" ");

					for (int z = 0; z < split2.length; z++) {
						if (!(j == 0 || j == (split.length - 1) || j % step == 0)) continue;

						String s = split2[z];
						String[] split1 = s.split(",");
						if (split1.length != 2) continue;
						Double lon = Double.valueOf(split1[0]);
						Double lat = Double.valueOf(split1[1]);
						JSONArray lonLat = new JSONArray();
						lonLat.add(lon);
						lonLat.add(lat);
						innerArray1.add(lonLat);
					}
					innerArray.add(innerArray1);
				}
				jsonObject2.put("innerLine", innerArray);
				Element description = element.element("styleUrl");
				jsonObject2.put("style", description.getData() != null ? (description.getData().toString().replace("#pg", "")) : 0);

				jsonObject1.put("polygon", jsonObject2);
				jsonArray.add(jsonObject1);

			}
			jsonObject.put("data", jsonArray);
			if (param != null) {
				jsonObject.putAll(param);
			}
			File file = new File(fileOut);
			FileWriter fw = new FileWriter(file);
			fw.write("");
			fw.flush();
			fw.write(jsonObject.toString());
			fw.flush();
			fw.close();
			File kmlFile = new File(fileIn);
			if (kmlFile.exists()) {
				kmlFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("flag", false);
			jsonObject.put("msg", e.getMessage());
		}
		return jsonObject;
	}

}
