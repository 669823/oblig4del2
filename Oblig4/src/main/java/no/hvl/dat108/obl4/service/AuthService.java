package no.hvl.dat108.obl4.service;

import no.hvl.dat108.obl4.model.Deltager;
import no.hvl.dat108.obl4.repo.DeltagerRepo;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final DeltagerRepo repo;
    private final PassordService passordService;

    public AuthService(DeltagerRepo repo, PassordService passordService) {
        this.repo = repo;
        this.passordService = passordService;
    }

    public Optional<Deltager> login(String mobil, String passord) {
        return repo.findById(mobil)
                .filter(d -> passordService.erKorrektPassord(
                        passord, d.getPassord().getSalt(), d.getPassord().getHash()));
    }
}
