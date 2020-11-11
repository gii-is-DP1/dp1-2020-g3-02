package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;


public interface EstadisticaPersonalPartidoRepository extends ExtendedJpaRepository<EstadisticaPersonalPartido>{
	
	@Query("SELECT e FROM EstadisticaPersonalPartido e, Partido p WHERE p.id=:partido_id")
	public List<EstadisticaPersonalPartido> findByPartido(@Param("partido_id") int partido_id);
	
	@Query("SELECT e FROM EstadisticaPersonalPartido e, Jugador j WHERE j.id=:jugador_id")
	public List<EstadisticaPersonalPartido> findByJugador(@Param("jugador_id") int jugador_id);
	
	@Query("SELECT e FROM EstadisticaPersonalPartido e, Jugador j, Partido p WHERE j.id=:jugador_id AND p.id=:partido_id")
	public List<EstadisticaPersonalPartido> findByJugadorPartido(@Param("jugador_id") int jugador_id,@Param("partido_id") int partido_id);
}
