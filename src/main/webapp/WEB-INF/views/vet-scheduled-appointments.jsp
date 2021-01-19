<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vet Clinic</title>
</head>
<body>
<form action="/vet/${vetId}" method="get">
    <label>
        <input type="date" name="date" value="${date}" onchange="this.form.submit()">
    </label><br>
</form>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Client</th>
        <th>Date</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${appointments}" var="appointment">
        <tr>
            <td>${appointment.id}</td>
            <td>${appointment.appUser.name} ${appointment.appUser.surname}</td>
            <td id="date${appointment.id}"></td>
            <td id="time${appointment.id}"></td>
        </tr>
        <script>
            var dateAndTime = '${appointment.dateAndTime}'.split('T');
            document.getElementById('date${appointment.id}').innerHTML = dateAndTime[0];
            document.getElementById('time${appointment.id}').innerHTML = dateAndTime[1];
        </script>
    </c:forEach>
    </tbody>
</table>
</body>

</html>
