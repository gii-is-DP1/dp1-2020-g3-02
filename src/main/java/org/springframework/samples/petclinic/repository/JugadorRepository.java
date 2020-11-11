package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.stereotype.Repository;

@Repository("jugadorRepository")
public interface JugadorRepository extends ExtendedJpaRepository<Jugador>{
	
	public List<Jugador> findByFirstName(String name);
	public List<Jugador> findByPosicionPrincipal(Posicion position);
	public List<Jugador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public List<Jugador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public List<Jugador> findByAlturaGreaterThanEqual(int height);
	public List<Jugador> findByAlturaLessThanEqual(int height);
	public List<Jugador> findByPesoGreaterThanEqual(int weight);
	public List<Jugador> findByPesoLessThanEqual(int weight);
	
	@Query("SELECT j FROM Jugador j, Equipo e WHERE e.id=:equipo_id")
	public List<Jugador> findByEquipo(@Param("equipo_id") int equipo_id);
	
}
