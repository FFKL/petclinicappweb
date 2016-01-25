<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Клиент</title>
    <link rel="stylesheet" href="../css/style.css">
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
<hr>
<div class="container">
    <div class="segment">
    <span>Изменение имени клиента</span>
        <form action="${pageContext.servletContext.contextPath}/clinic/edit" method="POST">
            <div><input type="text" name="client name" placeholder="Введите имя клиента"></div>
            <div><input type="submit" align="center" value="Изменить"/></div>
        </form>
    </div>
<hr>
<div class="segment">
    Добавление питомца
    <form action="${pageContext.servletContext.contextPath}/clinic/addpet" method="POST">
        <div><input type="text" name="pet name" placeholder="Введите имя питомца"></div>
        <div>Выберите тип питомца:
            <select name="type">
                <option value="Cat">Cat</option>
                <option value="Dog">Dog</option>
                <option value="Default">DefaultPet</option>
            </select></div>
        <div><input type="submit" align="center" value="Добавить"/></div>
    </form>
</div>
<hr>
    <div class="segment">
        <table border="1">
            <tr>
                <td>Тип</td>
                <td>Имя</td>
                <td>Действия</td>
            </tr>
            <c:forEach items="${pets}" var="pet" varStatus="status">
                <tr valign="top">
                    <td>${pet.getPetType()}</td>
                    <td>${pet.getName()}</td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/clinic/delpet?id=${client.getId()}&name=${pet.getName()}">Delete</a>
                        <a href="${pageContext.servletContext.contextPath}/clinic/editpet?id=${client.getId()}&name=${pet.getName()}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
