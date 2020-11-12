package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;

public interface EntrenamientoService {
	
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team);
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date);
	public List<Entrenamiento> findByPorcentajeSaquesLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeColocacionesLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeDefensasLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeBloqueosLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeRematesLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeFintasLessThanEqual(double percent);
	public List<Entrenamiento> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public List<Entrenamiento> findByNumFaltasTotalesGreaterThanEqual(int faults);
	public Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento);
	public void deleteEntrenamiento(Entrenamiento entrenamiento);

}
