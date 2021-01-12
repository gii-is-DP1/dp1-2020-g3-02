package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidoPuntos {
	
	private LocalDate fecha;
	
	private int numPuntosSet1;
	
	private int numPuntosSet2;
	
	private int numPuntosSet3;
	
	private int numPuntosSet4;
	
	private int numPuntosSet5;
	
	private int numPuntosTotales;

}
