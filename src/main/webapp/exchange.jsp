<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

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

                            </tr>
                        </c:forEach>
                                    </table>
            </div>
        </div>
    </body>

</html>