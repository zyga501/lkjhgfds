<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>未审核房源</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/layer.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/layer.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script>
</head>
<body >
<div>
    <div id="contactlist"   >
        <div id="toolbar"><button class="btn btn-primary" title="审核通过" onclick="sendok(1)">审核通过
        </button><button class="btn btn-danger" title="审核不过" onclick="sendok(2)">审核不过
        </button></div>
        <table  id="ctable"  class="table table-striped table-hover table-bordered"> <thead><tr>
            <th  data-checkbox="true" ><th  data-field="hid" data-visible="false">hid</th>
            <th  data-field="index" data-formatter="indexFormatter">序号</th>
            <th  data-field="cqz"  data-sortable="true" data-formatter="hrefFormatter" >不动产证号</th>
            <th data-field="jzmj"  data-sortable="true">面积</th><th data-field="jg"  data-sortable="true">价格</th>
            <th  data-field="xq"  data-sortable="true">小区</th><th data-field="sjdate"  data-sortable="true">日期</th>
            <th data-field="status"  data-sortable="true">审核状态</th></tr></thead>
        </table>
    </div>

    <div class="directory-info-row" style="display: none">
        <div class="row">
        </div>
    </div>
</div>
</body>
<script>
    function hrefFormatter(value, row, index) {
        return "<a href='#'>"+value+"</a>";
    }
    function indexFormatter(value, row, index) {
        return index+1;
    }
    function urlFormatter(value, row, index) {
        if (value!=undefined)
            return "<a href='http://www.baidu.com'>"+value+"</a>";
        else return "";
    }

    function format_ok(value, row, index) {
        return value==1?"已审核":( value==2?"不通过":"未审核");
    }

    function sendok(v){
        editc(v);
    }
    function editc(v){
        var t = ""+getIdSelections();
        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/uccheckedList!esf',
            dataType: "json",
            data:{hid:t,ok:v},
            success: function (data) {
                var json = eval("(" + data + ")");
                alert(json.resultCode);
                if (json.resultCode=="Succeed"){
                    myinitTable();
                }
            }
        });
    }
    //隐藏 $('#tableOrderRealItems').bootstrapTable('hideColumn', 'GoodsId');
    function myinitTable() {
        $.get('<%=request.getContextPath()%>/uncheckList!esf', function(data) {
            data=JSON.parse(data);
            //先销毁表格
            $('#ctable').bootstrapTable('destroy');
            //初始化表格,动态从服务器加载数据
            $("#ctable").bootstrapTable({
                //method: "get",  //使用get请求到服务器获取数据
                //url: "< %=request.getContextPath()%>/u!contactList", //获取数据的Servlet地址
                toolbar: '#toolbar',                //工具按钮用哪个容器
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                sortOrder: "asc",
                showToggle:true,
                striped: true,  //表格显示条纹
                pagination: true, //启动分页
                pageSize:10,  //每页显示的记录数
                pageNumber:1, //当前第几页
                pageList: [10,  20, 50],  //记录数可选列表
                search: true,  //是否启用查询
                showColumns: true,  //显示下拉框勾选要显示的列
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType : "queryParams",
                sidePagination: "client",
                onClickCell: function(field, value, row, $element){  //加载成功时执行
                    if (field=="cqz"){
                        $.ajax({
                            type: 'post',
                            url: '<%=request.getContextPath()%>/checkPic!esf',
                            dataType: "json",
                            data: {hid: row.hid},

                            success: function (data) {
                                var json = eval("(" + data + ")");
                                var contentstr="";
                                for(var p in json){
                                    contentstr +="<img style='width:100%' src='data:image/png;base64," + json[p] + "'><br> ";
                                }
                                layer.open({
                                    type: 1,
                                    title:"审核资料-"+row.cqz,
                                    skin: 'layui-layer-rim', //加上边框
                                    area: ['80%', '90%'], //宽高
                                    content: contentstr
                                });
                            }
                        });
                        return;
                    }
                },
                onLoadError: function(){  //加载失败时执行
                    alert("加载数据失败");
                },
                data: data
            });
        });
    }

    function getIdSelections() {
        return $.map($('#ctable').bootstrapTable('getSelections'), function(row) {
            return row.hid
        });
    }
    $().ready(function () {myinitTable();
    });
</script>
</html>
