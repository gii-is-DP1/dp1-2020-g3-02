package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;

public interface PruebaCondicionFisicaRepository extends JpaRepository<PruebaCondicionFisica, Serializable> {
	
	public List<PruebaCondicionFisica> findById(String id);
	public List<PruebaCondicionFisica> findByTipoPrueba(TipoPrueba tipo_prueba);
	public List<PruebaCondicionFisica> findByDatoLessThanEqual(double dato);
	public List<PruebaCondicionFisica> findByDatoAndTipoPrueba(double dato, TipoPrueba tipo_prueba);
	public List<PruebaCondicionFisica> findByTipoPruebaAndDatoLessThanEqual(double dato, TipoPrueba tipo_prueba);

}
