<%--
  Created by IntelliJ IDEA.
  User: Gogo
  Date: 10/16/2019
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="navbar navbar-expand-lg navbar-background">
    <a class="nav-link text-white active h5" href="/index">Home</a>
    <div class="collapse navbar-collapse d-flex justify-content-end">
        <ul class="navbar-nav row">
    <%if (request.getSession().getAttribute("loginUserModel") == null){ %>
            <li class="nav-item col-md-4">
                <a class="nav-link text-white active font-weight-bold" href="/users/login">Login</a>
            </li>
            <li class="nav-item col-md-4">
                <a class="nav-link text-white active font-weight-bold" href="/users/register">Register</a>
            </li>
    <% } else { %>
            <li class="nav-item col-md-4">
                <a class="nav-link text-white active font-weight-bold"
                   href="/cars/create">Upload Car</a>
            </li>
            <li class="nav-item col-md-4">
                <a class="nav-link text-white active font-weight-bold" href="/cars/all">All Cars</a>
            </li>
            <li class="nav-item col-md-4">
                <a class="nav-link text-white active font-weight-bold" href="/logout">Logout</a>
            </li>
    <% } %>
        </ul>
    </div>
</nav>
</html>
