package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.mallgp.backend.entities.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository <Store, Long> {

    Store findByName(String name);
    List<Store> findByCategory(String category);

    @Query("SELECT s FROM Store s WHERE s.id=?1")
    Store findByNameSQL(String Name);

}
