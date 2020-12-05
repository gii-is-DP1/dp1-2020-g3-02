package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	private List<Jugador> jugadores;
	
	@ManyToMany
	@JoinTable(name = "partidosbus", joinColumns = @JoinColumn(name = "partido_id"), 
	  inverseJoinColumns = @JoinColumn(name = "autobus_id"))
	private Set<Autobus> buses;
	
	@ManyToMany
	@JoinTable(name = "sevaen", joinColumns = @JoinColumn(name = "partido_id"), 
	  inverseJoinColumns = @JoinColumn(name = "personales_id"))
	private Set<Personales> personales;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "partido")
	private Set<EstadisticaPersonalPartido> estadisticas_personales_partidos;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "partido")
	private Set<Sustitucion> sustituciones;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "partido")
	private Set<SistemaJuego> sistemasJuego;
	
	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "hora", nullable = false, length = 5)
	private String hora;
	
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
