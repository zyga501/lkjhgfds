<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>房源信息</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/layer.css" rel="stylesheet">
</head>
<body>
<div class="panel-body">
    <div class="form-horizontal">
        <form class="form-horizontal adminex-form" id="form1">
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">宣传标语</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="title" placeholder="介绍房产概况" value="<s:property value="#request.hinfo.title"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">区域</label>
                <div class="col-sm-10">
                    <select class="form-control m-bot15" name="ssq" id="ssq">
                        <option>太平街道</option>
                        <option>城东街道</option>
                        <option>城西街道</option>
                        <option>城北街道</option>
                        <option>横峰街道</option>
                        <option>泽国</option>
                        <option>大溪</option>
                        <option>新河</option>
                        <option>松门</option>
                        <option>箬横</option>
                        <option>城南</option>
                        <option>石桥头</option>
                        <option>石塘</option>
                        <option>滨海</option>
                        <option>温峤</option>
                        <option>坞根</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">小区</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="xq"  value="<s:property value="#request.hinfo.xq"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">房屋结构</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="fwjg"  value="<s:property value="#request.hinfo.fwjg"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">户型结构</label>
                <div class="col-sm-10">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control" name="hx_s" id="hx_s">
                                        <option>0</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select>
                                </div>
                                <label class="col-sm-2 control-label">室</label>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control m-bot15" name="hx_t" id="hx_t">
                                        <option>0</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select>
                                </div>
                                <label class="col-sm-2 col-sm-2 control-label">厅</label>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control m-bot15" name="hx_w" id="hx_w">
                                        <option>0</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select>
                                </div>
                                <label class="col-sm-2 col-sm-2 control-label">卫</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">建筑面积</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="jzmj"  value="<s:property value="#request.hinfo.jzmj"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">总层数</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" data-trigger="click" name="zcs"  value="<s:property value="#request.hinfo.zcs"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">所在层</label>

                <div class="col-sm-10">
                    <input class="form-control" id="focusedInput" type="text" name="szc"  value="<s:property value="#request.hinfo.szc"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">朝向</label>
                <div class="col-sm-10">
                    <select class="form-control"  name="cx" id="cx">
                        <option>南</option>
                        <option>东</option>
                        <option>西</option>
                        <option>北</option>
                        <option>东南</option>
                        <option>西南</option>
                        <option>东北</option>
                        <option>西北</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">建成年份</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="" name="jcnf"  value="<s:property value="#request.hinfo.jcnf"></s:property>">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">产权信息</label>

                <div class="col-sm-10">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control" name="lx" id="lx">
                                        <option>商品房</option>
                                        <option>私房</option>
                                        <option>别墅</option>
                                        <option>单位产</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control m-bot15" name="syqx" id="syqx">
                                        <option>70</option>
                                        <option>50</option>
                                        <option>30</option>
                                        <option>未知</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control" name="yt" id="yt">
                                        <option>住宅</option>
                                        <option>商业</option>
                                        <option>厂房</option>
                                        <option>其他</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">价格</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="" name="jg"  value="<s:property value="#request.hinfo.jg"></s:property>">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">土地性质</label>
                <div class="col-sm-10">
                    <select class="form-control m-bot15" name="tdxz" id="tdxz">
                        <option>国有出让</option>
                        <option>国有划拨</option>
                        <option>其他</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">宣传照</label>
                <div class="col-sm-10" id="contenti_mg">加载中...
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/layer.min.js"></script>
<script>
    $().ready(function(){
            setv("#ssq","<s:property value="#request.hinfo.ssq"></s:property>");
            setv("#cx","<s:property value="#request.hinfo.cx"></s:property>");
            setv("#hx_t","<s:property value="#request.hinfo.hx_t"></s:property>");
            setv("#hx_s","<s:property value="#request.hinfo.hx_s"></s:property>");
            setv("#hx_w","<s:property value="#request.hinfo.hx_w"></s:property>");
            setv("#tdqx","<s:property value="#request.hinfo.tdqx"></s:property>");
            setv("#tdxz","<s:property value="#request.hinfo.tdxz"></s:property>");
            setv("#yt","<s:property value="#request.hinfo.yt"></s:property>");
            setv("#lx","<s:property value="#request.hinfo.lx"></s:property>");
        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/xczPic!esf',
            data:{hid:"<s:property value="#request.hinfo.hid"></s:property>"},
            success: function (data) {
                var json = eval("(" + data + ")");
                var contentstr = "";
                for (var p in json) {
                    contentstr += "<img style='width:100%' src='data:image/png;base64," + json[p] + "'><br> ";
                }
                $("#contenti_mg").html(contentstr);
            }
        });
    })

    function setv(objid,v) {
        $(objid).find("option:contains('"+v+"')").attr("selected",true);
    }
</script>
</html>