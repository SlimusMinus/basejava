<%@ page import="com.urize.webapp.model.ContactsType" %>
<%@ page import="com.urize.webapp.model.ListSection" %>
<%@ page import="com.urize.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../css/style.css">
    <jsp:useBean id="resume" type="com.urize.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>

</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resumes" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">

        <p>Имя: <input type="text" name="fullName" size=50 value="${resume.fullName}"></p>

        <h3 class="section-title">Контакты:</h3>
        <table>
            <c:forEach var="type" items="<%=ContactsType.values()%>">
                <tr>
                    <td class="title-column"><strong>${type.title}</strong></td>
                    <td><label>
                        <input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}">
                    </label></td>
                </tr>
            </c:forEach>
        </table>

        <h3 class="section-title">Секции:</h3>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.urize.webapp.model.AbstractSection"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <label>
                        <input type='text' name='${type}' size=70 value='<%=section%>'>
                    </label>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <label>
                        <textarea name='${type}' cols=75 rows=5><%=section%></textarea>
                    </label>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <label>
                        <textarea name='${type}' cols=75
                                  rows=5><%=String.join("\n", ((ListSection) section).getList())%></textarea>
                    </label>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="button" class="cancel-button" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
