<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h2>Your Ads</h2>

    <div class="card-group">
        <c:forEach var="ad" items="${ads}">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">${ad.title}</h3>
                    <p class="card-text">${ad.description}</p>
                </div>
                <div class="card-footer" style="display:flex; ">

                    <form action="/ads/edit" method="GET"  style="margin-right: 2px;">
                        <button class="btn btn-primary" type="button" name="editAdId" value="${ad.id}">Edit Ad</button>
                    </form>


                    <form action="/ads/delete" method="POST">
                        <button class="btn btn-primary" type="button" name="deleteAdId" value="${ad.id}">Delete Ad</button>
                    </form>

                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
