package org.springframework.samples.petclinic.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "personales")
public class Personales extends BaseEntity {
	@Column(name = "propietario", nullable = false, length = 5)
	private String propietario;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;

	
	@ManyToMany
	@JoinTable(name = "sevaen", joinColumns = @JoinColumn(name = "personal_id"), 
	  inverseJoinColumns = @JoinColumn(name = "partido_id"))
	Set<Partido> partidos;
}
