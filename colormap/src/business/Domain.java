package business;

import business.model.*;
import business.util.MeteoInfoUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

//色斑图测试
public class Domain {
	public static List<PoygonsList> PointFf() {
		Double[][] r1 = new Double[][]{
				new Double[]{121.47577457220581d, 30.787776949219619d},
				new Double[]{121.47513184420842d, 30.786984829495111d},
				new Double[]{121.47403315667356d, 30.787038580277624d},
				new Double[]{121.47015710455497d, 30.785550909135793d},
				new Double[]{121.46841568755286d, 30.784705477771922d},
				new Double[]{121.46445664120841d, 30.783347536954523d},
				new Double[]{121.45878803135457d, 30.78109269928575d},
				new Double[]{121.45844694937392d, 30.781635070816776d},
				new Double[]{121.45026087285879d, 30.794650218960214d},
				new Double[]{121.4656605852702d, 30.799070692580415d},
				new Double[]{121.4752818846793d, 30.801751829933096d},
				new Double[]{121.48389966859583d, 30.804887667667117d},
				new Double[]{121.48414970938182d, 30.804131601468498d},
				new Double[]{121.4848810000754d, 30.801995421345566d},
				new Double[]{121.48705150480265d, 30.794551006807183d},
				new Double[]{121.4873169047317d, 30.79445702253139d},
				new Double[]{121.48426735928587d, 30.792852009264038d},
				new Double[]{121.48429439789362d, 30.792259780463269d},
				new Double[]{121.48106056582486d, 30.790456880440559d},
				new Double[]{121.47577457220581d, 30.787776949219619d}
		};
		Double[][] r2 = new Double[][]{
				new Double[]{121.45192289103255d, 30.815964494003651d},
				new Double[]{121.45213168829292d, 30.815179779749712d},
				new Double[]{121.45389311289892d, 30.815677826722265d},
				new Double[]{121.45520993891779d, 30.814236537314969d},
				new Double[]{121.45580864972015d, 30.814322265102192d},
				new Double[]{121.45648677655055d, 30.813165950917835d},
				new Double[]{121.45632718469318d, 30.811976213367927d},
				new Double[]{121.45291158409435d, 30.809146748398575d},
				new Double[]{121.4536262172108d, 30.808580024825119d},
				new Double[]{121.45773748673827d, 30.810672907752519d},
				new Double[]{121.46105635684324d, 30.805928536754379d},
				new Double[]{121.461511900076d, 30.805587592137499d},
				new Double[]{121.4647222812585d, 30.800918889034619d},
				new Double[]{121.4656605852702d, 30.799070692580415d},
				new Double[]{121.45026087285879d, 30.794650218960214d},
				new Double[]{121.45062990626349d, 30.794909918054259d},
				new Double[]{121.44540248834807d, 30.803297252136076d},
				new Double[]{121.44509446428134d, 30.803326104229768d},
				new Double[]{121.44235505529815d, 30.807816513350247d},
				new Double[]{121.44077922375487d, 30.807291828079542d},
				new Double[]{121.43928591245339d, 30.808503389894327d},
				new Double[]{121.43867547448082d, 30.808796096050173d},
				new Double[]{121.44189939413053d, 30.810870534325034d},
				new Double[]{121.44409641166209d, 30.811632169673942d},
				new Double[]{121.44206717561184d, 30.812016093526495d},
				new Double[]{121.44208180765355d, 30.812942442478914d},
				new Double[]{121.44284564968831d, 30.813353877115446d},
				new Double[]{121.44482495923052d, 30.81349128556559d},
				new Double[]{121.44596338619709d, 30.813236183729941d},
				new Double[]{121.44617992247316d, 30.814374527984739d},
				new Double[]{121.45192289103255d, 30.815964494003651d}
		};
//		List<ArrayDataModel> list = new ArrayList<>();
//		list.add(new ArrayDataModel().setColor(Color.decode("#E2B7B4")).setData(r1));
//		list.add(new ArrayDataModel().setColor(Color.decode("#D8FD68")).setData(r2));
//		List<Double[][]> list = new ArrayList<>();
		List<PoygonsList> list = new ArrayList<>();
		PoygonsList p1 = new PoygonsList();
		PoygonsList p2 = new PoygonsList();
		p1.setPoint(r1);
		p2.setPoint(r2);
		p1.setColor("#2D24CE");
		p2.setColor("#E75E0C");
		list.add(p1);
		list.add(p2);
		return list;
	}

