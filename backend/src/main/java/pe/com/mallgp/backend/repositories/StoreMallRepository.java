package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.StoreMall;

import java.util.List;

public interface StoreMallRepository extends JpaRepository<StoreMall, Long> {

}
