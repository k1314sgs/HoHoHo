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
    <script src="js/district.js"></script>
</head>
<body>
<!--显示区域-->
<table id="dg"></table>
<%--表格工具栏--%>
<div id="tb">
    <a href="javascript:open('addDialog','添加数据')" class="easyui-linkbutton"
       data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="javascript:updateDistrict()" class="easyui-linkbutton"
       data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:deleteMoreDistrict()" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true">批量删除</a>
</div>

<!--添加开始-->
<!--弹窗-->
<div id="addDialog" class="easyui-dialog" buttons="#addDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <!--按钮-->
    <div id="addDialogButtons">
        <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:close('addDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
    <!--表单-->
    <form id="addForm" method="post">
        <table style="line-height: 30px">
            <tr>
                <td>类型名称:</td>
                <td><input type="text" name="name"/></td>
            </tr>
        </table>
    </form>
</div>
<!--添加结束-->

<!--修改开始-->
<!--弹窗-->
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <!--按钮-->
    <div id="upDialogButtons">
        <a href="javascript:upDialog()" class="easyui-linkbutton" iconCls="icon-ok">更新</a>
        <a href="javascript:close('upDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
    <!--表单-->
    <form id="upForm" method="post">
        <table style="line-height: 30px">
            <tr>
                <td>编号:</td>
                <td><input type="text" name="id" readonly/></td>
            </tr>
            <tr>
                <td>类型名称:</td>
                <td><input type="text" name="name"/></td>
            </tr>
        </table>
    </form>
</div>
<!--修改结束-->

<!--显示街道-->
<!--弹窗-->
<div id="showDialog" class="easyui-dialog" style="width: 600px; height: 600px; padding: 10px 20px;" closed="true">
    <!--显示表格-->
    <table id="showTable"></table>
    <div style="line-height: 40px">
        <form id="addStreet" method="post">
            街道名称:
            <input type="text" name="name"/>
            <%--隐藏域用于添加--%>
            <input type="hidden" name="districtid" id="districtid"/>
            <a href="javascript:addStreet()" class="easyui-linkbutton" iconCls="icon-ok">添加</a>
            <a href="javascript:close('showDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </form>
    </div>
</div>
</body>
</html>
