package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
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

	@Override
	public void deleteAllInEntrenamiento(Integer entrenamiento_id) {
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(entrenamiento_id);
		List<EstadisticaPersonalEntrenamiento> estadisticas = estadisticaPersonalEntrenamientoRepository.findByEntrenamiento(entrenamiento.get());
		estadisticaPersonalEntrenamientoRepository.deleteAll(estadisticas);
		
	}
	
	@Override
	public EstadisticaPersonalEntrenamiento save(EstadisticaPersonalEntrenamiento statistic) {
		
		
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
		
		EstadisticaPersonalEntrenamiento estadisticaPersonalPartido = estadisticaPersonalEntrenamientoRepository.save(statistic);
		return estadisticaPersonalPartido;
	}
	
	@Override
	public void saveEstadisticasByEntrenamiento(Integer entrenamiento_id) {
		Entrenamiento entrenamiento = entrenamientoService.findById(entrenamiento_id).get();
		List<EstadisticaPersonalEntrenamiento> estadisticas = estadisticaPersonalEntrenamientoRepository.findByEntrenamiento(entrenamiento);
		Equipo equipo = entrenamiento.getEquipo();
		for(int i = 0; i<estadisticas.size();i++) {
			
			Jugador jugador = estadisticas.get(i).getJugador();
			
			if(jugador.getEquipos().size() < 1) {
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
				
				Jugador player = jugadorService.save(jugador);
			}
			
			
			entrenamiento.setSaquesAcertados(entrenamiento.getSaquesAcertados()+estadisticas.get(i).getSaquesAcertados());
			entrenamiento.setSaquesTotales(entrenamiento.getSaquesTotales()+estadisticas.get(i).getSaquesTotales());
			entrenamiento.setRecepcionesAcertadas(entrenamiento.getRecepcionesAcertadas()+estadisticas.get(i).getRecepcionesAcertadas());
			entrenamiento.setRecepcionesTotales(entrenamiento.getRecepcionesTotales()+estadisticas.get(i).getRecepcionesTotales());
			entrenamiento.setColocacionesAcertadas(entrenamiento.getColocacionesAcertadas()+estadisticas.get(i).getColocacionesAcertadas());
			entrenamiento.setColocacionesTotales(entrenamiento.getColocacionesTotales()+estadisticas.get(i).getColocacionesTotales());
			entrenamiento.setDefensasAcertadas(entrenamiento.getDefensasAcertadas()+estadisticas.get(i).getDefensasAcertadas());
			entrenamiento.setDefensasTotales(entrenamiento.getDefensasTotales()+estadisticas.get(i).getDefensasTotales());
			entrenamiento.setBloqueosAcertados(entrenamiento.getBloqueosAcertados()+estadisticas.get(i).getBloqueosAcertados());
			entrenamiento.setBloqueosTotales(entrenamiento.getBloqueosTotales()+estadisticas.get(i).getBloqueosTotales());
			entrenamiento.setRematesAcertados(entrenamiento.getRematesAcertados()+estadisticas.get(i).getRematesAcertados());
			entrenamiento.setRematesTotales(entrenamiento.getRematesTotales()+estadisticas.get(i).getRematesTotales());
			entrenamiento.setFintasAcertadas(entrenamiento.getFintasAcertadas()+estadisticas.get(i).getFintasAcertadas());
			entrenamiento.setFintasTotales(entrenamiento.getFintasTotales()+estadisticas.get(i).getFintasTotales());
			entrenamiento.setNumAtaquesRapidosAcertados(entrenamiento.getNumAtaquesRapidosAcertados()+estadisticas.get(i).getNumAtaquesRapidosAcertados());
			entrenamiento.setNumAtaquesRapidosTotales(entrenamiento.getNumAtaquesRapidosTotales()+estadisticas.get(i).getNumAtaquesRapidosTotales());
			entrenamiento.setNumFaltasTotales(entrenamiento.getNumFaltasTotales()+estadisticas.get(i).getNumFaltasTotales());
			
			Entrenamiento entrenamiento_ = entrenamientoService.save(entrenamiento);
			
		}
	}

}
