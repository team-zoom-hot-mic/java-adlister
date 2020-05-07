<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Edit Ad</title>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="Title" value="Edit Ad"/>
    </jsp:include>
</head>


<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<form action="/ads/update" method="POST">

    <!-- Edit Title -->
    <div class="form-group">
        <label for="title">Title</label>
        <input id="title" value="${ad.title}" name="title" class="form-control" type="text">
    </div>

    <!-- Edit Description -->
    <h2>Description</h2>
    <div class="form-group">
        <label for="description">Description</label>
        <input id="description" value="${ad.description}" name="description" class="form-control" type="text">
    </div>

    <!-- Edit Category -->
    <div class="form-group">
        <label for="category">Category</label>
        <select id="category">
            <c:forEach var="cat" items="${cats}">
                <option value="${cat.id}">${cat.category}</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit">Update Ad</button>
</form>

</body>
</html>