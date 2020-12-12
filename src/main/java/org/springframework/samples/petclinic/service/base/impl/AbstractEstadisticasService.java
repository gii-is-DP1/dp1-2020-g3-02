package org.springframework.samples.petclinic.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.ExtendedJpaRepository;
import org.springframework.samples.petclinic.service.base.BaseEstadisticasService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractEstadisticasService<T> extends AbstractService<T> implements BaseEstadisticasService<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ExtendedJpaRepository<T> genericRepository;

	@Override
	public List<T> findByPorcentajeSaquesLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeSaquesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeRecepcionesLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeRecepcionesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeColocacionesLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeColocacionesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeDefensasLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeDefensasLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeBloqueosLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeBloqueosLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeRematesLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeRematesLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeFintasLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeFintasLessThanEqual(percent);
	}

	@Override
	public List<T> findByPorcentajeAtaquesRapidosLessThanEqual(double percent) {
		return genericRepository.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
	}

	@Override
	public List<T> findByNumFaltasTotalesGreaterThanEqual(int faults) {
		return genericRepository.findByNumFaltasTotalesGreaterThanEqual(faults);
	}

}
