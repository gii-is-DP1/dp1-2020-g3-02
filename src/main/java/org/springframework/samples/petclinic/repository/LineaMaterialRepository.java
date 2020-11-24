package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.stereotype.Repository;

@Repository("lineaMaterialRepository")
public interface LineaMaterialRepository extends JpaRepository<LineaMaterial, Serializable>{
	public List<LineaMaterial> findByCantidad(int cantidad);
	
	
	//@Query("SELECT l FROM LineaMaterial l, Material m WHERE m.id=:material_id")
	public List<LineaMaterial> findByMaterial( Material material);
	
	//@Query("SELECT l FROM LineaMaterial l, Entrenamiento e WHERE e.id=:entrenamiento_id")
	public List<LineaMaterial> findByEntrenamiento(Entrenamiento entrenamiento);

}
