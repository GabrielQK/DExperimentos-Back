package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Offer;

import java.util.List;

public interface OfferService {
    public List<Offer> listAll();
    public Offer save(Offer offer);

    public void delete(Long id, int forced);

    public Offer findById(Long id);

    public Offer update(Long id, Offer offer);
}
