package org.springframework.samples.petclinic.repository;

import java.io.Serializable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.stereotype.Repository;

@Repository("sustitucionRepository")
public interface SustitucionRepository extends JpaRepository<Sustitucion, Serializable>{
	public List<Sustitucion> findByMinutoSustitucion(int minuto_sustitucion);
	
//	@Query("SELECT s FROM Sustitucion s, Jugador j WHERE j.id=:jugador_id")
	public List<Sustitucion> findByJugador(Jugador jugador);
	
//	@Query("SELECT s FROM Sustitucion s, Partido p WHERE p.id=:partido_id")
	public List<Sustitucion> findByPartido(Partido partido);

}
