package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.stereotype.Repository;

@Repository("numCamisetaRepository")
public interface NumCamisetaRespository extends JpaRepository<NumCamiseta, Serializable>{
	
	public List<NumCamiseta> findByNumero(Integer numero);
	
	@Query("SELECT n FROM NumCamiseta n, Jugador j WHERE j.id=:jugador_id")
	public List<NumCamiseta> findByJugador(@Param("jugador_id") int jugador_id);
	
	@Query("SELECT n FROM NumCamiseta n, Equipo e WHERE e.id=:equipo_id")
	public List<NumCamiseta> findByEquipo(@Param("equipo_id") int equipo_id);
}
