package no.hvl.dat108.obl4.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Passord {
    private String hash;
    private String salt;

    protected Passord() {}

    public Passord(String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public String getHash() { return hash; }
    public String getSalt() { return salt; }
}
