package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface PruebaCondicionFisicaService extends BaseService<PruebaCondicionFisica>{
	
	public abstract List<PruebaCondicionFisica> findByJugador(int jugador_id);
	public abstract List<PruebaCondicionFisica> findByTipoPrueba(TipoPrueba tipo_prueba);
	public abstract List<PruebaCondicionFisica> findByDatoLessThanEqual(double dato);
	public abstract List<PruebaCondicionFisica> findByJugadorAndTipoPrueba(Jugador jugador, TipoPrueba tipo_prueba);
	public abstract List<PruebaCondicionFisica> findByDatoAndTipoPrueba(double dato, TipoPrueba tipo_prueba);
	public abstract List<PruebaCondicionFisica> findByTipoPruebaAndDatoLessThanEqual(TipoPrueba tipo_prueba, double dato);
}
