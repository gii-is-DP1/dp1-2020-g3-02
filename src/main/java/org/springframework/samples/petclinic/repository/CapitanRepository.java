package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.stereotype.Repository;

@Repository("capitanRepository")
public interface CapitanRepository extends JpaRepository<Capitan, Serializable>{
	public List<Capitan> findByActitud(Actitud actitud);
	public List<Capitan> findByNTiemposMuertos(Integer ntiempomuerto);
	
	@Query("SELECT j FROM Capitan j, Equipo e WHERE e.id=:equipo_id")
	public List<Capitan> findByEquipo(@Param("equipo_id") int equipo_id);
}
