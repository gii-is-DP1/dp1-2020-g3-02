package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Sustituciones;
import org.springframework.stereotype.Repository;

@Repository("sustitucionesRepository")
public interface SustitucionesRepository extends JpaRepository<Sustituciones, Serializable>{
	public List<Sustituciones> findByMinutoSustitucion(String minuto_sustitucion);
	
	@Query("SELECT s FROM Sustituciones s, Jugador j WHERE j.id=:jugador_id")
	public List<Sustituciones> findByJugador(@Param("jugador_id") int jugador_id);
	
	@Query("SELECT s FROM Sustituciones s, Partido p WHERE p.id=:partido_id")
	public List<Sustituciones> findByPartido(@Param("partido_id") int partido_id);

}
