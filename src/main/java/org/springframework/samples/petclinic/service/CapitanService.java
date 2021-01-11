package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface CapitanService extends BaseService<Capitan>{

	public abstract List<Capitan> findByActitud(Actitud actitud);
	public abstract List<Capitan> findByNtiemposmuertos(Integer ntiemposmuertos);
	public abstract List<Capitan> findByEquipo(int equipo_id);
	public abstract Capitan findByJugador(Jugador jugador);
	
	public void deleteAllInEquipo(Integer equipo_id);
}
