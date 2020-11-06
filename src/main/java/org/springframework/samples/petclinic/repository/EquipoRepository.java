package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.stereotype.Repository;

@Repository("equipoRepository")
public interface EquipoRepository extends JpaRepository<Equipo, Serializable> {
	
	public Equipo findByCategoria(String category);
	public List<Equipo> findByCategoriaStartingWith(String category);
	public List<Equipo> findByLiga(String league);
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
