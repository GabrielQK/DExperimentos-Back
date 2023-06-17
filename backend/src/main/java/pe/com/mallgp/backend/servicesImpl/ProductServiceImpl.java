package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.Offer;
import pe.com.mallgp.backend.entities.Product;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.repositories.OfferRepository;
import pe.com.mallgp.backend.repositories.ProductRepository;
import pe.com.mallgp.backend.services.ProductService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OfferRepository offerRepository;
    public List<Product> listAll(){
        List<Product> products;
        products=productRepository.findAll();
        for(Product p:products){
            p.setOffers(null);
            p.setProductStores(null);
        }
        return products;
    }

    @Transactional
    public Product save(Product product){
        Product newProduct = productRepository.save(new Product(product.getName(),product.getCategory(),product.getDescription(),product.getPrice(),product.getGender(),product.getImg()));
        return newProduct;
    }

    @Transactional
    public void delete(Long id, int forced){
        if (forced==1) {
            Product foundOffer = productRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found Product with id="+id));
            for (Offer offer: foundOffer.getOffers()) {
                offerRepository.deleteById(offer.getId());
            }
        }
        productRepository.deleteById(id);
    }

    public Product findById(Long id){
        Product product=productRepository.findById(id).get();
        product.setOffers(null);
        product.setProductStores(null);
        return product;
    }

    @Transactional
    public Product update(Long id, Product product){
        Product foundProduct=productRepository.findById(id).get();
        foundProduct.setName(product.getName());
        foundProduct.setCategory(product.getCategory());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setGender(product.getGender());
        foundProduct.setImg(product.getImg());
        Product updateProduct=productRepository.save(foundProduct);
        updateProduct.setOffers(null);
        return updateProduct;
    }

}
