<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Поиск</title>
        <link rel="stylesheet" href="../css/style.css">
    </head>
    <body>
        <div id="header">
            <div class="container">
                <div class="logo">
                    <a href="${pageContext.servletContext.contextPath}/clinic/view"></a>
                    <span>Клиника домашних животных</span>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="segment">
                <form action="${pageContext.servletContext.contextPath}/clinic/search" method="POST">
                    <div class="element" >Owner name: </div>
                    <div class="element">
                        <input type="text" name="client name">
                    </div>
                    <div class="element">Pet name: </div>
                    <div class="element">
                        <input type="text" name="pet name">
                    </div>
                    <div class="element"><input type="submit" align="center" value="Edit"/></div>
                </form>
            </div>

            <div class="segment">
                <table border="1">
                    <tr>
                        <td>ID</td>
                        <td>Имя клиента</td>
                        <td>Количество питомцев</td>
                    </tr>
                    <c:forEach items="${result}" var="result" varStatus="status">
                        <tr valign="top">
                            <td><a href="${pageContext.servletContext.contextPath}/clinic/client?id=${result.getId()}">${result.getId()}</a></td>
                            <td>${result.getClientName()}</td>
                            <td>${result.getPets().size()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
