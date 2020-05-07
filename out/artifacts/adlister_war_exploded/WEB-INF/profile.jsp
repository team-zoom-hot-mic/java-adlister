<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Your Posted Ads</h1>
        <c:forEach var="ad" items="${ads}">
                <div class="col-md-6">
                    <h2>${ad.title}</h2>
                    <h2>${ad.description}</h2>

                    <!-- edit ad form -->
                    <form action="/ads/edit" method="GET">
                        <button name="editAdId" value="${ad.id}">Edit Ad</button>
                    </form>

                    <!-- delete ad form -->
                    <form action="/ads/delete" method="POST">
                        <button name="deleteAdId" value="${ad.id}">Delete Ad</button>
                    </form>

                </div>
        </c:forEach>
    </div>


</body>
</html>
