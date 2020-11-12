package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Partido;

public interface PartidoService {
	
	public List<Partido> findByFechaOrderByHora(LocalDate date);
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
	public abstract void deletePartido(Partido partido);

}
