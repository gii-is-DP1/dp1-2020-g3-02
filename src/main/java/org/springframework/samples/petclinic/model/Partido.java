package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
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

import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.padres.EstadisticasEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partidos")
public class Partido extends EstadisticasEntity{
	
	@ManyToMany
	@JoinTable(name = "juegaPartido", joinColumns = @JoinColumn(name = "partido_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
	
	@ManyToMany
	@JoinTable(name = "partidosbus", joinColumns = @JoinColumn(name = "partido_id"), 
	  inverseJoinColumns = @JoinColumn(name = "autobus_id"))
	Set<Autobus> buses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partido")
	private Set<EstadisticaPersonalPartido> estadisticas_personales_partidos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partido")
	private Set<Sustituciones> sustituciones;
	
	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "hora", nullable = false, length = 5)
	private String hora;
	
	@Column(name = "sistema_juego", nullable = false, columnDefinition = "varchar(255) default 'CINCO_UNO'")
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
	
	@Column(name = "num_puntos_set1", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet1;
	
	@Column(name = "num_puntos_set2", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet2;
	
	@Column(name = "num_puntos_set3", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet3;
	
	@Column(name = "num_puntos_set4", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet4;
	
	@Column(name = "num_puntos_set5", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosSet5;
	
	@Column(name = "num_puntos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numPuntosTotales;
	
	@Column(name = "num_tiempos_muertos_totales", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numTiemposMuertosTotales;
	
	@Column(name = "tiempo_total_partido", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoTotalPartido;
	
	@Column(name = "num_sustituciones", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int numSustituciones;
	
	@Column(name = "tiempo_colocador_general", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoColocadorGeneral;
	
	@Column(name = "tiempo_5_1", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo51;
	
	@Column(name = "tiempo_4_2", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo42;
	
	@Column(name = "tiempo_6_2", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempo62;
	
	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoCalentamiento;

}
