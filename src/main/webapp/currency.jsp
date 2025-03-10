<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html>

        <head>
            <title>Currencies</title>
            <link rel="stylesheet" type="text/css" href="css/styles.css">
            <script src="js/scripts.js"></script>
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
                    </table>
                </div>
                <div class="addForm">

                    <form name="AddCurForm" action="" method="post">
                        <h1>Добавить валюту</h1>
                        <p>Код валюты <input name="code" type="text" placeholder="USD" required></p>
                        <p>Название валюты <input name="fullName" type="text" placeholder="Dollar" required></p>
                        <p>Символ валюты <input name="sign" type="text" placeholder="$" required></p>
                        <p><input value="Добавить" type="submit"></p>
                    </form>
                    <form action="exchangeRates">
                        <p><input value="Обменные курсы ->" type="submit"></p>
                    </form>
                </div>
            </div>
        </body>

        </html>