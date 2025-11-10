<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="no">
<head>
  <meta charset="UTF-8">
  <title>Innlogging</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
</head>
<body>
<header>
  <h1>Innlogging</h1>
  <nav>
    <ul>
      <li><a href="${pageContext.request.contextPath}/paamelding">Påmelding</a></li>
      <li><a href="${pageContext.request.contextPath}/deltagerliste">Deltagerliste</a></li>
    </ul>
  </nav>
</header>
<main>
  <c:if test="${not empty beskjed}">
    <aside><strong><c:out value="${beskjed}"/></strong></aside>
  </c:if>
  <c:if test="${not empty utlogget}">
    <aside><strong><c:out value="${utlogget}"/></strong></aside>
  </c:if>

  <c:if test="${not empty feil}">
    <aside><strong><c:out value="${feil}"/></strong></aside>
  </c:if>

  <c:if test="${param.utlogget == '1'}">
  <aside><strong>Du er nå logget ut.</strong></aside>
</c:if>

  <form method="post" action="${pageContext.request.contextPath}/innlogging">
    <fieldset>
      <legend>Logg inn</legend>
      <label for="mobil">Mobil</label>
      <input id="mobil" name="mobil" pattern="[0-9]{8}" inputmode="numeric" required>
      <label for="passord">Passord</label>
      <input id="passord" type="password" name="passord" required>
      <button type="submit">Logg inn</button>
    </fieldset>
  </form>
</main>
<footer><p>&copy; Påmeldingssystem</p></footer>
</body>
</html>
