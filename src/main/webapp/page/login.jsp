<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <TITLE>青鸟租房 - 用户登录</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">

    <style type="text/css">
        .code {
            font-family: Arial;
            font-style: italic;
            font-weight: bold;
            border: 0;
            letter-spacing: 3px;
            color: blue;
        }
    </style>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DIV class=box>
            <H4>用户登录</H4>
            <FORM id=user method=post name=user action="login">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD>
                                <INPUT id=name class=text type=text name=name>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>密　　码：</TD>
                            <TD>
                                <INPUT id=password class=text type=password name=password>
                            </TD>
                        </TR>
                        <tr>
                            <td class="field">手机号：</td>
                            <td><INPUT type=text size="11" class=text name=telephone id=telephone></td>
                        </tr>
                        <tr>
                            <td class="field">短信验证码：</td>
                            <td><INPUT type=text class=text name=inputCode id=inputCode>
                                <input id="getCode" type="button" value="获取短信验证码"></td>
                        </tr>

                        <tr>
                            <td class="field">验 证 码：</td>
                            <td>
                                <INPUT type=text class=text name=veryCode id=verycode>
                                <INPUT type=button id=checkCode class=code onclick=createCode()>
                                <a href="#" onclick="createCode()">看不清楚</a>
                            </td>
                        </tr>
                        <TR>
                            <TD colspan="2" style="text-align: center" bgcolor="aqua">${info}</TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT onclick="do_submit()" value=登陆 type=button>
                        <INPUT onclick="location.href='regs.jsp'" value=注册 type=button>
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
    var code;//全局验证码
    var objTime;//这个是周期函数的引用对象，不用的时候再清除
    $(function () {
        createCode();//开局创建验证码

        //短信验证码异步提交
        $("#getCode").click(function () {

            $.post("getSmsCode", {"telephone": $("#telephone").val()}, function (data) {

                if (data.result > 0) {
                    //倒计时，每隔一秒种调取函数一次
                    objTime = setInterval("backTime()", 1000);
                } else {
                    alert("发送失败");
                }
            }, "json");
        });
    });

    function createCode() {
        code = "";
        var codelength = 4;//验证码长度
        //产生随机数
        var array = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        for (var i = 0; i < codelength; i++) {
            var index = Math.floor(Math.random() * 36);
            code += array[index];
        }
        $("#checkCode").val(code);
    }

    function do_submit() {
        var username = $("#name").val();
        var password = $("#password").val();
        var verycode = $("#verycode").val().toUpperCase();
        //这个是输入短信验证码
        var inputCode = $("#inputCode").val();

        if (username.length == 0 || password.length == 0 || inputCode.length == 0) {
            alert("填完好不好，憋调皮了");
        } else if (verycode.length == 0) {
            alert("验证码不能为空啊小伙子");
        } else if (verycode != code) {
            alert("验证码都输不对，眼神不好吧");
        } else {
            $("#user").submit();
        }
    }




    //周期函数
    var time = 120;

    function backTime() {
        if (time > 0) {
            time--;
            $("#getCode")[0].disabled=true;//按钮设置为不可用
            $("#getCode").css("padding", "5px 10px");//设置按钮的属性
            $("#getCode").val(time + "秒");//设置按钮的显示值
        } else {
            clearInterval(objTime);//清除周期函数
            $("#getCode")[0].disabled=false;
            $("#getCode").val("重新发送验证码");
        }
    }
</script>
</HTML>