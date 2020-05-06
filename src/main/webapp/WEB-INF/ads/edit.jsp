<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Edit Ad</title>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="Title" value="Edit Ad" />
    </jsp:include>
</head>


<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<form action="/ads/edit" method="POST">
    <h1>${ad.title}</h1>
    <h2>${ad.description}</h2>
</form>

</body>
</html>