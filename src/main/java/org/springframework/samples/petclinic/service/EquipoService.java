package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface EquipoService extends BaseEstadisticasService<Equipo>{
	
	public abstract List<String> findCategoria();
	public abstract Equipo findByCategoria(String category);
	public abstract List<Equipo> findByCategoriaStartingWith(String category);
	public abstract List<Equipo> findByLiga(String league);
	public abstract List<Jugador> findJugadoresNoEquipo(int id);
	public abstract Equipo updateEquipo(Equipo team);
	public abstract List<Equipo> findByCapitan(Capitan capitan);
	public abstract Equipo deleteCapitan(Equipo team);
	public abstract Equipo updateCapitan(Equipo equipo);

}
