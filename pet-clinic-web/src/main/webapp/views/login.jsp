<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.0.min.js"></script>
    <script type="text/javascript">
        function login() {
            if (validateUsername() && validatePassword()) {
                $('#username').css('background-color', '');
                $('#password').css('background-color', '');
                document.getElementById("form").setAttribute("action", "/cpw//j_spring_security_check");
                document.getElementById("form").submit();
            } else {
                alert("Заполните поля!");
            }
        }

        function validateUsername() {
            var result = true;
            if ($('#username').val() == '') {
                $('#username').css('background-color', 'rgba(255, 0, 45, 0.55)');
                result = false;
            }
            return result;
        }
        function validatePassword() {
            var result = true;
            if ($('#password').val() == '') {
                $('#password').css('background-color', 'rgba(255, 0, 45, 0.55)');
                result = false;
            }
            return result;
        }
    </script>
</head>
<body>
<div id="header">
    <div class="container">
        <div class="logo">
            <a href="${pageContext.servletContext.contextPath}/clinic/view"></a>
            <span>Клиника домашних животных</span>
        </div>
    </div>
</div>
<div class="container">
    <div class="segment">
        <form id="form"  method="POST">
            <div class="element-block">
                <div class="element">Логин: </div>
                <div class="element"><input type="text" name="username" id="username"/></div>
                <div class="element">Пароль: </div>
                <div class="element"><input type="password" name="password" id="password"/></div>
            </div>
            <div class="element"><input type="button" value="Войти" onclick="return login()"/></div>
        </form>
        <span>${message}</span>
    </div>
</div>
</body>
</html>
