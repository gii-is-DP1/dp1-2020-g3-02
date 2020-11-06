package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;

public interface EquipoService {
	
	public abstract Equipo findByCategoria(String category);
	public abstract List<Equipo> findByCategoriaStartingWith(String category);
	public abstract List<Equipo> findByLiga(String league);
	public abstract List<Jugador> findByPorcentajeSaquesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeColocacionesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeDefensasLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeBloqueosLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeRematesLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeFintasLessThanEqual(double percent);
	public abstract List<Jugador> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public abstract List<Jugador> findByNumFaltasTotalesGreaterThanEqual(int faults);
	public abstract void saveTeam(Equipo team);

}
