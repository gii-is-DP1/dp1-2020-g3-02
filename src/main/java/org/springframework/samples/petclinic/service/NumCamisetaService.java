package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface NumCamisetaService extends BaseService<NumCamiseta>{

	public abstract List<NumCamiseta> findByJugador(int jugador_id);
	public abstract List<NumCamiseta> findByEquipo(int equipo_id);
	public abstract NumCamiseta findByEquipoAndJugador(int equipo_id, int jugador_id);
	public abstract List<Integer> findNumeroByEquipo(int equipo_id);
	public abstract void deleteAllInEquipo(Integer equipo_id);
	public abstract void deleteByJugadorEquipo(Jugador jugador, Equipo equipo);
	
}
