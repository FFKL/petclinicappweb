<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/search" method="POST">
    <table>
        <tr>
            <td align="right" >Owner name: </td>
            <td>
                <input type="text" name="owner">
            </td>
            <td align="right" >Pet name: </td>
            <td>
                <input type="text" name="pet">
            </td>
            <td><input type="submit" align="center" value="Edit"/></td>
        </tr>
    </table>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>CLIENT_NAME</td>
            <td>PET_VOICE</td>
            <td>PET_NAME</td>
            <td>Actions</td>
        </tr>
        <c:forEach items="${result}" var="result" varStatus="status">
        <tr valign="top">
            <td>${result.getId()}</td>
            <td>${result.getClientName()}</td>
            <td>${result.getPet().makeSound()}</td>
            <td>${result.getPet().getName()}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/clinic/edit?id=${result.getId()}">Edit</a>
                <a href="${pageContext.servletContext.contextPath}/clinic/delete?id=${result.getId()}">Delete</a>
            </td>
        </tr>
        </c:forEach>
</form>
</body>
</html>
