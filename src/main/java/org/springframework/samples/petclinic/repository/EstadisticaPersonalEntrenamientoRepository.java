package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;

public interface EstadisticaPersonalEntrenamientoRepository extends ExtendedJpaRepository<EstadisticaPersonalEntrenamiento>{
	
	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Entrenamiento en WHERE en.id=:entrenamiento_id")
	public List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(@Param("entrenamiento_id") int entrenamiento_id);
	
	@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Jugador j WHERE j.id=:jugador_id")
	public List<EstadisticaPersonalEntrenamiento> findByJugador(@Param("jugador_id") int jugador_id);
	
	//@Query("SELECT e FROM EstadisticaPersonalEntrenamiento e, Jugador j, Entrenamiento en WHERE j.id=:jugador_id, en.id=:entrenamiento_id")
	//public List<EstadisticaPersonalPartido> findByJugadorEntrenamiento(@Param("jugador_id") int jugador_id, @Param("entrenamiento_id") int entrenamiento_id);

}
