// 执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage() {
    // 1.获取分页数据
    var pageInfo = getPageInfoRemote();
    // 2.填充表格
    fillTableBody(pageInfo);
}

// 远程访问服务器端程序获取 pageInfo 数据
function getPageInfoRemote() {
    // 调用$.ajax()函数发送请求并接受
    // $.ajax()函数的返回值
    var ajaxResult = $.ajax({
        "url": "role/get/page/info.json",
        "type": "post",
        "data": {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyWord": window.keyWord
        },
        "async": false,
        "dataType": "json"
    });
    // 判断当前响应状态码是否为200
    var statusCode = ajaxResult.status;
    // 如果当前响应状态码不是 200，说明发生了错误或其他意外情况，显示提示消息，让 当前函数停止执行
    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + " 说明信息:" + ajaxResult.statusText);
        return null;
    }
    // 如果响应状态码是 200，说明请求处理成功，获取 pageInfo
    var resultEntity = ajaxResult.responseJSON;
    // 从 resultEntity 中获取 result 属性
    var result = resultEntity.result;
    // 判断 result 是否成功
    if (result == "FAILED") {
        layer.msg(resultEntity.message);
        return null;
    }
    // 确认 result 为成功后获取 pageInfo
    var pageInfo = resultEntity.data;
    // 返回 pageInfo
    return pageInfo;
}


// role-page的tbody填充表格
function fillTableBody(pageInfo) {
    // 清除 tbody 中的旧的内容
    $("#rolePageBody").empty();
    // 这里清空是为了让没有搜索结果时不显示页码导航条
    $("#Pagination").empty();
    // 判断 pageInfo 对象是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到您搜索的数据！</td></tr>");
        return;
    }
    // 使用 pageInfo 的 list 属性填充 tbody
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;
        var numberTd = "<td>" + (i + 1) + "</td>";
        var checkboxTd = "<td><input type='checkbox'></td>";
        var roleNameTd = "<td>" + roleName + "</td>";
        var checkBtn = "<button type='button'  id='" + roleId + "' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check '></i></button>";
        var pencilBtn = "<button type='button' id='" + roleId + "' class='btn btn-primary btn-xs update'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button type='button' id='" + roleId + "' class='btn btn-danger btn-xs remove'><i class=' glyphicon glyphicon-remove'></i></button>";
        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";
        $("#rolePageBody").append(tr);
    }
    // 生成分页导航条
    generateNavigator(pageInfo);
}


// 翻页时的回调函数
function paginationCallBack(pageIndex, jQuery) {
    // 修改 window 对象的 pageNum 属性
    window.pageNum = pageIndex + 1;
    // 调用分页函数
    generatePage();
    // 取消页码超链接的默认行为
    return false;
}

//声明一个函数用来在分配角色权限时模态框的数据渲染
function fillAuthTree() {
    var ajaxReturn = $.ajax({
        "url": "assign/getAll/auth.json",
        "type": "post",
        "async": false,
        "dataType": "json"
    });
    if (ajaxReturn.status!=200){
        layer.msg("请求错误，状态码为："+ajaxReturn.status+"错误信息："+ajaxReturn.statusText);
        return
    }
    var authList=ajaxReturn.responseJSON.data;
    //开启简单JSON功能，将不标准的JSON数据自己处理装配，修改默认字段，
    var settting = {
        "data": {
            "simpleData": {
                "enable": true,
                "pIdKey":"categoryId"
            },
            //修改默认对应的数据库字段
            "key":{
                "name":"title",
            }
        },
        "check":{
            "enable":true
        }
    };
    $.fn.zTree.init($("#authTreeDemo"), settting, authList);
    var treeObj=$.fn.zTree.getZTreeObj("authTreeDemo");
    treeObj.expandAll(true);
    var ajaxReturn=$.ajax({
        "url":"get/AuthId.json",
        "type":"post",
        "data":{
            "roleId":window.roleid
        },
        "dataType":"json",
        "async":false
    })
    if (ajaxReturn.status!=200){
        layer.msg("请求错误，状态码为："+ajaxReturn.status+"错误信息："+ajaxReturn.statusText);
        return
    }
    //获取到当前角色的权限内容ID集
    var authIds=ajaxReturn.responseJSON.data;
    for(var i=0;i<authIds.length;i++){
        var authId=authIds[i];
        //第一个参数表示每个节点的id值,是obj自带的属性，代表每个节点的属性,而每个Node又有自己的属性包括id
        var treeNode=treeObj.getNodeByParam("id",authId);
        //第一个代表节点，第二个代表是否选中，第三个代表是否有联动
        treeObj.checkNode(treeNode,true,false);
    }



}