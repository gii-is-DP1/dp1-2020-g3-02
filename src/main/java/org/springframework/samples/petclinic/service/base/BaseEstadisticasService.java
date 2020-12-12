package org.springframework.samples.petclinic.service.base;

import java.util.List;

public interface BaseEstadisticasService<T> extends BaseService<T>{
	
	public abstract List<T> findByPorcentajeSaquesLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeRecepcionesLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeColocacionesLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeDefensasLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeBloqueosLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeRematesLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeFintasLessThanEqual(double percent);
	
	public abstract List<T> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	
	public abstract List<T> findByNumFaltasTotalesGreaterThanEqual(int faults);

}
