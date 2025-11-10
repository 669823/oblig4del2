package no.hvl.dat108.obl4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "deltager")
public class Deltager {

    @Id
    @Column(length = 8, nullable = false)
    private String mobil;

    @Embedded
    private Passord passord;

    @Column(length = 40, nullable = false)
    private String fornavn;

    @Column(length = 40, nullable = false)
    private String etternavn;

    @Column(length = 6, nullable = false)
    private String kjonn; // "mann" | "kvinne"

    protected Deltager() {}

    public Deltager(String mobil, Passord passord, String fornavn, String etternavn, String kjonn) {
        this.mobil = mobil;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.kjonn = kjonn;
    }

    public String getMobil() { return mobil; }
    public Passord getPassord() { return passord; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public String getKjonn() { return kjonn; }

    public void setFornavn(String fornavn) { this.fornavn = fornavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }
    public void setKjonn(String kjonn) { this.kjonn = kjonn; }
}
