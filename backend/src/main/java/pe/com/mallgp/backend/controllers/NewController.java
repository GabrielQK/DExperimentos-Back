package pe.com.mallgp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.New;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.exporters.MallExporterExcel;
import pe.com.mallgp.backend.exporters.NewExporterExcel;
import pe.com.mallgp.backend.repositories.NewRepository;
import pe.com.mallgp.backend.services.NewService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class NewController {

    @Autowired
    private NewService newService;

    @Autowired
    private NewRepository newRepository;

    @GetMapping("/news")
    public ResponseEntity<List<New>> getAllNews(){
        List<New> news=newService.listAll();
        /*List<New> news;
        news=newRepository.findAll();
        for(New n:news){
            n.setMall(null);
        }*/
        return new ResponseEntity<List<New>>(news, HttpStatus.OK);
    }
    //hecho

    @GetMapping("/news/export/excel")
    public void exportToExcel(HttpServletResponse response)throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=new_report";

        response.setHeader(headerKey, headerValue);
        List<New>news;
        news=newService.listAll();
        NewExporterExcel exporterExcel=new NewExporterExcel(news);
        exporterExcel.export(response);
    }


    @PostMapping("/news")
    public ResponseEntity<New> createNew(@RequestBody New news){
        New newNew = newService.save(new New(news.getText(),news.getDate_on(),news.getDate_of(),news.getMall()));
        return new ResponseEntity<New>(newNew, HttpStatus.CREATED);
    }//hecho

    // http://localhost:8080/api/news/1

    @DeleteMapping("/news/{id}")
    public ResponseEntity<HttpStatus>deleteNewById(@PathVariable("id")Long id){
        newRepository.deleteById(id);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // http://localhost:8080/api/news/4/forced/1
    @DeleteMapping("/news/{id}/forced/{forced}")
    public ResponseEntity<HttpStatus> deleteNewByIdForced(@PathVariable("id") Long id, @PathVariable("forced") int forced) {

      /*  if (forced==1) {
            New foundOwner = newRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found New with id="+id));

        }
        newRepository.deleteById(id);*/
        newService.delete(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }//hecho

    @GetMapping("/news/{id}")
    public ResponseEntity<New>getNewById(@PathVariable("id") Long id){
        New news=newService.findById(id);
        //news.setMall(null);
        return new ResponseEntity<New>(news,HttpStatus.OK);
    }//hecho

    @PutMapping("/news/{id}")
    public ResponseEntity<New> updateNew(@PathVariable("id") Long id, @RequestBody New news){
        New foundNew=newService.findById(id);
       /* if(news.getText()!=null)
        foundNew.setText(news.getText());
        if(news.getDate_on()!=null)
        foundNew.setDate_on(news.getDate_on());
        if(news.getDate_of()!=null)
        foundNew.setDate_of(news.getDate_of());
        if(news.getMall()!=null)
        foundNew.setMall(news.getMall());*/
        New updateNew=newService.save(foundNew);
       // updateNew.setMall(null);
        return new ResponseEntity<New>(updateNew,HttpStatus.OK);
    }//hecho
}
