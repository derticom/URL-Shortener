package ru.derticom.urlshortener.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.derticom.urlshortener.models.Link;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    Optional<Link> findByOriginalLink (String originalLink);
    Optional<Link> findByShortLink (String shortLink);

}
