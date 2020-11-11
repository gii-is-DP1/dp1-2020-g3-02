package org.springframework.samples.petclinic.service.impl;

import java.util.List;

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
	public List<Equipo> findByPorcentajeSaquesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeDefensasLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeRematesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeFintasLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return equipoRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

	@Override
	@Transactional
	public void saveTeam(Equipo team) {
		
		equipoRepository.save(team);

	}

}
