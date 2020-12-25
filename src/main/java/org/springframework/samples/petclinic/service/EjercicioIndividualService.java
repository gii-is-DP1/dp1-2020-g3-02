package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface EjercicioIndividualService extends BaseService<EjercicioIndividual>{
	
	public abstract Optional<EjercicioIndividual> findByNombre(String nombre);
	public abstract List<EjercicioIndividual> findByNombreContaining(String nombre);
	public abstract List<EjercicioIndividual> findByTipoEjercicio(TipoEjercicio tipo_ejercicio);
	public abstract List<EjercicioIndividual> findEjerciciosRecomendados(Jugador jugador);

}
