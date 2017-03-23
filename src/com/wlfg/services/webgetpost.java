package com.wlfg.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class webgetpost {

    static CloseableHttpResponse httpResponse;
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

    public static String geturl(String url) throws IOException {
        String responseString;
        HttpGet httpget =  new HttpGet(url);
        httpget.setHeader("referer", "http://www.qq.com");
        httpResponse = httpclient.execute(httpget);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = httpResponse.getEntity();
            responseString = EntityUtils.toString(httpEntity).replaceAll("\n","").replaceAll("\"","\"");
            return (responseString);
        }
        return "";
    }
}
