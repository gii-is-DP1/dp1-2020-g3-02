package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtendedJpaRepository<T> extends JpaRepository<T, Serializable>{
	
	public List<T> findByPorcentajeSaquesLessThanEqual(double percent);
	public List<T> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public List<T> findByPorcentajeColocacionesLessThanEqual(double percent);
	public List<T> findByPorcentajeDefensasLessThanEqual(double percent);
	public List<T> findByPorcentajeBloqueosLessThanEqual(double percent);
	public List<T> findByPorcentajeRematesLessThanEqual(double percent);
	public List<T> findByPorcentajeFintasLessThanEqual(double percent);
	public List<T> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public List<T> findByNumFaltasTotalesGreaterThanEqual(int faults);
	
}
