<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Vet CLinic</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<header>
    <div id="setter"><img src="${pageContext.request.contextPath}/images/header_picture.jpg"
              alt="veterinarian checking dog's heart" class="header-img">
    </div>

    <div class="topnav">
        <a href="/appointment/make">Make an appointment</a>
        <a href="/appointment/cancel">Cancel your appointment</a>
        <a href="/vet">Vet's panel</a>
    </div>
    <script>
        var $setter = $("#setter");
        $setter.siblings(".topnav").css("max-width", $setter.width() + "px");
    </script>
</header>

