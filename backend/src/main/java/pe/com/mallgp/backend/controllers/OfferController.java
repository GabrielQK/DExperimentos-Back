package pe.com.mallgp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.Offer;
import pe.com.mallgp.backend.entities.Product;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.exporters.OfferExporterExcel;
import pe.com.mallgp.backend.repositories.OfferRepository;
import pe.com.mallgp.backend.services.OfferService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getAllOffers(){
        List<Offer> offers=offerService.listAll();
        if(offers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);

       /* List<Offer> offers;
        offers=offerRepository.findAll();
        for(Offer o:offers){
            o.setStore(null);
            o.setProduct(null);
        }
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK); */

        //LISTO
    }

    @GetMapping("/offers/export/excel")
    public void exportToExcel(HttpServletResponse response)throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=offer_report";

        response.setHeader(headerKey, headerValue);
        List<Offer>offers;
        offers=offerService.listAll();

        OfferExporterExcel exporterExcel=new OfferExporterExcel(offers);
        exporterExcel.export(response);
    }

    @PostMapping("/offers")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer){

        Offer newOffer = offerRepository.save(new Offer(offer.getName(),offer.getName_product(),offer.getGender_product(),
                offer.getPrice_s_desc(),offer.getPrice_c_desc(),offer.getDate_on(),offer.getDate_of(),offer.getImg(),offer.getStore(),offer.getProduct()));
        return new ResponseEntity<Offer>(newOffer, HttpStatus.CREATED);

        // Offer newOffer = offerService.save(new Offer(offer.getName(),offer.getName_product(),offer.getGender_product(),offer.getPrice_s_desc(),offer.getPrice_c_desc(),offer.getDate_on(), offer.getDate_of(), offer.getImg(),offer.getStore(),offer.getProduct()));
       // return new ResponseEntity<Offer>(newOffer, HttpStatus.CREATED);

        //LISTO
    }

    // http://localhost:8080/api/offers/1

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<HttpStatus>deleteOfferById(@PathVariable("id")Long id){
        offerRepository.deleteById(id);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // http://localhost:8080/api/offers/4/forced/1
    @DeleteMapping("/offers/{id}/forced/{forced}")
    public ResponseEntity<HttpStatus> deleteOfferByIdForced(@PathVariable("id") Long id, @PathVariable("forced") int forced) {

        /* if (forced==1) {
            Offer foundOffer = offerRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found Offer with id="+id));

        }
        offerRepository.deleteById(id); */
        offerService.delete(id,forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //LISTO
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable("id") Long id){
        Offer offer=offerService.findById(id);
        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
        //LISTO
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable("id")Long id, @RequestBody Offer offer){
        Offer foundOffer=offerService.findById(id);
        /*if(offer.getName()!=null)
            foundOffer.setName(offer.getName());
        if(offer.getDate_on()!=null)
            foundOffer.setDate_on(offer.getDate_on());
        if(offer.getDate_of()!=null)
            foundOffer.setDate_of(offer.getDate_of());
        if(offer.getStore()!=null)
            foundOffer.setStore(offer.getStore());
        if(offer.getProduct()!=null)
            foundOffer.setProduct(offer.getProduct());*/
        Offer updateOffer=offerService.save(foundOffer);
        /*updateOffer.setStore(null);
        updateOffer.setProduct(null);*/
        return new ResponseEntity<Offer>(updateOffer,HttpStatus.OK);
    }
}
