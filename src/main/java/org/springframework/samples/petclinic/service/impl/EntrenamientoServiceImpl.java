package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.repository.EntrenamientoRepository;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.stereotype.Service;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService{

	@Autowired
	private EntrenamientoRepository entrenamientoRepository;

	@Override
	public List<Entrenamiento> findByEquipoOrderByFecha(Equipo team) {
		return entrenamientoRepository.findByEquipoOrderByFecha(team);
	}

	@Override
	public List<Entrenamiento> findByFechaOrderByHora(LocalDate date) {
		return entrenamientoRepository.findByFechaOrderByHora(date);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeSaquesLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeDefensasLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeRematesLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeFintasLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return entrenamientoRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	public List<Entrenamiento> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return entrenamientoRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

	@Override
	public Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento) {
		return entrenamientoRepository.save(entrenamiento);
	}

	@Override
	public void deleteEntrenamiento(Entrenamiento entrenamiento) {
		entrenamientoRepository.delete(entrenamiento);
	}

	@Override
	public List<Entrenamiento> findAll() {
		return entrenamientoRepository.findAll();

	}

	@Override
	public Optional<Entrenamiento> findById(int id) {

		return entrenamientoRepository.findById(id);
	}



}
