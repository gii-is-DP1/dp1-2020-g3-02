package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.repository.SustitucionRepository;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sustitucionesService")
public class SustitucionServiceImpl extends AbstractService<Sustitucion> implements SustitucionService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(SustitucionServiceImpl.class);
	
	@Autowired
	@Qualifier("sustitucionRepository")
	private SustitucionRepository sustitucionRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private PartidoService partidoService;

	@Override
	@Transactional(readOnly = true)
	public List<Sustitucion> findByMinutoSustitucion(int minuto_sustitucion) {
		return sustitucionRepository.findByMinutoSustitucion(minuto_sustitucion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustitucion> findByPartido(int partido_id) {
		Optional<Partido> partido = partidoService.findById(partido_id);
		return sustitucionRepository.findByPartido(partido.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustitucion> findByJugadorEntra(int jugador_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		return sustitucionRepository.findByJugadorEntra(jugador.get());
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Sustitucion> findByJugadorSale(int jugador_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		return sustitucionRepository.findByJugadorSale(jugador.get());
	}

	@Override
	public int substitutionCount() {
		return (int) sustitucionRepository.count();
	}

	@Override
	public void deleteAllInPartido(Integer partido_id) {
		Optional<Partido> partido = partidoService.findById(partido_id);
		List<Sustitucion> sustituciones = sustitucionRepository.findByPartido(partido.get());
		sustitucionRepository.deleteAll(sustituciones);
	}
}
