package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jugadorService")
public class JugadorServiceImpl extends AbstractEstadisticasService<Jugador> implements JugadorService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(JugadorServiceImpl.class);
	
	@Autowired
	@Qualifier("jugadorRepository")
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService; 

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByFirstName(String name) throws DataAccessException {
		return jugadorRepository.findByFirstName(name);
	}
	
	@Override
	public Jugador findByEmail(String email) {
		return jugadorRepository.findByEmail(email);
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
	public List<Jugador> findByEquipo(int equipo_id) {
		return jugadorRepository.findByEquipo(equipo_id);
	}

	@Override
	@Transactional
	public Jugador save(Jugador player) throws DataAccessException {
		LOG.info("ALTURA DEL JUGADOR INSERTADO: "+player.getAltura());
		LOG.info("PESO DEL JUGADOR INSERTADO: "+player.getPeso());
		
		player.setImc(10000.*player.getPeso()/(player.getAltura()*player.getAltura()));
		LOG.info("IMC DEL JUGADOR INSERTADO: "+player.getImc());
		
		/** INSERCCION DE LOS VALORES DEL PESO IDEAL. SIEMPRE TE DA EL PESO QUE DEBES OBTENER MAS CERCANO DESDE TU PESO ACTUAL, EN CASO DE ESTAR EN NORMOPESO, EL PESO IDEAL ES EL PESO ACTUAL*/
		if(player.getImc()>24.9) player.setPesoIdeal((int) Math.floor(24.9*player.getAltura()*player.getAltura()/10000.));
		else if(player.getImc()<18.5) player.setPesoIdeal((int) Math.ceil(18.5*player.getAltura()*player.getAltura()/10000.));
		else player.setPesoIdeal(player.getPeso());
			
		LOG.info("PESO IDEAL DEL JUGADOR INSERTADO: "+player.getPesoIdeal());
		
		LOG.info("Se procede a guardar el usuario con user name: " + player.getUser().getUsername());
		userService.saveUser(player.getUser());
		
		LOG.info("Se procede a guardar las authorities de tipo jugador");
		authoritiesService.saveAuthorities(player.getUser().getUsername(), "jugador");
		
		Jugador jugador=jugadorRepository.save(player);
		
		LOG.info(jugador.toString());
		
		return jugador;
		
	}
	
	@Override
	public Jugador updateJugador(Jugador player) {
		LOG.info("ALTURA DEL JUGADOR INSERTADO: "+player.getAltura());
		LOG.info("PESO DEL JUGADOR INSERTADO: "+player.getPeso());
		
		player.setImc(10000.*player.getPeso()/(player.getAltura()*player.getAltura()));
		LOG.info("IMC DEL JUGADOR INSERTADO: "+player.getImc());
		
		/** INSERCCION DE LOS VALORES DEL PESO IDEAL. SIEMPRE TE DA EL PESO QUE DEBES OBTENER MAS CERCANO DESDE TU PESO ACTUAL, EN CASO DE ESTAR EN NORMOPESO, EL PESO IDEAL ES EL PESO ACTUAL*/
		if(player.getImc()>24.9) player.setPesoIdeal((int) Math.floor(24.9*player.getAltura()*player.getAltura()/10000.));
		else if(player.getImc()<18.5) player.setPesoIdeal((int) Math.ceil(18.5*player.getAltura()*player.getAltura()/10000.));
		else player.setPesoIdeal(player.getPeso());
			
		LOG.info("PESO IDEAL DEL JUGADOR INSERTADO: "+player.getPesoIdeal());
		
		/** CÁLCULO DE PORCENTAJE SIEMPRE QUE SE ACTUALIZA UN JUGADOR */
		if(player.getSaquesTotales() > 0) player.setPorcentajeSaques(1.*player.getSaquesAcertados()/player.getSaquesTotales());
		if(player.getRecepcionesTotales() > 0) player.setPorcentajeRecepciones(1.*player.getRecepcionesAcertadas()/player.getRecepcionesTotales());
		if(player.getColocacionesTotales() > 0) player.setPorcentajeColocaciones(1.*player.getColocacionesAcertadas()/player.getColocacionesTotales());
		if(player.getDefensasTotales() > 0) player.setPorcentajeDefensas(1.*player.getDefensasAcertadas()/player.getDefensasTotales());
		if(player.getBloqueosTotales() > 0) player.setPorcentajeBloqueos(1.*player.getBloqueosAcertados()/player.getBloqueosTotales());
		if(player.getRematesTotales() > 0) player.setPorcentajeRemates(1.*player.getRematesAcertados()/player.getRematesTotales());
		if(player.getFintasTotales() > 0) player.setPorcentajeFintas(1.*player.getFintasAcertadas()/player.getFintasTotales());
		if(player.getNumAtaquesRapidosTotales() > 0) player.setPorcentajeAtaquesRapidos(1.*player.getNumAtaquesRapidosAcertados()/player.getNumAtaquesRapidosTotales());
		
		userService.saveUser(player.getUser());
		
		authoritiesService.saveAuthorities(player.getUser().getUsername(), "jugador");
		
		Jugador jugador=jugadorRepository.save(player);
		
		return jugador;
	}

	@Override
	@Transactional
	public List<Jugador> saveEstadisticasJugadores(List<Jugador> players) {
		
		List<Jugador> result = jugadorRepository.saveAll(players);
		
		return result;
	}

	@Override
	public int playerCount() {
		return (int) jugadorRepository.count();
	}

	@Override
	public List<Jugador> findAuto(TipoAutorizacion autorizacion) {
		
		return jugadorRepository.findAuto(autorizacion);
	}
	
	@Override
	public List<Jugador> findPrivilegio(TipoPrivilegio privilegio) {
		
		return jugadorRepository.findPrivilegio(privilegio);
	}

	@Override
	public Jugador findByUser(User username) {
		
		return jugadorRepository.findByUser(username);
	}

}
