package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.repository.AutorizacionRepository;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.service.AutorizacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("autorizacionService")
public class AutorizacionServiceImpl implements AutorizacionService{
	private static final Log LOG = LogFactory.getLog(AutorizacionServiceImpl.class);

	@Autowired
	@Qualifier("autorizacionRepository")
	private AutorizacionRepository autorizacionRepository;
	
	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findAll() {
		return autorizacionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Autorizacion> findById(int id) {
		return autorizacionRepository.findById(id);
	}

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
		Optional<Jugador> jugador= jugadorRepository.findById(jugador_id);
		return autorizacionRepository.findByJugador(jugador.get());
	}

	@Override
	@Transactional
	public Autorizacion saveAutorizacion(Autorizacion autorizacion) {
		Autorizacion a = autorizacionRepository.save(autorizacion);
		
		return a;
	}

	@Override
	public List<Jugador> findJugadorByTipoAutorizacion(TipoAutorizacion tipoautorizacion) {
		
		return autorizacionRepository.findJugadorByTipoAutorizacion(tipoautorizacion);
	}

	@Override
	public void deleteAutorizacion(int id) {
		Optional<Autorizacion> vacio = Optional.empty();
		Optional<Autorizacion> autorizacion = findById(id);

		if(autorizacion != vacio) {
			autorizacionRepository.delete(autorizacion.get());
		}
		
	}

	@Override
	public Autorizacion findByJugadorAndTipo(Jugador jugador, TipoAutorizacion tipo) {
		return autorizacionRepository.findByJugadorAndTipoAutorizacion(jugador, tipo);
	}

}
