<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Here Are all the ads!</h1>
    <c:forEach var="ad" items="${ads}">
        <form action="/ads/single" method="POST">
            <div class="col-md-6">
                <button type="submit" class="btn btn-link"><h2>${ad.title}</h2></button>

                <input type="text" style="display: none" name="adId" value=${ad.id}>

                <p>${ad.description}</p>
            </div>
        </form>
    </c:forEach>
</div>

</body>
</html>
