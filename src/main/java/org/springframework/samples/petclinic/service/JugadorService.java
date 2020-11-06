package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Jugador;

public interface JugadorService {
	
	public abstract List<Jugador> findByFirstName(String name);

}
