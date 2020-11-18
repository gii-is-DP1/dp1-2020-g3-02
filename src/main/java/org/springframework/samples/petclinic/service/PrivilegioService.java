package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;


import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Privilegio;

public interface PrivilegioService {
	public abstract List<Privilegio> findAll();
	public abstract Optional<Privilegio> findById(int id);
	
	public abstract List<Privilegio> findByDescripcion(String descripcion);
	public abstract List<Privilegio> findByTipoPrivilegio(TipoPrivilegio tipoPrivilegio);
	
	public abstract List<Privilegio> findByJugador(int id);
	public abstract List<Privilegio> findByEquipo(int id);
	public abstract Privilegio savePrivilegio(Privilegio privilegio);
}