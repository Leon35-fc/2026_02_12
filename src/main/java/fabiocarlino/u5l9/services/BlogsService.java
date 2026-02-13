package fabiocarlino.u5l9.services;

import fabiocarlino.u5l9.entities.Author;
import fabiocarlino.u5l9.entities.Blog;
import fabiocarlino.u5l9.payload.BlogPayload;
import fabiocarlino.u5l9.repositories.BlogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogsService {

    @Autowired
    private final BlogsRepo blogRepo;

    @Autowired
    private final AuthorService authorService;

    @Autowired
    public BlogsService(BlogsRepo blogsRepo, AuthorService authorService) {
        this.blogRepo = blogsRepo;
        this.authorService = authorService;
    }

    public List<Blog> getBlogs() {
        return blogRepo.findAll();
    }

    public Blog save(BlogPayload payload) {
        if (this.blogRepo.existsByTitolo(payload.getTitolo())) {
            throw new RuntimeException("Blog con il titolo \"" + payload.getTitolo() + "\" è già presente.");
        }
        Blog newBlog = new Blog(payload.getCategoria(),
                payload.getTitolo(),
                payload.getContenuto(),
                payload.getMinutiTempoLettura(),
                authorService.findById(payload.getAuthorId()));
        newBlog.setCover("https://picsum.photos/200/300?random");
        Blog savedBlog = this.blogRepo.save(newBlog);
        return savedBlog;
    }

    public Blog findById(UUID id) {
        return this.blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Blog con ID " + id + " non trovato."));
    }

    public void findByIdAndDelete(UUID id) {
        Blog found = this.findById(id);
        blogRepo.delete(found);
    }

    public Blog findByIdAndUpdate(UUID id, BlogPayload body) {
        Blog found = this.findById(id);

        found.setMinutiTempoLettura(body.getMinutiTempoLettura());
        found.setContenuto(body.getContenuto());
        found.setTitolo(body.getTitolo());
        found.setCategoria(body.getCategoria());

        if (found.getAuthor().getId() != body.getAuthorId()) {
            Author newAuthor = authorService.findById(body.getAuthorId());
            found.setAuthor(newAuthor);
        }

        return blogRepo.save(found);
    }

    public List<Blog> findByAuthor(UUID authorId) {
        Author author = authorService.findById(authorId);
        return this.blogRepo.findByAuthor(author);
    }
}
