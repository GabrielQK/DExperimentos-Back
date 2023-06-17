package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.Suggestion;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findByNsugerencia(String nsugerencia);

}
