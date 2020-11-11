package org.springframework.samples.petclinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EquipoRepository;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("equipoService")
public class EquipoServiceImpl implements EquipoService {
	
	@Autowired
	@Qualifier("equipoRepository")
	private EquipoRepository equipoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Equipo> findById(int id) {
		return equipoRepository.findById(id);
	}
	
	public List<Equipo> findAll(){
		return equipoRepository.findAll();
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
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeSaquesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeDefensasLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeRematesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeFintasLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return equipoRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

	@Override
	@Transactional
	public Equipo saveTeam(Equipo team) {
		
		Equipo equipo = equipoRepository.save(team);
		
		return equipo;

	}

}
