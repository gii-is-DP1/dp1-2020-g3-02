package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;

public interface EntrenamientoRepository extends ExtendedJpaRepository<Entrenamiento> {
	
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team);
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date);
	public List<Entrenamiento> findByFechaAfter(LocalDate date);
	public List<Entrenamiento> findByEquipoAndFechaAndHoraBetween(Equipo equipo, LocalDate fecha, String hora1, String hora2);
	public List<Entrenamiento> findByEquipo(Equipo equipo);
	

}
