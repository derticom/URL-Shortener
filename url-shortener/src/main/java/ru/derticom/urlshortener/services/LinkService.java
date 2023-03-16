package ru.derticom.urlshortener.services;

import org.springframework.stereotype.Service;
import ru.derticom.urlshortener.models.Link;
import ru.derticom.urlshortener.repositories.LinkRepository;

import java.util.Random;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    public Link findByShort(String shortLink) {
        if (linkRepository.findByShortLink(shortLink).isPresent()) {
            return linkRepository.findByShortLink(shortLink).get();
        } else {
            return null;
        }
    }

    public Link findByOriginal(String originalLink) {
        if (linkRepository.findByOriginalLink(originalLink).isPresent()) {
            return linkRepository.findByOriginalLink(originalLink).get();
        } else {
            return null;
        }
    }


    public String generateShortLink() {

        int shortLinkLength = 7;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder shortLink = new StringBuilder();

        for (int i = 0; i < shortLinkLength; i++) {
            int index = random.nextInt(characters.length());
            shortLink.append(characters.charAt(index));
        }
        return shortLink.toString();
    }


}
