package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface PersonalesService extends BaseService<Personales>{

	public abstract List<Personales> findByPropietario(String propietario);
	public abstract List<Personales> findByJugador(int jugador_id);
}
