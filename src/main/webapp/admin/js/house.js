//表单异步提交修改审核
function passHouse(id) {
    $.post("houseYes", {"id": id}, function (data) {
        if (data == 0) {
            $.messager.alert("系统提示", "添加失败");
            return;
        }
        $("#dg").datagrid("reload");//刷新
    }, "json");
}


//单条删除
function deleteHouse(id) {
    $.messager.confirm('删除框', '删除？？？？', function (r) {
        if (r) {
            $.post("deleteHouse", {"id": id}, function (data) {
                //后面指定了json的格式，所以不用转换，data = $.parseJSON(data);
                if (data == 0) {
                    $.messager.alert("系统提示", "删除失败");
                    return;
                }
                $("#dg").datagrid('reload'); //刷新
            }, "json");
        }
    });
}

//多条删除
function deleteMoreHouse() {
    var selects = $("#dg").datagrid("getSelections");//返回数组
    if (selects.length == 0) {
        alert("你删空气呢？？？选择一项啊至少");
    } else {
        var arr = new Array(selects.length);
        for (var i = 0; i < selects.length; i++) {
            arr[i] = selects[i].id;
        }
        $.messager.confirm('删除框', '删除？？？？', function (r) {
            if (r) {
                $.post("deleteMoreHouse", {"ids": arr.join()}, function (data) {
                    if (data.result == 0) {
                        $.messager.alert("系统提示", "删除失败");
                        return;
                    }
                    $("#dg").datagrid('reload'); //刷新
                }, "json");
            }
        });
    }
}

//查询方法
function search2() {
    var title = $("#title").val();
    var min_price = $("#min_price").val();
    var max_price = $("#max_price").val();
    $("#dg").datagrid("load",{"title":title,"min_price":min_price,"max_price":max_price});
}