package no.hvl.dat108.obl4.service;

import no.hvl.dat108.obl4.model.Deltager;
import no.hvl.dat108.obl4.model.Passord;
import no.hvl.dat108.obl4.repo.DeltagerRepo;
import no.hvl.dat108.obl4.validering.DeltagerRules;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaameldingService {

    private final DeltagerRepo repo;
    private final PassordService passordService;

    public PaameldingService(DeltagerRepo repo, PassordService passordService) {
        this.repo = repo;
        this.passordService = passordService;
    }

    public static class UgyldigPaamelding extends RuntimeException {
        public UgyldigPaamelding(String msg) { super(msg); }
    }

    @Transactional
    public Deltager registrer(String fornavn, String etternavn, String mobil,
                              String kjonn, String passord, String passord2) {

        try {
            // Kall gjerne DeltagerRules som du gjør i dag.
            if (!DeltagerRules.gyldigFornavn(fornavn)) {
                throw new UgyldigPaamelding("Ugyldig fornavn");
            }
            if (!DeltagerRules.gyldigEtternavn(etternavn)) {
                throw new UgyldigPaamelding("Ugyldig etternavn");
            }
            if (!DeltagerRules.gyldigMobil(mobil)) {
                throw new UgyldigPaamelding("Mobil må være 8 siffer");
            }
            if (!DeltagerRules.gyldigKjonn(kjonn)) {
                throw new UgyldigPaamelding("Velg gyldig kjønn");
            }
            if (!DeltagerRules.gyldigPassord(passord)) {
                // Hvis gyldigPassord kan returnere false
                throw new UgyldigPaamelding("Passord må ha minst 8 tegn og inneholde liten, stor og siffer");
            }
            if (!passord.equals(passord2)) {
                throw new UgyldigPaamelding("Passordene må være like");
            }

            if (repo.existsByMobil(mobil)) {
                throw new UgyldigPaamelding("Deltager med dette mobilnummeret er allerede påmeldt");
            }

            String salt = passordService.genererTilfeldigSalt();
            String hash = passordService.hashMedSalt(passord, salt);
            Passord p = new Passord(hash, salt);

            Deltager d = new Deltager(mobil, p, fornavn, etternavn, kjonn);
            return repo.save(d);

        } catch (IllegalArgumentException e) {
            // NØKKEL: hvis DeltagerRules kaster IllegalArgumentException med detaljmelding,
            // pakk den om til typen controlleren allerede fanger.
            throw new UgyldigPaamelding(e.getMessage() != null ? e.getMessage() : "Påmeldingsdetaljer er ugyldige");
        }
    }
}
