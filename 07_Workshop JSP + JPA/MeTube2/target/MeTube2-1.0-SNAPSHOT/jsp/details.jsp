<%@ page import="meTube2.domain.model.service.TubeServiceModel" %>
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
    <% TubeServiceModel tubeServiceModel = (TubeServiceModel) request.getSession().getAttribute("currDetailsTube");%>
    <main>
        <hr class="my-2">
        <div class="container-fluid">
            <h2 class="text-center"><%= tubeServiceModel.getTitle()%></h2>
            <div class="row">
                <div class="col-md-6 my-5">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item"
                                src="https://www.youtube.com/embed/<%= tubeServiceModel.getYoutubeId()%>"
                                allowfullscreen frameborder="0"></iframe>
                    </div>
                </div>
                <div class="col-md-6 my-5">
                    <h1 class="text-center text-info"><%= tubeServiceModel.getAuthor()%></h1>
                    <h3 class="text-center text-info"><%= tubeServiceModel.getView()%> Views</h3>
                    <div class="h5 my-5 text-center"><%=tubeServiceModel.getDescription()%></div>
                </div>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="template/footer.jsp"/>
    </footer>
</div>
</body>
</html>