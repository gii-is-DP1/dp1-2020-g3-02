package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface SistemaJuegoService extends BaseService<SistemaJuego>{

	public abstract List<SistemaJuego> findByPartido(int id);
	public abstract List<SistemaJuego> findByminutoAplicacion(int minuto_aplicacion);
	public abstract List<SistemaJuego> findByTipoSistema(Sistema tipo);



}
