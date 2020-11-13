package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.samples.petclinic.enumerate.Sistema;

import lombok.Data;

@Data
@Entity
@Table(name = "equipos")
public class Equipo extends BaseEntity{
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "equipo")
	private Set<Entrenamiento> entrenamientos;
	
	@ManyToOne
	@JoinColumn(name = "capitan_id")
	private Capitan capitan;
	
	@ManyToOne
	@JoinColumn(name = "entrenador_id")
	private Entrenador entrenador;
	
	@ManyToMany
	@JoinTable(name = "numCamis", joinColumns = @JoinColumn(name = "equipo_id"), 
	  inverseJoinColumns = @JoinColumn(name = "num_camiseta_id"))
	Set<NumCamiseta> numCamisetas;
	
	@ManyToMany
	@JoinTable(name = "perteneceA", joinColumns = @JoinColumn(name = "equipo_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "equipo")
	private Set<Privilegio> privilegios;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "equipo")
	private Set<Partido> partidos;
	
	@Column(name="categoria", nullable = false, columnDefinition = "varchar(255) unique")
	private String categoria;
	
	@Column(name = "sistema_juego", columnDefinition = "varchar(255) default 'CINCO_UNO' NOT NULL")
	@Enumerated(value = EnumType.STRING)
	private Sistema sistemaJuego;
	
	@Column(name = "saques_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saquesAcertados;
	
	@Column(name = "saques_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int saquesTotales;
	
	@Column(name = "porcentaje_saques", scale = 2, columnDefinition = "double default 0")
	private double porcentajeSaques;
	
	@Column(name = "recepciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepcionesAcertadas;
	
	@Column(name = "recepciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int recepcionesTotales;
	
	@Column(name = "porcentaje_recepciones", scale = 2, columnDefinition = "double default 0")
	private double porcentajeRecepciones;
	
	@Column(name = "colocaciones_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocacionesAcertadas;
	
	@Column(name = "colocaciones_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int colocacionesTotales;
	
	@Column(name = "porcentaje_colocaciones", scale = 2, columnDefinition = "double default 0")
	private double porcentajeColocaciones;
	
	@Column(name = "defensas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensasAcertadas;
	
	@Column(name = "defensas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int defensasTotales;
	
	@Column(name = "porcentaje_defensas", scale = 2, columnDefinition = "double default 0")
	private double porcentajeDefensas;
	
	@Column(name = "bloqueos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueosAcertados;
	
	@Column(name = "bloqueos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int bloqueosTotales;
	
	@Column(name = "porcentaje_bloqueos", scale = 2, columnDefinition = "double default 0")
	private double porcentajeBloqueos;
	
	@Column(name = "remates_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int rematesAcertados;
	
	@Column(name = "remates_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int rematesTotales;
	
	@Column(name = "porcentaje_remates", scale = 2, columnDefinition = "double default 0")
	private double porcentajeRemates;
	
	@Column(name = "fintas_acertadas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintasAcertadas;
	
	@Column(name = "fintas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int fintasTotales;
	
	@Column(name = "porcentaje_fintas", scale = 2, columnDefinition = "double default 0")
	private double porcentajeFintas;
	
	@Column(name = "num_ataques_rapidos_acertados", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAtaquesRapidosAcertados;
	
	@Column(name = "num_ataques_rapidos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAtaquesRapidosTotales;
	
	@Column(name = "porcentaje_ataques_rapidos", scale = 2, columnDefinition = "double default 0")
	private double porcentajeAtaquesRapidos;
	
	@Column(name = "num_faltas_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numFaltasTotales;
	
	@Column(name = "num_amarillas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numAmarillas;
	
	@Column(name = "num_rojas", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numRojas;
	
	@Column(name = "posicion_liga", nullable = false, columnDefinition = "integer default 1")
	@Min(1)
	private int posicionLiga;
	
	@Column(name = "liga", nullable = false, columnDefinition = "varchar(250) default 'IMD'")
	private String liga;
	
	@Column(name = "federacion")
	private boolean federacion;
	
	public Equipo() {
	}
	
	public Equipo(String categoria, String liga, Sistema sistema_juego) {
		super();
		this.categoria = categoria;
		this.liga = liga;
		this.sistemaJuego = sistema_juego;
	}


	
}
