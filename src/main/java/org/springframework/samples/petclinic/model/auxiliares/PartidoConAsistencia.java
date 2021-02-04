package org.springframework.samples.petclinic.model.auxiliares;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoConAsistencia {
	
	private Integer id;
	
	private String equipo;
	
	private String fecha;
	
	private String hora;
	
	private List<Integer> asistencia;
	
	private boolean partidoFinalizado;
}
