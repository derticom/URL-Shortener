package ru.derticom.urlshortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.derticom.urlshortener.models.Link;
import ru.derticom.urlshortener.repositories.LinkRepository;

@Service
public class LinkService {

    @Autowired
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


}
