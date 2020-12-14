package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EstadisticaPersonalEntrenamientoRepository;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("estadisticaPersonalEntrenamientoService")
public class EstadisticaPersonalEntrenamientoServiceImpl extends AbstractEstadisticasService<EstadisticaPersonalEntrenamiento> implements EstadisticaPersonalEntrenamientoService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("estadisticaPersonalEntrenamientoRepository")
	private EstadisticaPersonalEntrenamientoRepository estadisticaPersonalEntrenamientoRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByJugador(int jugador_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		return estadisticaPersonalEntrenamientoRepository.findByJugador(jugador.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(int entrenamiento_id) {
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(entrenamiento_id);
		return estadisticaPersonalEntrenamientoRepository.findByEntrenamiento(entrenamiento.get());
	}
	
	@Override
	@Transactional(readOnly = true)
	public EstadisticaPersonalEntrenamiento findByJugadorAndEntrenamiento(int jugador_id, int entrenamiento_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(entrenamiento_id);
		return estadisticaPersonalEntrenamientoRepository.findByJugadorAndEntrenamiento(jugador.get(), entrenamiento.get());
	}

	@Override
	public int statisticCount() {
		return (int) estadisticaPersonalEntrenamientoRepository.count();
	}

}
