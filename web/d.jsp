<%--
  Created by IntelliJ IDEA.
  User: hammer
  Date: 2016-09-29
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tit333less</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script>
            alert(111);
            $.post("Act!qqlbsforpos",
                    {
                        gourl: 'http://apis.map.qq.com/ws/coord/v1/translate?locations=28.367172,121.36543;28.367144,121.36538' +
                        '&type=1&key=ZO4BZ-BZXK4-JN2UU-DIZMB-OWDS5-UKBVD',
                    },
                    function (data) {
                        alert(data)
                        alert( eval("(" + data + ")")[0].lng);
                    }
            )
        </script>
</head>
<body>

</body>
</html>
