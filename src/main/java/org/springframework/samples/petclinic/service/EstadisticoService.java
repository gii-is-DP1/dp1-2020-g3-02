package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Estadistico;

public interface EstadisticoService {
	public abstract List<Estadistico> findAll();
	public abstract Optional<Estadistico> findById(int id);
	public abstract List<Estadistico> findByFirstName(String name);
	public abstract List<Estadistico> findByEmail(String email);
	public abstract List<Estadistico> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public abstract List<Estadistico> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public abstract Estadistico saveEstadistico(Estadistico estadistico);
	public abstract int estadisticoCount();
}
