<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>房源</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/layer.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div  style="position: fixed; top: 30px;width: 100%;height: 300px;left: 0px;z-index: -999;">
        <img width="100%" src="/images/esf_head_back.png">
    </div>
    <div class="page-header">欢迎</div>
    <div  style="  background-color:#000; opacity:0.1; filter:alpha(opacity=10);"class="panel panel-default ">
        <div class="panel-body">
            <div class="row">
                &nbsp&nbsp区域:<a href="">太平</a>  <a href="">泽国</a>  <a href="">箬横</a>
            </div>
            <div class="row">
                &nbsp&nbsp价格:<a href="">100万以下</a>  <a href="">100~150万</a>  <a href="">150~200万</a>
            </div>
            <div class="row">
                &nbsp&nbsp面积:<a href="">90方以下</a>  <a href="">90~140方</a>  <a href="">140方以上</a>
            </div>
        </div>
    </div>
<s:iterator value="#request.houseList" status="stat" id="hl">
    <div class="page-header">
        <div class="row">
            <div class="col-md-3">
                <img class="post-featured-image" style="width: 100%" src="http://image.5i5j.com/picture/slpic/l/house/3709/37098141/shinei/hggpcoff3ce7ba77.jpg.jpg"/>
            </div>
            <div class="col-md-7">
                <h2><b><s:property value="#hl.title"></s:property></b></h2>
                <h3>小区：<s:property value="#hl.xq"></s:property></h3>&nbsp&nbsp
                <small>建筑面积：<s:property value="#hl.jzmj"></s:property>&nbsp&nbsp<s:property value="#hl.hx_s"></s:property>室
                    <s:property value="#hl.hx_t"></s:property>厅<s:property value="#hl.hx_w"></s:property>卫</small>
            </div>
            <div class="col-md-2">
                <h2><label class="label label-warning">￥<s:property value="#hl.jg"></s:property></label></h2>
            </div>
        </div>
    </div>
</s:iterator>
</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer.min.js"></script>
<script>
</script>
</html>