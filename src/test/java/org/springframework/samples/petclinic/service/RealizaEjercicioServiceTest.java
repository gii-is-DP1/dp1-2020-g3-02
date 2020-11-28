package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class RealizaEjercicioServiceTest {

	@Autowired
	private RealizaEjercicioService realizaEjercicioService;
	
	@Autowired
	private EjercicioIndividualService ejercicioIndividualService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<RealizaEjercicio> realiza =new ArrayList<RealizaEjercicio>(realizaEjercicioService.findAll());
		assertEquals(realiza.size(), 1);//
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<RealizaEjercicio> realiza=realizaEjercicioService.findById(id);
		assertNotNull(realiza);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=1000;
		Optional<RealizaEjercicio> realiza=realizaEjercicioService.findById(id);
		assertEquals(realiza, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataFinding() {
		int id=1;
		List<RealizaEjercicio> realiza=realizaEjercicioService.findByJugador(id);
		assertEquals(realiza.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataNotFinding() {
		int id=1000;
		List<RealizaEjercicio> realiza=realizaEjercicioService.findByJugador(id);
		assertEquals(realiza.size(), 0);
	}
	
	@Test
	@Transactional
	public void testSaveRealizaEjercicio() {
		Optional<Jugador> jugador= jugadorService.findById(2);
		Jugador player= jugador.get();
		Optional<EjercicioIndividual> ejercicio= ejercicioIndividualService.findByNombre("Saque");
		EjercicioIndividual excercise= ejercicio.get();
		RealizaEjercicio realiza = new RealizaEjercicio(player,excercise, LocalDate.of(2020, 11, 5));	

		RealizaEjercicio rea = realizaEjercicioService.saveRealizaEjercicio(realiza);

		assertNotNull(rea);

	}

}
