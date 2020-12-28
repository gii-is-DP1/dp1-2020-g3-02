package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface ViajeService extends BaseService<Viaje>{
	
	public abstract List<Viaje> findByJugador(Jugador jugador);
	public abstract List<Viaje> findByPartido(Partido partido);
	public abstract List<Viaje> findByTipoViaje(TipoViaje tipoViaje);
	public abstract List<Viaje> findByAutobus(Autobus autobus);
	public abstract List<Viaje> findByPersonal(Personales personal);
	public abstract Viaje findByJugadorAndPartidoAndTipoViaje(Jugador jugador, Partido partido, TipoViaje tipoViaje);
	public abstract List<Viaje> findByJugadorAndTipoViaje(Jugador jugador, TipoViaje tipoViaje);
	public abstract List<Viaje> findByPartidoAndTipoViaje(Partido partido, TipoViaje tipoViaje);
	public abstract List<Viaje> findByJugadorAndPersonal(Jugador jugador, Personales personal);
	public abstract void deleteAllInPartido(Integer partido_id);
	
}
