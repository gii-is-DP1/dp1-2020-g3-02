package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Privilegio;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface PrivilegioService extends BaseService<Privilegio>{
	
	public abstract List<Privilegio> findByDescripcion(String descripcion);
	public abstract List<Privilegio> findByTipoPrivilegio(TipoPrivilegio tipoPrivilegio);
	
	public abstract List<Privilegio> findByJugador(Jugador jugador);
	public abstract List<Privilegio> findByEquipo(Equipo equipo);
	
	public abstract Privilegio updatePrivilegio(Privilegio privilegio);
	
	public void deleteAllInEquipo(Integer equipo_id);
}
