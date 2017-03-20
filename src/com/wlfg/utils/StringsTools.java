package com.wlfg.utils;

import net.sf.json.JSONObject;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by hammer on 2017-02-10.
 */
public class StringsTools {
    public static void mapToXMLTest2(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXMLTest2(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXMLTest2((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                }

            }

        }
    }

    public static BasicNameValuePair[] generatNameValuePair(Map<String, String> map, String charset) throws Exception {
        BasicNameValuePair[] nameValuePair = new BasicNameValuePair[map.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nameValuePair[i++] = new BasicNameValuePair(entry.getKey(), URLEncoder.encode(entry.getValue(), charset));
        }
        return nameValuePair;
    }

    public static int getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    public static Map<String,String> serializeToMAp (String serializeStr)  {
        Map<String,String> paramMap =new HashMap<>();
        paramMap.clear();
        if (!"".equals(serializeStr)) {// 如果URL不是空字符串
            String paramaters[] = serializeStr.split("&");
            for (String param : paramaters) {
                String values[] = param.split("=");
                try {
                    if (values.length==2)
                        paramMap.put(values[0], URLDecoder.decode(values[1],"utf-8"));
                    else
                        paramMap.put(values[0], "");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return paramMap;
    }
    public static Map<String, String> json2Map(JSONObject jsonMap) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> it = jsonMap.keys();
        while(it.hasNext()) {
            String key = (String) it.next();
            String u = jsonMap.get(key).toString();
            map.put(key, u);
        }
        return map;
    }
}
