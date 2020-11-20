package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.stereotype.Repository;

@Repository("equipoRepository")
public interface EquipoRepository extends ExtendedJpaRepository<Equipo> {
	
	public Equipo findByCategoria(String category);
	public List<Equipo> findByCategoriaStartingWith(String category);
	public List<Equipo> findByLiga(String league);
	public Equipo findByEntrenadorAndCategoria(Entrenador entrenador, String categoria);
	public List<Equipo> findByEntrenador(Entrenador entrenador);
	public List<Equipo> findByCapitan(Capitan capitan);
	
	/** POR PROBAR */
	@Query(value = "SELECT * FROM equipos WHERE equipo_id IN (SELECT equipo_id FROM perteneceA WHERE jugador_id = ?0)", nativeQuery = true)
	public List<Equipo> findByJugador(int idJugador);
	
	@Query(value = "SELECT categoria FROM equipos", nativeQuery = true)
	public List<String> findCategoria();
}
