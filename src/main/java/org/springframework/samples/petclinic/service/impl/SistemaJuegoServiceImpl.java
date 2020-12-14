package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.samples.petclinic.repository.SistemaJuegoRepository;
import org.springframework.samples.petclinic.service.SistemaJuegoService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SistemaJuegoServiceImpl extends AbstractService<SistemaJuego> implements SistemaJuegoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(SistemaJuegoServiceImpl.class);

	@Autowired
	private SistemaJuegoRepository sistemaJuegoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<SistemaJuego> findByPartido(int partido_id) {
		return sistemaJuegoRepository.findByPartido(partido_id);
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

}
