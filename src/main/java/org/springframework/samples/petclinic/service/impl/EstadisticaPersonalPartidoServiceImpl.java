package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.EstadisticaPersonalPartidoRepository;
import org.springframework.samples.petclinic.service.EquipoService;
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
	
	@Autowired
	private EquipoService equipoService;

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
		
		
		if(statistic.getSaquesTotales() > 0) {
			statistic.setPorcentajeSaques(1.*statistic.getSaquesAcertados()/statistic.getSaquesTotales());
		} else {
			statistic.setPorcentajeSaques(0);
		}
		if(statistic.getRecepcionesTotales() > 0) {
			statistic.setPorcentajeRecepciones(1.*statistic.getRecepcionesAcertadas()/statistic.getRecepcionesTotales());
		} else {
			statistic.setPorcentajeRecepciones(0);
		}
		if(statistic.getColocacionesTotales() > 0) {
			statistic.setPorcentajeColocaciones(1.*statistic.getColocacionesAcertadas()/statistic.getColocacionesTotales());
		} else {
			statistic.setPorcentajeColocaciones(0);
		}
		if(statistic.getDefensasTotales() > 0) {
			statistic.setPorcentajeDefensas(1.*statistic.getDefensasAcertadas()/statistic.getDefensasTotales());
		} else {
			statistic.setPorcentajeDefensas(0);
		}
		if(statistic.getBloqueosTotales() > 0) {
			statistic.setPorcentajeBloqueos(1.*statistic.getBloqueosAcertados()/statistic.getBloqueosTotales());
		} else {
			statistic.setPorcentajeBloqueos(0);
		}
		if(statistic.getRematesTotales() > 0) {
			statistic.setPorcentajeRemates(1.*statistic.getRematesAcertados()/statistic.getRematesTotales());
		} else {
			statistic.setPorcentajeRemates(0);
		}
		if(statistic.getFintasTotales() > 0) {
			statistic.setPorcentajeFintas(1.*statistic.getFintasAcertadas()/statistic.getFintasTotales());
		} else {
			statistic.setPorcentajeFintas(0);
		}
		if(statistic.getNumAtaquesRapidosTotales() > 0) {
			statistic.setPorcentajeAtaquesRapidos(1.*statistic.getNumAtaquesRapidosAcertados()/statistic.getNumAtaquesRapidosTotales());
		} else {
			statistic.setPorcentajeAtaquesRapidos(0);
		}
		
		EstadisticaPersonalPartido estadisticaPersonalPartido = estadisticaPersonalPartidoRepository.save(statistic);
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
	
	//Testeado manualmente debido a lo complicado que resultado comprobar en un test que se ha realizado correctamente
	@Override
	public void saveEstadisticasByPartido(Integer partido_id) {
		Partido partido = partidoService.findById(partido_id).get();
		List<EstadisticaPersonalPartido> estadisticas = estadisticaPersonalPartidoRepository.findByPartido(partido);
		Equipo equipo = partido.getEquipo();
		for(int i = 0; i<estadisticas.size();i++) {
			
			Jugador jugador = estadisticas.get(i).getJugador();
			
			LOG.info("JUGADOR AL QUE DEBEN ACTUALIZÁRSELES LAS ESTADÍSTICAS: "+jugador);
			
			/** INTRODUCCIÓN DE LAS ESTADÍSTICAS EN LAS GENERALES DEL JUGADOR */
			jugador.setSaquesAcertados(jugador.getSaquesAcertados()+estadisticas.get(i).getSaquesAcertados());
			jugador.setSaquesTotales(jugador.getSaquesTotales()+estadisticas.get(i).getSaquesTotales());
			jugador.setRecepcionesAcertadas(jugador.getRecepcionesAcertadas()+estadisticas.get(i).getRecepcionesAcertadas());
			jugador.setRecepcionesTotales(jugador.getRecepcionesTotales()+estadisticas.get(i).getRecepcionesTotales());
			jugador.setColocacionesAcertadas(jugador.getColocacionesAcertadas()+estadisticas.get(i).getColocacionesAcertadas());
			jugador.setColocacionesTotales(jugador.getColocacionesTotales()+estadisticas.get(i).getColocacionesTotales());
			jugador.setDefensasAcertadas(jugador.getDefensasAcertadas()+estadisticas.get(i).getDefensasAcertadas());
			jugador.setDefensasTotales(jugador.getDefensasTotales()+estadisticas.get(i).getDefensasTotales());
			jugador.setBloqueosAcertados(jugador.getBloqueosAcertados()+estadisticas.get(i).getBloqueosAcertados());
			jugador.setBloqueosTotales(jugador.getBloqueosTotales()+estadisticas.get(i).getBloqueosTotales());
			jugador.setRematesAcertados(jugador.getRematesAcertados()+estadisticas.get(i).getRematesAcertados());
			jugador.setRematesTotales(jugador.getRematesTotales()+estadisticas.get(i).getRematesTotales());
			jugador.setFintasAcertadas(jugador.getFintasAcertadas()+estadisticas.get(i).getFintasAcertadas());
			jugador.setFintasTotales(jugador.getFintasTotales()+estadisticas.get(i).getFintasTotales());
			jugador.setNumAtaquesRapidosAcertados(jugador.getNumAtaquesRapidosAcertados()+estadisticas.get(i).getNumAtaquesRapidosAcertados());
			jugador.setNumAtaquesRapidosTotales(jugador.getNumAtaquesRapidosTotales()+estadisticas.get(i).getNumAtaquesRapidosTotales());
			jugador.setNumFaltasTotales(jugador.getNumFaltasTotales()+estadisticas.get(i).getNumFaltasTotales());
			jugador.setNumAmarillas(jugador.getNumAmarillas()+estadisticas.get(i).getNumAmarillas());
			jugador.setNumRojas(jugador.getNumRojas()+estadisticas.get(i).getNumRojas());
			
			Jugador player = jugadorService.save(jugador);
			
			equipo.setSaquesAcertados(equipo.getSaquesAcertados()+estadisticas.get(i).getSaquesAcertados());
			equipo.setSaquesTotales(equipo.getSaquesTotales()+estadisticas.get(i).getSaquesTotales());
			equipo.setRecepcionesAcertadas(equipo.getRecepcionesAcertadas()+estadisticas.get(i).getRecepcionesAcertadas());
			equipo.setRecepcionesTotales(equipo.getRecepcionesTotales()+estadisticas.get(i).getRecepcionesTotales());
			equipo.setColocacionesAcertadas(equipo.getColocacionesAcertadas()+estadisticas.get(i).getColocacionesAcertadas());
			equipo.setColocacionesTotales(equipo.getColocacionesTotales()+estadisticas.get(i).getColocacionesTotales());
			equipo.setDefensasAcertadas(equipo.getDefensasAcertadas()+estadisticas.get(i).getDefensasAcertadas());
			equipo.setDefensasTotales(equipo.getDefensasTotales()+estadisticas.get(i).getDefensasTotales());
			equipo.setBloqueosAcertados(equipo.getBloqueosAcertados()+estadisticas.get(i).getBloqueosAcertados());
			equipo.setBloqueosTotales(equipo.getBloqueosTotales()+estadisticas.get(i).getBloqueosTotales());
			equipo.setRematesAcertados(equipo.getRematesAcertados()+estadisticas.get(i).getRematesAcertados());
			equipo.setRematesTotales(equipo.getRematesTotales()+estadisticas.get(i).getRematesTotales());
			equipo.setFintasAcertadas(equipo.getFintasAcertadas()+estadisticas.get(i).getFintasAcertadas());
			equipo.setFintasTotales(equipo.getFintasTotales()+estadisticas.get(i).getFintasTotales());
			equipo.setNumAtaquesRapidosAcertados(equipo.getNumAtaquesRapidosAcertados()+estadisticas.get(i).getNumAtaquesRapidosAcertados());
			equipo.setNumAtaquesRapidosTotales(equipo.getNumAtaquesRapidosTotales()+estadisticas.get(i).getNumAtaquesRapidosTotales());
			equipo.setNumFaltasTotales(equipo.getNumFaltasTotales()+estadisticas.get(i).getNumFaltasTotales());
			equipo.setNumAmarillas(equipo.getNumAmarillas()+estadisticas.get(i).getNumAmarillas());
			equipo.setNumRojas(equipo.getNumRojas()+estadisticas.get(i).getNumRojas());
			
			Equipo team = equipoService.save(equipo);
			
			partido.setSaquesAcertados(partido.getSaquesAcertados()+estadisticas.get(i).getSaquesAcertados());
			partido.setSaquesTotales(partido.getSaquesTotales()+estadisticas.get(i).getSaquesTotales());
			partido.setRecepcionesAcertadas(partido.getRecepcionesAcertadas()+estadisticas.get(i).getRecepcionesAcertadas());
			partido.setRecepcionesTotales(partido.getRecepcionesTotales()+estadisticas.get(i).getRecepcionesTotales());
			partido.setColocacionesAcertadas(partido.getColocacionesAcertadas()+estadisticas.get(i).getColocacionesAcertadas());
			partido.setColocacionesTotales(partido.getColocacionesTotales()+estadisticas.get(i).getColocacionesTotales());
			partido.setDefensasAcertadas(partido.getDefensasAcertadas()+estadisticas.get(i).getDefensasAcertadas());
			partido.setDefensasTotales(partido.getDefensasTotales()+estadisticas.get(i).getDefensasTotales());
			partido.setBloqueosAcertados(partido.getBloqueosAcertados()+estadisticas.get(i).getBloqueosAcertados());
			partido.setBloqueosTotales(partido.getBloqueosTotales()+estadisticas.get(i).getBloqueosTotales());
			partido.setRematesAcertados(partido.getRematesAcertados()+estadisticas.get(i).getRematesAcertados());
			partido.setRematesTotales(partido.getRematesTotales()+estadisticas.get(i).getRematesTotales());
			partido.setFintasAcertadas(partido.getFintasAcertadas()+estadisticas.get(i).getFintasAcertadas());
			partido.setFintasTotales(partido.getFintasTotales()+estadisticas.get(i).getFintasTotales());
			partido.setNumAtaquesRapidosAcertados(partido.getNumAtaquesRapidosAcertados()+estadisticas.get(i).getNumAtaquesRapidosAcertados());
			partido.setNumAtaquesRapidosTotales(partido.getNumAtaquesRapidosTotales()+estadisticas.get(i).getNumAtaquesRapidosTotales());
			partido.setNumFaltasTotales(partido.getNumFaltasTotales()+estadisticas.get(i).getNumFaltasTotales());
			partido.setNumAmarillas(partido.getNumAmarillas()+estadisticas.get(i).getNumAmarillas());
			partido.setNumRojas(partido.getNumRojas()+estadisticas.get(i).getNumRojas());
			
			Partido match = partidoService.save(partido);
			
		}
	}

}
