package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Set;

import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface PersonalesService extends BaseService<Personales>{

	public abstract List<Personales> findByPropietario(String propietario);
	public abstract Set<Personales> findByJugador(int jugador_id);
}
