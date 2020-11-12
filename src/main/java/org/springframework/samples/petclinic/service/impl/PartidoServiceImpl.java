package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.PartidoRepository;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Service;

@Service
public class PartidoServiceImpl implements PartidoService {
	
	@Autowired
	private PartidoRepository partidoRepository;

	@Override
	public List<Partido> findByFechaOrderByHora(LocalDate date) {
		return partidoRepository.findByFechaOrderByHora(date);
	}

	@Override
	public List<Partido> findByPorcentajeSaquesLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeDefensasLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeRematesLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeFintasLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return partidoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	public List<Partido> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return partidoRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

	@Override
	public Partido savePartido(Partido partido) {
		return partidoRepository.save(partido);
	}

	@Override
	public void deletePartido(Partido partido) {
		partidoRepository.delete(partido);
	}

	@Override
	public List<Partido> findAll() {
		return partidoRepository.findAll();
		
	}

	@Override
	public Optional<Partido> findById(int partido_id) {
		return partidoRepository.findById(partido_id);
	}

}
