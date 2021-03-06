package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "viaje")
public class Viaje extends BaseEntity {
	
	public Viaje(TipoViaje tipo) {
		tipoViaje = tipo;
	}

	@Column(name = "tipo_viaje", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoViaje tipoViaje;
	
	@Column(name= "ha_llegado", nullable = false, length = 5)
	private boolean haLlegado; 
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name = "partido_id")
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "personal_id")
	private Personales personal;
	
	@ManyToOne
	@JoinColumn(name = "autobus_id")
	private Autobus autobus;


}
