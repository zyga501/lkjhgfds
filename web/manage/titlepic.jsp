
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #sortable { list-style-type: none; margin: 0; padding: 0 0 2.5em; float: left; margin-right: 10px; }
    #sortable li{ float:left;margin: 0 5px ; font-size: 1.2em; width: auto;background-color: #0e9aef }
</style>
<script>
    $(function() {
        $( "#sortable" ).sortable().disableSelection();
        initaction();
        liappendbtn();
    });
    function initaction(){
       $("#sortable  li").unbind('click');
        $("#sortable  li").click(function(){
            var name=prompt("请输入菜单名称",$(this).text())
            if (name!=null && name!="")
            {
                $(this).text(name);
                $(this).append("<button type='button' class='btn btn-default btn-xs remove' onclick='delitem(this.parent)'><span class='glyphicon glyphicon-trash'></span></button>");
            }
        });
        event.stopPropagation();
    }

    function delitem(obj,event){
        var r=confirm("是否删除菜单【"+$(obj).text()+"】");
        if (r==true)
        {
            alert("你选择了删除");
        }
        event.stopPropagation();
    }
    function liappendbtn(){
        $("#sortable  li").append("<button type='button' class='btn btn-default btn-xs remove' onclick='delitem(this.parent,event)'><span class='glyphicon glyphicon-trash'></span></button>");
    }
    function addmenu(){
        $("#sortable").append("<li class='ui-state-default' diy1=0>新菜单<button type='button' class='btn btn-default btn-xs remove' onclick='delitem(this.parent)'><span class='glyphicon glyphicon-trash'></span></button></li>");
        initaction();
    }
    function submit(){
        var jsonary ="content=[";
        jQuery(".ui-state-default").each(function(){
            jsonary += '{"id":"'+(jQuery(this).attr('diy1'))+'","menuname":"'+(jQuery(this).text())+'"},';
        });
        jsonary = jsonary.substr(0,jsonary.length-1);
        jsonary +="]";
        alert(jsonary);
        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/setmenu!html',
            dataType:"json",
            data:jsonary,
            success: function (data) {
                alert(data);
            }
        })
    }
</script>
<div class="form-inline " style="width: 100%">
    <button class="btn btn-primary" onclick="addmenu()" >增加菜单</button>
    <button class="btn btn-success" onclick="submit()" >确  认</button>
    <p>拖拉改变位置；单击更改菜单文字；双击删除菜单！</p>
</div>
<div class="form-inline form-control " style="width: 100%">
<ul id="sortable" class="connectedSortable">
    <li class="ui-state-default">Item 1</li>
    <li class="ui-state-default">Item 11</li>
    <li class="ui-state-default">Item 21</li>
    <li class="ui-state-default">Item 31</li>
    <li class="ui-state-default">Item 41</li>
</ul>
</div>
<form><input type="hidden" name="sds" value="2ss"><input type="hidden" name="sdos" value="2ssdfdfd"></form>
<script>
    $(function() {
        $.ajax({
            type: 'post',
            url: '<%=request.getContextPath()%>/getmenu!html',
            dataType:"json",
            data:$("form").serialize(),
            success: function (data) { alert(data);
                var json = eval("(" + data + ")");
                if (json.menulist != null) {
                    var htmlstr = "";
                    for (var i = 0; i < json.menulist.length; i++) {
                        htmlstr += "<li class='ui-state-default' diy1="+ json.menulist[i].id+">" + json.menulist[i].menuname + "</li>";
                    }
                    $("#sortable").html(htmlstr);
                    $( "#sortable" ).sortable().disableSelection();
                    initaction();
                    liappendbtn();
                }
            }
            });
    });
</script>