package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoResponsable;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Autorizacion;

public interface AutorizacionService {
		public abstract List<Autorizacion> findAll();
		public abstract Optional<Autorizacion> findById(int id);
		public abstract List<Autorizacion> findByTipoAutorizacion(TipoAutorizacion tipoautorizacion);
		public abstract List<Autorizacion> findByFecha(LocalDate fecha);
		public abstract List<Autorizacion> findByJugador(int jugador_id);
		public abstract List<Autorizacion> findByTipoResponsable(TipoResponsable tiporesponsable);
		public abstract Autorizacion saveAutorizacion(Autorizacion autorizacion);
}
