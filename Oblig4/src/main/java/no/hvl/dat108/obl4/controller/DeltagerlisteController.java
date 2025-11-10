package no.hvl.dat108.obl4.controller;

import no.hvl.dat108.obl4.repo.DeltagerRepo;
import no.hvl.dat108.obl4.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/deltagerliste")
public class DeltagerlisteController {

    private final DeltagerRepo repo;
    private final SessionService sessionService;

    public DeltagerlisteController(DeltagerRepo repo, SessionService sessionService) {
        this.repo = repo;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String liste(HttpSession session, Model model) {
        if (!sessionService.erInnlogget(session)) {
            model.addAttribute("feil", "Du må være innlogget for å se deltagerlisten");
            return "innlogging";
        }
        model.addAttribute("innloggetMobil", sessionService.hentInnloggetMobil(session));
        model.addAttribute("deltagere", repo.findAllByOrderByFornavnAscEtternavnAsc());
        return "deltagerliste";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/innlogging?utlogget=1";
}
    }

