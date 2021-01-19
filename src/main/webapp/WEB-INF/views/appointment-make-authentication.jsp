
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vet Clinic</title>
</head>
<body>
<form action="/user/authenticate" method="post">
    <label>
        <input name="loginId" placeholder="Client's ID"/><br>
    </label>
    <label>
        <input name="loginPIN" placeholder="PIN"/><br>
        <div style="color: red">${invalidIDOrPINMessage}</div>
    </label>
    <input type="hidden" name="appointmentDateString" value="${appointmentDateString}">
    <input type="hidden" name="appointmentTimeString" value="${appointmentTimeString}">
    <input type="hidden" name="vetId" value="${vetId}">
    <input type="submit">
</form>
</body>
</html>
