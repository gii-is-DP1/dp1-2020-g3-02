package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.Autobus;

public interface AutobusService {

	public abstract List<Autobus> findAll();
	public abstract Optional<Autobus> findById(int id);
	public abstract List<Integer> findByPartido(int partido_id);
	public abstract List<Integer> findByJugador(int jugador_id);
	public abstract List<Autobus> findByHoraSalida(String hora_salida);
	public abstract List<Autobus> findByHoraLlegada(String hora_llegada);
	public abstract Autobus saveAutobus(Autobus autobus);
}
