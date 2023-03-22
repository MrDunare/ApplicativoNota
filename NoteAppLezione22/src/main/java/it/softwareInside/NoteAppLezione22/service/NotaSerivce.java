package it.softwareInside.NoteAppLezione22.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.softwareInside.NoteAppLezione22.models.Nota;
import it.softwareInside.NoteAppLezione22.repository.NotaRepository;
import jakarta.validation.Valid;

@Service
public class NotaSerivce {

	@Autowired
	NotaRepository noteRepository;
	
	
	/**
	 * metodo per creare una nuova nota,con valid controllo 
	 * che non sia null e non sia vuota.
	 * 
	 * @param note
	 * @return
	 */
	public boolean createNote(@Valid Nota note) {
		
		try {
			noteRepository.save(note);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * metodo per aggiornare una nota , con valid controllo 
	 * che non sia null e non sia vuota.
	 * 
	 * @param note
	 * @return
	 */
	public boolean update(@Valid Nota note) {
	
		return createNote(note);
	}
	
	/**
	 * metodo per cancellare una nota per id
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		try {
			noteRepository.deleteById(id);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * mostro una nota su richiesta in base all'id
	 * 
	 * @param id
	 * @return
	 */
	public Nota findById(int id) {
		try {
			 return noteRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 *metodo per cancellare tutte le note 
	 * @return
	 */
	public boolean deleteAll() {
		noteRepository.deleteAll();
		return true;
	}
	
	/**
	 * mostra tutte le note esistenti
	 * 
	 * @return
	 */
	public Iterable<Nota> getAll(){
		return noteRepository.findAll();
	}
	

}
