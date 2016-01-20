<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Delete Pet</title>
</head>
<body>
    <table border="1">
        <tr>
            <td>PET_TYPE</td>
            <td>PET_NAME</td>
            <td>Actions</td>
        </tr>
        <c:forEach items="${pets}" var="pet" varStatus="status">
            <tr valign="top">
                <td>${pet.getPetType()}</td>
                <td>${pet.getName()}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/clinic/delpet?id=${id}&name=${pet.getName()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
