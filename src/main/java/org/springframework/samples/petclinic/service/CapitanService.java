package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.Capitan;

public interface CapitanService {

	public abstract List<Capitan> findAll();
	public abstract List<Capitan> findByActitud(Actitud actitud);
	public abstract List<Capitan> findByNtiemposmuertos(Integer ntiemposmuertos);
	public abstract List<Capitan> findByEquipo(int equipo_id);
	
	public abstract Optional<Capitan> findById(int id);
	public abstract Capitan saveCapitan(Capitan capitan);
	public abstract void deleteCapitan(int id);
}
