<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Клиент</title>
        <link rel="stylesheet" href="../css/style.css">
        <script type="text/javascript" src="../js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript">
            function editClientName() {
                if (validateClient()) {
                    $('#clientName').css('background-color', '');
                    document.getElementById("formname").submit();
                } else {
                    alert("Заполните поля!");
                }
            }
            function addPet() {
                if (validatePet()) {
                    $('#petName').css('background-color', '');
                    document.getElementById("formpet").submit();
                } else {
                    alert("Заполните поля!");
                }
            }

            function validateClient() {
                var result = true;
                if ($('#clientName').val() == '') {
                    $('#clientName').css('background-color', 'rgba(255, 0, 45, 0.55)');
                    result = false;
                }
                return result;
            }
            function validatePet() {
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
            <span>Изменение имени клиента</span>
                <form id="formname" action="${pageContext.servletContext.contextPath}/clinic/edit" method="POST">
                    <div class="element"><input type="text" name="client name" id="clientName" placeholder="Введите имя клиента"></div>
                    <div class="element"><input type="button" value="Изменить" onclick="return editClientName()"/></div>
                </form>
                <input type="button" value="Удалить клиента" onClick='location.href="${pageContext.servletContext.contextPath}/clinic/delete?id=${client.getId()}"'>
            </div>
            <div class="segment">
                <span>Добавление питомца</span>
                <form id="formpet" action="${pageContext.servletContext.contextPath}/clinic/addpet" method="POST">
                    <div class="element"><input type="text" name="pet name" id="petName" placeholder="Введите имя питомца"></div>
                    <div class="element">
                        <select name="type">
                            <option value="Cat">Cat</option>
                            <option value="Dog">Dog</option>
                            <option value="Default">DefaultPet</option>
                        </select>
                    </div>
                    <div class="element"><input type="button" value="Добавить" onclick="return addPet()"/></div>
                </form>
            </div>
            <div class="segment">
                <table border="1">
                    <tr>
                        <td>Тип</td>
                        <td>Имя</td>
                        <td>Действия</td>
                    </tr>
                    <c:forEach items="${pets}" var="pet" varStatus="status">
                        <tr valign="top">
                            <td>${pet.getPetType()}</td>
                            <td>${pet.getName()}</td>
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/clinic/delpet?id=${client.getId()}&name=${pet.getName()}">Delete</a>
                                <a href="${pageContext.servletContext.contextPath}/clinic/editpet?id=${client.getId()}&name=${pet.getName()}">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
