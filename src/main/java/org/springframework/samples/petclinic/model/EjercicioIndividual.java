package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;

@Entity
@Table(name = "ejercicios_individuales")
public class EjercicioIndividual extends BaseEntity {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio_individual")
	private Set<RealizaEjercicio> realiza_ejercicios;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "tipo_ejercicio", columnDefinition = "varchar(30) not null check (tipo_ejercicio in ('ataque','recepción','colocación','saque','bloqueo','defensa','ataqueRápido'))")
	private TipoEjercicio tipoEjercicio;
	
	public EjercicioIndividual() {
	}

	public EjercicioIndividual(String nombre, String descripcion, TipoEjercicio tipo_ejercicio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoEjercicio = tipo_ejercicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoEjercicio getTipo_ejercicio() {
		return tipoEjercicio;
	}

	public void setTipo_ejercicio(TipoEjercicio tipo_ejercicio) {
		this.tipoEjercicio = tipo_ejercicio;
	}

	@Override
	public String toString() {
		return "EjercicioIndividual [realiza_ejercicios=" + realiza_ejercicios + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", tipoEjercicio=" + tipoEjercicio + "]";
	}

}