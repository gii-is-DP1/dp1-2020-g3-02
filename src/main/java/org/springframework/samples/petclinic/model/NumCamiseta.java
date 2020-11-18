package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.model.padres.BaseEntity;

@Entity
@Table(name = "num_camisetas")
public class NumCamiseta extends BaseEntity{

	@ManyToMany
	@JoinTable(name = "numCamis", joinColumns = @JoinColumn(name = "num_camiseta_id"), 
	  inverseJoinColumns = @JoinColumn(name = "equipo_id"))
	Set<Equipo> equipos;
	
	@ManyToMany
	@JoinTable(name = "num_jugadores", joinColumns = @JoinColumn(name = "num_camiseta_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
	
	@Column(name = "numero")
	@Max(99)
	@Min(1)
	private Integer numero;

	
	
	public NumCamiseta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public NumCamiseta(@Max(99) @Min(1) Integer numero) {
		super();
		this.numero = numero;
	}



	public Set<Equipo> getEquipos() {
		return equipos;
	}



	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}



	public Set<Jugador> getJugadores() {
		return jugadores;
	}



	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}



	public Integer getNumero() {
		return numero;
	}



	public void setNumero(Integer numero) {
		this.numero = numero;
	}



	@Override
	public String toString() {
		return "NumCamiseta [equipos=" + equipos + ", jugadores=" + jugadores + ", numero=" + numero + "]";
	}



	
	
}
