package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface SustitucionService extends BaseService<Sustitucion>{
	
	public abstract List<Sustitucion> findByMinutoSustitucion(int minuto_sustitucion);
	public abstract List<Sustitucion> findByPartido(int partido_id);
	public abstract List<Sustitucion> findByJugadorEntra(int jugador_id);
	public abstract List<Sustitucion> findByJugadorSale(int jugador_id);
	public abstract int substitutionCount();
	public abstract void deleteAllInPartido(Integer partido_id);
	
}
