package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.stereotype.Repository;

@Repository("personalesRepository")
public interface PersonalesRepository extends JpaRepository<Personales,Serializable>{
	
	public List<Personales> findByPropietario(String propietario);	
	public List<Personales> findByJugador(Jugador jugador);

}
