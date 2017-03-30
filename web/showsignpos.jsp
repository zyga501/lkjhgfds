<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>考勤记录</title>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }
        body,
        button,
        input,
        select,
        textarea {
            font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
        }
        p {
            width: 603px;
            padding-top: 3px;
            margin-top: 10px;
            overflow: hidden;
        }
        .btn {
            width: 112px;
        }
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>

</head>

<body onLoad="init()">
<script>
    function init() {
        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/QYAct!getsignpos',
            success: function (data) { alert(data);
                var center = new qq.maps.LatLng(28.384642,121.383884);
                var marker = new qq.maps.Marker({
                    //设置Marker的位置坐标
                    position: center,
                    //设置显示Marker的地图
                    map: map
                });
                var json = eval("(" + data + ")");
                for(var i=0,l=json.length;i<l;i++)
                {
                var center1 = new qq.maps.LatLng(parseFloat(json[i].lat),parseFloat(json[i].lng));
                var marker1 = new qq.maps.Marker({
                    //设置Marker的位置坐标
                    position: center1,
                    //设置显示Marker的地图
                    map: map
                });
        //设置Marker的可见性，为true时可见,false时不可见，默认属性为true
//        marker.setVisible(true);
//        //设置Marker的动画属性为从落下
//        marker.setAnimation(qq.maps.MarkerAnimation.DOWN);
//        //设置Marker是否可以被拖拽，为true时可拖拽，false时不可拖拽，默认属性为false
//        marker.setDraggable(false);
//        ////设置Marker自定义图标的属性，size是图标尺寸，该尺寸为显示图标的实际尺寸，origin是切图坐标，该坐标是相对于图片左上角默认为（0,0）的相对像素坐标，anchor是锚点坐标，描述经纬度点对应图标中的位置
//       icon = new qq.maps.MarkerImage(
//                        "/doc_v2/img/nilt.png"
//                );
//        marker.setIcon(icon);
//        //设置标注的名称，当鼠标划过Marker时显示
//        marker.setTitle("测试");
//
//        //添加信息窗口
//        var info = new qq.maps.InfoWindow({
//            map: map
//        });
//        //获取标记的可拖动属性
//        info.open();
//        info.setContent('标记的可拖动属性为：' + marker.getDraggable());
//        info.setPosition(marker.getPosition());
//        //标记Marker点击事件
//        qq.maps.event.addListener(marker, 'click', function() {
//            info.open();
//            info.setContent('<div style="text-align:center;white-space:nowrap;' +
//                    'margin:10px;">你单击了我</div>');
//            info.setPosition(marker.getPosition());
//        });
                }
            }
        })
        var center = new qq.maps.LatLng(28.384642,121.383884);
        var map = new qq.maps.Map(document.getElementById("container"), {
            center: center,
            zoom: 16
        });
    }
</script>
<div style="width:100%;height:100%" id="container"></div>
</body>

</html>

