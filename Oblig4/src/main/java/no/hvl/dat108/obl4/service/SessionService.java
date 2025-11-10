package no.hvl.dat108.obl4.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private static final String KEY = "innloggetMobil";

    public void login(HttpSession session, String mobil) {
        session.setAttribute(KEY, mobil);
    }
    public void logout(HttpSession session) {
        session.invalidate();
    }
    public boolean erInnlogget(HttpSession session) {
        return session != null && session.getAttribute(KEY) != null;
    }
    public String hentInnloggetMobil(HttpSession session) {
        Object o = session.getAttribute(KEY);
        return o == null ? null : o.toString();
    }
}
