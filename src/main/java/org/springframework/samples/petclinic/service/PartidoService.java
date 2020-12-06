package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;

public interface PartidoService {
	
	public List<Partido> findByFechaOrderByHora(LocalDate date);
	public List<Partido> findByEquipoAndFechaAndHoraBetween(Equipo equipo, LocalDate fecha, String hora1, String hora2);
	public abstract Optional<Partido> findById(int partido_id);
	public abstract List<Partido> findByPorcentajeSaquesLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeColocacionesLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeDefensasLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeBloqueosLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeRematesLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeFintasLessThanEqual(double percent);
	public abstract List<Partido> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public abstract List<Partido> findByNumFaltasTotalesGreaterThanEqual(int faults);
	public abstract Partido savePartido(Partido partido);
	public abstract List<Partido> findAll();
	public abstract void deletePartido(int partido_id);
	
}