	public static JSONArray ff(){
		String qf = "[\n" +
				"\t\n" +
				"{\"data\":[[[0.0009349578826437099,0.00027534113193771116],[104.07803307064592,30.649035750594674],[104.07598908399825,30.649813029999603],\n" +
				"[104.07597810466473,30.64981630522736],[104.07687640399831,30.65191984599964],[104.07694318790777,30.652016612186557],\n" +
				"[104.07903540905501,30.650834602030674]]],\"color\":\"#dd1be5\"},\t\n" +
				"{\"data\":[[[104.10011517053857,30.554799134374367],[104.10002616799831,30.560037589999574],[104.10300215099839,30.56056406599964],\n" +
				"[104.10356593479122,30.560662112293176],[104.1035076149983,30.560156506999526],[104.10377804949445,30.55918900063529],\n" +
				"[104.10370026999836,30.557594943999607],[104.10362340099829,30.55739284899971],[104.10316436799843,30.556670257999645],\n" +
				"[104.1032531400884,30.55481669255931],[104.10011517053857,30.554799134374367]]],\"color\":\"#2f9a2\"},\t\n" +
				"{\"data\":[[[104.13032414012167,30.557738748041903],[104.1351415881547,30.558434435721587],[104.13521171099838,30.557626628999504],\n" +
				"[104.13585239799835,30.556806726999522],[104.13650114696509,30.55508884621274],[104.13144347207681,30.55305703492269],\n" +
				"[104.1311136039983,30.553863971999636],[104.13090409799838,30.554004321999496],[104.13077124705626,30.553984397043283],\n" +
				"[104.13054218499838,30.55512894199967],[104.13039312399837,30.55734036599949],[104.13032414012167,30.557738748041903]]],\"color\":\"#bb6f9a\"},\t\n" +
				"{\"data\":[[[104.17336360266883,30.582265032236414],[104.16984580904688,30.576086692722114],[104.16347585199833,30.57850370199963],\n" +
				"[104.16842259599832,30.58106231999958],[104.17125861099841,30.58379623499956],[104.17151219419982,30.584174893096456],\n" +
				"[104.17336360266883,30.582265032236414]]],\"color\":\"#6625b9\"},\t\n" +
				"{\"data\":[[[104.17718560399828,30.592646599999565],[104.1816979459984,30.59786409899948],[104.18274987910524,30.59875025247225],\n" +
				"[104.17865186298292,30.591552861817533],[104.17718560399828,30.592646599999565]]],\"color\":\"#cae043\"},\t\n" +
				"{\"data\":[[[104.14140460099834,30.607387751999564],[104.13733099399832,30.609484986999448],[104.13726938647426,30.609494802720338],\n" +
				"[104.1384913909983,30.611330189999627],[104.13849985190211,30.611339957316357],[104.14278980601779,30.608756272928012],\n" +
				"[104.14278271999837,30.608748044999622],[104.14140460099834,30.607387751999564]]],\"color\":\"#b50db5\"},\t\n" +
				"{\"data\":[[[104.14042019769671,30.58304049212471],[104.13831481102513,30.58165742102317],[104.13824467199822,30.581738048999622],\n" +
				"[104.13753856499837,30.58276323499956],[104.13716150499842,30.583703843999665],[104.1371088169983,30.584003044999626],\n" +
				"[104.13808154799841,30.584112526999597],[104.13823532299824,30.58416199399954],[104.1383654489984,30.58427858599958],\n" +
				"[104.13871057499837,30.584770478999538],[104.13882933799829,30.584872501999538],[104.13899291587633,30.585030609944727],\n" +
				"[104.14042019769671,30.58304049212471]]],\"color\":\"#b001f7\"},\t{\"data\":[[[104.1043888967007,30.573795436898077],[104.10867442077337,30.574128750512493],\n" +
				"[104.10881197657903,30.570544651858366],[104.10488021384982,30.57077590603949],[104.1043888967007,30.573795436898077]]],\"color\":\"#3d1edb\"},\t\n" +
				"{\"data\":[[[104.02646503999836,30.549075002999633],[104.02650715799842,30.54456766899955],[104.02650751470811,30.54456156943814],\n" +
				"[104.02195104699831,30.544531396999556],[104.0212923293936,30.5441389361295],[104.0204673499984,30.545368974999572],\n" +
				"[104.01991526638473,30.54663000753555],[104.02250132571267,30.550923054010685],[104.02646503999836,30.549075002999633]]],\"color\":\"#d4f87b\"},\t\n" +
				"{\"data\":[[[104.00809548616367,30.560077286707546],[104.00277448799835,30.556579845999625],[104.00190606499837,30.55618072799967],\n" +
				"[103.9990682439983,30.555444861999536],[103.99776122599837,30.55515631399964],[103.99773838638282,30.55516927745804],\n" +
				"[103.99775458099845,30.555697226999538],[103.99694539899843,30.556550472999604],[103.9954580799984,30.557075900999546],\n" +
				"[103.99442142199842,30.557753262999636],[103.99405797999823,30.559526470999618],[103.99295094899834,30.559969090999513],\n" +
				"[103.99170344199844,30.56027560699965],[103.99007899999833,30.560554264999613],[103.98952788599837,30.561370514999616],\n" +
				"[103.99000643399836,30.56260949599958],[103.99098922999832,30.563037717999627],[103.99341241199842,30.56341405699958],\n" +
				"[103.9951081089983,30.564284565999618],[103.99527075099844,30.565225306999523],[103.99473663004295,30.566040018763935],\n" +
				"[104.00132519899832,30.567654997999554],[104.00809548616367,30.560077286707546]]],\"color\":\"#1f1a75\"}]";
		return JSONArray.parseArray(qf);
	}


