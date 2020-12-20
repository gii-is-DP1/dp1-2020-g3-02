package org.springframework.samples.petclinic.converter;

import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.ediciones.MaterialEdit;
import org.springframework.stereotype.Component;

@Component
public class MaterialConverter {
	public MaterialEdit convertMaterialToMaterialEdit(Material material) {
		return new MaterialEdit(
				material.getId(),
				material.getLineaMaterial(),
				material.getDescripcion(),
				material.getTipo(),
				material.getStock(),
				material.getEstado()
				
				);
	}
	
}
