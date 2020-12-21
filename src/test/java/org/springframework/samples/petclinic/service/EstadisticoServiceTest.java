package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EstadisticoServiceTest {
	@Autowired
	@Qualifier("estadisticoService")
	private EstadisticoService estadisticoService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<Estadistico> estadistico=new ArrayList<Estadistico>(estadisticoService.findAll());
		assertEquals(estadistico.size(), 2);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Estadistico> estadistico=estadisticoService.findById(id);
		assertNotNull(estadistico);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=500;
		Optional<Estadistico> estadistico=estadisticoService.findById(id);
		assertEquals(estadistico, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testCountWithInitialData() {
		int count=estadisticoService.estadisticoCount();
		assertEquals(count, 2);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFirstNameInitialDataFinding() {
		String name="Romualdo";
		List<Estadistico> estadistico= new ArrayList<Estadistico>(estadisticoService.findByFirstName(name));
		assertEquals(estadistico.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFirstNameInitialDataNotFinding() {
		String name="Vlad";
		List<Estadistico> estadistico= new ArrayList<Estadistico>(estadisticoService.findByFirstName(name));
		assertEquals(estadistico.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEmailDataFinding() {
		String email = "romualdostats@gmail.com";
		Estadistico estadistico=estadisticoService.findByEmail(email);
		assertNotNull(estadistico);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEmailDataNotFinding() {
		String email = "paco123@gmail.com";
		Estadistico estadistico=estadisticoService.findByEmail(email);
		assertEquals(estadistico, null);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFechaNacimientoBetweenOrderByFechaNacimientoInitialDataFinding() {
		LocalDate firstDate = LocalDate.of(1978, 11, 5);
		LocalDate secondDate = LocalDate.of(2000, 11, 7);
		List<Estadistico> estadisticos=new ArrayList<Estadistico>(estadisticoService.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate));
		assertEquals(estadisticos.size(), 2);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaNacimientoBetweenOrderByFechaNacimientoInitialDataNotFinding() {
		LocalDate firstDate = LocalDate.of(2020, 11, 1);
		LocalDate secondDate = LocalDate.of(2020, 11, 3);
		List<Estadistico> estadisticos=new ArrayList<Estadistico>(estadisticoService.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate));
		assertEquals(estadisticos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaNacimientoAfterOrderByFechaNacimientoInitialDataFinding() {
		LocalDate date = LocalDate.of(1996, 11, 5);
		List<Estadistico> estadisticos=new ArrayList<Estadistico>(estadisticoService.findByFechaNacimientoAfterOrderByFechaNacimiento(date));
		assertEquals(estadisticos.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaNacimientoAfterOrderByFechaNacimientoInitialDataNotFinding() {
		LocalDate date = LocalDate.of(2020, 11, 7);
		List<Estadistico> estadisticos=new ArrayList<Estadistico>(estadisticoService.findByFechaNacimientoAfterOrderByFechaNacimiento(date));
		assertEquals(estadisticos.size(), 0);
	}
	
	@Test
	@Transactional
	public void testSaveEstadistico() {
		Estadistico estadistico= new Estadistico("diegoarmando@gmail.com", LocalDate.of(1957, 8, 19));
		estadistico.setFirstName("Diego");
		estadistico.setLastName("Maradona");
		estadistico.setUser(new User());
		estadistico.getUser().setUsername("D10S");
		estadistico.getUser().setPassword("asdf1234");
		estadistico.getUser().setEnabled(true);
		
		Estadistico stats = estadisticoService.save(estadistico);
		
		assertNotNull(stats);
		
	}
}
