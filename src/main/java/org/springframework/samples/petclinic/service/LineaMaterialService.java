package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.service.base.BaseService;

public interface LineaMaterialService extends BaseService<LineaMaterial>{

	public abstract List<LineaMaterial> findByCantidad(int cantidad);
	public abstract List<LineaMaterial> findByMaterial(int material_id);
	public abstract List<LineaMaterial> findByEntrenamiento(int entrenamiento_id);
	public abstract void deleteAllInEntrenamiento(Integer entrenamiento_id);
	public abstract List<LineaMaterial> findByTipoAndEstado(TipoMaterial tipo,EstadoMaterial estado);
	public abstract List<LineaMaterial> findByMaterialAndEntrenamiento(Material material,Entrenamiento entrenamiento);
	

}
