package org.springframework.samples.petclinic.model.ediciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenamientoEdit {
		
		private Integer id;
		
		private String equipo;
		
		private String fecha;
		
		private String hora;
}
