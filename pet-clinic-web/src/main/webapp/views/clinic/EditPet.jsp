<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Изменение имени питомца</title>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript">
            function editPet() {
                if (validate()) {
                    $('#petName').css('background-color', '');
                    document.getElementById("form").submit();
                } else {
                    alert("Заполните поля!");
                }
            }

            function validate() {
                var result = true;
                if ($('#petName').val() == '') {
                    $('#petName').css('background-color', 'rgba(255, 0, 45, 0.55)');
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
                <form id="form" action="${pageContext.servletContext.contextPath}/clinic/editpet" method="POST">
                    <div class="element">New pet name: </div>
                    <div class="element"><input type="text" name="petName" id="petName"></div>
                    <input type="hidden" name="id" value="${pet.id}">
                    <div class="element"><input type="button" value="Изменить" onclick="return editPet()"/></div>
                </form>
            </div>
        </div>
    </body>
</html>
