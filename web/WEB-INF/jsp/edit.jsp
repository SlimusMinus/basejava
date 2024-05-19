<%@ page import="com.urize.webapp.model.ContactsType" %>
<%@ page import="com.urize.webapp.model.ListSection" %>
<%@ page import="com.urize.webapp.model.CompanySection" %>
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
        <table>
            <c:forEach var="section" items="${resume.sections}">
                <c:set var="sectionValue" value="${section.value}"/>
                <c:choose>
                    <c:when test="${section.key == 'PERSONAL' || section.key == 'OBJECTIVE'}">
                        <tr>
                            <td class="title-column"><strong>${section.key.title}</strong></td>
                            <td><label>
                                <input type="text" name="${section.key}" size="30" value="${section.value}">
                            </label></td>
                        </tr>
                    </c:when>
                    <c:when test="${section.key == 'ACHIEVEMENT' || section.key == 'QUALIFICATIONS'}">
                        <tr>
                            <td class="title-column"><strong>${section.key.title}</strong></td>
                            <td>
                                <ul>
                                    <c:forEach var="item" items="${sectionValue.list}">
                                        <li>
                                            <input type="text" size="30" value="${item}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${section.key == 'EXPERIENCE' || section.key == 'EDUCATION'}">
                        <tr>
                            <td class="title-column"><strong>${section.key.title}</strong></td>
                            <td>
                                <ul>
                                    <c:forEach var="item" items="${sectionValue.list}">
                                        <li>
                                            <input type="text" size="30" value="${item.link.name}">
                                            <a href="${item.link.url}" target="_blank">${item.link.name}</a>
                                        </li>
                                        <c:forEach var="position" items="${item.periods}">
                                            <li>
                                                <input type="text" size="15" value="${position.startDate}">
                                                -
                                                <input type="text" size="15" value="${position.endDate}">
                                                <br>
                                                <input type="text" size="30" value="${position.title}">
                                                <br>
                                                <input type="text" size="30" value="${position.description}">
                                            </li>
                                        </c:forEach>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
        </table>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="button" class="cancel-button" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
