package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.auxiliares.EntrenamientoConAsistencia;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface EntrenamientoService extends BaseEstadisticasService<Entrenamiento>{
	
	public abstract List<Entrenamiento> findByEquipoOrderByFecha(Equipo team);
	public abstract List<Entrenamiento> findByFechaOrderByHora(LocalDate date);
	public abstract List<Entrenamiento> findByFechaAfter(LocalDate date);
	public abstract List<Entrenamiento> findByEquipoAndFechaAndHoraBetween(Equipo equipo, LocalDate fecha, String hora1, String hora2);
	public abstract List<Entrenamiento> findByEquipo(Equipo equipo);
	public abstract List<EntrenamientoConAsistencia> obtenerEntrenamientosAsistidos(List<Equipo> equipos, Jugador jugador, Entrenamiento entrenamiento);
	public abstract void deleteAllInEquipo(Integer equipo_id);

}
