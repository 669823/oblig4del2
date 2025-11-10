package no.hvl.dat108.obl4.validation;

import org.junit.jupiter.api.Test;

import no.hvl.dat108.obl4.validering.DeltagerRules;

import static org.junit.jupiter.api.Assertions.*;

public class DeltagerRulesTest {

    @Test
    void navn_mobil_og_passord() {
        assertTrue(DeltagerRules.gyldigFornavn("Anne Marit"));
        assertTrue(DeltagerRules.gyldigEtternavn("Østby-Li"));
        assertFalse(DeltagerRules.gyldigEtternavn("li"));
        assertTrue(DeltagerRules.gyldigMobil("12345678"));
        assertFalse(DeltagerRules.gyldigMobil("1234abcd"));
        assertTrue(DeltagerRules.gyldigKjonn("mann"));
        assertFalse(DeltagerRules.gyldigKjonn("annet"));
        assertTrue(DeltagerRules.gyldigPassord("hemmelig"));
    }
}
