package org.springframework.samples.petclinic.model.ediciones;

import java.time.LocalDate;
import java.util.List;

import org.springframework.samples.petclinic.model.Equipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoEdit {
	
	private String equipo;
	
	private LocalDate fecha;
	
	private String hora;
}
