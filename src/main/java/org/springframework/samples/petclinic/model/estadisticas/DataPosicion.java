package org.springframework.samples.petclinic.model.estadisticas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPosicion {
	private List<JugadorPartidoStats> data;
}
