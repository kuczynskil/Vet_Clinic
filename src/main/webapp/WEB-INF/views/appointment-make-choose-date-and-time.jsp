<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/repeatable/header.jsp" %>

<h2 style="text-align: center; margin-top: 50px">Choose date and time for your appointment</h2>
<div class="box">
    <form action="/appointment/make/veterinarian/date" method="post">
        <label>
            <input type="date" name="appointmentDateString" value="${appointmentDateString}"
                   onchange="this.form.submit()">
        </label><br>
        <div style="color: red">${weekendDateMessage}</div>
        <input type="hidden" name="id" value="${vetId}">
    </form>

    <form action="/appointment/make/veterinarian/${vetId}" method="post">
        <label>
            <select name="appointmentTimeString" class="input-text" style="width:100px;">
                <c:forEach items="${availableTimes}" var="time">
                    <option value="${time}">${time}</option>
                </c:forEach>
            </select>
        </label><br>
        <input type="hidden" name="id" value="${vetId}">
        <input type="hidden" name="appointmentDateString" value="${appointmentDateString}">
        <input type="submit" value="Submit" class="btn">
    </form>
</div>

</body>
<script>
    const today = new Date();
    today.setDate(today.getDate() + 1);
    const minDate = today.toISOString().split('T')[0];
    document.getElementsByName("appointmentDateString")[0].setAttribute('min', minDate);
</script>
</html>
