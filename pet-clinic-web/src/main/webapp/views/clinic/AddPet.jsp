<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add pet</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/addpet" method="POST">
    <table>
        <tr>
            <td align="right" >Pet name: </td>
            <td>
                <input type="text" name="pet name">
            </td>
            <td align="right" >Pet type: </td>
            <td>
                <select name="type">
                    <option value="Cat">Cat</option>
                    <option value="Dog">Dog</option>
                    <option value="Default">DefaultPet</option>
                </select>
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
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
