
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vet Clinic</title>
</head>
<body>
<form action="/appointment/cancel" method="post">
    <label>
        <input name="loginId" placeholder="Client's ID"/><br>
    </label>
    <label>
        <input name="loginPIN" placeholder="PIN"/><br>
        <div style="color: red">${invalidIDOrPINMessage}</div>
    </label>
    <input type="submit">
</form>
</body>
</html>