	public static void main(String[] args) throws Exception {
		JSONArray rr = ff();
		List<PoygonsList> lists = new ArrayList<>();

		for (int i = 0; i < rr.size(); i++) {
			PoygonsList p = new PoygonsList();
			List data = (List) rr.getJSONObject(i).get("data");
			List points = (List) data.get(0);
			Double[][] dd = new Double[points.size()][];
			for (int k = 0; k < points.size(); k++) {
				List point = (List) points.get(k);
				Double[] df = new Double[point.size()];
				for (int j = 0; j < point.size(); j++) {
					df[j] = Double.valueOf(point.get(j).toString());
				}
				dd[k] = df;
			}
			p.setPoint(dd);
			p.setColor(rr.getJSONObject(i).get("color").toString());
			lists.add(p);
		}
		lists.addAll(lists);
		lists.addAll(lists);
		lists.addAll(lists);
		lists.addAll(lists);
		System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date())+":"+lists.size());
		List<String> colors = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		colors.add("#FFFFFF");
		colors.add("#9cf790");
		colors.add("#37a600");
		colors.add("#67b4f8");
		colors.add("#0002fe");
		colors.add("#fa03f0");
		colors.add("#710100");
		colors.add("#000000");
		values.add(0.1);
		values.add(1.6);
		values.add(7d);
		values.add(15d);
		values.add(40d);
		values.add(50d);
		values.add(100d);
		List<GridNetModel> gridNetModels = new ArrayList<>();

		double xMax = 104.20244580146897;
		double xMin = 104.11467625278794;
		double yMin = 30.418971178357594;
		double yMax =  30.50910455306166;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Random random = new Random();
				String red = Integer.toHexString(random.nextInt(256)).toUpperCase();
				String green = Integer.toHexString(random.nextInt(256)).toUpperCase();
				String blue = Integer.toHexString(random.nextInt(256)).toUpperCase();
				red = red.length()==1 ? "0" + red : red ;
				green = green.length()==1 ? "0" + green : green ;
				blue = blue.length()==1 ? "0" + blue : blue ;
				gridNetModels.add(new GridNetModel()
						.setxMax((xMax-xMin)/10*(i+1)+xMin)
						.setxMin((xMax-xMin)/10*(i)+xMin)
						.setyMax((yMax-yMin)/10*(j+1)+yMin)
						.setyMin((yMax-yMin)/10*(j)+yMin)
				.setColor("#"+red+green+blue));
			}
		}

		List<TextModel> textModels = new ArrayList<>();
		textModels.add(new TextModel().setLon(104.13355).setLat(30.44376).setText("百合村"));
		textModels.add(new TextModel().setLon(104.17488).setLat(30.48585).setText("简华村"));
		textModels.add(new TextModel().setLon(104.19364).setLat(30.46907).setText("川心村"));
		textModels.add(new TextModel().setLon(104.18022).setLat(30.44513).setText("茅香村"));
		textModels.add(new TextModel().setLon(104.17579).setLat(30.47166).setText("团山村"));
		textModels.add(new TextModel().setLon(104.14941).setLat(30.46602).setText("白沙村"));
		textModels.add(new TextModel().setLon(104.15170).setLat(30.44238).setText("盛华村"));
		textModels.add(new TextModel().setLon(104.17137).setLat(30.43399).setText("梅家村"));
		List<Map<String,Object>> listData = DataMaker.simpleListDataMaker();
		PrintModel printModelJSON = new PrintModel()//输出参数载体
				.setLayerRootPath("E:\\压缩包\\arcgisSHP\\柯桥区\\乡镇边界简化84.shp")//设置图层参数 乡镇边界简化84.shp
