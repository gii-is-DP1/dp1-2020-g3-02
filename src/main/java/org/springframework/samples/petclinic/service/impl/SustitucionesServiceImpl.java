package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Sustituciones;
import org.springframework.samples.petclinic.repository.SustitucionesRepository;
import org.springframework.samples.petclinic.service.SustitucionesServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sustitucionesService")
public class SustitucionesServiceImpl implements SustitucionesServices{
	private static final Log LOG = LogFactory.getLog(SustitucionesServiceImpl.class);
	
	@Autowired
	@Qualifier("sustitucionesRepository")
	private SustitucionesRepository sustitucionesRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Optional<Sustituciones> findById(int id) {
		return sustitucionesRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustituciones> findByMinutoSustitucion(String minuto_sustitucion) {
		return sustitucionesRepository.findByMinutoSustitucion(minuto_sustitucion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustituciones> findByPartido(int partido_id) {
		return sustitucionesRepository.findByPartido(partido_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustituciones> findByJugador(int jugador_id) {
		return sustitucionesRepository.findByJugador(jugador_id);
	}

	@Override
	public List<Sustituciones> findAll() {
		return sustitucionesRepository.findAll();
	}

	@Override
	public Sustituciones saveSustitucion(Sustituciones substitution) {
		Sustituciones sust=sustitucionesRepository.save(substitution);
		LOG.info(sust.toString());
		
		return sust;
	}

	@Override
	public int substitutionCount() {
		return (int) sustitucionesRepository.count();
	}
}
