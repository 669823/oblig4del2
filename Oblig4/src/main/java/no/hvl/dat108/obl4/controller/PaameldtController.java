package no.hvl.dat108.obl4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaameldtController {
    @GetMapping("/paameldt")
    public String bekreftelse() { return "paameldt"; }
}
