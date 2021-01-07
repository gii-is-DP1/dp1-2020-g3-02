package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.repository.NumCamisetaRespository;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("numCamisetaService")
public class NumCamisetaServiceImpl extends AbstractService<NumCamiseta> implements NumCamisetaService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("numCamisetaRepository")
	private NumCamisetaRespository numCamisetaRepository;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EquipoService equipoService;

	@Override
	@Transactional(readOnly = true)
	public List<NumCamiseta> findByJugador(int jugador_id) {
		Optional<Jugador> jugador = jugadorService.findById(jugador_id);
		return numCamisetaRepository.findByJugador(jugador.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<NumCamiseta> findByEquipo(int equipo_id) {
		Optional<Equipo> equipo = equipoService.findById(equipo_id);
		return numCamisetaRepository.findByEquipo(equipo.get());
	}

	@Override
	public NumCamiseta findByEquipoAndJugador(int equipo_id, int jugador_id) {
		Equipo equipo = equipoService.findById(equipo_id).get();
		Jugador jugador = jugadorService.findById(jugador_id).get();
		NumCamiseta numCamiseta = numCamisetaRepository.findByEquipoAndJugador(equipo, jugador);
		return numCamiseta;
	}

	@Override
	public void deleteAllInEquipo(Integer equipo_id) {
		Optional<Equipo> equipo = equipoService.findById(equipo_id);
		List<NumCamiseta> numeros =numCamisetaRepository.findByEquipo(equipo.get());
		numCamisetaRepository.deleteAll(numeros);
		
	}

}
