package com.wlfg.action;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import java.io.IOException;
import java.io.PrintWriter;


public class CoreServlet extends AjaxActionSupport {
    private static final long serialVersionUID = 4440739483644821986L;
    String sToken = "DTpU1Iy79UPTCUNq9DXQ9oy";//这个Token是随机生成，但是必须跟企业号上的相同
    String sCorpID = "wxd8913b7903abf17f";//这里是你企业号的CorpID
    String sEncodingAESKey = "WYfRRdlX2ceQKQ4YmGlqCGBlOJL8MuItrX9ZGEds261";//这个EncodingAESKey是随机生成，但是必须跟企业号上的相同

    public void doGetp() throws IOException {
//        HttpServletRequest request =getRequest();
//         HttpServletResponse response=getResponse();
        // 微信加密签名
        String sVerifyMsgSig = getParameter("msg_signature").toString();
        // 时间戳
        String sVerifyTimeStamp = getParameter("timestamp").toString();
        // 随机数
        String sVerifyNonce = getParameter("nonce").toString();
        // 随机字符串
        String sVerifyEchoStr = getParameter("echostr").toString();
        String sEchoStr; //需要返回的明文
        PrintWriter out = getResponse().getWriter();
        WXBizMsgCrypt wxcpt;
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
            // 验证URL成功，将sEchoStr返回
            out.print(sEchoStr);
        } catch (AesException e1) {
            e1.printStackTrace();
        }
    }

}