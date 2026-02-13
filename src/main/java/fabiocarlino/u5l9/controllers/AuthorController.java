package fabiocarlino.u5l9.controllers;

import fabiocarlino.u5l9.entities.Author;
import fabiocarlino.u5l9.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    //1) POST - http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author body) throws Exception {
        return authorService.save(body);
    }

    //2) GET - http://localhost:3001/authors
    @GetMapping("")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return authorService.getAuthors(page, size, sortBy);
    }

    //3) GET - http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId) {
        return authorService.findById(authorId);
    }

    //4) - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Author findAndUpdate(@PathVariable UUID authorId, @RequestBody Author body) {
        return authorService.findByIdAndUpdate(authorId, body);
    }

    //5) - DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable UUID authorId) {
        authorService.findByIdAndDelete(authorId);
    }

}
