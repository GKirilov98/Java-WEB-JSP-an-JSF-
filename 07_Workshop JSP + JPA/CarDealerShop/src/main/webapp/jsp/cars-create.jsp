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
    <form class="mx-auto w-15" method="post">
        <div class="row">
            <div class="col col-md-3"></div>
            <div class="col col-md-3">
                <div class="form-group">
                    <div class="label-holder d-flex justify-content-center">
                        <label class="text-center text-white font-weight-bold" for="brand">Brand
                            <input type="text" class="form-control" name="brand" id="brand" placeholder="Brand">
                        </label>
                    </div>
                </div>
            </div>
            <div class="col col-md-3">
                <div class="form-group">
                    <div class="label-holder d-flex justify-content-center">
                        <label class="text-center text-white font-weight-bold" for="model">Model
                            <input type="text" class="form-control" name="model" id="model" placeholder="Model">
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-3"></div>
            <div class="col col-md-3">
                <div class="form-group">
                    <div class="label-holder d-flex justify-content-center">
                        <label class="text-center text-white font-weight-bold" for="year">Year
                            <input type="number" class="form-control" placeholder="Year" name="year" id="year">
                        </label>
                    </div>
                </div>
            </div>
            <div class="col col-md-3">
                <div class="form-group ">
                    <div class="label-holder d-flex justify-content-center">
                        <label class="text-center text-white font-weight-bold" for="engine">Engine
                            <select id="engine" name="engine" class="form-control">
                                <option value="diesel">Diesel</option>
                                <option value="gasoline">Gasoline</option>
                            </select>
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col col-md-4"></div>
            <div class="col col-md-4">
                <div class="button-holder d-flex justify-content-center">
                    <input type="submit" class="btn btn-secondary" value="Upload Car"/>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
