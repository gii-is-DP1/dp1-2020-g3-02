package org.springframework.samples.petclinic.repository;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.stereotype.Repository;

@Repository("jugadorRepository")
public interface JugadorRepository extends JpaRepository<Jugador, Serializable>{
	
	public List<Jugador> findByFirstName(String name);
	public List<Jugador> findByPosicionPrincipal(Posicion position);
	public List<Jugador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public List<Jugador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public List<Jugador> findByAlturaGreaterThanEqual(int height);
	public List<Jugador> findByAlturaLessThanEqual(int height);
	public List<Jugador> findByPesoGreaterThanEqual(int weight);
	public List<Jugador> findByPesoLessThanEqual(int weight);
	public List<Jugador> findByPorcentajeSaquesLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeColocacionesLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeDefensasLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeBloqueosLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeRematesLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeFintasLessThanEqual(double percent);
	public List<Jugador> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public List<Jugador> findByNumFaltasTotalesGreaterThanEqual(int faults);
	
}
