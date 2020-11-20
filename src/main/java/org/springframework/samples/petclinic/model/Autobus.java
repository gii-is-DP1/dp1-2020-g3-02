package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "autobus")
public class Autobus extends BaseEntity {

	@ManyToMany
	@JoinTable(name = "jugadoresbus", joinColumns = @JoinColumn(name = "autobus_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
	
	@ManyToMany
	@JoinTable(name = "partidosbus", joinColumns = @JoinColumn(name = "autobus_id"), 
	  inverseJoinColumns = @JoinColumn(name = "partido_id"))
	Set<Partido> partidos;
	
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