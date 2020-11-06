package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Jugador;

public interface JugadorService {
	
	public abstract List<Jugador> findAll();
	public abstract List<Jugador> findByFirstName(String name);
	public abstract List<Jugador> findByPosicionPrincipal(Posicion position);
	public abstract List<Jugador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public abstract List<Jugador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public abstract List<Jugador> findByAlturaGreaterThanEqual(int height);
	public abstract List<Jugador> findByAlturaLessThanEqual(int height);
	public abstract List<Jugador> findByPesoGreaterThanEqual(int weight);
	public abstract List<Jugador> findByPesoLessThanEqual(int weight);
	public abstract List<Jugador> findByPorcentajeSaquesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeColocacionesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeDefensasLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeBloqueosLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeRematesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeFintasLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public abstract List<Jugador> findByNumFaltasTotalesGreaterThanEqual(int faults);
	public abstract List<Jugador> findByEquipo(@Param("equipo_id") int equipo_id);
	public abstract void saveJugador(Jugador player);
	public abstract List<Jugador> saveEstadisticasJugadores(List<Jugador> players);
}
