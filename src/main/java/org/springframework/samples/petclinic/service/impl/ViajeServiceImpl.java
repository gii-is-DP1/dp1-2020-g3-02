package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.repository.AutobusRepository;
import org.springframework.samples.petclinic.repository.ViajeRepository;
import org.springframework.samples.petclinic.service.AutobusService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("viajeService")
public class ViajeServiceImpl extends AbstractService<Viaje> implements ViajeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(ViajeServiceImpl.class);
	
	@Autowired
	private ViajeRepository viajeRepository;
	
	@Autowired
	private PartidoService partidoService;

	@Override
	public List<Viaje> findByJugador(Jugador jugador) {
		
		return viajeRepository.findByJugador(jugador);
	}

	@Override
	public List<Viaje> findByPartido(Partido partido) {
		
		return viajeRepository.findByPartido(partido);
	}

	@Override
	public List<Viaje> findByTipoViaje(TipoViaje tipoViaje) {
		
		return viajeRepository.findByTipoViaje(tipoViaje);
	}

	@Override
	public List<Viaje> findByAutobus(Autobus autobus) {
		
		return viajeRepository.findByAutobus(autobus);
	}

	@Override
	public List<Viaje> findByPersonal(Personales personal) {
		
		return viajeRepository.findByPersonal(personal);
	}

	@Override
	public List<Viaje> findByJugadorAndTipoViaje(Jugador jugador, TipoViaje tipoViaje) {
		
		return viajeRepository.findByJugadorAndTipoViaje(jugador, tipoViaje);
	}

	@Override
	public List<Viaje> findByPartidoAndTipoViaje(Partido partido, TipoViaje tipoViaje) {
		
		return viajeRepository.findByPartidoAndTipoViaje(partido, tipoViaje);
	}

	@Override
	public List<Viaje> findByJugadorAndPersonal(Jugador jugador, Personales personal) {
		
		return viajeRepository.findByJugadorAndPersonal(jugador, personal);
	}

	@Override
	public void deleteAllInPartido(Integer partido_id) {
		Optional<Partido> partido = partidoService.findById(partido_id);
		List<Viaje> viajes = viajeRepository.findByPartido(partido.get());
		viajeRepository.deleteAll(viajes);
	}
}
