package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "realiza_ejercicio")
public class RealizaEjercicio extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugadores;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	public RealizaEjercicio() {
		
	}
	
	public RealizaEjercicio(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
