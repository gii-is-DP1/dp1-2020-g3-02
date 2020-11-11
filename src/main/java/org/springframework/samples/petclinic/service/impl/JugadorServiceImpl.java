package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.controller.JugadorController;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jugadorService")
public class JugadorServiceImpl implements JugadorService {
	
	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	
	@Autowired
	@Qualifier("jugadorRepository")
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService; 
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Jugador> findById(int id) {
		return jugadorRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findAll() {
		return jugadorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByFirstName(String name) throws DataAccessException {
		return jugadorRepository.findByFirstName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPosicionPrincipal(Posicion position) throws DataAccessException {
		return jugadorRepository.findByPosicionPrincipal(position);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByFechaNacimientoBetweenOrderByFechaNacimiento(LocalDate firstDate, LocalDate secondDate) throws DataAccessException {
		return jugadorRepository.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByFechaNacimientoAfterOrderByFechaNacimiento(LocalDate date) throws DataAccessException {
		return jugadorRepository.findByFechaNacimientoAfterOrderByFechaNacimiento(date);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByAlturaGreaterThanEqual(int height) throws DataAccessException {
		return jugadorRepository.findByAlturaGreaterThanEqual(height);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByAlturaLessThanEqual(int height) throws DataAccessException {
		return jugadorRepository.findByAlturaLessThanEqual(height);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPesoGreaterThanEqual(int weight) throws DataAccessException {
		return jugadorRepository.findByPesoGreaterThanEqual(weight);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPesoLessThanEqual(int weight) throws DataAccessException {
		return jugadorRepository.findByPesoLessThanEqual(weight);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeSaquesLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeRecepcionesLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeColocacionesLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeDefensasLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeBloqueosLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeRematesLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeFintasLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) throws DataAccessException {
		return jugadorRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByNumFaltasTotalesGreaterThanEqual(int faults) throws DataAccessException {
		return jugadorRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByEquipo(int equipo_id) {
		return jugadorRepository.findByEquipo(equipo_id);
	}

	@Override
	@Transactional
	public Jugador saveJugador(Jugador player) throws DataAccessException {
		
		Jugador jugador = jugadorRepository.save(player);
		
		LOG.info(jugador.toString());
		
		userService.saveUser(player.getUser());
		
		authoritiesService.saveAuthorities(player.getUser().getUsername(), "player");
		
		return jugador;
		
	}

	@Override
	@Transactional
	public List<Jugador> saveEstadisticasJugadores(List<Jugador> players) {
		
		List<Jugador> result = (List<Jugador>) jugadorRepository.saveAll(players);
		
		return result;
	}

	@Override
	public int playerCount() {
		return (int) jugadorRepository.count();
	}

}
