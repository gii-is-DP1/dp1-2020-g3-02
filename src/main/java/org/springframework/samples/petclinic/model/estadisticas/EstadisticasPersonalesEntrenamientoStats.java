package org.springframework.samples.petclinic.model.estadisticas;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasPersonalesEntrenamientoStats {
	
	private int jugadorId;
	
	private String firstName;
	
	private LocalDate fecha;
	
	private int saquesAcertados;
	
	private int saquesTotales;
	
	private double porcentajeSaques;
	
	private int recepcionesAcertadas;
	
	private int recepcionesTotales;
	
	private double porcentajeRecepciones;
	
	private int colocacionesAcertadas;
	
	private int colocacionesTotales;
	
	private double porcentajeColocaciones;
	
	private int defensasAcertadas;
	
	private int defensasTotales;
	
	private double porcentajeDefensas;
	
	private int bloqueosAcertados;
	
	private int bloqueosTotales;
	
	private double porcentajeBloqueos;
	
	private int rematesAcertados;

	private int rematesTotales;
	
	private double porcentajeRemates;
	
	private int fintasAcertadas;
	
	private int fintasTotales;
	
	private double porcentajeFintas;
	
	private int numAtaquesRapidosAcertados;
	
	private int numAtaquesRapidosTotales;
	
	private double porcentajeAtaquesRapidos;
	
	private int entrenamientoId;
	
}