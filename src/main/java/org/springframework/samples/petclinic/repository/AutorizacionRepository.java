package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.stereotype.Repository;

@Repository("autorizacionRepository")
public interface AutorizacionRepository extends JpaRepository<Autorizacion,Serializable> {
	
	public List<Autorizacion> findByFecha(LocalDate date);
	public List<Autorizacion> findByTipo(TipoAutorizacion tipo);
	
	@Query("SELECT a FROM Autorizacion a, Jugador j WHERE j.id=:jugador_id")
	public List<Autorizacion> findByJugador(@Param("jugador_id") int jugador_id);
}
