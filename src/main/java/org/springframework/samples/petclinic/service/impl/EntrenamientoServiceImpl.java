package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.repository.EntrenamientoRepository;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;

@Service
public class EntrenamientoServiceImpl extends AbstractEstadisticasService<Entrenamiento> implements EntrenamientoService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntrenamientoRepository entrenamientoRepository;

	@Override
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team) {
		return entrenamientoRepository.findByEquipoOrderByFecha(team);
	}

	@Override
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date) {
		return entrenamientoRepository.findByFechaOrderByHora(date);
	}
}
