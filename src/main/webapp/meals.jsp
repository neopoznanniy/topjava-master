<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>

<body>

<table>
    <caption>Calories table</caption>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealList}" var="mealTo">
        <tr style="color:${mealTo.excess ? 'red' : 'greenyellow'}">
            <th>${mealTo.dateTime}</th>
            <th>${mealTo.description}</th>
            <th>${mealTo.calories}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>
