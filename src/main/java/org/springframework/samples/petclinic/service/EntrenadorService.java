package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface EntrenadorService extends BaseService<Entrenador>{
	
	public abstract List<Entrenador> findByFirstName(String name);
	public abstract Entrenador findByEmail(String email);
	public abstract Entrenador findByUser(User user);
	public abstract List<Entrenador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public abstract List<Entrenador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public abstract int entrenadorCount();
}
