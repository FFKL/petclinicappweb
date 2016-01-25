<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Клиника домашних животных</title>
</head>
<body>
Клиника домашних животных
<hr>
<div>
<a href="${pageContext.servletContext.contextPath}/views/clinic/AddClient.jsp">Add client</a>
<a href="${pageContext.servletContext.contextPath}/clinic/search">Search client</a>
</div>
<div>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>CLIENT_NAME</td>
            <td>PETS</td>
            <td>Actions</td>
        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr valign="top">
                <td><a href="${pageContext.servletContext.contextPath}/clinic/client?id=${client.getId()}">${client.getId()}</a></td>
                <td>${client.getClientName()}</td>
                <td>${client.getPets().size()}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/clinic/delete?id=${client.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>