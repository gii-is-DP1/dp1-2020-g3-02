package org.springframework.samples.petclinic.service;

import java.util.List;

import java.util.Optional;

import org.springframework.samples.petclinic.model.Sustitucion;

public interface SustitucionService {
	
	public abstract List<Sustitucion> findAll();
	public abstract Optional<Sustitucion> findById(int id);
	public abstract List<Sustitucion> findByMinutoSustitucion(int minuto_sustitucion);
	public abstract List<Sustitucion> findByPartido(int partido_id);
	public abstract List<Sustitucion> findByJugadorEntra(int jugador_id);
	public abstract List<Sustitucion> findByJugadorSale(int jugador_id);
	public abstract Sustitucion saveSustitucion(Sustitucion substitution);
	public abstract int substitutionCount();
}
