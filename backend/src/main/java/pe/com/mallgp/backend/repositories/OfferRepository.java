package pe.com.mallgp.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mallgp.backend.entities.Offer;
import pe.com.mallgp.backend.entities.Product;
import pe.com.mallgp.backend.entities.Store;

import java.util.Date;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer>findByStore(Store store);
    List<Offer>findByProduct(Product product);
}
