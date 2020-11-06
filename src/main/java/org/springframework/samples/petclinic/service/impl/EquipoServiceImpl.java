package org.springframework.samples.petclinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.EquipoRepository;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.stereotype.Service;

@Service("equipoService")
public class EquipoServiceImpl implements EquipoService {
	
	@Autowired
	@Qualifier("equipoRepository")
	private EquipoRepository equipoRepository;

	@Override
	public Equipo findByCategoria(String category) {
		return equipoRepository.findByCategoria(category);
	}

	@Override
	public List<Equipo> findByCategoriaStartingWith(String category) {
		return equipoRepository.findByCategoriaStartingWith(category);
	}

	@Override
	public List<Equipo> findByLiga(String league) {
		return equipoRepository.findByLiga(league);
	}

	@Override
	public List<Jugador> findByPorcentajeSaquesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeDefensasLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeRematesLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeFintasLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return equipoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	public List<Jugador> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return equipoRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

	@Override
	public void saveTeam(Equipo team) {
		
		equipoRepository.save(team);

	}

}
