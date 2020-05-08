<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<div class="container">
    <h1>Welcome to the Adlister!</h1>
    <c:choose>
        <c:when test="${!sessionScope.containsKey('user')}">
            <!--if user is not logged in-->
            <h3><a href="/register">Register</a></h3> <!--remove after login-->
            <h3><a href="/login">Login</a></h3> <!--remove after login-->
            <h3><a href="/ads">Browse Ads</a></h3>
        </c:when>

        <c:otherwise>
            <h3><a href="/ads/create">Create an Ad</a></h3>
            <h3><a href="/profile">Profile</a></h3>
            <h3><a href="/logout">Logout</a></h3>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
