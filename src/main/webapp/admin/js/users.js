$(function () {
    //使用datagrid显示区域
    $('#dg').datagrid({

        /*//加一个方法，隔行变色
        rowStyler: function (index) {
            if (index % 2 == 0) {
                return 'background-color:blue;color:pink;';
            }
        },
*/
        toolbar: '#tb',//表格工具栏，增删查
        fit: true,//设置高自适应
        fitColumns: true,//设置为true为宽自适应，防止出现水平滚动
        striped: true,//隔行显示行背景
        rownumbers: true,//在表格左侧显示行数
        //singleSelect: true,//设置为true时只允许单选,如果有复选框就不能要这个
        pagePosition: 'botton',//分页栏的位置
        //sortName:'createdate',//按照某个字段名排序
        //remoteSort:false,//设置为true在服务端排序，设置为false时在客户端排序
        //sortOrder:'desc',


        title: "区域信息",
        url: 'getUsers',  //服务器地址
        pagination: true,  //启用分页
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 9, //每页三条
        columns: [[
            {checkbox: true},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'name', title: '姓名', width: 100, align: 'left'},
            {field: 'telephone', title: '电话', width: 100, align: 'left'},
            {
                field: 'a', title: '操作', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    return "<a href='javascript:deleteUsers(" + row.id + ")'>删除</a>";
                }
            }
        ]]
    });
});

//打开窗口,传入id+提示信息
function open(id, msg) {
    $("#" + id).dialog('open').dialog('setTitle', msg);
}

//关闭窗口
function close(id) {
    $("#" + id).dialog('close');
}

//表单异步提交添加
function SaveDialog() {
    $("#addForm").form('submit', {
        url: 'addUsers',
        success: function (data) {
            //返回的是一个字符串，需要转换成json对象
            data = $.parseJSON(data);
            if (data.result == 0) {
                $.messager.alert("系统提示", "保存失败");
                return;
            } else {
                close("addDialog");
                $.messager.alert("系统提示", "保存成功");
            }
            $("#dg").datagrid('reload'); //刷新
            $("#addForm").form("clear");//清空数据
        }
    });
}

//修改回显操作
function updateUsers() {
    var selects = $("#dg").datagrid("getSelections");//返回数组
    if (selects.length != 1) {
        $.messager.alert("提示框呀", "只能选择一行", "info");
        return;
    }
    //打开窗口
    open("upDialog", "修改信息");
    //如果列表的值刚好够修改的值，那就可以直接方便来
    //$('#upForm').form('load',selects[0]);

    //如果列表值不够添加值，需要ajax查询回显
    $.post("getUsersById", {"id": selects[0].id}, function (data) {
        //回显
        $("#upDialog").form("load", data);
    }, "json");
}

//修改操作
function upDialog() {
    $('#upForm').form('submit', {
        url: "upUsers",
        success: function (data) {
            data = $.parseJSON(data);
            if (data.result == 0) {
                $.messager.alert("系统提示", "修改失败");
                return;
            }
            close("upDialog");
            $.messager.alert("系统提示", "修改成功");
            $("#dg").datagrid('reload'); //刷新
        }
    });
}

//单条删除
function deleteUsers(id) {
    $.messager.confirm('删除框', '删除？？？？', function (r) {
        if (r) {
            $.post("deleteUsers", {"id": id}, function (data) {
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
function deleteMoreUsers() {
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
                $.post("deleteMoreUsers", {"ids": arr.join()}, function (data) {
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
function search1() {
    var name = $("#name").val();
    var telephone = $("#telephone").val();
    $("#dg").datagrid("load",{"name":name,"telephone":telephone});
}