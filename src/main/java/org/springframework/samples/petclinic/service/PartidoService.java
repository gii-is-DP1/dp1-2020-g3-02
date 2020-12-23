package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface PartidoService extends BaseEstadisticasService<Partido>{
	
	public abstract List<Partido> findByFechaOrderByHora(LocalDate date);
	public abstract List<Partido> findByFechaAfter(LocalDate date);
	public abstract List<Partido> findByEquipoAndFechaAndHoraBetween(Equipo equipo, LocalDate fecha, String hora1, String hora2);
	public abstract List<Partido> findByEquipo(Equipo equipo);
	
}
