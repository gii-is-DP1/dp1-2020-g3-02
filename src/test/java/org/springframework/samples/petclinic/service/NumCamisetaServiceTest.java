package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class NumCamisetaServiceTest {

	@Autowired
	@Qualifier("numCamisetaService")
	private NumCamisetaService numCamisetaService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<NumCamiseta> numCamiseta=new ArrayList<NumCamiseta>(numCamisetaService.findAll());
		assertEquals(numCamiseta.size(), 2);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataFinding() {
		int jugador_id = 1;
		List<NumCamiseta> numcamiseta = new ArrayList<NumCamiseta>(numCamisetaService.findByJugador(jugador_id));
		assertEquals(numcamiseta.size(), 1);//
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataNotFinding() {
		int jugador_id = 4;
		List<NumCamiseta> numcamiseta = new ArrayList<NumCamiseta>(numCamisetaService.findByJugador(jugador_id));
		assertEquals(numcamiseta.size(), 0);//
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoInitialDataFinding() {
		int equipo_id = 1;
		List<NumCamiseta> numcamiseta = new ArrayList<NumCamiseta>(numCamisetaService.findByEquipo(equipo_id));
		assertEquals(numcamiseta.size(), 2);//
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoInitialDataNotFinding() {
		int equipo_id = 2;
		List<NumCamiseta> numcamiseta = new ArrayList<NumCamiseta>(numCamisetaService.findByEquipo(equipo_id));
		assertEquals(numcamiseta.size(), 0);//
	}

	@Test
	@Transactional
	public void testSaveNumCamiseta() {
		NumCamiseta numCamiseta = new NumCamiseta(6);	

		NumCamiseta numCami = numCamisetaService.save(numCamiseta);

		assertNotNull(numCami);
	}
}
