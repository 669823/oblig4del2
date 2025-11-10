package no.hvl.dat108.obl4.controller;

import no.hvl.dat108.obl4.service.AuthService;
import no.hvl.dat108.obl4.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/innlogging")
public class InnloggingController {

    private final AuthService authService;
    private final SessionService sessionService;

    public InnloggingController(AuthService authService, SessionService sessionService) {
        this.authService = authService;
        this.sessionService = sessionService;
    }

    @GetMapping
    public String side() { return "innlogging"; }

    @PostMapping
    public String login(@RequestParam String mobil,
                        @RequestParam String passord,
                        RedirectAttributes ra,
                        HttpSession session) {
        return authService.login(mobil, passord)
                .map(d -> {
                    sessionService.login(session, d.getMobil());
                    return "redirect:/deltagerliste";
                })
                .orElseGet(() -> {
                    ra.addFlashAttribute("feil", "Ugyldig brukernavn og/eller passord");
                    return "redirect:/innlogging";
                });
    }
}
