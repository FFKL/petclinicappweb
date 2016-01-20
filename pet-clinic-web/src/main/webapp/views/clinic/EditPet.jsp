<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Pet name</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/editpet" method="POST">
    <table>
        <tr>
            <td align="right" >New pet name: </td>
            <td>
                <input type="text" name="pet name">
            </td>

            <td><input type="submit" align="center" value="Edit"/></td>
        </tr>
    </table>
</form>
</body>
</html>
