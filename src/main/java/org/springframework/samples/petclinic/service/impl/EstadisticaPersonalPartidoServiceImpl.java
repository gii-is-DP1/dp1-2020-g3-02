package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.EstadisticaPersonalPartidoRepository;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;

@Service("estadisticaPersonalPartidoService")
public class EstadisticaPersonalPartidoServiceImpl extends AbstractEstadisticasService<EstadisticaPersonalPartido> implements EstadisticaPersonalPartidoService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Log LOG = LogFactory.getLog(EstadisticaPersonalEntrenamientoServiceImpl.class);

	@Autowired
	@Qualifier("estadisticaPersonalPartidoRepository")
	private EstadisticaPersonalPartidoRepository estadisticaPersonalPartidoRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private PartidoService partidoService;

	@Override
	public List<EstadisticaPersonalPartido> findByJugador(int jugador_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		
		return estadisticaPersonalPartidoRepository.findByJugador(jugador.get());
	}

	@Override
	public List<EstadisticaPersonalPartido> findByPartido(int partido_id) {
		Optional<Partido> partido = partidoService.findById(partido_id);
		return estadisticaPersonalPartidoRepository.findByPartido(partido.get());
	}
	
	@Override
	public EstadisticaPersonalPartido findByJugadorAndPartido(int jugador_id, int partido_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		Optional<Partido> partido = partidoService.findById(partido_id);
		return estadisticaPersonalPartidoRepository.findByJugadorAndPartido(jugador.get(), partido.get());
	}

	@Override
	public EstadisticaPersonalPartido save(EstadisticaPersonalPartido statistic) {
		EstadisticaPersonalPartido estadisticaPersonalPartido = estadisticaPersonalPartidoRepository.save(statistic);
		
		if(statistic.getSaquesTotales() > 0) statistic.setPorcentajeSaques(1.*statistic.getSaquesAcertados()/statistic.getSaquesTotales());
		if(statistic.getRecepcionesTotales() > 0) statistic.setPorcentajeRecepciones(1.*statistic.getRecepcionesAcertadas()/statistic.getRecepcionesTotales());
		if(statistic.getColocacionesTotales() > 0) statistic.setPorcentajeColocaciones(1.*statistic.getColocacionesAcertadas()/statistic.getColocacionesTotales());
		if(statistic.getDefensasTotales() > 0) statistic.setPorcentajeDefensas(1.*statistic.getDefensasAcertadas()/statistic.getDefensasTotales());
		if(statistic.getBloqueosTotales() > 0) statistic.setPorcentajeBloqueos(1.*statistic.getBloqueosAcertados()/statistic.getBloqueosTotales());
		if(statistic.getRematesTotales() > 0) statistic.setPorcentajeRemates(1.*statistic.getRematesAcertados()/statistic.getRematesTotales());
		if(statistic.getFintasTotales() > 0) statistic.setPorcentajeFintas(1.*statistic.getFintasAcertadas()/statistic.getFintasTotales());
		if(statistic.getNumAtaquesRapidosTotales() > 0) statistic.setPorcentajeAtaquesRapidos(1.*statistic.getNumAtaquesRapidosAcertados()/statistic.getNumAtaquesRapidosTotales());
		
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
		
		Jugador player = jugadorService.save(jugador);
		
		LOG.info("JUGADOR YA ACTUALIZADO POR ESTADÍSTICAS: "+player);
		
		return estadisticaPersonalPartido;
	}

	@Override
	public int statisticCount() {
		return (int) estadisticaPersonalPartidoRepository.count();
	}

	@Override
	public void deleteAllInPartido(Integer partido_id) {
		Optional<Partido> partido = partidoService.findById(partido_id);
		List<EstadisticaPersonalPartido> estadisticas = estadisticaPersonalPartidoRepository.findByPartido(partido.get());
		estadisticaPersonalPartidoRepository.deleteAll(estadisticas);
	}

}
