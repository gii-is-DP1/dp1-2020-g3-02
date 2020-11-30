package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;

public interface EjercicioIndividualService {
	
	public abstract List<EjercicioIndividual> findAll();
	public abstract Optional<EjercicioIndividual> findById(int id);
	public abstract Optional<EjercicioIndividual> findByNombre(String nombre);
	public abstract List<EjercicioIndividual> findByNombreContaining(String nombre);
	public abstract List<EjercicioIndividual> findByTipoEjercicio(TipoEjercicio tipo_ejercicio);
	public abstract EjercicioIndividual saveEjercicioIndividual(EjercicioIndividual ejercicioIndividual);
	public abstract void deleteEjercicioIndividual(int id);

}
