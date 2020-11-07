package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;

public interface EjercicioIndividualRepository extends JpaRepository<EjercicioIndividual, Serializable> {
	
	public List<EjercicioIndividual> findByNombre(String nombre);
	public List<EjercicioIndividual> findByTipoEjercicio(TipoEjercicio tipo_ejercicio);
	
}
