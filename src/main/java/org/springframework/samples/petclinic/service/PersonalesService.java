package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;


import org.springframework.samples.petclinic.model.Personales;

public interface PersonalesService {

	public abstract List<Personales> findAll();
	public abstract Optional<Personales> findById(int id);
	public abstract List<Personales> findByPropietario(String propietario);

	public abstract List<Personales> findByJugador(int jugador_id);
	public abstract List<Integer> findByPartido(int partido_id);

	public abstract Personales savePersonales(Personales personales);
}
