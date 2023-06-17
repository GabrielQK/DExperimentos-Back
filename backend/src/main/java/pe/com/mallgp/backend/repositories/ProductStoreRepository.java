package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.ProductStore;

public interface ProductStoreRepository extends JpaRepository<ProductStore, Long> {
}
