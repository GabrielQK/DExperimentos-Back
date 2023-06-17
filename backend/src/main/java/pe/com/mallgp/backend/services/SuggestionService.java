package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Suggestion;

import java.util.List;

public interface SuggestionService {
    public List<Suggestion> listAll();

    public Suggestion save(Suggestion suggestion);

    public void delete(Long id, int forced);

    public Suggestion findById(Long id);

}
