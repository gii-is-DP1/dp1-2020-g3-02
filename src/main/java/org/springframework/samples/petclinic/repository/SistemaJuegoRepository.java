package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.stereotype.Repository;
@Repository("sistemaJuegoRepository")
public interface SistemaJuegoRepository extends JpaRepository<SistemaJuego, Serializable>{


	public List<Autorizacion> findByminutoAplicacion(int minutoAplicacion);
	public List<Autorizacion> findByTipo(TipoAutorizacion tipo);

	@Query("SELECT e FROM SistemaJuego e, Partido p WHERE p.id=:partido_id")
	public List<SistemaJuego> findByPartido(@Param("partido_id") int partido_id);
}
