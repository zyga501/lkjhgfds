package com.wlfg.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Httpclientweb {

    public static void main(String[] arg) throws IOException {
//114.55.231.115:9003/wljsj/master/login.aspx
       // dourl();
    }
    private static PoolingHttpClientConnectionManager createrDefaultConnectionManager() throws UnknownHostException {
        ConnectionSocketFactory socketFactory = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", socketFactory)
                .register("https", sslSocketFactory)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);
        HttpHost localhost = new HttpHost(InetAddress.getLocalHost());
        connectionManager.setMaxPerRoute(new HttpRoute(localhost), 50);
        return connectionManager;
    }
    private static RequestConfig createDefaultRequestConfig() {
        return RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
    }
    private static CloseableHttpClient httpclient;
    static {
        try {
            httpclient = HttpClients.custom()
                    .setConnectionManager(createrDefaultConnectionManager())
                    .setDefaultRequestConfig(createDefaultRequestConfig())
                    .build();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    CloseableHttpResponse httpResponse;
    public  String  dourl(String uname,String upwd) throws IOException {
        login(uname,upwd);
        return getCodeList();
    }

    public  String  login(String uname,String upwd) throws IOException {
        String responseString;
        httpResponse = httpclient.execute(new HttpGet("http://114.55.231.115:9003/wljsj/master/login.aspx"));
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            responseString = EntityUtils.toString(httpEntity);
            //System.out.print(responseString);
            Document doc = Jsoup.parse(String.valueOf(responseString));
            Elements formfields = doc.select("form input");
            Map<String, String> map = new HashMap<>();
            for (org.jsoup.nodes.Element e : formfields) {
                //System.out.println(e.attr("name") + ":" + e.attr("value"));
                map.put(e.attr("name"), e.attr("value"));
            }
            map.put("uidTextBox", uname);
            map.put("pwdTextBox", upwd);
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append("&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            String formString = sb.substring(1, sb.length());

            HttpPost httpPost = new HttpPost("http://114.55.231.115:9003/wljsj/master/login.aspx");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new StringEntity(formString, "UTF-8"));
            httpResponse = httpclient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                httpEntity = httpResponse.getEntity();
                responseString = EntityUtils.toString(httpEntity);
            }
            //System.out.print(responseString);
        }
        return "";
    }
    private String getCodeList() throws IOException {
            httpResponse =httpclient.execute(new HttpGet("http://114.55.231.115:9003/wljsj/WorkDay/DayList.aspx?Mid=A2411&Title=日考勤"));
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String responseString = EntityUtils.toString(httpEntity);
                //System.out.print(responseString);
                Document doc = Jsoup.parse(String.valueOf(responseString));
                Map<String,String> maps = new HashMap<>();
                String rtstr = doc.select(".tabContents").html();
                Elements formfields = doc.select(".tabContents").select("tr");
                responseString = "";
                for (org.jsoup.nodes.Element e  : formfields) {
                    Elements formfields2 = e.children().select("td");
                    for (org.jsoup.nodes.Element ef  : formfields2) {
                        responseString+=(ef.text());
                    }
                    responseString+="\n";
                }
                //return  responseString;

                Date doDate=new Date();
                String  doDateStr =(new SimpleDateFormat("yyyy-MM-dd")).format(doDate);
                int h = Integer.parseInt((new SimpleDateFormat("HH")).format(doDate));
                if (h<7 || (h>=9 && h<13)||h>14)
                    return rtstr;
                if (h<12?responseString.split(doDateStr).length==3:responseString.split(doDateStr).length==5)
                    return rtstr;
                httpResponse = httpclient.execute(new HttpGet("http://114.55.231.115:9003/wljsj/WorkDay/DayEdit.aspx?isnew=1"));
                if(httpResponse.getStatusLine().getStatusCode() == 200) {
                    httpEntity = httpResponse.getEntity();
                    responseString = EntityUtils.toString(httpEntity);
                    //System.out.print(responseString);
                    doc = Jsoup.parse(String.valueOf(responseString));
                    formfields = doc.select("form input");
                    Map<String,String>map = new HashMap<>();
                    for (org.jsoup.nodes.Element e : formfields) {
                        //System.out.println(e.attr("name") + ":" + e.attr("value"));
                        map.put(e.attr("name"), e.attr("value"));
                    }
                    map.put("ctl00$ContentPlaceHolder1$TextBox1", doDateStr);
                    map.put("ctl00$ContentPlaceHolder1$txtEndDate", doDateStr);
                    map.put("ctl00$ContentPlaceHolder1$DropDownList1", Integer.parseInt((new SimpleDateFormat("HH")).format(doDate))<12?"上午":"下午");
                    map.put("ctl00$ContentPlaceHolder1$DropDownList2","办公");
                    StringBuffer sb = new StringBuffer();
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        sb.append("&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
                    }
                    String formString = sb.substring(1, sb.length());
                    //System.out.print(formString);

                    HttpPost httpPost = new HttpPost("http://114.55.231.115:9003/wljsj/WorkDay/DayEdit.aspx?isnew=1");
                    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpPost.setEntity(new StringEntity(formString, "UTF-8"));
                    httpResponse = httpclient.execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        httpEntity = httpResponse.getEntity();
                        responseString = EntityUtils.toString(httpEntity);
                    }
                    //System.out.print(responseString);

                    httpResponse =httpclient.execute(new HttpGet("http://114.55.231.115:9003/wljsj/WorkDay/DayList.aspx?Mid=A2411&Title=日考勤"));
                    if(httpResponse.getStatusLine().getStatusCode() == 200) {
                        httpEntity = httpResponse.getEntity();
                        responseString = EntityUtils.toString(httpEntity);
                        doc = Jsoup.parse(String.valueOf(responseString));
                        rtstr = doc.select(".tabContents").html();
                    }
                    return rtstr;
                }
            }
        return "404page";
    }
}
