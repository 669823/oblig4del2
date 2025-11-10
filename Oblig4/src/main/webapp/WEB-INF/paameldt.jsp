<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="no">
<head>
  <meta charset="UTF-8">
  <title>Påmelding bekreftet</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
</head>
<body>
<header>
  <h1>Påmelding bekreftet</h1>
  <nav>
    <ul>
      <li><a href="${pageContext.request.contextPath}/paamelding">Ny påmelding</a></li>
      <li><a href="${pageContext.request.contextPath}/deltagerliste">Deltagerliste</a></li>
    </ul>
  </nav>
</header>
<main>
  <c:choose>
    <c:when test="${not empty deltager}">
      <article>
        <h2>Velkommen, <c:out value="${deltager.fornavn}"/> <c:out value="${deltager.etternavn}"/>!</h2>
        <p>Du er nå registrert med mobil <strong><c:out value="${deltager.mobil}"/></strong>.</p>
        <p><a role="button" href="${pageContext.request.contextPath}/deltagerliste">Gå til deltagerlisten</a></p>
      </article>
    </c:when>
    <c:otherwise>
      <p>Mangler deltagerdata. Gå til <a href="${pageContext.request.contextPath}/paamelding">påmelding</a>.</p>
    </c:otherwise>
  </c:choose>
</main>
<footer><p>&copy; Påmeldingssystem</p></footer>
</body>
</html>
