<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
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
            <DT>新用户注册</DT>
            <DD class=past>填写个人信息</DD>
        </DL>
        <DIV class=box>
            <FORM action=regs method="post" id="regsForm">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD>
                                <INPUT id="name" class=text type=text name=name>
                                <span id="notice"></span>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>密　　码：</TD>
                            <TD><INPUT id="password" class=text type=password name=password></TD>
                        </TR>
                        <TR>
                            <TD class=field>确认密码：</TD>
                            <TD>
                                <INPUT id="repassword" class=text type=password name=repassword>
                                <span id="psd"></span>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>电　　话：</TD>
                            <TD><INPUT class=text type=text name=telephone></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT onclick="do_submit()" value=立即注册 type=button>
                    </DIV>
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
        $("#name").blur(function () {
            //异步请求
            var username = $("#name").val();
            if (username == "") {
                $("#notice").html("用户名不能为空呀小伙子").css("color", "blue");
            }
            $.post("checkname", {"name": username}, function (data) {
                if (data.result == 1) {
                    $("#notice").html("用户名已存在啊沙雕").css("color", "red");
                } else {
                    $("#notice").html("可以用的").css("color", "green");
                }
            }, "json");
        });
        $("#repassword").blur(function () {
            var password = $("#password").val();
            var repassword = $("#repassword").val();
            if (password != repassword) {
                $("#psd").html("两次密码不一致").css("color", "red");
            }else {
                $("#psd").html("密码一致").css("color","green");
            }
        });
    });

    function do_submit() {
        var username = $("#name").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        if (username != "" && password != "" && repassword == password) {
            $("#regsForm").submit();
        } else {
            alert("填完好不好，憋调皮了");
        }
    }
</script>
</HTML>
