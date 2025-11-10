package no.hvl.dat108.obl4.service;

import no.hvl.dat108.obl4.model.Deltager;
import no.hvl.dat108.obl4.repo.DeltagerRepo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class PaameldingServiceTest {

    @Test
    void nekter_duplikat_mobil() {
        var repo = Mockito.mock(DeltagerRepo.class);
        var pass = Mockito.mock(PassordService.class);
        Mockito.when(repo.existsByMobil("12345678")).thenReturn(true);

        var svc = new PaameldingService(repo, pass);
        assertThrows(PaameldingService.UgyldigPaamelding.class, () ->
            svc.registrer("Anne","Nord","12345678","kvinne","passord","passord"));
    }

    @Test
    void registrerer_ok() {
        var repo = Mockito.mock(DeltagerRepo.class);
        var pass = Mockito.mock(PassordService.class);
        Mockito.when(repo.existsByMobil("11112222")).thenReturn(false);
        Mockito.when(pass.genererTilfeldigSalt()).thenReturn("0123456789ABCDEF0123456789ABCDEF");
        Mockito.when(pass.hashMedSalt(Mockito.anyString(), Mockito.anyString())).thenReturn("AABB");
        Mockito.when(repo.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0, Deltager.class));

        var svc = new PaameldingService(repo, pass);
        var d = svc.registrer("Arne","Aas","11112222","mann","passord","passord");
        assertEquals("11112222", d.getMobil());
    }
}
