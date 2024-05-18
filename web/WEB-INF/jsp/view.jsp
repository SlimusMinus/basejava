<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.urize.webapp.model.ContactsType" %>

<html>
<head>
    <jsp:useBean id="resume" type="com.urize.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp; <a href="resumes?uuid=${resume.uuid}&action=edit"><img src="../../img/pencil.png" alt=""></a></h2>
    <c:forEach var="contacts" items="${resume.contacts}">
       <tr>
           <td>${contacts.key.toHtml(contacts.value)} </td>
           <br>
       </tr>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
