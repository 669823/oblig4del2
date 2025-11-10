<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="no">
<head>
  <meta charset="UTF-8">
  <title>Påmelding</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
</head>
<body>
<header>
  <h1>Påmelding</h1>
  <nav>
    <ul>
      <li><a href="${pageContext.request.contextPath}/innlogging">Innlogging</a></li>
      <li><a href="${pageContext.request.contextPath}/deltagerliste">Deltagerliste</a></li>
    </ul>
  </nav>
</header>

<main>
  <c:if test="${not empty feil}">
    <aside><strong><c:out value="${feil}"/></strong></aside>
  </c:if>

  <form method="post" action="${pageContext.request.contextPath}/paamelding">
    <fieldset>
      <legend>Fyll inn detaljer</legend>

      <label for="fornavn">Fornavn</label>
      <input id="fornavn" name="fornavn" value="${fn:escapeXml(prefillFornavn)}" required>

      <label for="etternavn">Etternavn</label>
      <input id="etternavn" name="etternavn" value="${fn:escapeXml(prefillEtternavn)}" required>

      <label for="mobil">Mobil</label>
      <input id="mobil" name="mobil" value="${fn:escapeXml(prefillMobil)}" pattern="[0-9]{8}" inputmode="numeric" required>
      <small>8 siffer, f.eks. 90000000</small>

      <label for="kjonn">Kjønn</label>
      <select id="kjonn" name="kjonn" required>
        <option value="">Velg…</option>
        <option value="mann"   <c:if test="${prefillKjonn == 'mann'}">selected</c:if>>Mann</option>
        <option value="kvinne" <c:if test="${prefillKjonn == 'kvinne'}">selected</c:if>>Kvinne</option>
        <option value="annet"  <c:if test="${prefillKjonn == 'annet'}">selected</c:if>>Annet</option>
      </select>

      <label for="passord">Passord</label>
      <input id="passord" type="password" name="passord"
             pattern="(?=.*[0-9])(?=.*[a-zæøå])(?=.*[A-ZÆØÅ]).{8,}"
             title="Minst 8 tegn inkl. liten, stor og et siffer" required>

      <label for="passordRepetert">Gjenta passord</label>
      <input id="passordRepetert" type="password" name="passordRepetert" required>

      <button type="submit">Meld meg på</button>
    </fieldset>
  </form>
</main>

<footer>
  <p>&copy; Påmeldingssystem</p>
</footer>
</body>
</html>
