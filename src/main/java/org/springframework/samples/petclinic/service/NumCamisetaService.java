package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface NumCamisetaService extends BaseService<NumCamiseta>{

	public abstract List<NumCamiseta> findByJugador(int jugador_id);
	public abstract List<NumCamiseta> findByEquipo(int equipo_id);
	
}
