package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "realiza_ejercicios")
public class RealizaEjercicio extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name = "ejercicio_individual_id")
	private EjercicioIndividual ejercicioIndividual;
	
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	public RealizaEjercicio(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	
	
}
