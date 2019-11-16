<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gogo
  Date: 10/16/2019
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <c:import url="template/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <c:import url="template/navigation.jsp"/>
    <h2 class="text-center text-white mt-5">West Compass Dealer Shop</h2>
    <hr style="width: 50%"/>
    <h4 class="font-italic text-center text-white ">
        By clicking the buttons below you can check our car deals our upload a
        brand new.
    </h4>
    <br/>
    <div class="text-center">
        <a class="btn btn-secondary text-center mr-4" href="/cars/create"
        >Upload Offer</a
        >
        <a class="btn btn-secondary text-center ml-4" href="/cars/all">All Offers</a>
    </div>
</div>
</body>
</html>
