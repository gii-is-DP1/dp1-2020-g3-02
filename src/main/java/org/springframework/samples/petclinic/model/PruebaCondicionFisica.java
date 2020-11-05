package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;

@Entity
@Table(name = "prueba_condicion_fisica")
public class PruebaCondicionFisica extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugadores;
	
	@Column(name = "dato", nullable = false)
	private String dato;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "tipo_prueba", columnDefinition = "varchar(30) default 'saltoVertical' check (tipo_prueba in ('abdominal', 'flexibilidad','velocidad',"
			+ "'saltoVertical','agilidad','resistencia','pulsacionesMinimas'))")
	@Enumerated(value = EnumType.STRING)
	private TipoPrueba tipo_prueba;
	
	public PruebaCondicionFisica() {
		
	}

	public PruebaCondicionFisica(String dato, LocalDate fecha, TipoPrueba tipo_prueba) {
		super();
		this.dato = dato;
		this.fecha = fecha;
		this.tipo_prueba = tipo_prueba;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public TipoPrueba getTipo_prueba() {
		return tipo_prueba;
	}

	public void setTipo_prueba(TipoPrueba tipo_prueba) {
		this.tipo_prueba = tipo_prueba;
	}

	


}