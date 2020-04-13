<%--
  Created by IntelliJ IDEA.
  User: H
  Date: 2020/4/2
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="/WEB-INF/include-head.jsp" %>
    <link rel="stylesheet" href="css/pagination.css" charset="UTF-8">
    <script type="text/javascript" src="jquery/jquery.pagination.js" charset="UTF-8"></script>
    <link rel="stylesheet" href="ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="crowd/my-role.js" charset="UTF-8"></script>

    <script type="text/javascript">
        $(function () {
            window.pageNum = 1;
            window.pageSize = 8;
            window.keyWord = "";
            generatePage();

            $("#searchBtn").click(function () {
                window.keyWord = $("#keyWordInput").val();
                generatePage();
            });
            $("#modalAddBtn").click(function () {
                $("#modalAdd").modal("show");
            })
            $("#saveBtn").click(function () {
                var roleName = $.trim($("#modalAdd [name=roleName]").val());
                $.ajax({
                    "url": "save/role.json",
                    "data": {
                        "name": roleName
                    },
                    "dataType": "json",
                    "success": function (response) {
                        var result = response.result;
                        if (result == "SUCCESS") {
                            layer.msg("操作成功");
                            window.pageNum = 999999;
                            generatePage();
                        }
                        if (result == "FAILED") {
                            layer.msg("操作失败" + response.message)
                        }
                    },
                    "error": function (response) {
                        layer.msg(response.status + " " + response.statusText);
                    }
                })
                $("#modalAdd").modal("hide");
                $("#modalAdd [name=roleName]").val("");

            });
            $("#rolePageBody").on("click", ".update", function () {
                window.roleid = this.id;
                $.ajax({
                    "url": "query/role.json",
                    "data": {
                        "id": roleid
                    },
                    "dataType": "json",
                    "success": function (response) {
                        var roleName = response.data;
                        $("#inputSuccess5").val(roleName);
                        $("#editModal").modal("show");
                    }
                })
            });
            $("#updateRoleBtn").click(function () {
                $.ajax({
                    "url": "update/role.json",
                    "data": {
                        "id": window.roleid,
                        "name": $("#inputSuccess5").val()
                    },
                    "dataType": "json",
                    "success": function (response) {
                        var result = response.result;
                        if (result == "SUCCESS") {
                            layer.msg("更新成功")
                            generatePage();
                            $("#editModal").modal("hide");
                        }
                        if (result == "FAILED") {
                            layer.msg("更新失败")
                        }

                    },
                    "error": function (response) {
                        layer.msg(response.status + " " + response.statusText);
                    }
                })
            })

            $("#rolePageBody").on("click", ".remove", function () {
                var roleId = this.id;
                $.ajax({
                    "url": "remove/role.json",
                    "data": {
                        "id": roleId
                    },
                    "dataType": "json",
                    "success": function (response) {
                        var result = response.result;
                        if (result == "SUCCESS") {
                            layer.msg("删除成功")
                            generatePage();
                        }
                        if (result == "FAILED") {
                            layer.msg("删除失败")
                        }
                    },
                    "error": function (response) {
                        layer.msg(response.status + " " + response.statusText);
                    }
                })
            })
            //为了给每个页面的按钮都绑定事件
            $("#rolePageBody").on("click",".checkBtn",function () {
                window.roleid=this.id;
                $("#assignModal").modal("show");
                fillAuthTree();
            })
            //给分配权限的提交按钮分配单击事件提交数据
            $("#assignBtn").click(function () {
                var authIdList=[];
                var treeObj=$.fn.zTree.getZTreeObj("authTreeDemo");
                var checkedNodes=treeObj.getCheckedNodes(true);
                for (var i=0;i<checkedNodes.length;i++){
                    var checkedNode=checkedNodes[i];
                    authIdList.push(checkedNode.id);
                }
                //为了两个数据都可以用List<Integer>来接收，把roleid弄成一个元素的数组
                var requestBody={
                    "roleId":[window.roleid],
                    "authIdList":authIdList
                };
                requestBody=JSON.stringify(requestBody);
                $.ajax({
                    "url":"save/authByRoleId.json",
                    "type":"post",
                    "data":requestBody,
                        // "roleId":window.roleid,
                        // "authIdList":authIdList
                    "contentType":"application/json;charset=UTF-8",
                    "dataType":"json",
                    "success":function (response) {
                        var result=response.result;
                        if (result=="SUCCESS"){
                            layer.msg("操作成功")
                            $("#assignModal").modal("hide");
                            generatePage();
                        }
                        if (result=="FAILED"){
                            layer.msg(response.message);
                        }
                    },
                    "error":function (response) {
                        layer.msg(response.status+" "+response.statusText)
                    }
                })
            })
//全局加载的结束
        });

        // 生成分页页码导航条
        function generateNavigator(pageInfo) {
            // 获取总记录数
            var totalRecord = pageInfo.total;
            // 声明相关属性
            var properties = {
                "num_edge_entries": 3,
                "num_display_entries": 5,
                "callback": paginationCallBack,
                "items_per_page": pageInfo.pageSize,
                "current_page": pageInfo.pageNum - 1,
                "prev_text": "上一页",
                "next_text": "下一页"
            }
            // 调用 pagination()函数
            $("#Pagination").pagination(totalRecord, properties);
        }
    </script>
</head>

<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keyWordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            id="modalAddBtn"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <!--在这里插入表格！！！-->
                            <tbody id="rolePageBody"></tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/modal-role-add.jsp" %>
<%@include file="/WEB-INF/modal-role-update.jsp" %>
<%@include file="/WEB-INF/modal-role-assign-auth.jsp" %>

</body>
</html>


