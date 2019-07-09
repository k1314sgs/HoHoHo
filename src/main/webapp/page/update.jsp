<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>房屋信息修改</DT>
            <DD class=past>填写房屋信息</DD>
        </DL>
        <DIV class=box>
            <FORM id=update_action method=post name=update_action action="updateHouse" enctype="multipart/form-data">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <%--用来修改的--%>
                            <input type="hidden" value="${house.id}" name="id" id="id">

                            <TD class=field>标　　题：</TD>
                            <TD><INPUT id=update_action_title class=text type=text name=title
                                       value="${house.title}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD><SELECT class=text name=typeId id="typeId"></SELECT></TD>
                        </TR>
                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD><INPUT id=floorage class=text type=text name=floorage
                                       value="${house.floorage}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=price class=text type=text name=price
                                       value="${house.price}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>发布日期：</TD>
                            <TD><INPUT class=text type=date name=pubdate
                                       value='<fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"/>'></TD>
                        </TR>
                        <TR>
                            <TD class=field>位　　置：</TD>
                            <TD>区：<SELECT class=text name=districtId id="districtId">
                                <OPTION value="">--请选择--</OPTION>
                            </SELECT>
                                街：<SELECT class=text name=streetId id="streetId">
                                    <OPTION value="">--请选择--</OPTION>
                                </SELECT>
                            </TD>
                        </TR>

                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text type=text name=contact
                                       value="${house.contact}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>选择图片：</TD>
                            <TD><input type="file" id="pfile" name="pfile"></TD>
                            <%--隐藏域用来发送原来的图片数据--%>
                            <input type="hidden" id="path" name="path" value="${house.path}">
                        </TR>
                        <TR>
                            <TD>
                                <img width="100px" src="http://localhost:80/${house.path}" alt="tupian">
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons><INPUT value=立即发布 type=submit></DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
<script type="application/javascript" src="../admin/js/jquery-1.8.3.js"></script>
<script type="application/javascript" src="../admin/js/jquery.easyui.min.js"></script>
<script type="application/javascript">
    $(function () {
        //类型异步提交
        $.post("getTypeList", null, function (data) {
            for (var i = 0; i < data.length; i++) {
                var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                $("#typeId").append(node);
            }
            $("#typeId").val(${house.typeId});
        }, "json");

        //区域异步提交
        $.post("getDistrictList", null, function (data) {
            for (var i = 0; i < data.length; i++) {
                var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                $("#districtId").append(node);
            }
            $("#districtId").val(${house.did});

            //街道显示要在区域显示的里面，先后顺序
            var did = $("#districtId").val();
            $.post("getStreetByDid", {"did": did}, function (data) {
                $("#streetId>option:gt(0)").remove();//移除
                for (var i = 0; i < data.length; i++) {
                    var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                    $("#streetId").append(node);
                }
                $("#streetId").val(${house.streetId});
            }, "json");
        }, "json");

        //改变区域来改变街道
        $("#districtId").change(function () {
            var did = $(this).val();
            $.post("getStreetByDid", {"did": did}, function (data) {
                $("#streetId>option:gt(0)").remove();//移除
                for (var i = 0; i < data.length; i++) {
                    var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                    $("#streetId").append(node);
                }
            }, "json");
        });

        /*//图片上传,这个不对
        $("#pfile").change(function () {
            var pfile = $("#pfile").val();
            $.post("addHouse",{"pfile":pfile},function (data) {
                var path="http://localhost:80/"+data;
                $("#pfile").val(path);
            });
        });*/

    });
</script>
</HTML>
