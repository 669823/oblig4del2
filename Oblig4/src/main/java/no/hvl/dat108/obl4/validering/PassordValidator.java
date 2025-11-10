package no.hvl.dat108.obl4.validering;

import java.util.regex.Pattern;

public final class PassordValidator {

    // Min 8 tegn, minst én liten, én stor (inkl. ÆØÅ) og ett siffer.
    private static final Pattern PASSORD = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-zæøå])(?=.*[A-ZÆØÅ]).{8,}$"
    );

    private PassordValidator() {}

    public static void sjekk(String passord, String passord2, String mobil) {
        if (passord == null || passord2 == null) {
            throw new IllegalArgumentException("Passord kan ikke være tomt.");
        }
        if (!passord.equals(passord2)) {
            throw new IllegalArgumentException("Passordene må være like.");
        }
        if (!PASSORD.matcher(passord).matches()) {
            throw new IllegalArgumentException(
                "Passord må ha minst 8 tegn og inneholde liten, stor og siffer."
            );
        }
        if (mobil != null && !mobil.isBlank() && passord.contains(mobil)) {
            throw new IllegalArgumentException("Passord må ikke inneholde mobilnummeret.");
        }
    }
}
