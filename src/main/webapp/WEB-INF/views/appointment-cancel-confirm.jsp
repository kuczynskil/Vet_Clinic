<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/repeatable/header.jsp" %>

<div class="box" style="margin-top: 50px">
   <h2>Do you wish to cancel this appointment?</h2>
    <a href="/appointment/cancel/${appointmentId}/perform">
        <div class="btn">Yes</div>
    </a>
    <a href="/appointment/cancel/user/${appUserId}">
        <div class="btn2">No</div>
    </a>
</div>

</body>
</html>
