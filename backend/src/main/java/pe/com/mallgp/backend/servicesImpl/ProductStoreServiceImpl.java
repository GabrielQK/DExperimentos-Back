package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.ProductStore;
import pe.com.mallgp.backend.repositories.ProductStoreRepository;
import pe.com.mallgp.backend.services.ProductStoreService;

import java.util.List;

@Service
public class ProductStoreServiceImpl implements ProductStoreService {
    @Autowired
    ProductStoreRepository productStoreRepository;

    public List<ProductStore> listAll(){
        List<ProductStore> productStores;
        productStores = productStoreRepository.findAll();
        for(ProductStore p:productStores){
            p.setProduct(null);
            p.setStore(null);
        }
        return productStores;
    }

    public ProductStore listById(Long id){
        ProductStore productStore;
        productStore=productStoreRepository.findById(id).get();
        productStore.setStore(null);
        productStore.setProduct(null);
        return productStore;
    }

    public void delete(Long id){

        ProductStore productStore = productStoreRepository.findById(id).get();

        productStoreRepository.deleteById(productStore.getId());

    }
}
