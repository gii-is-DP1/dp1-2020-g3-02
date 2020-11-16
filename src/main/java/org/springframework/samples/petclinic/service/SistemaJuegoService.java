package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.SistemaJuego;

public interface SistemaJuegoService {

	public abstract List<SistemaJuego> findAll();
	public abstract Optional<SistemaJuego> findById(int id);
	public abstract List<SistemaJuego> findByPartido(int id);
	public abstract List<SistemaJuego> findByminutoAplicacion(int minuto_aplicacion);
	public abstract List<SistemaJuego> findByTipoSistema(Sistema tipo);
	public abstract SistemaJuego saveSistemaJuego(SistemaJuego sistemaJuego);



}
