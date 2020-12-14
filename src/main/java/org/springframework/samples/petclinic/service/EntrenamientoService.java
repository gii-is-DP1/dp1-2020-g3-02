package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;

public interface EntrenamientoService extends BaseEstadisticasService<Entrenamiento>{
	
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team);
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date);

}
