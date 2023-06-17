package pe.com.mallgp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.*;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.exporters.StoreExporterExcel;
import pe.com.mallgp.backend.exporters.StoreMallExporterExcel;
import pe.com.mallgp.backend.repositories.OfferRepository;
import pe.com.mallgp.backend.repositories.StoreRepository;
import pe.com.mallgp.backend.services.StoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getAllStore(){
       List<Store> stores=storeService.listAll();
        /*
        List<Store>stores;
        stores=storeRepository.findAll();
        for (Store s:stores){
            s.setOffers(null);
            s.setStoreMalls(null);
            s.setProductStores(null);
        }*/
        return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
    }
    //hecho

    @GetMapping("/stores/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=store_report";

        response.setHeader(headerKey,headerValue);
        List<Store>stores;
        stores=storeService.listAll();

        StoreExporterExcel exporterExcel=new StoreExporterExcel(stores);
        exporterExcel.export(response);
    }

    @PostMapping("/stores")
    public ResponseEntity<Store> createStore(@RequestBody Store store){
        Store newStore = storeService.save(new Store(store.getName(),store.getCategory(),store.getHorario(),store.getUbicacion(),store.getImg()));
        return new ResponseEntity<Store>(newStore, HttpStatus.CREATED);
    }
    //hecho


    // http://localhost:8080/api/products/1
    @DeleteMapping("/stores/{id}")
    public ResponseEntity<HttpStatus>deleteStoreById(@PathVariable("id")Long id){
        storeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // http://localhost:8080/api/products/4/forced/1
    @DeleteMapping("/stores/{id}/forced/{forced}")
    public ResponseEntity<HttpStatus> deleteProductByIdForced(@PathVariable("id") Long id, @PathVariable("forced") int forced) {
/*
        if (forced==1) {
            Store foundOwner = storeRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found Store with id="+id));
            for (Offer offer: foundOwner.getOffers()) {
                offerRepository.deleteById(offer.getId());
            }
        }
        storeRepository.deleteById(id);*/
        storeService.delete(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //hecho

    @GetMapping("/stores/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id")Long id){
        Store store=storeService.findById(id);
        //store.setOffers(null);

        return new ResponseEntity<Store>(store,HttpStatus.OK);
    }//hecho

    @GetMapping("/stores/name/{name}")
    public ResponseEntity<Store>getStoreByName(@PathVariable("name") String name){

        Store store=storeService.findByName(name);
        /*store.setOffers(null);
        store.setStoreMalls(null);
        store.setProductStores(null);*/
        return new ResponseEntity<Store>(store,HttpStatus.OK);

    }

    @GetMapping("/stores/category/{category}")
    public ResponseEntity<List<Store>> getStoreByCategory(@PathVariable("category") String category){
        List<Store>stores;
        stores=storeService.findByCategory(category);
        /*
        for (Store s:stores){
            s.setOffers(null);
            s.setStoreMalls(null);
            s.setProductStores(null);
        }*/
        return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
    }


    @PutMapping("/stores/{id}")
    public ResponseEntity<Store>updateStore(@PathVariable("id") Long id, @RequestBody Store store){
        Store foundStore=storeRepository.findById(id).orElseThrow();
        if(store.getName()!=null)
            foundStore.setName(store.getName());
        if(store.getCategory()!=null)
            foundStore.setCategory(store.getCategory());
        if(store.getHorario()!=null)
            foundStore.setHorario(store.getHorario());
        if(store.getUbicacion()!=null)
            foundStore.setUbicacion(store.getUbicacion());
        if(store.getImg()!=null)
            foundStore.setImg(store.getImg());

        Store updateStore=storeRepository.save(foundStore);
        updateStore.setOffers(null);
        return new ResponseEntity<Store>(updateStore, HttpStatus.OK);
    }
}
