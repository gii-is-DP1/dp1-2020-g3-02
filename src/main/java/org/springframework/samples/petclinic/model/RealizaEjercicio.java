package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.ManyToOne;

@Data
@Entity
@Table(name = "realiza_ejercicios")
public class RealizaEjercicio extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name = "ejercicio_individual_id")
	private EjercicioIndividual ejercicio_individual;
	
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	public RealizaEjercicio() {
		
	}
	
	public RealizaEjercicio(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	
	
}
