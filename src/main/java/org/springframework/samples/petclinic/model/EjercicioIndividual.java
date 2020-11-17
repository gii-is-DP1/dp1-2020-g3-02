package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.samples.petclinic.enumerate.TipoEjercicio;

import lombok.Data;

@Data
@Entity
@Table(name = "ejercicios_individuales", uniqueConstraints = @UniqueConstraint(columnNames = { "nombre" }))
public class EjercicioIndividual extends BaseEntity {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio_individual")
	private Set<RealizaEjercicio> realiza_ejercicios;
	
	@ManyToMany
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "tipo_ejercicio", nullable = false)
	private TipoEjercicio tipoEjercicio;
	
	public EjercicioIndividual() {
	}

	public EjercicioIndividual(String nombre, String descripcion, TipoEjercicio tipo_ejercicio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoEjercicio = tipo_ejercicio;
	}


	@Override
	public String toString() {
		return "EjercicioIndividual [realiza_ejercicios=" + realiza_ejercicios + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", tipoEjercicio=" + tipoEjercicio + "]";
	}

}