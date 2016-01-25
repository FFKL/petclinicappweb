<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Изменение имени питомца</title>
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
                <form action="${pageContext.servletContext.contextPath}/clinic/editpet" method="POST">
                    <div class="element">New pet name: </div>
                    <div class="element"><input type="text" name="pet name"></div>
                    <div class="element"><input type="submit" align="center" value="Edit"/></div>
                </form>
            </div>
        </div>
    </body>
</html>
