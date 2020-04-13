function generateTree() {
    $.ajax({
        "url":"menu/get/whole/tree.json",
        "type":"post",
        "dataType":"json",
        "success":function (response) {
            var result=response.result;
            if (result=="SUCCESS"){
                var setting={
                    "view":{

                        "addDiyDom":myAddDiyDom,
                        //控制鼠标移动的效果
                        "addHoverDom":myAddHoverDom,
                        "removeHoverDom":myRemoveHoverDom
                    },
                    "data":{
                        "key":{
                            "url":"null"
                        }
                    }
                };
                var zNodes=response.data;
                //zTree根据返回的标准的JSON数据进行装配树
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if (result=="FAILED"){
                layer.msg(response.message)
            }
        }
    });
}
//给每个menu绑定它自己的icon字段的属性生成图标
function myAddDiyDom(treeId, treeNode) {
    // treeId 是整个树形结构附着的 ul 标签的 id
// console.log("treeId="+treeId);
// treeNode是当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性
// console.log(treeNode);
// zTree 生成 id 的规则
// 例子：treeDemo_7_ico
// 解析：ul标签的id_当前节点的序号_功能
// 提示：“ul 标签的id_当前节点的序号”部分可以通过访问 treeNode 的 tId 属性得到
// 根据 id 的生成规则拼接出来span标签的id
     var spanId = treeNode.tId + "_ico";
// 根据控制图标的 span 标签的 id 找到这个 span 标签
// 删除旧的 class添加新的class
    $("#"+spanId) .removeClass() .addClass(treeNode.icon);
}

function myAddHoverDom(treeId, treeNode) {
    var editBtn="<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs editBtn' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    var removeBtn="<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs removeBtn' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var addBtn="<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs addBtn' style='margin-left:10px;padding-top:0px;' href='#' title='增加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var btnHTML;
    var level=treeNode.level;
    //根节点
    if (level==0){
        btnHTML=addBtn;
    }
    //分支节点
    if (level==1){
        btnHTML=addBtn+editBtn;
        var length=treeNode.children.length;
        if (length==0){
            btnHTML=btnHTML+removeBtn;
        }
    }
    //叶子结点
    if (level==2){
        btnHTML=removeBtn+editBtn;
    }
    var btnGroupId=treeNode.tId+"_btmGrp";
    //避免同一个id的元素多次加入
    if ($("#"+btnGroupId).length>0){
        return;
    }
    var anchorId=treeNode.tId+"_a";
    //在超链接的后边拼接span图标
    $("#"+anchorId).after("<span id='"+btnGroupId+"'>"+btnHTML+"</span>");
}
function myRemoveHoverDom(treeId, treeNode) {
    var btnGroupId=treeNode.tId+"_btmGrp";
    $("#"+btnGroupId).remove();
}