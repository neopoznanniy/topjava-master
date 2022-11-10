<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>

<body>
<a href="meals?action=create">Add Meal</a>
<br><br>
<table>
    <caption>Calories table</caption>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach items="${mealList}" var="mealTo">
        <tr style="color:${mealTo.excess ? 'red' : 'greenyellow'}">
            <th>${mealTo.dateTime}</th>
            <th>${mealTo.description}</th>
            <th>${mealTo.calories}</th>
            <td><a href="MealServlet?action=update&id=<c:out value="${mealTo.id}"/>">Update</a></td>
            <td><a href="MealServlet?action=delete&id=<c:out value="${mealTo.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
