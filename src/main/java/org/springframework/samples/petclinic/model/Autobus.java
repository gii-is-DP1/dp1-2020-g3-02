package org.springframework.samples.petclinic.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;

import lombok.Data;

@Data
@Entity
@Table(name = "autobus")
public class Autobus extends BaseEntity {
	
	
	@Column(name = "hora_salida", nullable = false, length = 5)
	private String horaSalida;
	
	@Column(name = "hora_llegada", nullable = false, length = 5)
	private String horaLlegada;
	
	public Autobus() {
	}

	public Autobus(String hora_salida, String hora_llegada) {
		super();
		this.horaSalida = hora_salida;
		this.horaLlegada = hora_llegada;
	}


}