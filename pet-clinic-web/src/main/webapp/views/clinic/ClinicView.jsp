<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/views/clinic/AddClient.jsp">Add client</a>
<a href="${pageContext.servletContext.contextPath}/clinic/search">Search client</a>
<table border="1">
    <tr>
        <td>ID</td>
        <td>CLIENT_NAME</td>
        <td>PET_VOICE</td>
        <td>PET_NAME</td>
        <td>Actions</td>
    </tr>
    <c:forEach items="${clients}" var="client" varStatus="status">
        <tr valign="top">
            <td>${client.getId()}</td>
            <td>${client.getClientName()}</td>
            <td>${client.getPet().makeSound()}</td>
            <td>${client.getPet().getName()}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/clinic/edit?id=${client.getId()}">Edit</a>
                <a href="${pageContext.servletContext.contextPath}/clinic/delete?id=${client.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
