package pe.com.mallgp.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.mallgp.backend.entities.Suggestion;
import pe.com.mallgp.backend.exporters.SuggestionExporterExcel;
import pe.com.mallgp.backend.repositories.SuggestionRepository;
import pe.com.mallgp.backend.services.SuggestionService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @GetMapping("/suggestions")
    public ResponseEntity<List<Suggestion>>getAllSuggestion(){
        List<Suggestion>suggestions=suggestionService.listAll();
        return new ResponseEntity<List<Suggestion>>(suggestions, HttpStatus.OK);
    }

    @GetMapping("/suggestions/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename=suggestion_report";

        response.setHeader(headerKey,headerValue);
        List<Suggestion>suggestions;
        suggestions=suggestionService.listAll();

        SuggestionExporterExcel exporterExcel=new SuggestionExporterExcel(suggestions);
        exporterExcel.export(response);
    }

    @PostMapping("/suggestions")
    public ResponseEntity<Suggestion> createSuggestion(@RequestBody Suggestion suggestion){
        Suggestion newSuggestion=suggestionService.save(new Suggestion(suggestion.getNsugerencia(),suggestion.getContenido(),suggestion.getDatesugerencia()));
        return new ResponseEntity<Suggestion>(newSuggestion, HttpStatus.CREATED);
    }

    @DeleteMapping("/suggestions/{id}/forced/{forced}")
    public ResponseEntity<HttpStatus> deleteSuggestionByIdForced(@PathVariable("id") Long id, @PathVariable("forced") int forced){
        suggestionService.delete(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/suggestions/{id}")
    public ResponseEntity<Suggestion>getSuggestionById(@PathVariable("id")Long id){
        Suggestion suggestion=suggestionService.findById(id);
        return new ResponseEntity<Suggestion>(suggestion,HttpStatus.OK);
    }

    @DeleteMapping("/suggestions/{id}")
    public ResponseEntity<HttpStatus>deleteStoreById(@PathVariable("id")Long id){
        suggestionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
