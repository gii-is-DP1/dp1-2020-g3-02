package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.LineaMaterial;

import org.springframework.stereotype.Repository;

@Repository("lineaMaterialRepository")
public interface LineaMaterialRepository extends JpaRepository<LineaMaterial, Serializable>{
	public List<LineaMaterial> findByCantidad(LineaMaterial cantidad);
	
	@Query("SELECT l FROM LineaMaterial l, Material m WHERE m.id=:material_id")
	public List<LineaMaterial> findByMaterial(@Param("material_id") int material_id);
	
	@Query("SELECT l FROM LineaMaterial l, Entrenamiento e WHERE e.id=:entrenamiento_id")
	public List<LineaMaterial> findByEntrenamiento(@Param("entrenamiento_id") int entrenamiento_id);

}