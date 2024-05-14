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
</head>
<body>
    <table>
        <tr>
            <th>uuid</th>
            <th>full name</th>
        </tr>
        <c:forEach items="${listResume}" var="list">
            <tr>
                <td>${list.getUuid()}</td>
                <td>${list.getFullName()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
