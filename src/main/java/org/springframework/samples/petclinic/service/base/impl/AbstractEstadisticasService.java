package org.springframework.samples.petclinic.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.ExtendedJpaRepository;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractEstadisticasService<T> extends AbstractService<T> implements BaseEstadisticasService<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ExtendedJpaRepository<T> genericEstadisticaRepository;

	@Override
	public List<T> findByPorcentajeSaquesLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeDefensasLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeRematesLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeFintasLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return genericEstadisticaRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	public List<T> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return genericEstadisticaRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

}
