<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>${project.title}</title>
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/index.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--[if lt IE 9]>
    <script src="js/modernizr.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="js/png.js"></script>
    <script type="text/javascript">
        EvPNG.fix('.logo');
    </script>
    <![endif]-->
    <script type="text/javascript">
        function loadpage(url) {
            $("#pageframe").load(url);
        }
    </script>
</head>
<body>
<header>
    <div class="headtop">
        <div class="timer box">
            <div id="jnkc" style="width: 200px;float: left "></div>
            <marquee  style=" float: left;width:800px"  onMouseOver="this.stop()"
                     onMouseOut="this.start()"> <font style="color: #8a6d3b;"> ${project.title}1</font>
            </marquee>
            <script>setInterval("jnkc.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
            </script>
        </div>
    </div>
    <div class="logo box"></div>
    <div style=" width:1000px;margin:0 auto;" ><div class="row">
    <section class="panel">
        <header class="panel-heading custom-tab dark-tab">
            <ul class="nav nav-tabs">
                <s:iterator id="map" value="#request.navpageList">
                    <li class=""><a href="#" onclick="loadpage('<s:property value="#map.href"/>')">
                        <s:property value="#map.label"/> </a></li>
                </s:iterator>
            </ul>
        </header>
    </section>
    </div></div>
    <script src="js/silder.js"></script>
</header>
<div class="panel-body">
<div class="box" id="pageframe">123123333
</div></div>
<footer>
    <!--div class="footnav">
        <ul>
            <s:iterator id="map" value="#request.navpageList">
                <li><a href="<s:property value="#map.href"/>">
                    <s:property value="#map.label"/> </a></li>
            </s:iterator>
        </ul>
    </div-->
    <div class="copyright">
        <p>Copyright 2016 All Rights Reserved 版权所有 ${project.title}</p>

        <p>地址：${project.address} 联系电话：${project.contacttel}</p>

        <p>备案号：${project.bah}</p></div>
</footer>
</body>
</html>