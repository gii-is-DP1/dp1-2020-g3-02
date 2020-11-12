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
		// TODO Auto-generated method stub
		return sustitucionesRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustituciones> findByMinutoSustitucion(String minuto_sustitucion) {
		// TODO Auto-generated method stub
		return sustitucionesRepository.findByMinutoSustitucion(minuto_sustitucion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustituciones> findByPartido(int partido_id) {
		// TODO Auto-generated method stub
		return sustitucionesRepository.findByPartido(partido_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sustituciones> findByJugador(int jugador_id) {
		// TODO Auto-generated method stub
		return sustitucionesRepository.findByJugador(jugador_id);
	}

	@Override
	public List<Sustituciones> findAll() {
		// TODO Auto-generated method stub
		return sustitucionesRepository.findAll();
	}

	@Override
	public Sustituciones saveSustitucion(Sustituciones substitution) {
		// TODO Auto-generated method stub
		Sustituciones sust=sustitucionesRepository.save(substitution);
		LOG.info(sust.toString());
		
		return sust;
	}

	@Override
	public int substitutionCount() {
		// TODO Auto-generated method stub
		return (int) sustitucionesRepository.count();
	}
}
