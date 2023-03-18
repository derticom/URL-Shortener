package ru.derticom.urlshortener.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.derticom.urlshortener.models.Link;
import ru.derticom.urlshortener.services.LinkService;

@Controller
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String addProcess(HttpServletRequest request, Model model) {
        String originalLink = request.getParameter("link");
        String shortLink = linkService.generateShortLink();

        while (linkService.findByShort(shortLink) != null) {
            shortLink = linkService.generateShortLink();
        }

        linkService.saveLink(new Link(originalLink, shortLink));

        model.addAttribute("link", request.getRequestURL() + shortLink);
        model.addAttribute("qr", "http://api.qrserver.com/v1/create-qr-code/?data="
                + request.getRequestURL() + shortLink + "&amp;size=150x150");
        return "result";
    }

    @GetMapping("/{var}")
    public String getProcess(@PathVariable("var") String var, Model model) {
        Link link = linkService.findByShort(var);
        if (link == null) {
            return "not_exist";
        } else {
            model.addAttribute("redirect_link", link.getOriginalLink());
            return "redirect";
        }

    }


}
