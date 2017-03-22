<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getLocation</title>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    $.ajax({
        type: 'post',
        url: '<%=request.getContextPath()%>/QYAct!getSignPackage',
        dataType: "json",
        data: $("form").serialize(),
        success: function (data) {
            var json = eval("(" + data + ")");
            wx.config({
                "appId": json.appId,
                "timestamp": json.timeStamp,
                "nonceStr": json.nonceStr,
                "signature": json.signature,
                jsApiList: [
                    getLocation
                ]
            });
            wx.ready(function () {
                alert("ready");
            });
        }
    })
</script>
</body>
</html>
