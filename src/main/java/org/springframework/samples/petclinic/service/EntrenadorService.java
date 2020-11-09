package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.Entrenador;

public interface EntrenadorService {
	
	public abstract List<Entrenador> findAll();
	public abstract Optional<Entrenador> findById(int id);
	public abstract List<Entrenador> findByFirstName(String name);
	public abstract List<Entrenador> findByEmail(String email);
	public abstract List<Entrenador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public abstract List<Entrenador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public abstract Entrenador saveEntrenador(Entrenador entrenador);
	public abstract int entrenadorCount();
}
