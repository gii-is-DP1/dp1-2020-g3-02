package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.Jugador;

public interface EstadisticaPersonalEntrenamientoRepository extends ExtendedJpaRepository<EstadisticaPersonalEntrenamiento>{
	
//	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Entrenamiento en WHERE en.id=:entrenamiento_id")
	public List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(Entrenamiento entrenamiento);
	
//	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Jugador j WHERE j.id=:jugador_id")
	public List<EstadisticaPersonalEntrenamiento> findByJugador(Jugador jugador);
	
//	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Jugador j, Entrenamiento en WHERE j.id=:jugador_id AND en.id=:entrenamiento_id")
	public EstadisticaPersonalEntrenamiento findByJugadorAndEntrenamiento(Jugador jugador, Entrenamiento entrenamiento);

}
