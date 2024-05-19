<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.urize.webapp.model.ContactsType" %>
<%@ page import="com.urize.webapp.model.ListSection" %>
<%@ page import="com.urize.webapp.model.CompanySection" %>

<html>
<head>
    <meta charset="UTF-8">
    <jsp:useBean id="resume" type="com.urize.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp; <a href="resumes?uuid=${resume.uuid}&action=edit"><img src="../../img/pencil.png"
                                                                                        alt=""></a></h2>
    <c:forEach var="contacts" items="${resume.contacts}">
        <tr>
            <td>${contacts.key.toHtml(contacts.value)} </td>
            <br>
        </tr>
    </c:forEach>
    <hr>
    <table>
        <c:forEach var="sections" items="${resume.sections}">
            <c:set var="type" value="${sections.key}"/>
            <c:set var="section" value="${sections.value}"/>
            <jsp:useBean id="section" type="com.urize.webapp.model.AbstractSection"/>
            <td><h3><a name="type.name">${type.title}</a></h3></td>
            <c:choose>
                <c:when test="${type == 'PERSONAL' || type == 'OBJECTIVE'}">
                    <tr>
                        <td>
                                ${sections.value}
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <tr>
                        <td>
                            <ul>
                                <c:forEach var="item" items="<%= ((ListSection)section).getList()  %>">
                                    <li>${item}</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:when>

                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <tr>
                        <td>
                            <ul>
                                <c:forEach var="item" items="<%= ((CompanySection)section).getList()  %>">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty item.link.url}">
                                                    <h3>${item.link.name}</h3>
                                                </c:when>
                                                <c:otherwise>
                                                    <h3><a href="${item.link.url}">${item.link.name}</a></h3>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <c:forEach var="position" items="${item.periods}">
                                        <tr>
                                            <td>
                                                <b>${position.startDate} - ${position.endDate}</b>
                                            </td>
                                            <td>
                                                    ${position.title} ${position.description}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:when>

            </c:choose>
        </c:forEach>
    </table>
    <button onclick=window.history.back()>OK</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
