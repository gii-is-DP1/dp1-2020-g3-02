package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface EstadisticoService extends BaseService<Estadistico>{
	public abstract List<Estadistico> findByFirstName(String name);
	public abstract Estadistico findByEmail(String email);
	public abstract List<Estadistico> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate);
	public abstract List<Estadistico> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date);
	public abstract int estadisticoCount();
}
