<%@page language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>展示页面</title>
    <script src="js/jquery-3.4.1.min.js"></script>
    <style>
        table{
            margin: auto;
            border: 1px gray solid;
            width: 70%;
        }
        th,td{
            border: 1px gray solid;
        }
    </style>
    <script>
        function changePerson(personId) {
            $.post("login.servlet?changeId="+personId,{"type":"changeperson"},function (data) {
                location.href = "change.html";
            });
        }

        function removePerson(personId) {
            $.post("login.servlet?deleteId="+personId,{"type":"removeperson"},function (data) {
                if (data==1){//删除成功
                    location.href = "look.jsp";
                }else{
                    alert("删除失败！请重试！");
                }
            });
        }
    </script>
</head>
<body>
<table border="1" align="center" bgcolor="#ffe4c4">
    <caption align="center" style="color: red">
        请输入排序规则：<form action="login.servlet?type=order" method="post">
                <input type="text" name="choose" value="personid"/>
                <button>排序</button>
              </form>
    </caption>
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>个人电话</th>
        <th>地址</th>
        <th>个人E-mail</th>
        <th>家庭电话</th>
        <th>邮政编码</th>
        <th>修改</th>
        <th>删除</th>
    </tr>
    <!--var：迭代变量 items：取的东西 varStatus：自增的1,2,3...-->
    <c:forEach var="person" items="${sessionScope.ALL_PERSON}" varStatus="j">
        <tr>
            <td>${person.personId}</td>
            <td>${person.personName}</td>
            <td>${person.personSex}</td>
            <td>${person.personPhone}</td>
            <td>${person.personAddress}</td>
            <td>${person.personEmail}</td>
            <td>${person.homePhone}</td>
            <td>${person.postCode}</td>
            <td><button onclick=" changePerson(${person.personId})">修改</button></td>
            <td><button onclick=" removePerson(${person.personId})">删除</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
