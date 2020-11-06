package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Serializable> {
	
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team);
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date);
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
