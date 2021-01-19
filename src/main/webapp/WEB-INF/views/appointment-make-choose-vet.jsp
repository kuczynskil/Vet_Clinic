<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vet Clinic</title>
</head>
<body>
<c:forEach items="${veterinarians}" var="veterinarian">
    <a href="/appointment/make/veterinarian/${veterinarian.id}">${veterinarian.name} ${veterinarian.surname}</a><br>
</c:forEach>
</body>
</html>
