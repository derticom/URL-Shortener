package ru.derticom.urlshortener.services;

import org.junit.jupiter.api.Test;
import ru.derticom.urlshortener.repositories.LinkRepository;

import static org.junit.jupiter.api.Assertions.*;

class LinkServiceTest {

    LinkService linkService = new LinkService(null);

    @Test
    void shortLinkNotNull() {
        assertFalse(linkService.generateShortLink() == null);
    }

    @Test
    void shortLinkLengthIsSeven() {
        assertEquals(7, linkService.generateShortLink().length());
    }

    @Test
    void shortLinkHasOnlyRequiredChars() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < linkService.generateShortLink().length(); j++) {
                assertTrue(characters.contains(String.valueOf(linkService.generateShortLink().charAt(j))));
            }
        }
    }

}