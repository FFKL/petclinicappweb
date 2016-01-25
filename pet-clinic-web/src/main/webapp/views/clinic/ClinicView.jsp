<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Клиника домашних животных</title>
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
        <div class="container">
            <div class="segment">
                <div class="top"><img src="../images/dog.png"></div>
            </div>
        </div>
        <div class="container">
            <div class="button"><a href="${pageContext.servletContext.contextPath}/clinic/create">Добавить клиента</a></div>
            <div class="button"><a href="${pageContext.servletContext.contextPath}/clinic/search">Поиск</a></div>
        </div>
        <div class="container">
            <div class="segment">
                <table border="1">
                    <tr>
                        <td>ID</td>
                        <td>Имя клиента</td>
                        <td>Количество питомцев</td>
                        <td>Действия</td>
                    </tr>
                    <c:forEach items="${clients}" var="client" varStatus="status">
                        <tr valign="top">
                            <td><a href="${pageContext.servletContext.contextPath}/clinic/client?id=${client.getId()}">${client.getId()}</a></td>
                            <td>${client.getClientName()}</td>
                            <td>${client.getPets().size()}</td>
                            <td><a href="${pageContext.servletContext.contextPath}/clinic/delete?id=${client.getId()}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>