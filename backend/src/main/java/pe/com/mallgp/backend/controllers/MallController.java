package pe.com.mallgp.backend.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.*;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.exporters.MallExporterExcel;
import pe.com.mallgp.backend.repositories.AdminRepository;
import pe.com.mallgp.backend.repositories.MallRepository;
import pe.com.mallgp.backend.repositories.NewRepository;
import pe.com.mallgp.backend.repositories.StoreRepository;
import pe.com.mallgp.backend.services.MallService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MallController {
    @Autowired
    private MallRepository mallRepository;

    @Autowired
    private MallService mallService;

    @GetMapping("/malls")
    public ResponseEntity<List<Mall>>getAllMall(){
        List<Mall>malls=mallService.listAll();

       /* List<Mall>malls;
        malls=mallRepository.findAll();
        for (Mall m:malls){
            m.setNews(null);
        }*/
        return new ResponseEntity<List<Mall>>(malls, HttpStatus.OK);
    }//hecho

    @GetMapping("/malls/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=mall_report";

        response.setHeader(headerKey,headerValue);
        List<Mall>malls;
        malls=mallService.listAll();

        MallExporterExcel exporterExcel=new MallExporterExcel(malls);
        exporterExcel.export(response);
    }



    //http://localhost:8080/api/malls
    @PostMapping("/malls")
    public ResponseEntity<Mall> createMall(@RequestBody Mall mall){
        Mall newMall = mallService.save(new Mall(mall.getName(), mall.getDirection(), mall.getLocation(), mall.getImg()));
        return new ResponseEntity<Mall>(newMall, HttpStatus.CREATED);
    }//hecho

    @GetMapping("/malls/{id}")
    public ResponseEntity<Mall> getMallById(@PathVariable("id")Long id){
        Mall mall=mallService.findById(id);
        //mall.setNews(null);
        return new ResponseEntity<Mall>(mall,HttpStatus.OK);
    }//hecho

    @PutMapping("/malls/{id}")
    public ResponseEntity<Mall> updateMall(@PathVariable("id") Long id, @RequestBody Mall mall){
        Mall foundMall=mallService.findById(id);
       /* if(mall.getName()!=null)
        foundMall.setName(mall.getName());
        if(mall.getLocation()!=null)
        foundMall.setLocation(mall.getLocation());*/
        Mall updateMall=mallService.save(foundMall);
        //  updateMall.setNews(null);
        return new ResponseEntity<Mall>(updateMall,HttpStatus.OK);
    }//hecho

    // http://localhost:8080/api/malls/1
    /*
    @DeleteMapping("/malls/{id}")
    public ResponseEntity<HttpStatus>deleteMallById(@PathVariable("id")Long id){
        mallRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
*/
    // http://localhost:8080/api/malls/4/forced/1
    @DeleteMapping("/malls/{id}/forced/{forced}")
    public ResponseEntity<HttpStatus> deleteMallByIdForced(@PathVariable("id") Long id, @PathVariable("forced") int forced) {
/*
        if (forced==1) {
            Mall foundOwner = mallRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found Mall with id="+id));
            for (New n: foundOwner.getNews()) {
                newRepository.deleteById(n.getId());
            }
        }
        mallRepository.deleteById(id);*/
        mallService.delete(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }//hecho
}
