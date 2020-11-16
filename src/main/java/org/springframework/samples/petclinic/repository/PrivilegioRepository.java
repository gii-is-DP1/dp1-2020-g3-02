package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Privilegio;

public interface PrivilegioRepository extends JpaRepository<Privilegio, Serializable>{
	public List<Privilegio> findByDescripcion(String descripcion);
	public List<Privilegio> findByTipoPrivilegio(TipoPrivilegio tipoPrivilegio);
	
	@Query("SELECT l FROM Privilegio l, Jugador m WHERE m.id=:jugador_id")
	public List<Privilegio> findByJugador(@Param("jugador_id") int jugador_id);
	
	@Query("SELECT l FROM Privilegio l, Equipo e WHERE e.id=:equipo_id")
	public List<Privilegio> findByEquipo(@Param("equipo_id") int equipo_id);
}
