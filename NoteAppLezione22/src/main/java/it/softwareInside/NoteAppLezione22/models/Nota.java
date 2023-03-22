package it.softwareInside.NoteAppLezione22.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Nota {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String testo;
	
	@NotNull
	@NotEmpty
	private String autore;
	
	
	public Nota(String testo,String autore) {
		setAutore(autore);
		setTesto(testo);
	}

}
