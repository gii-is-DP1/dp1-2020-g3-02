package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaJuegoRepository extends JpaRepository<SistemaJuego, Serializable>{


	public List<SistemaJuego> findByminutoAplicacion(int minuto_aplicacion);
	public List<SistemaJuego> findBySistema(Sistema sistema);
	


	@Query("SELECT e FROM SistemaJuego e, Partido p WHERE p.id=:partido_id")
	public List<SistemaJuego> findByPartido(@Param("partido_id") int partido_id);
}
