package fabiocarlino.u5l9.repositories;

import fabiocarlino.u5l9.entities.Author;
import fabiocarlino.u5l9.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogsRepo extends JpaRepository<Blog, UUID> {
    List<Blog> findByAuthor(Author author);

    boolean existsByTitolo(String titolo);

}
