package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@Table(name = "num_camisetas")
public class NumCamiseta extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;
	
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;
	
	@Column(name = "numero")
	@Max(99)
	@Min(1)
	private Integer numero;

	
	
	public NumCamiseta() {
		super();
	}



	public NumCamiseta(@Max(99) @Min(1) Integer numero) {
		super();
		this.numero = numero;
	}



	public Equipo getEquipos() {
		return equipo;
	}



	public void setEquipos(Equipo equipo) {
		this.equipo = equipo;
	}



	public Jugador getJugadores() {
		return jugador;
	}



	public void setJugadores(Jugador jugador) {
		this.jugador = jugador;
	}



	public Integer getNumero() {
		return numero;
	}



	public void setNumero(Integer numero) {
		this.numero = numero;
	}



	@Override
	public String toString() {
		return "NumCamiseta [equipos=" + equipo + ", jugadores=" + jugador + ", numero=" + numero + "]";
	}



	
	
}
