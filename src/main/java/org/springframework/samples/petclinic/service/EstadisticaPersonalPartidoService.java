package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface EstadisticaPersonalPartidoService extends BaseEstadisticasService<EstadisticaPersonalPartido>{
	
	public abstract List<EstadisticaPersonalPartido> findByJugador(int jugador_id);
	public abstract List<EstadisticaPersonalPartido> findByPartido(int partido_id);
	public abstract EstadisticaPersonalPartido findByJugadorAndPartido(int jugador_id,int partido_id);
	public abstract int statisticCount();
	public abstract void deleteAllInPartido(Integer partido_id);
	
}
