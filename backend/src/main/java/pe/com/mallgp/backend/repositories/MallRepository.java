package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.mallgp.backend.entities.Mall;

import java.util.List;

public interface MallRepository extends JpaRepository <Mall, Long> {
    @Query(value = "SELECT * FROM malls WHERE id=?1", nativeQuery = true)
    Mall findByNameSQL(String Name);

    Mall findByName(String name);
    List<Mall> findByLocation(String location);

}
