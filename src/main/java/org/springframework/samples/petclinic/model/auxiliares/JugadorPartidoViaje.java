package org.springframework.samples.petclinic.model.auxiliares;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorPartidoViaje {
	
	private int viajeId;
	private LocalDate fecha;
	private String hora;
	private String nombre;
	private Integer numCamiseta;
	private int autobusId;
	private String propietario;
	private boolean haLlegado;
}
