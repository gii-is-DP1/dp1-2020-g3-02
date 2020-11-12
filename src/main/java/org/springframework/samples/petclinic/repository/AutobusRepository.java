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
	
	@Query("SELECT a FROM Autobus a, Partido p WHERE p.id=:partido_id")
	public List<Autobus> findByPartido(@Param("partido_id") int partido_id);

}
