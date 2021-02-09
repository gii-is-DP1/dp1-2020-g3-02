package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface AutorizacionService extends BaseService<Autorizacion>{
		public abstract List<Autorizacion> findByTipoAutorizacion(TipoAutorizacion tipoautorizacion);
		public abstract List<Autorizacion> findByFecha(LocalDate fecha);
		public abstract List<Autorizacion> findByJugador(int jugador_id);
		public abstract Autorizacion findByJugadorAndTipo(Jugador jugador, TipoAutorizacion tipo);
}
