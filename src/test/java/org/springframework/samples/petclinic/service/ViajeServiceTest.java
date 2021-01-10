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
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ViajeServiceTest {

	@Autowired
	@Qualifier("viajeService")
	private ViajeService viajeSevice;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private AutobusService autobusService;
	
	@Autowired
	private PersonalesService personalService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<Viaje> viajes=new ArrayList<Viaje>(viajeSevice.findAll());
		assertEquals(viajes.size(), 20);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Viaje> viaje=viajeSevice.findById(id);
		assertNotNull(viaje);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=500;
		Optional<Viaje> viaje=viajeSevice.findById(id);
		assertEquals(viaje, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataFinding() {
		Jugador jugador = jugadorService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByJugador(jugador);
		assertEquals(viaje.size(), 4);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataNotFinding() {
		Jugador jugador = jugadorService.findById(22).get();
		List<Viaje> viaje=viajeSevice.findByJugador(jugador);
		assertEquals(viaje.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPartidoInitialDataFinding() {
		Partido partido = partidoService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByPartido(partido);
		assertEquals(viaje.size(), 12);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPartidoInitialDataNotFinding() {
		Partido partido = partidoService.findById(6).get();
		List<Viaje> viaje=viajeSevice.findByPartido(partido);
		assertEquals(viaje.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByAutobusInitialDataFinding() {
		Autobus autobus = autobusService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByAutobus(autobus);
		assertEquals(viaje.size(), 14);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByAutobusInitialDataNotFinding() {
		Autobus autobus = autobusService.findById(3).get();
		List<Viaje> viaje=viajeSevice.findByAutobus(autobus);
		assertEquals(viaje.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPersonalInitialDataFinding() {
		Personales personal = personalService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByPersonal(personal);
		assertEquals(viaje.size(), 3);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPersonalInitialDataNotFinding() {
		Personales personal = personalService.findById(3).get();
		List<Viaje> viaje=viajeSevice.findByPersonal(personal);
		assertEquals(viaje.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoViajeInitialDataFinding() {
		List<Viaje> viaje= viajeSevice.findByTipoViaje(TipoViaje.IDA);
		assertEquals(viaje.size(), 12);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoViajeInitialDataNotFinding() {
		List<Viaje> viaje=viajeSevice.findByTipoViaje(TipoViaje.VUELTA);
		assertEquals(viaje.size(), 8);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndTipoViajeInitialDataFinding() {
		Jugador jugador = jugadorService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByJugadorAndTipoViaje(jugador, TipoViaje.IDA);
		assertEquals(viaje.size(), 2);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndTipoViajeInitialDataNotFinding() {
		Jugador jugador = jugadorService.findById(22).get();
		List<Viaje> viaje= viajeSevice.findByJugadorAndTipoViaje(jugador, TipoViaje.IDA);
		assertEquals(viaje.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPartidoAndTipoViajeInitialDataFinding() {
		Partido partido = partidoService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByPartidoAndTipoViaje(partido, TipoViaje.IDA);
		assertEquals(viaje.size(), 8);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPartidoAndTipoViajeInitialDataNotFinding() {
		Partido partido = partidoService.findById(6).get();
		List<Viaje> viaje= viajeSevice.findByPartidoAndTipoViaje(partido, TipoViaje.IDA);
		assertEquals(viaje.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndPersonalInitialDataFinding() {
		Jugador jugador = jugadorService.findById(1).get();
		Personales personal = personalService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByJugadorAndPersonal(jugador, personal);
		assertEquals(viaje.size(), 2);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndPersonalInitialDataNotFinding() {
		Jugador jugador = jugadorService.findById(22).get();
		Personales personal = personalService.findById(1).get();
		List<Viaje> viaje= viajeSevice.findByJugadorAndPersonal(jugador, personal);
		assertEquals(viaje.size(), 0);
	}
	
	@Test
	@Transactional
	public void testSaveViaje() {
		Jugador jugador = jugadorService.findById(1).get();
		Partido partido = partidoService.findById(1).get();
		Autobus autobus = autobusService.findById(1).get();
		Viaje viaje = new Viaje();
		viaje.setId(76);
		viaje.setTipoViaje(TipoViaje.IDA);
		viaje.setJugador(jugador);
		viaje.setPartido(partido);
		viaje.setAutobus(autobus);
		viaje.setHoraSalida("17:00");
		
		Viaje _viaje = viajeSevice.save(viaje);
		
		assertNotNull(_viaje);
	}
}
