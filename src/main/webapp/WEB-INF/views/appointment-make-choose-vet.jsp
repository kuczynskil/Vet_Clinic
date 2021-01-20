<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/repeatable/header.jsp" %>
<div class="row">
    <h2>Choose a veterinarian:</h2>
    <c:forEach items="${veterinarians}" var="veterinarian">
        <a href="/appointment/make/veterinarian/${veterinarian.id}">
            <div class="column">
                <div class="card">
                    <h3><b>${veterinarian.name} ${veterinarian.surname}</b></h3>
                    <p>${veterinarian.specialty}</p>
                </div>
            </div>
        </a>
    </c:forEach>
</div>
</body>
</html>
