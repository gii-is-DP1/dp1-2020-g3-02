package org.springframework.samples.petclinic.model.auxiliares;

import java.util.List;

import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPosicion {
	private List<JugadorPartidoStats> data;
}
