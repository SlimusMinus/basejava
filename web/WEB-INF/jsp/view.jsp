<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
    <h2>${resume.fullName}</h2>

    <h3>Контакты:</h3>
    <ul>
        <c:forEach var="type" items="${resume.contacts.keySet()}">
            <li><strong>${type.title}: </strong>${resume.contacts[type]}</li>
        </c:forEach>
    </ul>

    <h3>Секции:</h3>
    <c:forEach var="section" items="${resume.sections}">
        <c:choose>
            <c:when test="${section.key == 'PERSONAL' || section.key == 'OBJECTIVE'}">
                <h4>${section.key.title}</h4>
                <p>${section.value}</p>
            </c:when>
            <c:when test="${section.key == 'ACHIEVEMENT' || section.key == 'QUALIFICATIONS'}">
                <h4>${section.key.title}</h4>
                <ul>
                    <c:forEach var="item" items="${section.value.list}">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${section.key == 'EXPERIENCE' || section.key == 'EDUCATION'}">
                <h4>${section.key.title}</h4>
                <c:forEach var="company" items="${section.value.list}">
                    <h5>${company.link.name}</h5>
                    <c:forEach var="position" items="${company.periods}">
                        <p>
                                ${position.startDate} - ${position.endDate}<br>
                            <strong>${position.title}</strong><br>
                                ${position.description}
                        </p>
                    </c:forEach>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
