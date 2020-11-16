package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.samples.petclinic.repository.SistemaJuegoRepository;
import org.springframework.samples.petclinic.service.SistemaJuegoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SistemaJuegoServiceImpl implements SistemaJuegoService {
	private static final Log LOG = LogFactory.getLog(SistemaJuegoServiceImpl.class);

	@Autowired
	private SistemaJuegoRepository sistemaJuegoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<SistemaJuego> findAll() {
		return sistemaJuegoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SistemaJuego> findByPartido(int partido_id) {
		return sistemaJuegoRepository.findByPartido(partido_id);
	}


	@Override
	@Transactional(readOnly = true)
	public Optional<SistemaJuego> findById(int id) {
		return sistemaJuegoRepository.findById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public List<SistemaJuego> findByminutoAplicacion(int minuto_aplicacion) {
		return sistemaJuegoRepository.findByminutoAplicacion(minuto_aplicacion);
	}


	@Override
	@Transactional(readOnly = true)
	public List<SistemaJuego> findByTipoSistema(Sistema tipo) {
		return sistemaJuegoRepository.findBySistema(tipo);
	}

	@Override
	@Transactional
	public SistemaJuego saveSistemaJuego(SistemaJuego sistemaJuego) {
		return sistemaJuegoRepository.save(sistemaJuego);
	}







}
