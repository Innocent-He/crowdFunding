<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2020/4/3
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="/WEB-INF/include-head.jsp" %>

    <link rel="stylesheet" href="ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="crowd/my-menu.js" charset="UTF-8"></script>
    <script type="text/javascript">
        $(function () {
            generateTree();

            $("#treeDemo").on("click", ".addBtn", function () {
                window.pid = this.id;
                $("#menuAddModal").modal("show");
                return false;
            });

            $("#treeDemo").on("click", ".editBtn", function () {
                window.id = this.id;
                $("#menuEditModal").modal("show");

                var zTreeObj=$.fn.zTree.getZTreeObj("treeDemo");
                var key="id";
                var value=window.id;
                var currentNode= zTreeObj.getNodeByParam(key,value);
                $("#menuEditModal [name=name]").val(currentNode.name);
                $("#menuEditModal [name=url]").val(currentNode.url);
                //必须放在方括号中
                $("#menuEditModal [name=icon]").val([currentNode.icon]);

                return false;
            });

            $("#treeDemo").on("click", ".removeBtn", function () {
                window.id = this.id;
                var zTreeObj=$.fn.zTree.getZTreeObj("treeDemo");
                var key="id";
                var value=window.id;
                var currentNode= zTreeObj.getNodeByParam(key,value);
                $("#removeNodeSpan").text(currentNode.name);
                $("#menuConfirmModal").modal("show");
                return false;
            });

            $("#menuSaveBtn").click(function () {
                var name = $.trim($("#menuAddModal [name=name]").val());
                var url = $.trim($("#menuAddModal [name=url]").val());
                var icon=$("#menuAddModal [name=icon]:checked").val();
                $.ajax({
                    "url":"menu/save.json",
                    "type":"post",
                    "data":{
                        "pid":window.pid,
                        "name":name,
                        "url":url,
                        "icon":icon
                    },
                    "dataType":"json",
                    "success":function (response) {
                        var result=response.result;
                        if(result=="SUCCESS"){
                            layer.msg("操作成功");
                            //重新转回页面
                            generateTree();
                        }
                        if (result=="FAILED"){
                            layer.msg("操作失败"+response.message)
                        }

                    },
                    "error":function (response) {
                        layer.msg(response.status+""+response.statusText)
                    }
                })
                $("#menuAddModal").modal("hide");
                //不传参数相当于点击了一下，重置表单内容
                $("#menuResetBtn").click();
            })

            $("#menuEditBtn").click(function () {
                var name = $.trim($("#menuEditModal [name=name]").val());
                var url = $.trim($("#menuEditModal [name=url]").val());
                var icon=$("#menuEditModal [name=icon]:checked").val();
                $.ajax({
                    "url":"menu/edit.json",
                    "type":"post",
                    "data":{
                        "id":window.id,
                        "name":name,
                        "url":url,
                        "icon":icon
                    },
                    "dataType":"json",
                    "success":function (response) {
                        var result=response.result;
                        if(result=="SUCCESS"){
                            layer.msg("操作成功");
                            //重新转回页面
                            generateTree();
                        }
                        if (result=="FAILED"){
                            layer.msg("操作失败"+response.message)
                        }

                    },
                    "error":function (response) {
                        layer.msg(response.status+""+response.statusText)
                    }
                })
                $("#menuEditModal").modal("hide");
            })

            $("#confirmBtn").click(function () {
                $.ajax({
                    "url":"menu/remove.json",
                    "type":"post",
                    "data":{
                        "id":window.id
                    },
                    "dataType":"json",
                    "success":function (response) {
                        var result=response.result;
                        if(result=="SUCCESS"){
                            layer.msg("操作成功");
                            //重新转回页面
                            generateTree();
                        }
                        if (result=="FAILED"){
                            layer.msg("操作失败"+response.message)
                        }

                    },
                    "error":function (response) {
                        layer.msg(response.status+""+response.statusText)
                    }
                })
                $("#menuConfirmModal").modal("hide");
            })
        })
    </script>
</head>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <!--动态依附的节点-->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/modal-menu-add.jsp" %>
    <%@include file="/WEB-INF/modal-menu-edit.jsp" %>
    <%@include file="/WEB-INF/modal-menu-confirm.jsp" %>
</div>
</body>
</html>
