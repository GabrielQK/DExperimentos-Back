package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.StoreMall;
import pe.com.mallgp.backend.repositories.StoreMallRepository;
import pe.com.mallgp.backend.services.StoreMallService;

import java.util.List;

@Service
public class StoreMallServiceImpl implements StoreMallService {
    @Autowired
    StoreMallRepository storeMallRepository;

    public List<StoreMall> listAll() {

        List<StoreMall> storeMalls;
        storeMalls= storeMallRepository.findAll();
        for(StoreMall s:storeMalls){
            s.setStore(null);
            s.setMall(null);
            s.setAdmin(null);
        }
        return storeMalls;
    }

    public StoreMall listById(Long id){
        StoreMall storeMall;
        storeMall=storeMallRepository.findById(id).get();
        storeMall.setStore(null);
        storeMall.setMall(null);
        storeMall.setAdmin(null);
        return storeMall;
    }

    public void delete(Long id){

        StoreMall storeMall = storeMallRepository.findById(id).get();

        storeMallRepository.deleteById(storeMall.getId());

    }
}
