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
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.repository.PersonalesRepository;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PersonalServiceTests {
		

	@Autowired
	private PersonalesService personalesService;
	
	
	
	@Test
	public void testFindAllInitialData() {
		List<Personales>personales=new ArrayList<Personales>(personalesService.findAll());
		assertEquals(personales.size(), 0);//
	}



	@Test
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Personales> personales=personalesService.findById(id);
		assertNotNull(personales);
	}
	
	@Test
	public void testFindByIdInitialDataNotFinding() {
		int id=1000;
		Optional<Personales> personales=personalesService.findById(id);
		assertEquals(personales, Optional.empty());
	}
	
	@Test
	public void testFindByPropietarioInitialDataFinding() {
		String propietario="Pablo";
		List<Personales> personales = new ArrayList<Personales>(personalesService.findByPropietario(propietario));
		assertEquals(personales.size(), 0);//
	}
	
	@Test
	public void testFindByPropietarioInitialDataNotFinding() {
		String propietario="Carmen";
		List<Personales> personales = new ArrayList<Personales>(personalesService.findByPropietario(propietario));
		assertEquals(personales.size(), 0);//
	}
	
	@Test
	public void testFindByJugadorInitialDataFinding() {
		int jugador_id=1;
		Optional<Personales> personales=personalesService.findByJugador(jugador_id);
		assertEquals(personales, Optional.empty());///
	}
	
	@Test
	public void testFindByJugadorInitialDataNotFinding() {
		int jugador_id=1000;
		Optional<Personales> personales=personalesService.findByJugador(jugador_id);
		assertEquals(personales, Optional.empty());//
	}
	
	@Test
	public void testFindByPartidoInitialDataFinding() {
		int partido_id=1;
		Optional<Personales> personales=personalesService.findByPartido(partido_id);
		assertEquals(personales, Optional.empty());//
	}
	
	@Test
	public void testFindByPartidoInitialDataNotFinding() {
		int partido_id=1000;
		Optional<Personales> personales=personalesService.findByPartido(partido_id);
		assertEquals(personales,Optional.empty());//
	}
	
	@Test
	public void testSavePersonales() {
		Personales personales = new Personales("Pablo");	

		Personales personal = personalesService.savePersonales(personales);

		assertNotNull(personal);

	}

}
