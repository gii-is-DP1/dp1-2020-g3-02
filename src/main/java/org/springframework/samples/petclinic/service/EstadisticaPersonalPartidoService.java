package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;

public interface EstadisticaPersonalPartidoService {
	
	public abstract List<EstadisticaPersonalPartido> findAll();
	public abstract Optional<EstadisticaPersonalPartido> findById(int id);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeSaquesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeRecepcionesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeColocacionesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeDefensasLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeBloqueosLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeRematesLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeFintasLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByPorcentajeAtaquesRapidosLessThanEqual(double percent);
	public abstract List<EstadisticaPersonalPartido> findByJugador(int jugador_id);
	public abstract List<EstadisticaPersonalPartido> findByPartido(int partido_id);
	public abstract EstadisticaPersonalPartido findByJugadorAndPartido(int jugador_id,int partido_id);
	public abstract EstadisticaPersonalPartido saveEstadisticaPersonalPartido(EstadisticaPersonalPartido statistic);
	public abstract int statisticCount();
}
