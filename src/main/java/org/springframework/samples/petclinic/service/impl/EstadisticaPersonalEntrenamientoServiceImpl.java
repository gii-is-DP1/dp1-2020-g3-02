package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.repository.EstadisticaPersonalEntrenamientoRepository;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("estadisticaPersonalEntrenamientoService")
public class EstadisticaPersonalEntrenamientoServiceImpl implements EstadisticaPersonalEntrenamientoService{

	@Autowired
	@Qualifier("estadisticaPersonalEntrenamientoRepository")
	private EstadisticaPersonalEntrenamientoRepository estadisticaPersonalEntrenamientoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findAll() {
		return estadisticaPersonalEntrenamientoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EstadisticaPersonalEntrenamiento> findById(int id) {
		return estadisticaPersonalEntrenamientoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeSaquesLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeDefensasLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeRematesLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeFintasLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return estadisticaPersonalEntrenamientoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByJugador(int jugador_id) {
		return estadisticaPersonalEntrenamientoRepository.findByJugador(jugador_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByEntrenamiento(int entrenamiento_id) {
		return estadisticaPersonalEntrenamientoRepository.findByJugador(entrenamiento_id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalEntrenamiento> findByJugadorEntrenamiento(int jugador_id, int entrenamiento_id) {
		return estadisticaPersonalEntrenamientoRepository.findByJugadorEntrenamiento(jugador_id, entrenamiento_id);
	}

	@Override
	@Transactional
	public EstadisticaPersonalEntrenamiento saveEstadisticaPersonalEntrenamiento(EstadisticaPersonalEntrenamiento statistic) {
		EstadisticaPersonalEntrenamiento estadisticaPersonalPartido = estadisticaPersonalEntrenamientoRepository.save(statistic);
		
		//LOG.info(estadisticaPersonalPartido.toString());
		
		return estadisticaPersonalPartido;
	}

	@Override
	public int statisticCount() {
		return (int) estadisticaPersonalEntrenamientoRepository.count();
	}

}
