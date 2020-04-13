<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2020/3/29
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>测试</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
     $(function () {
       $("#btn").click(function () {
          $.ajax({
            "url":"send/array.html",
            "type":"post",
            "data":{
              "array":[1,2,3,4,5]
            },
            "dataType":"text",
            "success":function(response) {
              alert(response)
            },
            "error":function(response){
              alert(response)
            }
          })
       }
     )
     })
    </script>
  </head>
  <body>
  <a href="test/ssm.html">测试SSM整合</a>
  <button id="btn">Jquery测试！</button>
  <a id="login" href="/admin/to/login/page.html">进入登陆页面</a>
  </body>
</html>
