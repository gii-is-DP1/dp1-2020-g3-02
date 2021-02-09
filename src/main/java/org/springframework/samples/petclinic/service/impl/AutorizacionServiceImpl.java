package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.AutorizacionRepository;
import org.springframework.samples.petclinic.service.AutorizacionService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("autorizacionService")
public class AutorizacionServiceImpl extends AbstractService<Autorizacion> implements AutorizacionService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(AutorizacionServiceImpl.class);

	@Autowired
	@Qualifier("autorizacionRepository")
	private AutorizacionRepository autorizacionRepository;
	
	@Autowired
	private JugadorService jugadorService;

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByTipoAutorizacion(TipoAutorizacion tipoautorizacion) {
		return autorizacionRepository.findByTipoAutorizacion(tipoautorizacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByFecha(LocalDate fecha) {
		return autorizacionRepository.findByFecha(fecha);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByJugador(int jugador_id) {
		Optional<Jugador> jugador= jugadorService.findById(jugador_id);
		return autorizacionRepository.findByJugador(jugador.get());
	}

	@Override
	public Autorizacion findByJugadorAndTipo(Jugador jugador, TipoAutorizacion tipo) {
		return autorizacionRepository.findByJugadorAndTipoAutorizacion(jugador, tipo);
	}

}
