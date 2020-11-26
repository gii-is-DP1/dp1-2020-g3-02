package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.stereotype.Repository;

@Repository("autobusRepository")
public interface AutobusRepository extends JpaRepository<Autobus, Serializable>{

	public List<Autobus> findByHoraSalida(String hora_salida);
	public List<Autobus> findByHoraLlegada(String hora_llegada);
	
	@Query(value = "SELECT id FROM autobus WHERE id IN (SELECT autobus_id FROM partidosbus WHERE partido_id = ?1)", nativeQuery = true)
	public List<Integer> findByPartido(int partido_id);
	
	@Query(value = "SELECT id FROM autobus WHERE id IN (SELECT autobus_id FROM jugadoresbus WHERE jugador_id = ?1)", nativeQuery = true)
	public List<Integer> findByJugador(int jugador_id);

}
