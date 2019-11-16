<%@ page import="domain.model.view.TubeViewModel" %>
<%@ page import="domain.model.service.TubeServiceModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="template/head.jsp"/>
</head>
<body>
<% TubeViewModel tubeDetailsViewModel =
        (TubeViewModel) request.getAttribute("tubeDetailsViewModel");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1><%=tubeDetailsViewModel.getName()%></h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3><%=tubeDetailsViewModel.getDescription()%></h3>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="<%=tubeDetailsViewModel.getYouTubeLink()%>">Link to video</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <p><%=tubeDetailsViewModel.getUploader()%>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/tubes/all">Back to all Tubes.</a>
                </div>
            </div>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to Home</a>
                </div>
            </div>
        </div>
    </main>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>