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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personales")
public class Personales extends BaseEntity {
	
	public Personales(String string) {
		propietario = string;
	}

	@Column(name = "propietario", nullable = false, length = 5)
	private String propietario;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;


}
