<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vet Clinic</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Veterinarian</th>
        <th>Date</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${appointments}" var="appointment">
        <tr>
            <td>${appointment.id}</td>
            <td>${appointment.veterinarian.name} ${appointment.veterinarian.surname}</td>
            <td id="date${appointment.id}"></td>
            <td id="time${appointment.id}"></td>
            <td><a href="/appointment/cancel/${appointment.id}">Cancel</a></td>
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
