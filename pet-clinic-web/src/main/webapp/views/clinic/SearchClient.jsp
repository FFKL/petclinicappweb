<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<h1>Search</h1>
<hr>
<form action="${pageContext.servletContext.contextPath}/clinic/search" method="POST">
    <table>
        <tr>
            <td align="right" >Owner name: </td>
            <td>
                <input type="text" name="client name">
            </td>
            <td align="right" >Pet name: </td>
            <td>
                <input type="text" name="pet name">
            </td>
            <td><input type="submit" align="center" value="Edit"/></td>
        </tr>
    </table>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>CLIENT_NAME</td>
            <td>PETS</td>
            <td>Actions</td>
        </tr>
        <c:forEach items="${result}" var="result" varStatus="status">
        <tr valign="top">
            <td>${result.getId()}</td>
            <td>${result.getClientName()}</td>
            <td>${client.getPets().size()}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/clinic/addpet?id=${client.getId()}">Add pet</a>
                <a href="${pageContext.servletContext.contextPath}/clinic/edit?id=${client.getId()}">Edit</a>
                <a href="${pageContext.servletContext.contextPath}/clinic/delete?id=${client.getId()}">Delete</a>
            </td>
        </tr>
        </c:forEach>
</form>
</body>
</html>
