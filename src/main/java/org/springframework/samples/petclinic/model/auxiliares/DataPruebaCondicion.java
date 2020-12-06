package org.springframework.samples.petclinic.model.auxiliares;

import java.util.List;

import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPruebaCondicion {
	private List<PruebasSinJugador> data;
}
