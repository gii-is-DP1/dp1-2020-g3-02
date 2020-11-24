package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.NumCamiseta;

public interface NumCamisetaService {

	public abstract List<NumCamiseta> findAll();
	public abstract List<NumCamiseta> findByJugador(int jugador_id);
	public abstract List<NumCamiseta> findByEquipo(int equipo_id);
	public abstract NumCamiseta saveNumCamiseta(NumCamiseta numCamiseta);
}
