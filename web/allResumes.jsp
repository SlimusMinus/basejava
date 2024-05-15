<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 14.05.2024
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="outlines.css">
</head>
<body>
<header>База данных резюме</header>
    <table border="1">
        <tr>
            <th>num</th>
            <th>uuid</th>
            <th>full name</th>
        </tr>
        <c:forEach items="${listResume}" var="list" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
                <td>${list.getUuid()}</td>
                <td>${list.getFullName()}</td>
            </tr>
        </c:forEach>
    </table>
<footer>Author Alexandr Krylov</footer>
</body>
</html>
