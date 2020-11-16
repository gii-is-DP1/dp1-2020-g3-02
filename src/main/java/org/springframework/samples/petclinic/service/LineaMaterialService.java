package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.model.LineaMaterial;

public interface LineaMaterialService {

	public abstract List<LineaMaterial> findAll();
	public abstract Optional<LineaMaterial> findById(int id);
	public abstract List<LineaMaterial> findByCantidad(LineaMaterial cantidad);
	
	public abstract List<LineaMaterial> findByMaterial(int material_id);
	public abstract List<LineaMaterial> findByEntrenamiento(int entrenamiento_id);
	
	public abstract LineaMaterial saveLineaMaterial(LineaMaterial lineaMaterial);

}
