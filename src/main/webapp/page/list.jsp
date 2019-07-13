<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 首页</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
    <DIV class=search>

        <c:if test="${sessionScope.user==null}">
            <LABEL class="ui-green searchs">
                <a href="login.jsp" target="_self">登录呀沙雕</a>
            </LABEL>
        </c:if>
        <c:if test="${sessionScope.user!=null}">
            欢迎,小${sessionScope.user.name}
            <LABEL class=ui-green>
                <INPUT onclick='document.location="login.jsp"' value="退  出" type=button>
            </LABEL>
        </c:if>

    </DIV>
</DIV>
<DIV id=navbar class=wrap>
    <DL class="search clearfix">
        <FORM id=sform method=post action=searchPage>
            <c:if test="${info.pages>0}">
                <%--隐藏域添加传送页码--%>
            <input type="hidden" id="page" name="page" value="1">
            <DT>
                <UL>
                    <LI class=bold>房屋信息</LI>
                    <LI>标题：<INPUT class=text type=text name=title value="${houseParam.title}">
                        <LABEL class=ui-blue>
                            <INPUT value=搜索房屋 type=submit name=search>
                        </LABEL>
                    </LI>
                </UL>
            </DT>
            <DD>
                <UL>
                    <LI class=first>最小价格</LI>
                    <LI><input type="number" style="width: 70px;" id="min_price" name="min_price"
                               value="${houseParam.min_price}"></LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>最大价格</LI>
                    <LI><input type="number" style="width: 70px;" id="max_price" name="max_price"
                               value="${houseParam.max_price}"></LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>房屋位置</LI>
                    <LI>区域：<SELECT id=did name=did>
                        <OPTION value="">不限</OPTION>
                    </SELECT></LI>
                    <LI> 街道：<SELECT id=streetId name=streetId>
                        <OPTION value="">不限</OPTION>
                    </SELECT>
                    </LI>
                </UL>
            </DD>
            <DD>
                <UL>
                    <LI class=first>房型</LI>
                    <LI><SELECT name=typeId id="typeId">
                        <OPTION value="">不限</OPTION>
                    </SELECT></LI>
                </UL>
            </DD>
        </FORM>
    </DL>
</DIV>
<DIV class="main wrap">
    <TABLE class=house-list>
        <TBODY>

        <c:forEach items="${info.list}" var="house">
            <%--隐藏域来传送id--%>
            <input type="hidden" id="id" name="id" value="${house.id}">
            <TR>
                <TD class=house-thumb>
                    <span><A href="details" target="_blank">
                        <img src="http://localhost:80${house.path}" width="100" height="75" alt="">
                    </a></span></TD>
                <TD>
                    <DL>
                        <DT><A href="details.jsp" target="_blank">${house.title}</A></DT>
                        <DD>${house.dname}区${house.sname},${house.floorage}平米
                            <BR>联系方式：${house.contact}</DD>
                    </DL>
                </TD>
                <TD class=house-type>${house.tname}</TD>
                <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD>
            </TR>
        </c:forEach>
        </TBODY>
    </TABLE>
    <DIV class=pager>
        <UL>
            <LI class=current><A href="javascript:do_page(1)">首页</A></LI>
            <LI><A href="javascript:do_page(${info.prePage==0?1:info.prePage})">上一页</A></LI>
            <LI><A href="javascript:do_page(${info.nextPage==0?info.pages:info.nextPage})">下一页</A></LI>
            <LI><A href="javascript:do_page(${info.pages})">末页</A></LI>
        </UL>
        <SPAN class=total>${info.pageNum}/${info.pages}页</SPAN></DIV>
    </c:if>
    <c:if test="${info.pages==0}">
        <center style="color: red; font-size: 24px;">暂无出租房信息</center>
        <a href="searchPage">返回首页</a>
    </c:if>
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
    function do_page(dn) {
        $("#page").val(dn);
        $("#sform").submit();
    }

    $(function () {
        //类型初始化
        $.post("getTypeList", null, function (data) {
            for (var i = 0; i < data.length; i++) {
                var node = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                $("#typeId").append(node);
            }
            $("#typeId").val(${houseParam.typeId});
        }, "json");

        //区域初始化
        $.post("getDistrictList", null, function (data) {
            for (var i = 0; i < data.length; i++) {
                var node = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                $("#did").append(node);
            }
            $("#did").val(${houseParam.did});

            //然后再街道初始化
            var did = $("#did").val();
            $.post("getStreetByDid", {"did": did}, function (data) {
                for (var i = 0; i < data.length; i++) {
                    var node = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                    $("#streetId").append(node);
                }
                $("#streetId").val(${houseParam.streetId});
            });
        }, "json");

        //改变区域来改变街道
        $("#did").change(function () {
            var did = $(this).val();
            $.post("getStreetByDid", {"did": did}, function (data) {
                $("#streetId>option:gt(0)").remove();
                for (var i = 0; i < data.length; i++) {
                    var node = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                    $("#streetId").append(node);
                }
            });
        });
    })
</script>
</HTML>
