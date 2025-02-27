<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>Обменник валют</title>
    <style>
            .container {
                display: flex;
                align-items: flex-start; /* Выровняет элементы по верхнему краю */
                align-items: center;
                gap: 20px;
            }
            .curList, .addCurForm {
                        background: #f9f9f9; /* Светлый фон */
                        border-radius: 10px;
                        padding: 20px;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }
            .curList {
                margin-right: 40px; /* Отступ между таблицей и формой */
            }

            body {
                        display: flex;
                        justify-content: center; /* Центрирует содержимое по горизонтали */
                        align-items: center; /* Центрирует по вертикали (если нужно) */
                        margin: 0;
                    }

        </style>
</head>
<body>
<div class="container">
<div class="curList">
    <h1>Список валют</h1>
    <table border="1" style="text-align: center">
        <tr>
            <th>Код валюты</th>
            <th>Полное название</th>
            <th>Обозначение</th>
        </tr>
        <c:forEach var="currency" items="${curList}">


            <tr>
                <td>${currency.code}</td>
                <td>${currency.fullName}</td>
                <td>${currency.sign}</td>
                <td>
                <button type="button" onClick="deleteCurrency(${currency.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>

        <script>
            function deleteCurrency(id) {
                fetch('/currencies?id=' + id, {
                    method: 'DELETE'
                })
                .then(response => {
                        window.location.reload();
                })
                .catch(error => console.error('Ошибка:', error));
            }
        </script>
    </table>
</div>
    <div class="addCurForm">

        <form name="AddCurForm" action="" method="post">
            <h1>Добавить валюту</h1>
            <p>Код валюты <input name="code" type="text" placeholder="USD" required></p>
            <p>Название валюты <input name="fullName" type="text" placeholder="Dollar" required></p>
            <p>Символ валюты <input name="sign" type="text" placeholder="$" required></p>
            <p><input value="Добавить" type="submit"></p>
        </form>
        <form action="/exchanges">
            <p><input value="Обменные курсы ->" type="submit"></p>
        </form>
    </div>


</div>
</body>
</html>
