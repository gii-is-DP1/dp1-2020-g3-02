package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;

public interface EstadisticaPersonalEntrenamientoRepository extends JpaRepository<EstadisticaPersonalEntrenamiento, Serializable>{
	
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeSaquesLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeColocacionesLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeDefensasLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeBloqueosLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeRematesLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeFintasLessThanEqual(double percent);
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	
	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Entrenamiento en WHERE en.id=:entrenamiento_id")
	public List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(@Param("entrenamiento_id") int entrenamiento_id);
	
	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Jugador j WHERE j.id=:jugador_id")
	public List<EstadisticaPersonalEntrenamiento> findByJugador(@Param("jugador_id") int jugador_id);
	
	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Jugador j, Entrenamiento en WHERE j.id=:jugador_id AND en.id=:entrenamiento_id")
	public List<EstadisticaPersonalEntrenamiento> findByJugadorEntrenamiento(@Param("jugador_id") int jugador_id, @Param("entrenamiento_id") int entrenamiento_id);

}
