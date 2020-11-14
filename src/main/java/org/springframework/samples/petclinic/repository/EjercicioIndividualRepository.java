package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.stereotype.Repository;

@Repository("ejercicioIndividualRepository")
public interface EjercicioIndividualRepository extends JpaRepository<EjercicioIndividual, Serializable> {
	
	public Optional<EjercicioIndividual> findByNombre(String nombre);
	public List<EjercicioIndividual> findByNombreContaining(String nombre);
	public List<EjercicioIndividual> findByTipoEjercicio(TipoEjercicio tipo_ejercicio);
	
}
