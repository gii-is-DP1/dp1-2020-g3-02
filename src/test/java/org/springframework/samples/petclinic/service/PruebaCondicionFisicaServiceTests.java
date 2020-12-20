package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.enumerate.TipoPrueba;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PruebaCondicionFisicaServiceTests {
	
	@Autowired
	@Qualifier("pruebaCondicionFisicaService")
	private PruebaCondicionFisicaService pruebaService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id = 1;
		Optional<PruebaCondicionFisica> prueba = pruebaService.findById(id);
		assertNotNull(prueba);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id = 100;
		Optional<PruebaCondicionFisica> prueba = pruebaService.findById(id);
		assertEquals(prueba, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataFinding() {
		int jugador_id = 1;
		List<PruebaCondicionFisica> prueba = pruebaService.findByJugador(jugador_id);
		assertEquals(prueba.size(),11);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataNotFinding() {
		int jugador_id = 222;
		List<PruebaCondicionFisica> prueba = pruebaService.findByJugador(jugador_id);
		assertEquals(prueba.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoPruebaInitialDataFinding() {
		TipoPrueba tipo_prueba = TipoPrueba.SALTOVERTICAL;
		List<PruebaCondicionFisica> prueba = pruebaService.findByTipoPrueba(tipo_prueba);
		assertEquals(prueba.size(),3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByDatoLessThanEqualInitialDataFinding() {
		int dato = 4;
		List<PruebaCondicionFisica> prueba = pruebaService.findByDatoLessThanEqual(dato);
		assertEquals(prueba.size(),15);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByDatoLessThanEqualInitialDataNotFinding() {
		int dato = 2;
		List<PruebaCondicionFisica> prueba = pruebaService.findByDatoLessThanEqual(dato);
		assertEquals(prueba.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByDatoAndTipoPruebaInitialDataFinding() {
		int dato = 3;
		TipoPrueba tipo_prueba = TipoPrueba.SALTOVERTICAL;
		List<PruebaCondicionFisica> prueba = pruebaService.findByDatoAndTipoPrueba(dato, tipo_prueba);
		assertEquals(prueba.size(),3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByDatoAndTipoPruebaInitialDataNotFinding() {
		int dato = 2;
		TipoPrueba tipo_prueba = TipoPrueba.PULSACIONESMINIMAS;
		List<PruebaCondicionFisica> prueba = pruebaService.findByDatoAndTipoPrueba(dato, tipo_prueba);
		assertEquals(prueba.size(),0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoPruebaAndDatoLessThanEqualInitialDataFinding() {
		int dato = 4;
		TipoPrueba tipo_prueba = TipoPrueba.SALTOVERTICAL;
		List<PruebaCondicionFisica> prueba = pruebaService.findByTipoPruebaAndDatoLessThanEqual(tipo_prueba, dato);
		assertEquals(prueba.size(),3);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoPruebaAndDatoLessThanEqualInitialDataNotFinding() {
		int dato = 2;
		TipoPrueba tipo_prueba = TipoPrueba.PULSACIONESMINIMAS;
		List<PruebaCondicionFisica> prueba = pruebaService.findByDatoAndTipoPrueba(dato, tipo_prueba);
		assertEquals(prueba.size(),0);
	}
	
	
	@Test
	@Transactional
	public void testSavePruebaCondicionFisica() {
		PruebaCondicionFisica p = new PruebaCondicionFisica(3.1,LocalDate.of(2020, 11, 01),TipoPrueba.FLEXIBILIDAD);
		
		PruebaCondicionFisica prueba = pruebaService.save(p);
		
		assertNotNull(prueba);
		
	}

}
