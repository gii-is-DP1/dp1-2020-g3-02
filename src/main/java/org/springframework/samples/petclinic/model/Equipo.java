package org.springframework.samples.petclinic.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.padres.EstadisticasEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipos")
public class Equipo extends EstadisticasEntity{
	
	public Equipo(String trim, Sistema parameter, String trim2) {
		this.categoria = trim;
		this.sistemaJuego = parameter;
		this.liga = trim2;
	}

//	@OneToMany( mappedBy = "equipo")
//	private Set<Entrenamiento> entrenamientos;
	
	@ManyToOne
	@JoinColumn(name = "capitan_id")
	private Capitan capitan;
	
	@ManyToOne
	@JoinColumn(name = "entrenador_id")
	private Entrenador entrenador;
	
//	@OneToMany( mappedBy = "equipo")
//	private Set<NumCamiseta> numCamisetas;
	
  	@ManyToMany
  	@JoinTable(name = "perteneceA", joinColumns = @JoinColumn(name = "equipo_id"), 
  	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
  	  List<Jugador> jugadores;
	
//	@OneToMany( mappedBy = "equipo")
//	private Set<Privilegio> privilegios;
	
//	@OneToMany( mappedBy = "equipo")
//	private Set<Partido> partidos;
	
	@Column(name="categoria", nullable = false, columnDefinition = "varchar(255) unique")
	private String categoria;
	
	@Column(name = "sistema_juego", columnDefinition = "varchar(255) default 'CINCO_UNO' NOT NULL")
	@Enumerated(value = EnumType.STRING)
	private Sistema sistemaJuego;
	
	@Column(name = "num_amarillas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAmarillas;
	
	@Column(name = "num_rojas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numRojas;
	
	@Column(name = "liga", nullable = false, columnDefinition = "varchar(250) default 'IMD'")
	private String liga;
	
	@Column(name = "federacion")
	private boolean federacion;
	
	public boolean getFederacion() {
		return this.federacion;
	}
	
	public void setFederacion(boolean federacion) {
		this.federacion = federacion;
	}
	
}
