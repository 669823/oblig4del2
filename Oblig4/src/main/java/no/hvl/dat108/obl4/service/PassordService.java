package no.hvl.dat108.obl4.service;

import org.springframework.stereotype.Service;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.HexFormat;

@Service
public class PassordService {

    private static final int SALT_BYTES = 16;        // 16 bytes -> 32 hex chars
    private static final int HASH_BYTES = 32;        // 32 bytes -> 64 hex chars
    private static final int ITERATIONS = 65536;
    private static final String ALGO = "PBKDF2WithHmacSHA256";

    public String genererTilfeldigSalt() {
        byte[] salt = new byte[SALT_BYTES];
        new SecureRandom().nextBytes(salt);
        return HexFormat.of().withUpperCase().formatHex(salt);
    }

    public String hashMedSalt(String passord, String saltHex) {
        if (passord == null || saltHex == null) throw new IllegalArgumentException();
        try {
            byte[] salt = HexFormat.of().parseHex(saltHex);
            PBEKeySpec spec = new PBEKeySpec(passord.toCharArray(), salt, ITERATIONS, HASH_BYTES * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return HexFormat.of().withUpperCase().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Feil ved hashing", e);
        }
    }

    public boolean erKorrektPassord(String passord, String salt, String hash) {
        if (passord == null || salt == null || hash == null) throw new IllegalArgumentException();
        String beregnet = hashMedSalt(passord, salt);
        return hash.equalsIgnoreCase(beregnet);
    }
}
