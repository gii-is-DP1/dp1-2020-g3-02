package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.ediciones.MaterialDTO;
import org.springframework.stereotype.Component;

@Component
public class MaterialConverter {
	public MaterialDTO convertMaterialToMaterialDTO(Material material) {
		return new MaterialDTO(
				material.getId(),
				material.getDescripcion(),
				material.getTipo(),
				material.getStock(),
				material.getEstado()
				
				);
	}
	
	public List<MaterialDTO> convertListEntityToListDTO(List<Material> materiales){
		List<MaterialDTO> dtos = new ArrayList<MaterialDTO>();
		
		for(Material material:materiales) {
			MaterialDTO dto = new MaterialDTO(material.getId(), 
					material.getDescripcion(), 
					material.getTipo(), 
					material.getStock(), 
					material.getEstado());
			dtos.add(dto);
		}
		
		return dtos;
		
	}
}
