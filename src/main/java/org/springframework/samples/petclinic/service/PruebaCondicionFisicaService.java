package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;

public interface PruebaCondicionFisicaService {
	
	public abstract Optional<PruebaCondicionFisica> findById(int id);
	public abstract List<PruebaCondicionFisica> findByJugador(int jugador_id);
	public abstract List<PruebaCondicionFisica> findByTipoPrueba(TipoPrueba tipo_prueba);
	public abstract List<PruebaCondicionFisica> findByDatoLessThanEqual(double dato);
	public abstract List<PruebaCondicionFisica> findByDatoAndTipoPrueba(double dato, TipoPrueba tipo_prueba);
	public abstract List<PruebaCondicionFisica> findByTipoPruebaAndDatoLessThanEqual(TipoPrueba tipo_prueba, double dato);
	public abstract PruebaCondicionFisica savePruebaCondicionFisica(PruebaCondicionFisica prueba);

}
