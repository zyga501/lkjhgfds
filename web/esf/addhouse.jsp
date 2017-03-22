<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>新增房源</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/layer.css" rel="stylesheet">
</head>
<body>
<div class="panel-body">
    <div class="row">
        <span id="btnstep1" class="center-block btn btn-warning col-sm-6" onclick="pre()">第一步<br>二手房信息</span>
        <span id="btnstep2" class="center-block btn btn-default col-sm-6"  onclick="ntt()"> 第二步<br>审核信息</span>
    </div>
    <input type="hidden" id="gid" name="gid" value="">

    <div class="form-horizontal" id="step1">
        <form class="form-horizontal adminex-form" id="form1">
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">区域</label>

                <div class="col-sm-10">
                    <select class="form-control m-bot15" name="ssq">
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
                    <input type="text" class="form-control" name="xq">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">房屋结构</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="fwjg">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">户型结构</label>
                <div class="col-sm-10">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control" name="hx_s">
                                        <option>0</option>
                                        <option>1</option>
                                        <option selected>2</option>
                                        <option>3</option>
                                    </select>
                                </div>
                                <label class="col-sm-2 control-label">室</label>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control m-bot15" name="hx_t">
                                        <option>0</option>
                                        <option selected>1</option>
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
                                    <select class="form-control m-bot15" name="hx_w">
                                        <option>0</option>
                                        <option selected>1</option>
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
                    <input type="text" class="form-control" name="jzmj">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">总层数</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" data-trigger="click" name="zcs">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">所在层</label>

                <div class="col-sm-10">
                    <input class="form-control" id="focusedInput" type="text" name="szc">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">朝向</label>
                <div class="col-sm-10">
                    <select class="form-control"  name="cx">
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
                    <input type="text" class="form-control" placeholder="" name="jznf">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">产权信息</label>

                <div class="col-sm-10">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <select class="form-control" name="lx">
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
                                    <select class="form-control m-bot15" name="syqx">
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
                                    <select class="form-control" name="yt">
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
                    <input type="text" class="form-control" placeholder="" name="jg">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">土地性质</label>
                <div class="col-sm-10">
                    <select class="form-control m-bot15" name="tdxz">
                        <option>国有出让</option>
                        <option>国有划拨</option>
                        <option>其他</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">宣传照</label>

                <div class="col-sm-10">
                    <input id="xcz" name="xcz" type="file" class="file" multiple class="file-loading">
                </div>
            </div>
        </form>
    </div>
    <div class="form-horizontal" id="step2" style="display:none">
        <form class="form-horizontal adminex-form" id="form2">
            <input hidden name="hid" id="hid">
            <div class="form-group">
                <label class="col-sm-2 control-label">房产/不动产证号</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="" name="cqz" id="cqz">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">审核用</label><span>  请上传【房产证】或【不动产证】可多张！</span>
                <div class="col-sm-10">
                    <input id="fcz" name="fcz" type="file" class="file" multiple class="file-loading">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/fileinput.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/fileinput_locale_zh.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/layer.min.js"></script>
<script>
    $("#xcz").fileinput({
        language: 'zh',
        uploadUrl: '<%=request.getContextPath()%>/addHouse!esf',
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        overwriteInitial: false,
        dropZoneEnabled: false,
        maxFileCount: 10,
        showCaption: false,
        uploadAsync: false,
        showPreview:false,
        enctype: 'multipart/form-data',
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        },
        uploadExtraData: function () {
            var datas = $("#form1").serialize();
            return {"ExtData":datas};
        }
    }).on("filebatchselected", function(event, files) {

    }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
        if ($.parseJSON(response).resultCode=="Succeed") {
            $("#hid").val($.parseJSON(response).hid);
            $("#btnstep2").attr("disabled", false);
            $("#step1").css("display", "none");
            $("#step2").css("display", "block");
            $("#btnstep1").attr("class", "center-block btn btn-default col-sm-6");
            $("#btnstep2").attr("class", "center-block btn btn-warning col-sm-6");
        }
        else{
            alert($.parseJSON(response).msg);
        }
    }).on('filepreupload', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
        alert(JSON.stringify(event));
    });;

    function ntt() {
        if ($("#btnstep2").attr("disabled") != "disabled") {
            $("#step1").css("display", "none");
            $("#step2").css("display", "block");
            $("#btnstep1").attr("class", "center-block btn btn-default col-sm-6");
            $("#btnstep2").attr("class", "center-block btn btn-warning col-sm-6");
        }
    }
    function pre() {
        $("#step1").css("display", "block");
        $("#step2").css("display", "none");
        $("#btnstep2").attr("class", "center-block btn btn-default col-sm-6");
        $("#btnstep1").attr("class", "center-block btn btn-warning col-sm-6");
    }

    $("#fcz").fileinput({
        language: 'zh',
        uploadUrl: '<%=request.getContextPath()%>/addHouseSH!esf',
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        overwriteInitial: false,
        dropZoneEnabled: false,
        maxFileCount: 4,
        showCaption: false,
        uploadAsync: false,
        enctype: 'multipart/form-data',
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        },
        uploadExtraData: function () {
            var datas = $("#hid").val();
            var vcqz =   $("#cqz").val();
            return {"hid":datas,"cqz":vcqz};
        }
    }).on("filebatchselected", function(event, files) {

    }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
        if ($.parseJSON(response).resultCode=="Succeed") {
            alert('提交成功！');
            history.go(0);
        }else{
            alert('请选择图片，不要其他文件！');
        }
    }); ;
</script>
</html>