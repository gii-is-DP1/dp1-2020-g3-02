package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SustitucionServiceTest {

	@Autowired
	private SustitucionService sustitucionService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private JugadorService jugadorService;
	
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<Sustitucion> sustitucion = new ArrayList<Sustitucion>(sustitucionService.findAll());
		assertEquals(sustitucion.size(), 3);//
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Sustitucion> sustitucion=sustitucionService.findById(id);
		assertNotNull(sustitucion);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=100;
		Optional<Sustitucion> sustitucion=sustitucionService.findById(id);
		assertEquals(sustitucion, Optional.empty());
	}

	@Test
	@Transactional(readOnly = true)
	public void findByMinutoSustitucionInitialDataFinding() {
		int minuto_sustitucion = 30;
		List<Sustitucion> sustitucion = sustitucionService.findByMinutoSustitucion(minuto_sustitucion);
		assertEquals(sustitucion.size(),1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByMinutoSustitucionInitialDataNotFinding() {
		int minuto_sustitucion = 1000;
		List<Sustitucion> sustitucion = sustitucionService.findByMinutoSustitucion(minuto_sustitucion);
		assertEquals(sustitucion.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByPartidoInitialDataFinding() {
		int partido_id = 1;
		List<Sustitucion> sustitucion = sustitucionService.findByPartido(partido_id);
		assertEquals(sustitucion.size(),2);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByPartidoInitialDataNotFinding() {
		int partido_id = 5;
		List<Sustitucion> sustitucion = sustitucionService.findByPartido(partido_id);
		assertEquals(sustitucion.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByJugadorInitialDataFinding() {
		int jugador_id = 1;
		List<Sustitucion> sustitucion = sustitucionService.findByJugadorEntra(jugador_id);
		assertEquals(sustitucion.size(),2);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByJugadorInitialDataNotFinding() {
		int jugador_id = 5;
		List<Sustitucion> sustitucion = sustitucionService.findByJugadorEntra(jugador_id);
		assertEquals(sustitucion.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByJugadorSaleInitialDataFinding() {
		int jugador_id = 1;
		List<Sustitucion> sustitucion = sustitucionService.findByJugadorSale(jugador_id);
		assertEquals(sustitucion.size(),1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void findByJugadorSaleInitialDataNotFinding() {
		int jugador_id = 5;
		List<Sustitucion> sustitucion = sustitucionService.findByJugadorSale(jugador_id);
		assertEquals(sustitucion.size(),0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testCountSustituciones() {
		int n = sustitucionService.substitutionCount();
		assertEquals(n, 3);
	}
	
	@Test
	@Transactional
	public void testSaveSustitucion() {
		Partido partido = partidoService.findById(1).get();
		Jugador player1 = jugadorService.findById(1).get();
		Jugador player2 = jugadorService.findById(2).get();
		Sustitucion sustitucion = new Sustitucion(partido, player1, player2, 15);
		Sustitucion sus = sustitucionService.save(sustitucion);

		assertNotNull(sus);
		
		assertEquals(sus.getJugadorEntra(), player1);
		assertEquals(sus.getJugadorSale(), player2);
		assertEquals(sus.getPartido(), partido);
	}
}
