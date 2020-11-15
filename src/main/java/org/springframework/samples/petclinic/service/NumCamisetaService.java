package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.NumCamiseta;

public interface NumCamisetaService {

	public abstract List<NumCamiseta> findAll();
	public abstract Optional<NumCamiseta> findByIdJugador(int id);
	public abstract Optional<NumCamiseta> findByIdEquipo(int id);
	public abstract NumCamiseta saveNumCamiseta(NumCamiseta numCamiseta);
}
