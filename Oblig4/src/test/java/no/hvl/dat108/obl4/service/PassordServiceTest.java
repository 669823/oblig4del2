package no.hvl.dat108.obl4.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassordServiceTest {

    @Test
    void hashing_og_verifisering_fungerer() {
        PassordService s = new PassordService();
        String salt = s.genererTilfeldigSalt();
        String hash = s.hashMedSalt("hemmelig", salt);
        assertEquals(32, salt.length());
        assertEquals(64, hash.length());
        assertTrue(s.erKorrektPassord("hemmelig", salt, hash));
        assertFalse(s.erKorrektPassord("feilpass", salt, hash));
    }
}
