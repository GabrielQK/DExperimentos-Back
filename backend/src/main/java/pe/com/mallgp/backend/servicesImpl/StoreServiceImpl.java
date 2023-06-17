package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.Offer;
import pe.com.mallgp.backend.entities.Store;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.repositories.OfferRepository;
import pe.com.mallgp.backend.repositories.StoreRepository;
import pe.com.mallgp.backend.services.StoreService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OfferRepository offerRepository;

    public List<Store> listAll(){
        List<Store>stores;
        stores=storeRepository.findAll();
        for (Store s:stores){
            s.setOffers(null);
            s.setStoreMalls(null);
            s.setProductStores(null);
        }
        return stores;
    }

    @Transactional
    public Store save(Store store){
        Store newStore = storeRepository.save(new Store(store.getName(),store.getCategory(),store.getHorario(),store.getUbicacion(),store.getImg()));
        return newStore;
    }

    @Transactional
    public void delete(Long id, int forced){
        if (forced==1) {
            Store foundOwner = storeRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found Store with id="+id));
            for (Offer offer: foundOwner.getOffers()) {
                offerRepository.deleteById(offer.getId());
            }
        }
        storeRepository.deleteById(id);
    }

    public Store findById(Long id){
        Store store=storeRepository.findById(id).get();
        store.setOffers(null);
        store.setProductStores(null);
        store.setStoreMalls(null);
        return store;
    }

    @Transactional
    public Store update(Long id, Store store){
        Store foundStore=storeRepository.findById(id).get();
        if(store.getName()!=null)
            foundStore.setName(store.getName());
        if(store.getCategory()!=null)
            foundStore.setCategory(store.getCategory());
        Store updateStore=storeRepository.save(foundStore);
        updateStore.setOffers(null);
        return updateStore;
    }

    public List<Store> findByCategory(String category){
        List<Store>stores;
        stores=storeRepository.findByCategory(category);
        for (Store s:stores){
            s.setOffers(null);
            s.setStoreMalls(null);
            s.setProductStores(null);
        }
        return stores;
    }

    public Store findByName(String name){
        Store store=storeRepository.findByName(name);
        store.setOffers(null);
        store.setStoreMalls(null);
        store.setProductStores(null);
        return store;
    }

}
