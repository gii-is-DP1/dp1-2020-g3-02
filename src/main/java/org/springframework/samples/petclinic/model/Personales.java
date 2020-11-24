package org.springframework.samples.petclinic.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "personales")
public class Personales extends BaseEntity {
	
	@Column(name = "propietario", nullable = false, length = 5)
	private String propietario;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@ManyToMany
	@JoinTable(name = "sevaen", joinColumns = @JoinColumn(name = "personales_id"), 
	  inverseJoinColumns = @JoinColumn(name = "partido_id"))
	Set<Partido> partidos;


	public Personales(String propietario) {
		super();
		this.propietario = propietario;

	}
	public Personales() {
	}
	@Override
	public String toString() {
		return "Personales [propietario=" + propietario + "]";
	}





	

}
