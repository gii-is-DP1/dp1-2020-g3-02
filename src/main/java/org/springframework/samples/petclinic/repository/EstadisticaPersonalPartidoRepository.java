package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;


public interface EstadisticaPersonalPartidoRepository extends ExtendedJpaRepository<EstadisticaPersonalPartido>{
	
//	@Query("SELECT e FROM EstadisticaPersonalPartido e, Partido p WHERE p.id=:partido_id")
	public List<EstadisticaPersonalPartido> findByPartido(Partido partido);
	
	
	
//	@Query("SELECT e FROM EstadisticaPersonalPartido e, Jugador j WHERE j.id=:jugador_id")
	public List<EstadisticaPersonalPartido> findByJugador(Jugador jugador);
	
//	@Query("SELECT e FROM EstadisticaPersonalPartido e, Jugador j, Partido p WHERE j.id=:jugador_id AND p.id=:partido_id")
	public EstadisticaPersonalPartido findByJugadorAndPartido(Jugador jugador, Partido partido);
}
