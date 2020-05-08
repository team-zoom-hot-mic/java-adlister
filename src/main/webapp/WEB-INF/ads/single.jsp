<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Single Ad</title>

    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="Title" value="Single Ad"/>
    </jsp:include>

</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
<h1>${ad.title}</h1>
<p>${ad.description}</p>
</div>
</body>
</html>
