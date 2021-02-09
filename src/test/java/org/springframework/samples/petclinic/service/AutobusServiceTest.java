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
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AutobusServiceTest {

	@Autowired
	@Qualifier("autobusService")
	private AutobusService autobusService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<Autobus> autobus=new ArrayList<Autobus>(autobusService.findAll());
		assertEquals(autobus.size(), 1);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Autobus> autobus=autobusService.findById(id);
		assertNotNull(autobus);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=500;
		Optional<Autobus> autobus=autobusService.findById(id);
		assertEquals(autobus, Optional.empty());
	}

	
	@Test
	@Transactional
	public void testSaveAutobus() {
		Autobus autobus = new Autobus();
		autobus.setId(76);
		Autobus bus = autobusService.save(autobus);
		assertNotNull(bus);
		

	}
}
