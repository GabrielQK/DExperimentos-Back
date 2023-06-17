package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.ProductStore;

import java.util.List;

public interface ProductStoreService {
    public List<ProductStore> listAll();

    public ProductStore listById(Long id);

    public void delete(Long id);

}
