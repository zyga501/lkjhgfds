<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>pc登录</title>
</head>
<body>
<div class="panel-body">
    <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">请扫码登录</label>
                <div class="col-sm-10"><div id="userqrcode"></div></div>
    </div>
</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/qrcode.js"></script>
<script>
    $(function(){
        refreshQrcode()
    })

    function refreshQrcode(){
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>QYAct!mkpclogin",
            cache: false,
            success: function (data) {
                var json =  eval("(" + data + ")");
                $("#userqrcode").html("");
                {
                    var qr = qrcode(10, 'H');
                    qr.addData("http://"+document.domain+"/<%=request.getContextPath()%>/QYAct!sdfgk?uuid="+json.uuid);
                    qr.make();
                    var dom=document.createElement('DIV');
                    dom.innerHTML = qr.createImgTag();
                    $("#userqrcode")[0].appendChild(dom);
                }
                _poll(json.uuid);
            }
        })
    }

    function _poll(_asUUID) {
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath()%>/QYAct!pclogin?uuid=" + _asUUID ,
            dataType: "json",
            cache: false,
            timeout: 60000,
            success: function(data) {
                var json =  eval("(" + data + ")");
                if (json.resultCode=="Succeed") {
                        window.location.href = json.href;
                }else {
                    _poll(_asUUID)
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                refreshQrcode()
            }
        });
    }
</script>
</html>