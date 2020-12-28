package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personales")
public class Personales extends BaseEntity {
	
	public Personales(String string) {
		propietario = string;
	}

	@Column(name = "propietario", nullable = false)
	private String propietario;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;


}
