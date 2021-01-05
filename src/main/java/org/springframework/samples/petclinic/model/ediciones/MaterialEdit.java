package org.springframework.samples.petclinic.model.ediciones;

import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEdit {

		
		private Integer id;
		
		private String nombre;
		
		private TipoMaterial tipo;
		
		private Integer stock;
		
		
}
