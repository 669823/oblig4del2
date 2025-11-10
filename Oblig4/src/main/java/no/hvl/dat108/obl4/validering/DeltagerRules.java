package no.hvl.dat108.obl4.validering;

import java.util.regex.Pattern;

public final class DeltagerRules {
    private DeltagerRules() {}

    private static final String BOK = "A-Za-zÆØÅæøå";
    private static final Pattern FORNAVN = Pattern.compile("^[A-ZÆØÅ]["+BOK+"\\- ]{1,19}$");
    private static final Pattern ETTERNAVN = Pattern.compile("^[A-ZÆØÅ]["+BOK+"\\-]{1,19}$");
    private static final Pattern MOBIL = Pattern.compile("^\\d{8}$");

    public static boolean gyldigFornavn(String s) { return s != null && FORNAVN.matcher(s).matches(); }
    public static boolean gyldigEtternavn(String s) { return s != null && ETTERNAVN.matcher(s).matches(); }
    public static boolean gyldigMobil(String s) { return s != null && MOBIL.matcher(s).matches(); }
    public static boolean gyldigKjonn(String s) { return "mann".equals(s) || "kvinne".equals(s); }
    public static boolean gyldigPassord(String s) { return s != null && s.length() >= 6; }
}
