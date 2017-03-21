package com.wlfg.weixin.utils;

import com.wlfg.weixin.api.QYAccessToken;
import com.wlfg.weixin.api.QYMessage;
import utils.ProjectSettings;

/**
 * Created by hammer on 2017-03-21.
 */
public class QYMessageControl {
    public static void sendMsg(String userid,String msgContent,String agentid) throws Exception {
        QYMessage qyMessage=new QYMessage(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()));
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"touser\":" + "\""+userid+"\",");
//        sb.append("\"toparty\":" + "\"" + toparty + "\",");
//        sb.append("\"totag\":" + "\"" + totag + "\",");
        sb.append("\"msgtype\":" + "\"text\",");
        sb.append("\"text\":" + "{");
        sb.append("\"content\":" + "\""+msgContent+"\"");
        sb.append("},");
        sb.append("\"agentid\":" + agentid);//8
        sb.append("}");
        qyMessage.postRequest(sb.toString());
    }
}
