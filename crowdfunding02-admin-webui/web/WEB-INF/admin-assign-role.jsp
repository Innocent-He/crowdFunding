<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2020/4/4
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="/WEB-INF/include-head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#toRight").click(function () {
                $("select:eq(0)>option:selected").appendTo("select:eq(1)");
            })
            $("#toLeft").click(function () {
                $("select:eq(1)>option:selected").appendTo("select:eq(0)");
            })
            $("#subBtn").click(function () {
                $("select:eq(1)>option").prop("selected","selected");
            })
        })
    </script>
</head>

<body>

<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div style="width:100%;text-align:center">
                    <form action="assign/edit/role.html"  method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" name="keyWord" value="${param.keyWord}"/>
                        <div class="form-group">
                            <label >未分配角色列表</label><br>
                            <select  class="form-control"  multiple size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList}" var="unrole">
                                    <option  value="${unrole.id}">${unrole.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRight" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeft" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label >已分配角色列表</label><br>
                            <select name="assignRoleIdList" class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoleList}" var="role">
                                    <option value="${role.id}" selected>${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br/>
                        <br/>
                        <<div style="width:100%;text-align:center">
                            <button id="subBtn" type="submit" class="btn btn-small btn-success btn-block">提交</button>
                         </div>
                    </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>

