<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/create" method="POST">
    <table>
        <tr>
            <td align="right" >Client ID: </td>
            <td>
                <input type="text" name="id">
            </td>
            <td align="right" >Owner name: </td>
            <td>
                <input type="text" name="owner">
            </td>
            <td align="right" >Pet name: </td>
            <td>
                <input type="text" name="pet">
            </td>
            <td align="right" >Pet type: </td>
            <td>
                <input type="text" name="type">
            </td>
            <td><input type="submit" align="center" value="Submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>
