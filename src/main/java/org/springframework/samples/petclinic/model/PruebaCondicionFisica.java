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
@Table(name = "pruebas_condicion_fisica")
public class PruebaCondicionFisica extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@Column(name = "dato", nullable = false)
	private Double dato;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "tipo_prueba", columnDefinition = "varchar(30) default 'SALTOVERTICAL' check (tipo_prueba in ('ABDOMINAL', 'FLEXIBILIDAD','VELOCIDAD',"
			+ "'SALTOVERTICAL','AGILIDAD','RESISTENCIA','PULSACIONESMINIMAS'))")
	@Enumerated(value = EnumType.STRING)
	private TipoPrueba tipoPrueba;
	
	public PruebaCondicionFisica() {
		
	}

	public PruebaCondicionFisica(Double dato, LocalDate fecha, TipoPrueba tipo_prueba) {
		super();
		this.dato = dato;
		this.fecha = fecha;
		this.tipoPrueba = tipo_prueba;
	}

	public Double getDato() {
		return dato;
	}

	public void setDato(Double dato) {
		this.dato = dato;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public TipoPrueba getTipo_prueba() {
		return tipoPrueba;
	}

	public void setTipo_prueba(TipoPrueba tipo_prueba) {
		this.tipoPrueba = tipo_prueba;
	}

	@Override
	public String toString() {
		return "PruebaCondicionFisica [jugador=" + jugador + ", dato=" + dato + ", fecha=" + fecha + ", tipoPrueba="
				+ tipoPrueba + "]";
	}

}