<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/edit" method="POST">
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
</form>
</body>
</html>
