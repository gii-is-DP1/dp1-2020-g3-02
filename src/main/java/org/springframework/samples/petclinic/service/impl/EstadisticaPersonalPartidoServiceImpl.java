package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.repository.EstadisticaPersonalPartidoRepository;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("estadisticaPersonalPartidoService")
public class EstadisticaPersonalPartidoServiceImpl implements EstadisticaPersonalPartidoService{

	@Autowired
	@Qualifier("estadisticaPersonalPartidoRepository")
	private EstadisticaPersonalPartidoRepository estadisticaPersonalPartidoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<EstadisticaPersonalPartido> findAll() {
		return estadisticaPersonalPartidoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EstadisticaPersonalPartido> findById(int id) {
		return estadisticaPersonalPartidoRepository.findById(id);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeSaquesLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeDefensasLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeRematesLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeFintasLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return estadisticaPersonalPartidoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByJugador(int jugador_id) {
		return estadisticaPersonalPartidoRepository.findByJugador(jugador_id);
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPartido(int partido_id) {
		return estadisticaPersonalPartidoRepository.findByJugador(partido_id);
	}
	
	@Override
	public List<EstadisticaPersonalPartido> findByJugadorPartido(int jugador_id, int partido_id) {
		return estadisticaPersonalPartidoRepository.findByJugadorPartido(jugador_id, partido_id);
	}

	@Override
	public EstadisticaPersonalPartido saveEstadisticaPersonalPartido(EstadisticaPersonalPartido statistic) {
		EstadisticaPersonalPartido estadisticaPersonalPartido = estadisticaPersonalPartidoRepository.save(statistic);
		
		//LOG.info(estadisticaPersonalPartido.toString());
		
		return estadisticaPersonalPartido;
	}

	@Override
	public int statisticCount() {
		return (int) estadisticaPersonalPartidoRepository.count();
	}

}
