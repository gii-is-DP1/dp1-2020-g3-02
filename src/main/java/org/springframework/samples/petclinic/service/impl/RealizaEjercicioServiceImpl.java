package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.samples.petclinic.repository.RealizaEjercicioRepository;
import org.springframework.samples.petclinic.service.RealizaEjercicioService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("realizaEjercicioService")
public class RealizaEjercicioServiceImpl extends AbstractService<RealizaEjercicio> implements RealizaEjercicioService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(RealizaEjercicioServiceImpl.class);

	@Autowired
	@Qualifier("realizaEjercicioRepository")
	private RealizaEjercicioRepository realizaEjercicioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<RealizaEjercicio> findByJugador(int id) {
		return realizaEjercicioRepository.findByJugador(id);
	}
	
}
