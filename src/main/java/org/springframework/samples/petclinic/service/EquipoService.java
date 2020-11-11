package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;

public interface EquipoService {
	
	public abstract Optional<Equipo> findById(int id);
	public abstract List<Equipo> findAll();
	public abstract Equipo findByCategoria(String category);
	public abstract List<Equipo> findByCategoriaStartingWith(String category);
	public abstract List<Equipo> findByLiga(String league);
	public abstract List<Equipo> findByPorcentajeSaquesLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeColocacionesLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeDefensasLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeBloqueosLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeRematesLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeFintasLessThanEqual(double percent);
	public abstract List<Equipo> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public abstract List<Equipo> findByNumFaltasTotalesGreaterThanEqual(int faults);
	public abstract Equipo saveTeam(Equipo team);

}
