package fabiocarlino.u5l9.services;

import fabiocarlino.u5l9.entities.Author;
import fabiocarlino.u5l9.exceptions.NotFoundException;
import fabiocarlino.u5l9.repositories.AuthorRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    AuthorRepo authorRepo;

    public Author save(Author payload) {
        authorRepo.findByEmail(payload.getEmail()).ifPresent(author -> {
            try {
                throw new BadRequestException("L'email " + author.getEmail() + " è già stata utilizzata.");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        payload.setAvatar("https://ui-avatars.com/api/?name=" + payload.getNome().charAt(0) + "+" + payload.getCognome().charAt(0));
        return authorRepo.save(payload);
    }

    public Page<Author> getAuthors(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return authorRepo.findAll(pageable);
    }

    public Author findById(UUID id) {
        return authorRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Author found = this.findById(id);
        authorRepo.delete(found);
    }

    public Author findByIdAndUpdate(UUID id, Author payload) {

        Author found = this.findById(id);
        found.setEmail(payload.getEmail());
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setDataDiNascita(payload.getDataDiNascita());
        found.setAvatar(payload.getAvatar());
        return authorRepo.save(found);
    }
}
