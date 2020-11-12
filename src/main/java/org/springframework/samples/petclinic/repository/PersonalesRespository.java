package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Personales;

public interface PersonalesRespository extends ExtendedJpaRepository<Personales>{

	@Query("SELECT p FROM Personales p, Jugador j WHERE j.id=:jugador_id")
	public List<Personales> findByJugador(@Param("jugador_id") int jugador_id);
}
