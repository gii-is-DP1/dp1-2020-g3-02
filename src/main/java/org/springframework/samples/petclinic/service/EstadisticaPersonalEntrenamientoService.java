package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface EstadisticaPersonalEntrenamientoService extends BaseEstadisticasService<EstadisticaPersonalEntrenamiento>{
	
	public abstract List<EstadisticaPersonalEntrenamiento> findByJugador(int jugador_id);
	public abstract List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(int entrenamiento_id);
	public abstract EstadisticaPersonalEntrenamiento findByJugadorAndEntrenamiento(int jugador_id,int entrenamiento_id);
	public abstract int statisticCount();
	public abstract void deleteAllInEntrenamiento(Integer entrenamiento_id);
}
