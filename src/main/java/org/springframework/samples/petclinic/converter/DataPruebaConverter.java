package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.auxiliares.DataPruebaCondicion;
import org.springframework.samples.petclinic.model.auxiliares.PruebasSinJugador;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.stereotype.Component;

@Component
public class DataPruebaConverter {
	
	public DataPruebaCondicion convertPruebaToDataPrueba(List<PruebasSinJugador> pruebas) {

		DataPruebaCondicion data = new DataPruebaCondicion();
		data.setData(pruebas);

		return data;
	}
}
