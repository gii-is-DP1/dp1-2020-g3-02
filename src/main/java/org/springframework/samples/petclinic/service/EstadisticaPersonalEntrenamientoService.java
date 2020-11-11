package org.springframework.samples.petclinic.service;

import java.util.List;

import java.util.Optional;

import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;

public interface EstadisticaPersonalEntrenamientoService {
	
	public abstract List<EstadisticaPersonalEntrenamiento> findAll();
	public abstract Optional<EstadisticaPersonalEntrenamiento> findById(int id);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeSaquesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeColocacionesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeDefensasLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeBloqueosLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeRematesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeFintasLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalEntrenamiento> findByJugador(int jugador_id);
	public abstract List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(int entrenamiento_id);
	public abstract List<EstadisticaPersonalEntrenamiento> findByJugadorEntrenamiento(int jugador_id,int entrenamiento_id);
	public abstract EstadisticaPersonalEntrenamiento saveEstadisticaPersonalEntrenamiento(EstadisticaPersonalEntrenamiento statistic);
	public abstract int statisticCount();
}
