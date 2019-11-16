<%@ page import="meTube2.domain.model.servlets.UserLoginServletModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="template/head.jsp"/>
</head>
<% UserLoginServletModel userLoginServletModel = (UserLoginServletModel)
        request.getSession().getAttribute("username"); %>
<body>
<div class="container-fluid">
    <header>
        <c:import url="template/nav.jsp"/>
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="h4 text-info">Welcome, <%= userLoginServletModel.getUsername()%></h4>
        </div>
        <hr class="my-4">
        <div class="container">
<%--todo--%>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="template/footer.jsp"/>
    </footer>
</div>
</body>
</html>