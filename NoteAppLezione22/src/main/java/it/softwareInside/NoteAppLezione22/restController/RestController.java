package it.softwareInside.NoteAppLezione22.restController;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.softwareInside.NoteAppLezione22.models.Nota;
import it.softwareInside.NoteAppLezione22.service.NotaSerivce;
import it.softwareInside.NoteAppLezione22.util.GeneratePdf;



@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	NotaSerivce noteSerivce;
	
	
	@PostMapping("/create")
	public boolean createNote(@RequestBody Nota note) {
		return noteSerivce.createNote(note);	
	}
	
	@PostMapping("/updateNote")
	public boolean updateNote(@RequestBody Nota note) {
		return noteSerivce.update(note);	
	}
	
	@GetMapping("/getAllNote")
	public Iterable<Nota> findAll() {
		return noteSerivce.getAll();
	}
	
	@GetMapping("/getNoteById")
	public Nota getById(@RequestParam ("id") int id) {
		return noteSerivce.findById(id);
	}
	
	@DeleteMapping("/deleteAll")
	public boolean deleteAll(){
		return noteSerivce.deleteAll();
	}
	
	@DeleteMapping("/deleteById")
	public boolean deleteById(@RequestParam ("id") int id) {
		return noteSerivce.delete(id);
		
	}
	 @RequestMapping(value = "/pdf", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> generaNota(@RequestParam("id") int id ,
	    		@RequestParam("testo") String testo , @RequestParam("autore") String autore) {

	       
	    	Nota nota = new Nota(id, testo, autore);
	    	
	    	try {
	    		
	    		GeneratePdf generatePdf = new GeneratePdf();

	            ByteArrayInputStream bis =  generatePdf.generaPDFNota(nota) ; 

	            var headers = new HttpHeaders();
	            headers.add("Content-Disposition", "inline; filename=example.pdf");
	    		
	            
	            
	            return ResponseEntity
	                    .ok()
	                    .headers(headers)
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(new InputStreamResource(bis));
	    		
	    	}catch (Exception e) {
				return null;
	    	
	    
	    	}
	
	 }

}
