package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "autorizaciones")
public class Autorizacion extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoAutorizacion tipoAutorizacion;

	
	public Autorizacion() {
	}
	
	
	public Autorizacion(Jugador jugador, LocalDate fecha, TipoAutorizacion tipoAutorizacion) {
		super();
		this.jugador = jugador;
		this.fecha = fecha;
		this.tipoAutorizacion = tipoAutorizacion;
	}

	@Override
	public String toString() {
		return "Autorizacion [jugador=" + jugador + ", fecha=" + fecha 
				+ ", tipoAutorizacion=" + tipoAutorizacion + "]";
		
	}

	
	
}
