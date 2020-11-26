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
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SustitucionServiceTest {

	@Autowired
	private SustitucionService sustitucionService;
	
	
	@Test
	public void testFindAllInitialData() {
		List<Sustitucion> sustitucion = new ArrayList<Sustitucion>(sustitucionService.findAll());
		assertEquals(sustitucion.size(), 3);//
	}
	
	@Test
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Sustitucion> sustitucion=sustitucionService.findById(id);
		assertNotNull(sustitucion);
	}
	
	@Test
	public void testFindByIdInitialDataNotFinding() {
		int id=100;
		Optional<Sustitucion> sustitucion=sustitucionService.findById(id);
		assertEquals(sustitucion, Optional.empty());
	}

	@Test
	public void findByMinutoSustitucionInitialDataFinding() {
		int minuto_sustitucion = 30;
		List<Sustitucion> sustitucion = sustitucionService.findByMinutoSustitucion(minuto_sustitucion);
		assertEquals(sustitucion.size(),1);
	}
	
	@Test
	public void findByMinutoSustitucionInitialDataNotFinding() {
		int minuto_sustitucion = 1000;
		List<Sustitucion> sustitucion = sustitucionService.findByMinutoSustitucion(minuto_sustitucion);
		assertEquals(sustitucion.size(),0);
	}
	
	@Test
	public void findByPartidoInitialDataFinding() {
		int partido_id = 1;
		List<Sustitucion> sustitucion = sustitucionService.findByPartido(partido_id);
		assertEquals(sustitucion.size(),2);
	}
	
	@Test
	public void findByPartidoInitialDataNotFinding() {
		int partido_id = 5;
		List<Sustitucion> sustitucion = sustitucionService.findByPartido(partido_id);
		assertEquals(sustitucion.size(),0);
	}
	
	@Test
	public void findByJugadorInitialDataFinding() {
		int jugador_id = 1;
		List<Sustitucion> sustitucion = sustitucionService.findByJugador(jugador_id);
		assertEquals(sustitucion.size(),2);
	}
	
	@Test
	public void findByJugadorInitialDataNotFinding() {
		int jugador_id = 5;
		List<Sustitucion> sustitucion = sustitucionService.findByJugador(jugador_id);
		assertEquals(sustitucion.size(),0);
	}
	
	@Test
	public void testSaveSustitucion() {
		Sustitucion sustitucion = new Sustitucion(50);	

		Sustitucion sus = sustitucionService.saveSustitucion(sustitucion);

		assertNotNull(sus);
	}
	
	@Test
	public void testCountSustituciones() {
		int n = sustitucionService.substitutionCount();
		assertEquals(n, 3);
	}
}
