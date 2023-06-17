package pe.com.mallgp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.StoreMall;
import pe.com.mallgp.backend.exporters.StoreMallExporterExcel;
import pe.com.mallgp.backend.repositories.AdminRepository;
import pe.com.mallgp.backend.repositories.MallRepository;
import pe.com.mallgp.backend.repositories.StoreMallRepository;
import pe.com.mallgp.backend.repositories.StoreRepository;
import pe.com.mallgp.backend.services.StoreMallService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StoreMallController {


    @Autowired
    private StoreMallService storeMallService;

    @GetMapping("/store/mall")
    public ResponseEntity<List<StoreMall>> getAllStoreMall(){
       /* List<StoreMall> storeMalls = storeMallRepository.findAll();
        return new ResponseEntity<>(storeMalls, HttpStatus.OK); */

        List<StoreMall> storeMalls = storeMallService.listAll();
        return new ResponseEntity<>(storeMalls, HttpStatus.OK);

        //LISTO
    }

    @GetMapping("/store/mall/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=store_mall_report";

        response.setHeader(headerKey,headerValue);
        List<StoreMall>storeMalls;
        storeMalls=storeMallService.listAll();

        StoreMallExporterExcel exporterExcel=new StoreMallExporterExcel(storeMalls);
        exporterExcel.export(response);
    }

    @GetMapping("/store/mall/{id}")
    public ResponseEntity<StoreMall> getStoreMallById(@PathVariable("id") Long id){
       /* StoreMall storeMall=storeMallRepository.findById(id).get();
        return new ResponseEntity<>(storeMall, HttpStatus.OK); */

        StoreMall storeMall=storeMallService.listById(id);
        return new ResponseEntity<>(storeMall, HttpStatus.OK);
    }

   /* @PostMapping("/store/{storeId}/mall/{mallId}/admin/{adminId}")
    public ResponseEntity<StoreMall> createStoreMall(@RequestBody StoreMallDto request, @PathVariable Long storeId, @PathVariable Long mallId, @PathVariable Long adminId){

        Store store = storeRepository.findById(storeId).get();
        Mall mall = mallRepository.findById(mallId).get();
        Admin admin = adminRepository.findById(adminId).get();

        StoreMall newStoreMall = storeMallRepository.save(new StoreMall(store, mall, request.getCapacity(), admin));

        return new ResponseEntity<>(newStoreMall, HttpStatus.CREATED);
    }

    @PutMapping("/store/mall/{id}")
    public ResponseEntity<StoreMall> updateStoreMall(@RequestBody StoreMallDto request, @PathVariable Long id){
        StoreMall storeMall = storeMallRepository.findById(id).get();

        storeMall.setCapacity(request.getCapacity());

        StoreMall newProductStore = storeMallRepository.save(storeMall);
        return new ResponseEntity<>(newProductStore, HttpStatus.OK);
    } */

    @DeleteMapping("/store/mall/{id}")
    public ResponseEntity<StoreMall> deleteStoreMall(@PathVariable Long id){

       /* StoreMall storeMall = storeMallRepository.findById(id).get();

        storeMallRepository.deleteById(storeMall.getId());

        return new ResponseEntity<>(storeMall, HttpStatus.OK); */

        storeMallService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
