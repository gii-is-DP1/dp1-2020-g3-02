package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.RealizaEjercicio;

public interface RealizaEjercicioService {

	public abstract Optional<RealizaEjercicio> findById(int id);
	public abstract List<RealizaEjercicio> findByJugador(int id);
	public abstract List<RealizaEjercicio> findAll();
	public abstract RealizaEjercicio saveRealizaEjercicio(RealizaEjercicio realiza_ejercicio);
}
