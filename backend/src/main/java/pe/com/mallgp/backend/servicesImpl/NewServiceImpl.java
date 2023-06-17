package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.New;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.repositories.NewRepository;
import pe.com.mallgp.backend.services.NewService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    NewRepository newRepository;

    public List<New> listAll(){
        List<New> news;
        news=newRepository.findAll();
        for(New n:news){
            n.setMall(null);
        }
        return news;
    }

    @Transactional
    public New save(New news){
        New newNew = newRepository.save(new New(news.getText(),news.getDate_on(),news.getDate_of(),news.getMall()));
        return newNew;
    }

    @Transactional
    public void delete(Long id, int forced){
        if (forced==1) {
            New foundService = newRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found New with id="+id));

        }
        newRepository.deleteById(id);
    }

    public New findById(Long id){
        New news=newRepository.findById(id).get();
        news.setMall(null);
        return news;
    }

    public New update(Long id, New news){
        New foundNew=newRepository.findById(id).get();
        if(news.getText()!=null)
            foundNew.setText(news.getText());
        if(news.getDate_on()!=null)
            foundNew.setDate_on(news.getDate_on());
        if(news.getDate_of()!=null)
            foundNew.setDate_of(news.getDate_of());
        if(news.getMall()!=null)
            foundNew.setMall(news.getMall());
        New updateNew=newRepository.save(foundNew);
        updateNew.setMall(null);
        return updateNew;
    }
}
