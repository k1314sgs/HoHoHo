<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script src="js/house.js"></script>
    <script type="application/javascript">
        $(function () {

            //使用datagrid显示区域
            $('#dg').datagrid({


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


                title: "房子信息",
                url: 'searchHouse?ispass=${param.ispass}',  //服务器地址
                pagination: true,  //启用分页
                pageList: [3, 6, 9, 15, 20], //设置每页大小
                pageSize: 9, //每页三条
                columns: [[
                    {checkbox: true},
                    {field: 'id', title: '编号', width: 100, align: 'left'},
                    {field: 'title', title: '标题', width: 100, align: 'left'},
                    {field: 'description', title: '描述', width: 100, align: 'left'},
                    {field: 'price', title: '价格', width: 100, align: 'left'},
                    {field: 'floorage', title: '面积', width: 100, align: 'left'},
                    {field: 'contact', title: '电话', width: 100, align: 'left'},
                    {field: 'path', title: '图片', width: 100, align: 'left'},
                    {field: 'tname', title: '类型', width: 100, align: 'left'},
                    {field: 'dname', title: '区域', width: 100, align: 'left'},
                    {field: 'sname', title: '街道', width: 100, align: 'left'},
                    {
                        field: 'pubdate', title: '发布日期', width: 100, align: 'left',
                        formatter: function (value, row, index) {
                            var date = new Date(value);
                            var fullYear = date.getFullYear();
                            var month = date.getMonth() + 1;
                            var day = date.getDate();
                            return fullYear + "年" + month + "月" + day + "日";
                        }
                    },
                    {
                        field: 'a', title: '操作', width: 100, align: 'left',
                        formatter: function (value, row, index) {
                            if (${param.ispass==0}) {
                                return "<a href='javascript:passHouse(" + row.id + ")'>审核</a>";
                            }
                            return "已审核";
                        }
                    }
                ]]
            });
        });

    </script>

</head>
<body>

<!--显示区域-->
<table id="dg"></table>
<%--表格工具栏--%>
<div id="tb">
    标题<input type="text" name="title" id="title" value="">
    价格<input type="number" name="min_price" id="min_price" value="">
    ——<input type="number" name="max_price" id="max_price" value="">
    <a href="javascript:search2()" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true">查询</a>
</div>

</body>
</html>
