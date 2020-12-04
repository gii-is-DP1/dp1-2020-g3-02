package org.springframework.samples.petclinic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PruebaCondicionFisicaService;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PruebaCondicionFisicaController {
	
	@Autowired
	private PruebaCondicionFisicaService pruebaService;
	
//	@RequestMapping(value = "findJugadorPosicionPartido/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DataPosicion> graficoJugadorPosicionPartido(@PathVariable("id") int id) {
//		try {
//			Optional<Partido> partido = partidoService.findById(id);
//			List<JugadorPartidoStats> listaJugadorStats = new ArrayList<JugadorPartidoStats>();
//			for (int i = 0; i<partido.get().getJugadores().size();i++) {
//				
//				JugadorPartidoStats stats = jugadorPartidoStatsConverter.convertPartidoToPartidoStats(partido.get().getJugadores().get(i));
//				listaJugadorStats.add(stats);
//			}
//			
//			
//			DataPosicion data = utilConverter.convertPartidoToPartidoStats(listaJugadorStats);
//			return new ResponseEntity<DataPosicion>(data, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<DataPosicion>(HttpStatus.BAD_REQUEST);
//		}	
//	}

}
