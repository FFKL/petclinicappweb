<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Добавление клиента</title>
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
                <form action="${pageContext.servletContext.contextPath}/clinic/create" method="POST">
                    <div class="element-block">
                        <div class="element">ID: </div>
                        <div class="element"><input type="text" name="id"></div>
                        <div class="element">Имя клиента: </div>
                        <div class="element"><input type="text" name="client name"></div>
                    </div>
                    <div class="element-block">
                        <div class="element">Имя питомца: </div>
                        <div class="element"><input type="text" name="pet name"></div>
                        <div class="element">Тип питомца: </div>
                        <div class="element">
                            <select name="type">
                                <option value="Cat">Cat</option>
                                <option value="Dog">Dog</option>
                                <option value="Default">DefaultPet</option>
                            </select>
                        </div>
                    </div>
                    <div class="element"><input type="submit" align="center" value="Добавить"/></div>
                    <div class="message">${message}</div>
                </form>
            </div>
        </div>
    </body>
</html>
