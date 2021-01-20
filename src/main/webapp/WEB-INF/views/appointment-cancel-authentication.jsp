
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/repeatable/header.jsp" %>

<h2 style="text-align: center; margin-top: 50px">Enter your ID and PIN to cancel your appointment.</h2>
<form action="/appointment/cancel" method="post" style="margin-top: 30px">
    <div class="box">
        <label>
            <input name="loginId" placeholder="Client's ID" class="input-text"><br>
        </label>
        <label>
            <input name="loginPIN" placeholder="PIN" class="input-text"><br>
            <div style="color: red">${invalidIDOrPINMessage}</div>
        </label>
        <input type="submit" value="Submit" class="btn">
    </div>
</form>
</body>
</html>
