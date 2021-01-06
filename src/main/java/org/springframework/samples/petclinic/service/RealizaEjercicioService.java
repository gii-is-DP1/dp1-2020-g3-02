package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface RealizaEjercicioService extends BaseService<RealizaEjercicio>{

	public abstract List<RealizaEjercicio> findByJugador(int id);
	public abstract List<RealizaEjercicio> findByEjercicio(Integer id);
}
