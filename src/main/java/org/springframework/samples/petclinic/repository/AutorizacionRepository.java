package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoResponsable;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.stereotype.Repository;

@Repository("autorizacionRepository")
public interface AutorizacionRepository extends JpaRepository<Autorizacion,Serializable> {

	public List<Autorizacion> findByFecha(LocalDate date);
	public List<Autorizacion> findByTipoAutorizacion(TipoAutorizacion tipo);
	public List<Autorizacion> findByTipoResponsable(TipoResponsable tipo);
	public List<Autorizacion> findByJugador(Jugador jugador);
}