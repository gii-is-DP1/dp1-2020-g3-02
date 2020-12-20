package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface MaterialService extends BaseService<Material>{

	public abstract Optional<Material> findById(int id);
	
	public abstract List<Material> findByTipo(TipoMaterial tipo);
	public abstract List<Material> findByDescripcion(String descripcion);
	public abstract List<Material> findByStock(int stock);
	public abstract Material updateMaterial(Material material);
	
	public abstract Material saveMaterial(Material material);

	int porcentajeUso(int material);
}
