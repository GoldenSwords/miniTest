package business.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.RequestContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author htdf_chenw
 * @date 2017/6/30
 */
public class ToolUtil {

    /**
     * 获取项目根路径
     *
     * @return 路径url
     */
    public static String getBasePath() {
        String root = null;
        try {
            root = RequestContext.class.getResource("/").toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String basePath = "D:\\";
        try {
            basePath = new File(root).getParentFile().getParentFile().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePath;
    }
}