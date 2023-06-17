package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.Suggestion;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.repositories.SuggestionRepository;
import pe.com.mallgp.backend.services.SuggestionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SuggestionServiceImpl  implements SuggestionService {

    @Autowired
    SuggestionRepository suggestionRepository;

    public List<Suggestion> listAll(){
        List<Suggestion>suggestions;
        suggestions=suggestionRepository.findAll();
        return suggestions;
    }

    @Transactional
    public Suggestion save(Suggestion suggestion){
        Suggestion newSuggestion = suggestionRepository.save(new Suggestion(suggestion.getNsugerencia(),suggestion.getContenido(),suggestion.getDatesugerencia()));
        return newSuggestion;
    }

    @Transactional
    public void delete(Long id, int forced){
        if(forced==1){
            Suggestion foundSuggestion = suggestionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found New with id="+id));
        }
        suggestionRepository.deleteById(id);
    }

    public Suggestion findById(Long id){
        Suggestion suggestion=suggestionRepository.findById(id).get();
        return suggestion;
    }
}
