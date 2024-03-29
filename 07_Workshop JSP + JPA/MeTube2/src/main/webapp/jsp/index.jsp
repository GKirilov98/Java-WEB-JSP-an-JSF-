<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="template/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <header>
        <c:import url="template/nav.jsp"/>
    </header>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome to MeTube&trade;!</p>
            <p class="h3">The simplest, easiest to use, most comfortable Multimedia Application.</p>
            <hr class="my-3">
            <p><a href="/login">Login</a> if you have an account or <a href="/register">Register</a> now and start
                tubing.</p>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="template/footer.jsp"/>
    </footer>
</div>
</body>
</html>