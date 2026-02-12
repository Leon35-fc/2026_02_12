package fabiocarlino.u5l9.repositories;

import fabiocarlino.u5l9.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepo extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);
}
