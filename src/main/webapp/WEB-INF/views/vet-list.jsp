<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/repeatable/header.jsp" %>

<c:forEach items="${veterinarians}" var="veterinarian">
    <a href="/vet/${veterinarian.id}">${veterinarian.name} ${veterinarian.surname}</a><br>
</c:forEach>
</body>
</html>
