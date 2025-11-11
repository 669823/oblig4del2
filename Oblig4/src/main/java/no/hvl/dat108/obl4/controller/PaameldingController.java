package no.hvl.dat108.obl4.controller;

import no.hvl.dat108.obl4.model.Deltager;
import no.hvl.dat108.obl4.service.PaameldingService;
import no.hvl.dat108.obl4.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/paamelding")
public class PaameldingController {

    private final PaameldingService paameldingService;
    private final SessionService sessionService;

    public PaameldingController(PaameldingService paameldingService, SessionService sessionService) {
        this.paameldingService = paameldingService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String skjema() { return "paamelding"; }

    @PostMapping
    public String registrer(@RequestParam String fornavn,
                            @RequestParam String etternavn,
                            @RequestParam String mobil,
                            @RequestParam String kjonn,
                            @RequestParam String passord,
                            @RequestParam("passordRepetert") String passord2,
                            RedirectAttributes ra, HttpSession session) {
        try {
            Deltager d = paameldingService.registrer(fornavn, etternavn, mobil, kjonn, passord, passord2);
            sessionService.login(session, d.getMobil());
            ra.addFlashAttribute("deltager", d);
            return "redirect:/paameldt";
        } catch (PaameldingService.UgyldigPaamelding e) {
            ra.addFlashAttribute("feil", e.getMessage());
            ra.addFlashAttribute("prefillFornavn", fornavn);
            ra.addFlashAttribute("prefillEtternavn", etternavn);
            ra.addFlashAttribute("prefillMobil", mobil);
            ra.addFlashAttribute("prefillKjonn", kjonn);
            return "redirect:/paamelding";
        }
    }
}
