package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface JugadorService extends BaseEstadisticasService<Jugador> {
	
	public abstract List<Jugador> findByFirstName(String name);
	public abstract Jugador findByUser(User username);
	public abstract List<Jugador> findByPosicionPrincipal(Posicion position);
	public abstract List<Jugador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public abstract List<Jugador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public abstract List<Jugador> findByAlturaGreaterThanEqual(int height);
	public abstract List<Jugador> findByAlturaLessThanEqual(int height);
	public abstract List<Jugador> findByPesoGreaterThanEqual(int weight);
	public abstract List<Jugador> findByPesoLessThanEqual(int weight);
	public abstract List<Jugador> findByEquipo(int equipo_id);
	public abstract List<Jugador> findAuto(TipoAutorizacion autorizacion);
	public abstract List<Jugador> findPrivilegio(TipoPrivilegio privilegio);
	public abstract Jugador updateJugador(Jugador player);
	public abstract List<Jugador> saveEstadisticasJugadores(List<Jugador> players);
	public abstract int playerCount();
	
}
