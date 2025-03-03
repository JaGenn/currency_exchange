<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>Exchange Rates</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script src="js/scripts.js"></script>
</head>
    <body>
        <div class="container">
            <div class="exList">
                <h1>Обменные курсы</h1>
                    <table border="1" style="text-align: center">
                        <tr>
                            <th>Currency</th>
                            <th>Exchange Rate</th>

                        </tr>
                        <c:forEach var="exRate" items="${exList}">
                            <tr>
                                <td>${exRate.currencies}</td>
                                <td>${exRate.rate}</td>
                                <td>
                                    <form onsubmit="updateRate(event, '${fn:trim(exRate.currencies)}')" style="text-align: center">
                                        <input type="number" name="rate" step="0.01" placeholder="Input new rate" required>
                                        <button type="submit">Update</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
            </div>
            <div class="addExForm">
                    <form name="AddExForm" action="/exchangeRates" method="post">
                        <h1>Добавить валютную пару</h1>
                        <p>Base currency <input list="currencies" name="baseCurrency" required></p>
                            <datalist id="currencies">
                                <c:forEach var="currency" items="${currencies}">
                                    <option value="${currency.code}">${currency.fullName}</option>
                                </c:forEach>
                            </datalist>
                        <p>Target currency <input list="currencies" name="targetCurrency" required></p>
                            <datalist id="currencies">
                                <c:forEach var="currency" items="${currencies}">
                                    <option value="${currency.code}">${currency.fullName}</option>
                                </c:forEach>
                            </datalist>
                        <p>Rate <input name="rate" required></p>
                        <p><input value="Добавить" type="submit"></p>
                    </form>

            </div>
        </div>
    </body>

</html>