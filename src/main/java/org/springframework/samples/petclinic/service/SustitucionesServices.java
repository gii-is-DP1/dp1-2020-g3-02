package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Sustituciones;

public interface SustitucionesServices {
	
	public abstract List<Sustituciones> findAll();
	public abstract Optional<Sustituciones> findById(int id);
	public abstract List<Sustituciones> findByMinutoSustitucion(String minuto_sustitucion);
	public abstract List<Sustituciones> findByPartido(int partido_id);
	public abstract List<Jugador> findByJugador(int jugador_id);
	public abstract Sustituciones saveSubstitution(Sustituciones substitution);
}
