package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoResponsable;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.AutorizacionRepository;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.service.AutorizacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("autorizacionService")
public class AutorizacionServiceImpl implements AutorizacionService{

	@Autowired
	@Qualifier("autorizacionRepository")
	private AutorizacionRepository autorizacionRepository;
	
	@Qualifier("jugadorRepository")
	private JugadorRepository jugadorRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findAll() {
		// TODO Auto-generated method stub
		return autorizacionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Autorizacion> findById(int id) {
		// TODO Auto-generated method stub
		return autorizacionRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByTipoAutorizacion(TipoAutorizacion tipoautorizacion) {
		// TODO Auto-generated method stub
		return autorizacionRepository.findByTipoAutorizacion(tipoautorizacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByFecha(LocalDate fecha) {
		// TODO Auto-generated method stub
		return autorizacionRepository.findByFecha(fecha);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByJugador(int jugador_id) {
		// TODO Auto-generated method stub
		
		Optional<Jugador> jug= jugadorRepository.findById(jugador_id);
		return autorizacionRepository.findByJugador(jug.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Autorizacion> findByTipoResponasble(TipoResponsable tiporesponsable) {
		// TODO Auto-generated method stub
		return autorizacionRepository.findByTipoResponsable(tiporesponsable);
	}

	@Override
	@Transactional
	public Autorizacion saveAutorizacion(Autorizacion autorizacion) {
		Autorizacion a = autorizacionRepository.save(autorizacion);
		
		return a;
	}

}