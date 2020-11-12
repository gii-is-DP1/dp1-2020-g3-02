package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.stereotype.Repository;


@Repository("realizaEjercicioRepository")
public interface RealizaEjercicioRepository extends JpaRepository<RealizaEjercicio, Serializable> {

	public List<RealizaEjercicio> findByFecha(LocalDate fecha);
	

	@Query("SELECT a FROM RealizaEjercicio a, Jugador j WHERE j.id=:jugador_id")
	public List<RealizaEjercicio> findByJugador(@Param("jugador_id") int jugador_id);

}
