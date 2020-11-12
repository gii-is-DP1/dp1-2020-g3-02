package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.samples.petclinic.model.Sustituciones;

public interface SustitucionesServices {
	
	public abstract List<Sustituciones> findAll();
	public abstract Optional<Sustituciones> findById(int id);
	public abstract List<Sustituciones> findByMinutoSustitucion(String minuto_sustitucion);
	public abstract List<Sustituciones> findByPartido(int partido_id);
	public abstract List<Sustituciones> findByJugador(int jugador_id);
}
