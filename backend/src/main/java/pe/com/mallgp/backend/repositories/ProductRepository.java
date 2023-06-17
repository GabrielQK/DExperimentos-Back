package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {

    Product findByCategory(String category);
    Product findByName(String name);
}
