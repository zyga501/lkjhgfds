<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <link href="http://www.gov.cn/govweb/xhtml/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <title>${project.title}</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <style>
        body { font-size: 18px;  }
        .headtop { width: 100%; height: 23px; line-height: 23px; background: url(imgs/top.jpg) repeat-x; border-bottom: #CCC 1px solid }
        .timer span { float: right }
        .box { width: 1024px; margin: auto; overflow: hidden }
        .nav {font-size: 26px; }
        .navbar-nav {font-family: "微软雅黑","宋体";}
        .navbar-nav a{
            color: #fff;}
        .nav>li>a:hover, .nav>li>a:focus{
            text-decoration: none;
            background-color: #015292;
        }
        .anavbar { background-color: #015292
        }
        .anavbar-nav:hover{background-color: #1392e9}
        .copyright { text-align: center; line-height: 24px; height: 100px; color: #666 }
        .carousel-caption{
            text-shadow:#000 1px 0 0,#000 0 1px 0,#000 -1px 0 0,#000 0 -1px 0;
            -webkit-text-shadow:#000 1px 0 0,#000 0 1px 0,#000 -1px 0 0,#000 0 -1px 0;
            -moz-text-shadow:#000 1px 0 0,#000 0 1px 0,#000 -1px 0 0,#000 0 -1px 0;
            *filter: Glow(color=#000, strength=1);
        }

    </style>
    <script type="text/javascript">
        function loadpage(url) {
            $("#pageframe").load(url);
        }
    </script>
</head>
<body  class="sticky-header" >
<div class="box">
    <div>
        <div style=" height:80px;background: url(imgs/logo_bak.png) no-repeat"><img style=" height:80px;" src="images/headbk.png">
        <div class="pull-right" ><span class="label label-primary">移动设备扫描打开</span><img src="images/weburl.png" style=" height:80px;" />
    </div></div></div>
    <div style="background-color: #002a80">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <s:iterator id="map" value="#request.navpageList" status="L">
                        <li <s:if test="#L.index==0">class="tab-pane active" </s:if><s:else>class="tab-pane" </s:else> >
                            <a id="homepage" data-toggle="tab"  href='#' onclick="loadpage('<s:property value="#map.webpath"/>')">
                                <s:property value="#map.menuname"/> </a></li>
                    </s:iterator>
                </ul>
            </div>
        </div>
    </div>
    <div id="pageframe"></div>
</div>
<footer>
    <div class="copyright">
            <span>友情链接：</span>
        <select class="" id="menu">
            <option> 相关部门 </option>
            <option><a href="#">水利局</a></option>
            <option><a href="#">规划局</a></option>
            <option><a href="#">国土局</a></option>
        </select>
        <select class="" id="menu2">
            <option> 其他 </option>
            <option><a href="#">12306火车票</a></option>
            <option><a href="#">百度地图</a></option>
        </select>
        <p>Copyright 2016 All Rights Reserved 版权所有 ${project.title}</p>

        <p>地址：${project.address} 联系电话：${project.contacttel}</p>

        <p>备案号：${project.bah}</p></div>
</footer>
</body>
<!-- Placed js at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="js/html5shiv.min.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<!--common scripts for all pages-->
<script src="js/scripts.js"></script>
<script>
    $().ready(function () {
        $("#homepage").trigger("click");
    });
</script>
</html>