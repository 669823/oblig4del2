package no.hvl.dat108.obl4.repo;

import no.hvl.dat108.obl4.model.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeltagerRepo extends JpaRepository<Deltager, String> {
    boolean existsByMobil(String mobil);
    List<Deltager> findAllByOrderByFornavnAscEtternavnAsc();
}