//				.setLayerRootPath("E:\\压缩包\\arcgisSHP\\奉贤气象\\奉贤街镇.shp")//设置图层参数 乡镇边界简化84.shp
				.setFileOutPath("e:\\tmp\\")//设置输出目录
				.setPngTitle("内涝风险系数评估")//设置图片名称
				.setPngSubTitle("预报时段：2018年05月16日 08时---2018年05月16日 11时")
//				.setClipsValue(lists)//设置采集值PointFf()
				.setValues(values)
				.setColors(colors)
				.setX(100)
				.setY(100)
				.setN(listData.size())
				.setTextModels(textModels)
				.setClipsValue(lists)
				.setFileName("100_100")
//				.setGrid(gridNetModels)
				.setBorderSwitch(true)
				.setPrint(PrintEnum.PNG)
				.setPrintTypeEnum(PrintTypeEnum.DATA_SINGLE_CUT)
				.setData(listData)
				.setFileSwitch(false);//文件删除开关 true 为不保留文件 false 为保留文件
//		MeteoInfoUtil.colorMap(printModelJSON);
		printModelJSON.setX(1000).setY(1000).setFileName("1000_1000");
//		MeteoInfoUtil.colorMap(printModelJSON);

		List<Map<String,Object>> data = new ArrayList<>();
		data.add(newMap(new Object[]{"lon", 120.659},new Object[]{"lat",  29.938},new Object[]{"value", 341.8}));
		data.add(newMap(new Object[]{"lon", 120.639},new Object[]{"lat",  30.198},new Object[]{"value", 141.8}));
		data.add(newMap(new Object[]{"lon", 120.619},new Object[]{"lat",  29.938},new Object[]{"value", 341.8}));
		data.add(newMap(new Object[]{"lon", 120.639},new Object[]{"lat",  29.998},new Object[]{"value", 421.8}));
		data.add(newMap(new Object[]{"lon", 120.629},new Object[]{"lat",  29.888},new Object[]{"value", 6141.8}));
		data.add(newMap(new Object[]{"lon", 120.649},new Object[]{"lat",  29.878},new Object[]{"value", 541.8}));
		data.add(newMap(new Object[]{"lon", 120.659},new Object[]{"lat",  29.868},new Object[]{"value", 441.8}));
		data.add(newMap(new Object[]{"lon", 120.669},new Object[]{"lat",  29.58},new Object[]{"value", 141.8}));
		data.add(newMap(new Object[]{"lon", 120.689},new Object[]{"lat",  29.848},new Object[]{"value", 421.8}));
		data.add(newMap(new Object[]{"lon", 120.619},new Object[]{"lat",  29.838},new Object[]{"value", 431.8}));
		printModelJSON.setData(data);
		MeteoInfoUtil.colorMap(printModelJSON);
		System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date()));

	}

	public static Map<String,Object> newMap(Object[]... qf){
		Map<String,Object> map = new HashMap<>();
		for (int i = 0; i < qf.length; i++) {
			map.put(qf[i][0].toString(),qf[i][1]);
		}
		return map;
	}
}
