package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Admin;
import pe.com.mallgp.backend.entities.Product;

import java.util.List;

public interface ProductService {
    public List<Product> listAll();

    public Product save(Product product);

    public void delete(Long id, int forced);

    public Product findById(Long id);

    public Product update(Long id, Product product);

}
