package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EstadisticaPersonalPartidoRepository;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("estadisticaPersonalPartidoService")
public class EstadisticaPersonalPartidoServiceImpl implements EstadisticaPersonalPartidoService{
	
	public static final Log LOG = LogFactory.getLog(EstadisticaPersonalEntrenamientoServiceImpl.class);

	@Autowired
	@Qualifier("estadisticaPersonalPartidoRepository")
	private EstadisticaPersonalPartidoRepository estadisticaPersonalPartidoRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
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
		
		Jugador jugador = statistic.getJugador();
		
		LOG.info("JUGADOR AL QUE DEBEN ACTUALIZÁRSELES LAS ESTADÍSTICAS: "+jugador);
		
		/** INTRODUCCIÓN DE LAS ESTADÍSTICAS EN LAS GENERALES DEL JUGADOR */
		jugador.setSaquesAcertados(jugador.getSaquesAcertados()+statistic.getSaquesAcertados());
		jugador.setSaquesTotales(jugador.getSaquesTotales()+statistic.getSaquesTotales());
		jugador.setRecepcionesAcertadas(jugador.getRecepcionesAcertadas()+statistic.getRecepcionesAcertadas());
		jugador.setRecepcionesTotales(jugador.getRecepcionesTotales()+statistic.getRecepcionesTotales());
		jugador.setColocacionesAcertadas(jugador.getColocacionesAcertadas()+statistic.getColocacionesAcertadas());
		jugador.setColocacionesTotales(jugador.getColocacionesTotales()+statistic.getColocacionesTotales());
		jugador.setDefensasAcertadas(jugador.getDefensasAcertadas()+statistic.getDefensasAcertadas());
		jugador.setDefensasTotales(jugador.getDefensasTotales()+statistic.getDefensasTotales());
		jugador.setBloqueosAcertados(jugador.getBloqueosAcertados()+statistic.getBloqueosAcertados());
		jugador.setBloqueosTotales(jugador.getBloqueosTotales()+statistic.getBloqueosTotales());
		jugador.setRematesAcertados(jugador.getRematesAcertados()+statistic.getRematesAcertados());
		jugador.setRematesTotales(jugador.getRematesTotales()+statistic.getRematesTotales());
		jugador.setFintasAcertadas(jugador.getFintasAcertadas()+statistic.getFintasAcertadas());
		jugador.setFintasTotales(jugador.getFintasTotales()+statistic.getFintasTotales());
		jugador.setNumAtaquesRapidosAcertados(jugador.getNumAtaquesRapidosAcertados()+statistic.getNumAtaquesRapidosAcertados());
		jugador.setNumAtaquesRapidosTotales(jugador.getNumAtaquesRapidosTotales()+statistic.getNumAtaquesRapidosTotales());
		jugador.setNumFaltasTotales(jugador.getNumFaltasTotales()+statistic.getNumFaltasTotales());
		jugador.setNumAmarillas(jugador.getNumAmarillas()+statistic.getNumAmarillas());
		jugador.setNumRojas(jugador.getNumRojas()+statistic.getNumRojas());
		
		Jugador player = jugadorService.saveJugador(jugador);
		
		LOG.info("JUGADOR YA ACTUALIZADO POR ESTADÍSTICAS: "+player);
		
		return estadisticaPersonalPartido;
	}

	@Override
	public int statisticCount() {
		return (int) estadisticaPersonalPartidoRepository.count();
	}

}
