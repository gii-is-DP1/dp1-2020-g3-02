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
import org.springframework.samples.petclinic.enumerate.TipoEjercicio;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EjercicioIndividualServiceTest {

	@Autowired
	private EjercicioIndividualService ejercicioIndividualService;

	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<EjercicioIndividual>ejer=new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findAll());
		assertEquals(ejer.size(), 3);//
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<EjercicioIndividual> ejer=ejercicioIndividualService.findById(id);
		assertNotNull(ejer);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=1000;
		Optional<EjercicioIndividual> ejer=ejercicioIndividualService.findById(id);
		assertEquals(ejer, Optional.empty());
	}


	@Test
	@Transactional(readOnly = true)
	public void testFindByNombreInitialDataFinding() {
		String nombre ="Saque";
		List<EjercicioIndividual>  ejer = new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findByNombreContaining(nombre));
		assertEquals(ejer.size(), 1);//
	}


	@Test
	@Transactional(readOnly = true)
	public void testFindByNombreInitialDataNotFinding() {
		String nombre ="bloqueo";
		List<EjercicioIndividual>  ejer = new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findByNombreContaining(nombre));
		assertEquals(ejer.size(), 0);//
	}


	@Test
	@Transactional(readOnly = true)
	public void testFindByNombreContainingInitialDataFinding() {
		String nombre ="u";
		List<EjercicioIndividual>  ejer = new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findByNombreContaining(nombre));
		assertEquals(ejer.size(), 2);//
	}


	@Test
	@Transactional(readOnly = true)
	public void testFindByNombreContainingInitialDataNotFinding() {
		String nombre ="k";
		List<EjercicioIndividual>  ejer = new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findByNombreContaining(nombre));
		assertEquals(ejer.size(), 0);//
	}


	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoEjercicioInitialDataFinding() {
		TipoEjercicio tipo= TipoEjercicio.ATAQUE ;
		List<EjercicioIndividual> ejer = new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findByTipoEjercicio(tipo));
		assertEquals(ejer.size(), 1);//
	}


	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoEjercicioInitialDataNotFinding() {
		TipoEjercicio  tipo= TipoEjercicio.BLOQUEO ;
		List<EjercicioIndividual> ejer = new ArrayList<EjercicioIndividual>(ejercicioIndividualService.findByTipoEjercicio(tipo));
		assertEquals(ejer.size(), 0);//
	}

	@Test
	@Transactional
	public void testSaveEjercicioIndividual() {
		

		EjercicioIndividual ejer = new EjercicioIndividual("Defensa", "Defender" , TipoEjercicio.DEFENSA);	

		EjercicioIndividual ej = ejercicioIndividualService.save(ejer);

		assertNotNull(ej);

	}


	@Test
	@Transactional
	public void testDeleteEjercicioIndividual() {
		
		int id= 6;
		this.ejercicioIndividualService.deleteByIdSiExiste(id);
		Optional<EjercicioIndividual> ejercicio=ejercicioIndividualService.findById(id);
		assertEquals(ejercicio, Optional.empty());
		
		
	}

}
