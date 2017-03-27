<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <title>房源</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/layer.css" rel="stylesheet">
    <style>
        .panel-body a {
            color: #000000;
            font-size: 16px;
            margin: 0 10px 0 0;
        }
        .pic-num{
            padding: 0 10px;
            height: 22px;
            line-height: 22px;
            color: #fff;
            font-size: 12px;
            position: absolute;
            left: 20px;
            bottom: 0px;
            z-index: 2;
            background: rgba(0,0,0,.4);
        }
    </style>
</head>
<body>
<div class="container">
    <div  style="position: absolute; top: 30px;width: 100%;height: 300px;left: 0px;z-index: -999;">
        <img width="100%" src="/images/esf_head_back.png">
    </div>
    <div class="page-header">
        <div class="input-group">
            通知：********
        </div>
        <div class="input-group">
            <span class="input-group-addon">欢迎光临 <s:property value="#request.zj"></s:property></span>
            <input type="text" class="form-control" placeholder="输入中介名称或小区名称">
                                              <span class="input-group-btn">
                                                <button class="btn btn-default" type="button">Go!</button>
                                              </span>
        </div>
    </div>
    <div class="panel panel-default " style="background: url('/images/esf-mask-back.png') repeat">
        <div class="panel-body">
            <div class="row form-group">
               <label class="control-label">&nbsp&nbsp区域：</label><a href="">太平</a><a href="">泽国</a><a href="">箬横</a>
            </div>
            <div class="row form-group">
                <label class="control-label"> &nbsp&nbsp价格：</label><a href="">100万以下</a><a href="">100~150万</a><a href="">150~200万</a>
            </div>
            <div class="row form-group">
                <label class="control-label"> &nbsp&nbsp面积：</label><a href="">90方以下</a><a href="">90~140方</a><a href="">140方以上</a>
            </div>
            <div class="row form-group  form-inline">
                <label class="control-label"> &nbsp&nbsp中介：</label><select class="form-control"><option>所有中介</option></select><a href="">城西中介</a><a href="">皇朝中介</a>等
            </div>
            <div class="row form-group form-inline">
                <label class="control-label"> &nbsp&nbsp综合：</label><select class="form-control"><option>99平方</option></select>
                <select class="form-control"><option>城东街道</option></select><select class="form-control"><option>10~100万</option></select>
                <button class="btn btn-success">检索</button>
            </div>
        </div>
    </div>
<s:iterator value="#request.houseList" status="stat" id="hl">
    <div class="page-header">
        <div class="row">
            <div class="col-md-3">
                <a href="houseinfo!esf?hid=<s:property value="#hl.hid"></s:property>" target="hinfo">
                    <img class="post-featured-image" style="width: 100%;height: 180px" alt="<s:property value="#hl.hid"></s:property>"
                         src="<%=request.getContextPath()%>/xczFirstPic!esf?hid=<s:property value="#hl.hid"></s:property>">
                </a>
            </div>
            <div class="col-md-7">
                <h2><b><a href="houseinfo!esf?hid=<s:property value="#hl.hid"></s:property>" target="hinfo"><s:property value="#hl.title"></s:property></a></b></h2>
                <h3>小区：<s:property value="#hl.xq"></s:property></h3>
                <small>建筑面积：<s:property value="#hl.jzmj"></s:property>&nbsp&nbsp<s:property value="#hl.hx_s"></s:property>室<s:property value="#hl.hx_t"></s:property>厅<s:property value="#hl.hx_w"></s:property>卫</small>
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
<!--[if lt IE 9]>
<script src="/js/html5shiv.min.js"></script>
<script src="/js/respond.min.js"></script>
<![endif]-->
<!--common scripts for all pages-->
<script src="js/scripts.js"></script>
<script>
</script>
</html>