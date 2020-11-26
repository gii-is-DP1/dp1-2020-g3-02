package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Material;

public interface MaterialService {

	public abstract List<Material> findAll();
	public abstract Optional<Material> findById(int id);
	
	public abstract List<Material> findByTipo(TipoMaterial tipo);
	public abstract List<Material> findByDescripcion(String descripcion);
	public abstract List<Material> findByStock(int stock);
	
	public abstract List<Material> findByLineaMaterial(int linea_material_id);
	
	public abstract Material saveMaterial(Material material);
}
