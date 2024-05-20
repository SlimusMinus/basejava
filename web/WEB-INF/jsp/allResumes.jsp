<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.urize.webapp.model.ContactsType" %>

<html>
<head>
    <title>Список всех резюме</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<table border="1">
    <tr>
        <th>num</th>
        <th>full name</th>
        <th>mail</th>
        <th></th>
        <th></th>

    </tr>
    <c:forEach items="${listResume}" var="list" varStatus="loop">
        <jsp:useBean id="list" type="com.urize.webapp.model.Resume"/>
        <tr>
            <td>${loop.index+1}</td>
            <td><a href="resumes?uuid=${list.uuid}&action=view">${list.fullName}</a></td>
            <td>${list.contacts.get(ContactsType.MAIL)}</td>
            <td><a href="resumes?uuid=${list.uuid}&action=delete"> <img src="../../img/delete.png" alt=""> </a></td>
            <td><a href="resumes?uuid=${list.uuid}&action=edit"><img src="../../img/pencil.png" alt=""></a></td>
        </tr>
    </c:forEach>
    <form action="resumes" method="get" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="add">
        <button type="submit">Добавить новое резюме</button>
    </form>

</table>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>