<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vet Clinic</title>
</head>
<body>
Do you wish to cancel this appointment?
<a href="/appointment/cancel/${appointmentId}/perform">Yes</a>
<a href="/appointment/cancel/user/${appUserId}">No</a>
</body>
</html>
