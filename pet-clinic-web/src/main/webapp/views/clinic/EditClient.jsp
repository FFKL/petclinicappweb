<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h1>Edit</h1>
<hr>
<form action="${pageContext.servletContext.contextPath}/clinic/edit" method="POST">
    <table>
        <tr>
            <td align="right" >Owner name: </td>
            <td>
                <input type="text" name="client name">
            </td>

            <td><input type="submit" align="center" value="Edit"/></td>
        </tr>
    </table>
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
                    <a href="${pageContext.servletContext.contextPath}/clinic/editpet?name=${pet.getName()}&clientid=${client.getId()}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
