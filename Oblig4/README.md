# DAT108 Oblig 4 – Del 2 (løsningsforslag)

Dette er et ferdig forslag der **all logikk ligger i services/hjelpeklasser**, og **validering skjer i service-laget**.
JSP-ene ligger under `/WEB-INF/jsp`, og kontrollerne er tynne (PRG).

## Kjøring (lokalt)
1. Bygg: `mvn clean package`
2. Kjør: `mvn spring-boot:run`
3. Åpne: <http://localhost:8080/paamelding>

## DB
Standardoppsett antar Postgres på `localhost:5432/obl4` (se `application.properties`). Du kan også kjøre med H2 ved å endre `spring.datasource.url` og fjerne Postgres-driveren.

## Mapper
- `no.hvl.dat108.obl4.controller` – tynne controllere
- `no.hvl.dat108.obl4.service` – **all forretningslogikk + validering**
- `no.hvl.dat108.obl4.validation` – regex/regler
- `no.hvl.dat108.obl4.model` – JPA-entiteter
- `no.hvl.dat108.obl4.repository` – Spring Data JPA

## Brukerflyt
- `POST /paamelding` -> `redirect:/paameldt` (PRG)
- Innlogging nødvendig for `/deltagerliste`

## Tester
Se `/src/test/java`.
