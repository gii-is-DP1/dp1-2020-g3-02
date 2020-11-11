package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.stereotype.Repository;

@Repository("equipoRepository")
public interface EquipoRepository extends ExtendedJpaRepository<Equipo> {
	
	public Equipo findByCategoria(String category);
	public List<Equipo> findByCategoriaStartingWith(String category);
	public List<Equipo> findByLiga(String league);

}
