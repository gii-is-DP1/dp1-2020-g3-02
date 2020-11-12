package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Sustituciones;
import org.springframework.samples.petclinic.repository.SustitucionesRepository;
import org.springframework.samples.petclinic.service.SustitucionesServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sustitucionesService")
public class SustitucionesServiceImpl implements SustitucionesServices{

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
}
