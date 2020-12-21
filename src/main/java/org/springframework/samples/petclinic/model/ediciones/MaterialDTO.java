package org.springframework.samples.petclinic.model.ediciones;

import java.util.Set;

import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.LineaMaterial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MaterialDTO {


	
	private int id;
	
	//private Set<LineaMaterial> lineaMaterial;
	
	private String descripcion;
	
	private TipoMaterial tipo;
	
	private Integer stock;
	
	private EstadoMaterial estado;

}
