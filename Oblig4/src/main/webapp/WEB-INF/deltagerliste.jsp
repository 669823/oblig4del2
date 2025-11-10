<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="no">
<head>
  <meta charset="UTF-8">
  <title>Deltagerliste</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
  <style>
    /* for amrkering av meg som bruker */
    .sr-only { position:absolute; width:1px; height:1px; padding:0; margin:-1px; overflow:hidden; clip:rect(0,0,0,0); white-space:nowrap; border:0; }
    tr.me { background: #68eb73; }                /* gronn */ht green
    tr.me td { border-color: #5ff167; }           /* gronn */
    .legend { display:flex; gap:.75rem; align-items:center; font-size: .95rem; color: var(--text-light); }
    .legend .swatch { width:1rem; height:1rem; background:#e8f5e9; border:1px solid #81c784; border-radius:3px; display:inline-block; }
    .userbar { display:flex; gap:1rem; align-items:center; justify-content:space-between; }
    .badge { display:inline-block; padding:.15rem .5rem; border:1px solid var(--border); border-radius: 999px; font-size:.9rem; color: var(--text-light); }
    .kjonn-ikon { font-size: 1.25rem; line-height: 1; }
  </style>
</head>
<body>
<header>
  <h1>Deltagerliste</h1>
  <nav>
    <ul>
      <li><a href="${pageContext.request.contextPath}/paamelding">Påmelding</a></li>
      <li><a href="${pageContext.request.contextPath}/innlogging">Innlogging</a></li>
    </ul>
  </nav>
</header>
<main>

  <c:if test="${not empty beskjed}">
    <aside><strong><c:out value="${beskjed}"/></strong></aside>
  </c:if>

  <!-- Finn navn på innlogget bruker ut fra listen (om controller ikke allerede setter innloggetNavn) -->
  <c:set var="innloggetNavn" value="" />
  <c:forEach var="d" items="${deltagere}">
    <c:if test="${d.mobil == innloggetMobil}">
      <c:set var="innloggetNavn" value="${d.fornavn} ${d.etternavn}" />
    </c:if>
  </c:forEach>

  <section class="userbar">
    <div>
      <span class="badge">Innlogget</span>
      <strong>
        <c:choose>
          <c:when test="${not empty innloggetNavn}">
            <c:out value="${innloggetNavn}"/> (<c:out value="${innloggetMobil}"/>)
          </c:when>
          <c:otherwise>
            <c:out value="${innloggetMobil}"/>
          </c:otherwise>
        </c:choose>
      </strong>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/deltagerliste/logout" style="display:inline;">
      <button type="submit">Logg ut</button>
    </form>
  </section>

  <p class="legend"><span class="swatch" aria-hidden="true"></span> Grønn markering = deg</p>

  <c:choose>
    <c:when test="${empty deltagere}">
      <p>Ingen deltagere enda.</p>
    </c:when>
    <c:otherwise>
      <table>
        <caption>Registrerte deltagere</caption>
        <thead>
          <tr>
            <th>Mobil</th>
            <th>Fornavn</th>
            <th>Etternavn</th>
            <th title="Kjønn">Kjønn</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="d" items="${deltagere}">
            <tr class="${d.mobil == innloggetMobil ? 'me' : ''}">
              <td><c:out value="${d.mobil}"/></td>
              <td><c:out value="${d.fornavn}"/></td>
              <td><c:out value="${d.etternavn}"/></td>
              <td>
                <c:choose>
                  <c:when test="${d.kjonn eq 'mann'}">
                    <span class="kjonn-ikon" aria-hidden="true">♂</span>
                    <span class="sr-only">Mann</span>
                  </c:when>
                  <c:when test="${d.kjonn eq 'kvinne'}">
                    <span class="kjonn-ikon" aria-hidden="true">♀</span>
                    <span class="sr-only">Kvinne</span>
                  </c:when>
                  <c:otherwise>
                    <span class="kjonn-ikon" aria-hidden="true">⚧</span>
                    <span class="sr-only">Annet</span>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</main>
<footer><p>&copy; Påmeldingssystem</p></footer>
</body>
</html>
