package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.Mall;
import pe.com.mallgp.backend.entities.New;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.repositories.MallRepository;
import pe.com.mallgp.backend.repositories.NewRepository;
import pe.com.mallgp.backend.services.MallService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MallServiceImpl implements MallService {

    @Autowired
    MallRepository mallRepository;

    @Autowired
    NewRepository newRepository;

    public List<Mall> listAll(){
        List<Mall>malls;
        malls=mallRepository.findAll();
        for (Mall m:malls){
            m.setNews(null);
            m.setStoreMalls(null);
        }
        return malls;
    }

    @Transactional
    public Mall save(Mall mall){
        Mall newMall = mallRepository.save(new Mall(mall.getName(), mall.getDirection(), mall.getLocation(), mall.getImg()));
        return  newMall;
    }

    @Transactional
    public void delete(Long id, int forced){

        if (forced==1) {
            Mall foundOwner = mallRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found Mall with id="+id));
            for (New n: foundOwner.getNews()) {
                newRepository.deleteById(n.getId());
            }
        }
        mallRepository.deleteById(id);
    }

    public Mall findById(Long id){
        Mall mall=mallRepository.findById(id).get();
        mall.setNews(null);
        mall.setStoreMalls(null);
        return mall;
    }

    @Transactional
    public Mall update(Long id, Mall mall){
        Mall foundMall=mallRepository.findById(id).get();
        if(mall.getName()!=null)
            foundMall.setName(mall.getName());
        if(mall.getDirection()!=null)
            foundMall.setDirection(mall.getDirection());
        if(mall.getLocation()!=null)
            foundMall.setLocation(mall.getLocation());
        if(mall.getImg()!=null)
            foundMall.setImg(mall.getImg());
        Mall updateMall=mallRepository.save(foundMall);
        updateMall.setNews(null);
        return updateMall;
    }
}
