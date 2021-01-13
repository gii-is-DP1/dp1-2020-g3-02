package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

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
@Table(name = "entrenamientos")
public class Entrenamiento extends EstadisticasEntity{
	
	@ManyToMany
	@JoinTable(
	  name = "realizaEntrenamiento", 
	  joinColumns = @JoinColumn(name = "entrenamiento_id"), 
	  inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	Set<Jugador> jugadores;
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "entrenamiento")
//	private Set<EstadisticaPersonalEntrenamiento> estadisticas_personales_entrenamientos;
	
	@ManyToOne
	@JoinColumn(name="equipo_id", nullable = true)
	private Equipo equipo;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "hora", nullable = false, length = 5)
	private String hora;
	
	@Column(name = "sistema_juego", nullable = false, columnDefinition = "varchar(255) default 'CINCO_UNO'")
	@Enumerated(value = EnumType.STRING)
	private Sistema sistemaJuego;	

	@Column(name = "tiempo_calentamiento", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoCalentamiento;
	
	@Column(name = "tiempo_mejora_fisico", nullable = false, columnDefinition = "integer default 0")
	@Min(0)
	private int tiempoMejoraFisico;

}
