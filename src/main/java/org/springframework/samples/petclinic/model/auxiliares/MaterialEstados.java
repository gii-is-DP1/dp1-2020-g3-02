package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Equipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEstados {
	
	private TipoMaterial tipo;
	
	private Map<EstadoMaterial,Integer> estados;
	
}
