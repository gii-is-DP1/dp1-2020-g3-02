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
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {
	
	@Autowired
	@Qualifier("jugadorService")
	private JugadorService jugadorService;
	
	@Test
	public void testFindAllInitialData() {
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findAll());
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Jugador> jugador=jugadorService.findById(id);
		assertNotNull(jugador);
	}
	
	@Test
	public void testFindByIdInitialDataNotFinding() {
		int id=500;
		Optional<Jugador> jugador=jugadorService.findById(id);
		assertEquals(jugador, Optional.empty());
	}
	
	@Test
	public void testCountWithInitialData() {
		int count=jugadorService.playerCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testFindByFirstNameInitialDataFinding() {
		String name="Paco";
		List<Jugador> jugadores = new ArrayList<Jugador>(jugadorService.findByFirstName(name));
		assertEquals(jugadores.size(), 1);
	}
	
	@Test
	public void testFindByFirstNameInitialDataNotFinding() {
		String name="Rasputin";
		List<Jugador> jugadores = new ArrayList<Jugador>(jugadorService.findByFirstName(name));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPosicionPrincipalInitialDataFinding() {
		Posicion position=Posicion.PUNTA;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPosicionPrincipal(position));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPosicionPrincipalInitialDataNotFinding() {
		Posicion position=Posicion.CENTRAL;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPosicionPrincipal(position));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFechaNacimientoBetweenOrderByFechaNacimientoInitialDataFinding() {
		LocalDate firstDate = LocalDate.of(1998, 11, 5);
		LocalDate secondDate = LocalDate.of(2004, 11, 7);
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByFechaNacimientoBetweenOrderByFechaNacimientoInitialDataNotFinding() {
		LocalDate firstDate = LocalDate.of(2020, 11, 1);
		LocalDate secondDate = LocalDate.of(2020, 11, 3);
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByFechaNacimientoBetweenOrderByFechaNacimiento(firstDate, secondDate));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByFechaNacimientoAfterOrderByFechaNacimientoInitialDataFinding() {
		LocalDate date = LocalDate.of(2004, 11, 5);
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByFechaNacimientoAfterOrderByFechaNacimiento(date));
		assertEquals(jugadores.size(), 1);
	}
	
	@Test
	public void testFindByFechaNacimientoAfterOrderByFechaNacimientoInitialDataNotFinding() {
		LocalDate date = LocalDate.of(2020, 11, 7);
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByFechaNacimientoAfterOrderByFechaNacimiento(date));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByAlturaGreaterThanEqualInitialDataFinding() {
		int height=160;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByAlturaGreaterThanEqual(height));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByAlturaGreaterThanEqualInitialDataNotFinding() {
		int height=300;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByAlturaGreaterThanEqual(height));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByAlturaLessThanEqualInitialDataFinding() {
		int height=176;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByAlturaLessThanEqual(height));
		assertEquals(jugadores.size(), 1);
	}
	
	@Test
	public void testFindByAlturaLessThanEqualInitialDataNotFinding() {
		int height=100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByAlturaLessThanEqual(height));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPesoGreaterThanEqualInitialDataFinding() {
		int weight=75;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPesoGreaterThanEqual(weight));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPesoGreaterThanEqualInitialDataNotFinding() {
		int weight=100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPesoGreaterThanEqual(weight));
		assertEquals(jugadores.size(), 0);
	}

	@Test
	public void testFindByPesoLessThanEqualInitialDataFinding() {
		int weight=80;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPesoLessThanEqual(weight));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPesoLessThanEqualInitialDataNotFinding() {
		int weight=50;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPesoLessThanEqual(weight));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeRecepcionesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeRecepcionesLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeRecepcionesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeRecepcionesLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeColocacionesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeColocacionesLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeColocacionesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeColocacionesLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeDefensasLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeDefensasLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeDefensasLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeDefensasLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeBloqueosLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeBloqueosLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeBloqueosLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeBloqueosLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeRematesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeRematesLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeRematesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeRematesLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeFintasLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeFintasLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeFintasLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeFintasLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeAtaquesRapidosLessThanEqual(percent));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByPorcentajeAtaquesRapidosLessThanEqual(percent));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testFindByNumFaltasTotalesGreaterThanEqualInitialDataFinding() {
		int faults=0;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(jugadores.size(), 2);
	}
	
	@Test
	public void testFindByNumFaltasTotalesGreaterThanEqualInitialDataNotFinding() {
		int faults=100;
		List<Jugador> jugadores=new ArrayList<Jugador>(jugadorService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(jugadores.size(), 0);
	}
	
	@Test
	public void testSavePlayer() {
		Jugador jugador = new Jugador("22222222B","Calle Alcafran 12","aquinohayquienduerma@gmail.com","Guarroman",LocalDate.of(1997, 8, 19),173,75,Posicion.COLOCADOR,Posicion.OPUESTO);
		jugador.setFirstName("Javier");
		jugador.setLastName("Gutierrez Falcon");
		jugador.setPeso_ideal(70);
		jugador.setImc(22.5);
		jugador.setUser(new User());
		jugador.getUser().setUsername("Superjavi");
		jugador.getUser().setPassword("asdf1234");
		jugador.getUser().setEnabled(true);
		
		Jugador player = jugadorService.saveJugador(jugador);
		
		assertNotNull(player);
		
	}
	
}
