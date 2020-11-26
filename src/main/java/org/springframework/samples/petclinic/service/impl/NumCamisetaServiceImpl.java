package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.repository.EquipoRepository;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.samples.petclinic.repository.NumCamisetaRespository;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("numCamisetaService")
public class NumCamisetaServiceImpl implements NumCamisetaService{

	@Autowired
	@Qualifier("numCamisetaRepository")
	private NumCamisetaRespository numCamisetaRepository;
	
	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private EquipoRepository equipoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<NumCamiseta> findAll() {
		return numCamisetaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<NumCamiseta> findByJugador(int jugador_id) {
		Optional<Jugador> jugador = jugadorRepository.findById(jugador_id);
		return numCamisetaRepository.findByJugador(jugador.get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<NumCamiseta> findByEquipo(int equipo_id) {
		Optional<Equipo> equipo = equipoRepository.findById(equipo_id);
		return numCamisetaRepository.findByEquipo(equipo.get());
	}

	@Override
	public NumCamiseta saveNumCamiseta(NumCamiseta numCamiseta) {
		return numCamisetaRepository.save(numCamiseta);
	}

}
