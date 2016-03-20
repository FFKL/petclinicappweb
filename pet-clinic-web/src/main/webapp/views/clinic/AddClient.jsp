<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Добавление клиента</title>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript">
            function addClient() {
                if (validate()) {
                    $('#clientName').css('background-color', '');
                    document.getElementById("form").submit();

                } else {
                    alert("Заполните поля!");
                }
            }

            function validate() {
                var result = true;
                if ($('#clientName').val() == '') {
                    $('#clientName').css('background-color', 'rgba(255, 0, 45, 0.55)');
                    result = false;
                }
                return result;
            }
        </script>
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
                <form id="form" action="${pageContext.servletContext.contextPath}/clinic/create" method="POST">
                    <div class="element-block">
                        <div class="element">Имя клиента: </div>
                        <div class="element"><input type="text" name="clientName"></div>
                    </div>
                    <div class="element-block">
                        <div class="element">Имя питомца: </div>
                        <div class="element"><input type="text" name="petName"></div>
                        <div class="element">Тип питомца: </div>
                        <div class="element">
                            <select name="petType">
                                <option value="Cat">Cat</option>
                                <option value="Dog">Dog</option>
                                <option value="Default">DefaultPet</option>
                            </select>
                        </div>
                    </div>
                    <div class="element"><input type="button" value="Добавить" onclick="return addClient()"/></div>
                    <div class="message">${message}</div>
                </form>
            </div>
        </div>
    </body>
</html>
