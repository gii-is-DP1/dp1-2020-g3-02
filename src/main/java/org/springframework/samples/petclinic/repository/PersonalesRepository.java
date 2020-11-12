package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.stereotype.Repository;

@Repository("personalesRepository")
public interface PersonalesRepository extends JpaRepository<Personales,Serializable>{
	public List<Personales> findByJugador(String jugador);
	
	@Query("SELECT a FROM Personales a, Partido p WHERE p.id=:partido_id")
	public List<Personales> findByPartido(@Param("partido_id") int partido_id); 


}
