<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
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
                        <table border="1" class="centered">
                            <tr>
                                <th>Currency</th>
                                <th>Exchange Rate</th>

                            </tr>
                            <c:forEach var="exRate" items="${exList}">
                                <tr>
                                    <td>${exRate.currencies}</td>
                                    <td>${exRate.rate}</td>

                                    <td>
                                        <form onsubmit="updateRate(event, '${fn:trim(exRate.currencies)}')"
                                            style="text-align: center">
                                            <input type="number" name="rate" step="0.0001" placeholder="Input new rate"
                                                required>
                                            <button type="submit">Update</button>
                                        </form>
                                    </td>

                                    <!--
                                    <td>
                                        <button type="button" onClick="deleteExchangeRate(${exRate.id})">Delete</button>
                                    </td>
                                    -->


                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                    <div class="forms">
                        <div class="addForm">
                            <form name="AddExForm" action="exchangeRates" method="post">
                                <h1>Добавить валютную пару</h1>
                                <p>Base currency <input list="currencies" name="baseCurrency" required></p>
                                
                                <p>Target currency <input list="currencies" name="targetCurrency" required></p>
                                
                                <datalist id="currencies">
                                    <c:forEach var="currency" items="${currencies}">
                                        <option value="${currency.code}">${currency.fullName}</option>
                                    </c:forEach>
                                </datalist>

                                <p>Rate <input name="rate" type="number" step="0.0001" required></p>
                                <p><input value="Добавить" type="submit"></p>
                            </form>

                        </div>
                        <br><br>
                        <div class="changeForm">
                            <form name="exchangeForm" action="exchange" method="get">
                                <h1>Обмен валюты</h1>

                                <p>Base currency
                                    <input list="currencies" name="from" value="${fromValue}" required>
                                </p>

                                <p>Target currency
                                    <input list="currencies" name="to" value="${toValue}" required>
                                </p>

                                <datalist id="currencies">
                                    <c:forEach var="currency" items="${currencies}">
                                        <option value="${currency.code}">${currency.fullName}</option>
                                    </c:forEach>
                                </datalist>

                                <p>Amount
                                    <input name="amount" type="number" step="0.01" value="${amountValue}" required>
                                </p>

                                <p><input value="Посчитать" type="submit"></p>

                                <!-- Вывод результата -->
                                <p>Рузультат:
                                    <output id="result">
                                        <c:if test="${not empty result}">${result}</c:if>
                                    </output>
                                </p>
                            </form>
                        </div>
                        <form action="currencies">
                            <p><input value="<-- Список валют" type="submit"></p>
                        </form>
                    </div>
                </div>

            </body>

            </html>