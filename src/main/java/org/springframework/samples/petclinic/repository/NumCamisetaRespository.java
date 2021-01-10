package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.stereotype.Repository;

@Repository("numCamisetaRepository")
public interface NumCamisetaRespository extends JpaRepository<NumCamiseta, Serializable>{
	
	public List<NumCamiseta> findByNumero(Integer numero);
	
	public List<NumCamiseta> findByJugador(Jugador jugador);
	
	public List<NumCamiseta> findByEquipo(Equipo equipo);
	
	public NumCamiseta findByEquipoAndJugador(Equipo equipo, Jugador jugador);
	
	public List<Integer> findNumeroByEquipo(Equipo equipo);
}
