package pe.com.mallgp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.ProductStore;
import pe.com.mallgp.backend.exporters.ProductExporterExcel;
import pe.com.mallgp.backend.exporters.ProductStoreExporterExcel;
import pe.com.mallgp.backend.repositories.ProductRepository;
import pe.com.mallgp.backend.repositories.ProductStoreRepository;
import pe.com.mallgp.backend.repositories.StoreRepository;
import pe.com.mallgp.backend.services.ProductStoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductStoreController {


    @Autowired
    private ProductStoreService productStoreService;

    @GetMapping("/product/stores")
    public ResponseEntity<List<ProductStore>> getAllProductStore(){
       /* List<ProductStore> productStores = productStoreRepository.findAll();
        return new ResponseEntity<>(productStores, HttpStatus.OK); */
        List<ProductStore> productStores = productStoreService.listAll();
        return new ResponseEntity<>(productStores, HttpStatus.OK);
        //LISTO
    }

    @GetMapping("/product/stores/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=product_store_report";

        response.setHeader(headerKey,headerValue);
        List<ProductStore>productStores;
        productStores=productStoreService.listAll();

        ProductStoreExporterExcel exporterExcel=new ProductStoreExporterExcel(productStores);
        exporterExcel.export(response);
    }

    @GetMapping("/product/stores/{id}")
    public ResponseEntity<ProductStore> getProductStoreById(@PathVariable("id") Long id){
       /* ProductStore productStore=productStoreRepository.findById(id).get();
        return new ResponseEntity<>(productStore, HttpStatus.OK); */
        ProductStore productStore=productStoreService.listById(id);
        return new ResponseEntity<>(productStore, HttpStatus.OK);

    }

   /* @PostMapping("/product/{productId}/stores/{storeId}")
    public ResponseEntity<ProductStore> createProductStore(@RequestBody CreateProductStore request, @PathVariable Long productId, @PathVariable Long storeId){

        Product product = productRepository.findById(productId).get();
        Store store = storeRepository.findById(storeId).get();

        ProductStore newProductStore = productStoreRepository.save(new ProductStore(product, store, request.getPrice(), request.getRestock()));

        return new ResponseEntity<>(newProductStore, HttpStatus.CREATED);
    }

     @PutMapping("/product/stores/{id}")
    public ResponseEntity<ProductStore> updateProductStore(@RequestBody CreateProductStore request, @PathVariable Long id){
        ProductStore productStore = productStoreRepository.findById(id).get();

        productStore.setPrice(request.getPrice());
        productStore.setRestock(request.getRestock());

        ProductStore newProductStore = productStoreRepository.save(productStore);
        return new ResponseEntity<>(newProductStore, HttpStatus.OK);
    } */

    @DeleteMapping("/product/stores/{id}")
    public ResponseEntity<ProductStore> deleteProductStore(@PathVariable Long id){

       /* ProductStore productStore = productStoreRepository.findById(id).get();

        productStoreRepository.deleteById(productStore.getId()); */

        productStoreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
