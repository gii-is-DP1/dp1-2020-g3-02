package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sustituciones")
public class Sustitucion extends BaseEntity{
	
	/*@ManyToMany
	@JoinTable(name = "jugadores", joinColumns = @JoinColumn(name = "sustitucion_id"), 
			  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
			Set<Jugador> jugadores;*/
	
	@ManyToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "jugador_entra")
	private Jugador jugadorEntra;
	
	@ManyToOne
	@JoinColumn(name = "jugador_sale")
	private Jugador jugadorSale;
	
	@Column(name = "minuto_sustitucion", nullable = false)
	@Min(0)
	private Integer minutoSustitucion;
	
	

}
