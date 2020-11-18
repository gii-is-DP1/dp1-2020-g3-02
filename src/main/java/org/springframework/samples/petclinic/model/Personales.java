package org.springframework.samples.petclinic.model;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "personales")
public class Personales extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
}
