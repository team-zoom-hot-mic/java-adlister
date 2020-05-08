<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="head.jsp"%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="../">Adlister</a>
        </div>
        <ul id="nav" class="nav navbar-nav navbar-right">
            <li class="nav-item">
                <form class="navbar-form" action="/ads/search" method="get">
                    <label for="search">Search:</label>
                    <input id="search" name="search" class="form-control" type="text" placeholder="Search Ads">
                </form>
            </li>
            <c:choose>
                <c:when test="${!sessionScope.containsKey('user')}">
                    <!--if user is not logged in-->
                    <li><a href="/register">Register</a></li> <!--remove after login-->
                    <li><a href="/login">Login</a></li> <!--remove after login-->
                </c:when>
                <c:otherwise>
                    <li><a href="/ads/create">Create an Ad</a></li>
                    <li><a href="/profile">Profile</a></li>
                    <li><a href="/logout">Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <!-- /.container-fluid -->
</nav>
