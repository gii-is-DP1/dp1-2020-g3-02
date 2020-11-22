package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CapitanServiceTests {

	@Autowired
	@Qualifier("capitanService")
	private CapitanService capitanService;

	@Test
	public void testFindAllInitialData() {
		List<Capitan> capitan=new ArrayList<Capitan>(capitanService.findAll());
		assertEquals(capitan.size(), 2);
	}
	
	@Test
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Capitan> capitan=capitanService.findById(id);
		assertNotNull(capitan);
	}
	
	@Test
	public void testFindByIdInitialDataNotFinding() {
		int id=500;
		Optional<Capitan> capitan=capitanService.findById(id);
		assertEquals(capitan, Optional.empty());
	}
	
	@Test
	public void testFindByActitudInitialDataFinding() {
		Actitud at=Actitud.POSITIVA;
		List<Capitan> capitan=capitanService.findByActitud(at);
		assertEquals(capitan.size(), 2);
	}
	
	@Test
	public void testFindByActitudInitialDataNotFinding() {
		Actitud at=Actitud.NEGATIVA;
		List<Capitan> capitan= capitanService.findByActitud(at);
		assertEquals(capitan.size(), 0);
	}
	
	@Test
	public void testFindByNTiemposMuertosDataFinding() {
		int ntiemposmuertos=6;
		List<Capitan> capitan=capitanService.findByNtiemposmuertos(ntiemposmuertos);
		assertEquals(capitan.size(), 1);
	}
	
	@Test
	public void testFindByNTiemposMuertosDataNotFinding() {
		int ntiemposmuertos=60;
		List<Capitan> capitan=capitanService.findByNtiemposmuertos(ntiemposmuertos);
		assertEquals(capitan.size(), 0);
	}
	
	@Test
	public void testFindByEquipoDataFinding() {
		int equipo_id= 1;
		List<Capitan> capitan=capitanService.findByEquipo(equipo_id);
		assertEquals(capitan.size(), 2);
	}
	
	@Test
	public void testFindByEquipoDataNotFinding() {
		int equipo_id= 100;
		List<Capitan> capitan=capitanService.findByEquipo(equipo_id);
		assertEquals(capitan.size(), 0);
	}
	
	@Test
	public void testSaveCapitan() {
		Capitan capitan = new Capitan(10, Actitud.NEGATIVA);
		capitan.setJugador(null);
		
		Capitan captain = capitanService.saveCapitan(capitan);
		assertNotNull(captain);
		
	}
	public void deleteCapitan() {
		int id= 3;
		this.capitanService.deleteCapitan(id);
		Optional<Capitan> capitan=capitanService.findById(id);
		assertEquals(capitan, Optional.empty());
	}
}
