package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EquipoRepository;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.base.impl.AbstractEstadisticasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("equipoService")
public class EquipoServiceImpl extends AbstractEstadisticasService<Equipo> implements EquipoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("equipoRepository")
	private EquipoRepository equipoRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Override
	public List<String> findCategoria() {
		return equipoRepository.findCategoria();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Equipo findByCategoria(String category) {
		return equipoRepository.findByCategoria(category);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByCategoriaStartingWith(String category) {
		return equipoRepository.findByCategoriaStartingWith(category);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByLiga(String league) {
		return equipoRepository.findByLiga(league);
	}

	@Override
	public List<Jugador> findJugadoresNoEquipo(int id) {
		Optional<Equipo> equipo = equipoRepository.findById(id);
		List<Jugador> jugadoresEquipo = equipo.get().getJugadores();
		List<Jugador> jugadoresNoEquipo = jugadorService.findAll();
		jugadoresNoEquipo.removeAll(jugadoresEquipo);
		
		return jugadoresNoEquipo;
		
	}

}
