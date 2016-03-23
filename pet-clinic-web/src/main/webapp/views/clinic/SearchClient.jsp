<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Поиск</title>
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript">
            function search() {
                if (validate()) {
                    $('#clientName').css('background-color', '');
                    $('#petName').css('background-color', '');
                    document.getElementById("form").submit();
                } else {
                    alert("Заполните поля!");
                }
            }

            function validate() {
                var result = true;
                if (($('#clientName').val() == '')&&($('#petName').val() == '')) {
                    $('#clientName').css('background-color', 'rgba(255, 0, 45, 0.55)');
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
                <form id="form" action="${pageContext.servletContext.contextPath}/clinic/search" method="POST">
                    <div class="element" >Owner name: </div>
                    <div class="element">
                        <input type="text" name="clientName" id="clientName">
                    </div>
                    <div class="element">Pet name: </div>
                    <div class="element">
                        <input type="text" name="petName" id="petName">
                    </div>
                    <div class="element"><input type="button" value="Поиск" onclick="return search()"/></div>
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
                            <td>${result.clientName}</td>
                            <td>${result.pets.size()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
