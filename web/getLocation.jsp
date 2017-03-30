<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到打卡</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
</head>
<body>
<div id="content"></div>
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
                    'getLocation'
                ]
            });
            wx.ready(function () {
                wx.getLocation({
                    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                    success: function (res) {
                        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
                        var speed = res.speed; // 速度，以米/每秒计
                        var accuracy = res.accuracy; // 位置精度
                        $.ajax({
                        type: 'post',
                        url: '<%=request.getContextPath()%>/QYAct!getgps',
                        dataType: "json",
                        data: {lx:latitude,ly:longitude},
                        success: function (data) {
                        var json = eval("(" + data + ")");
                        $("#content").html(json.rt);
                        }
                        })
                    },
                    fail:function(res){
                        alert(JSON.stringify(res));
                    }
                });
            });
        }
    })
</script>
</body>
</html>