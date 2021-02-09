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
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AutorizacionServiceTest {

		

	@Autowired
	private AutorizacionService autorizacionService;
	
	@Autowired
	private JugadorService jugadorService;
	
	
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialData() {
		List<Autorizacion>autorizacion=new ArrayList<Autorizacion>(autorizacionService.findAll());
		assertEquals(autorizacion.size(), 51);//
	}



	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id=1;
		Optional<Autorizacion> autorizacion=autorizacionService.findById(id);
		assertNotNull(autorizacion);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id=1000;
		Optional<Autorizacion> autorizacion=autorizacionService.findById(id);
		assertEquals(autorizacion, Optional.empty());
	}
	

	
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoAutorizacionInitialDataFinding() {
		TipoAutorizacion tipo= TipoAutorizacion.TRANSPORTE ;
		List<Autorizacion> autorizacion = new ArrayList<Autorizacion>(autorizacionService.findByTipoAutorizacion(tipo));
		assertEquals(autorizacion.size(), 24);//
	}
	@Test
	@Transactional(readOnly = true)
	public void testFindByTipoAutorizacionInitialDataNotFinding() {
		TipoAutorizacion tipo= TipoAutorizacion.RESPONSABILIDADLESION ;
		List<Autorizacion> autorizacion = new ArrayList<Autorizacion>(autorizacionService.findByTipoAutorizacion(tipo));
		assertEquals(autorizacion.size(), 24);//
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaInitialDataFinding() {
		LocalDate date = LocalDate.of(2020, 10, 9);
		List<Autorizacion> autorizacion=new ArrayList<Autorizacion>(autorizacionService.findByFecha(date));
		assertEquals(autorizacion.size(), 4);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaInitialDataNotFinding() {
		LocalDate date = LocalDate.of(2003, 11, 7);
		List<Autorizacion> autorizacion=new ArrayList<Autorizacion>(autorizacionService.findByFecha(date));
		assertEquals(autorizacion.size(), 0);
	}
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataFinding() {
		int jugador_id=1;
		List<Autorizacion> autorizacion=autorizacionService.findByJugador(jugador_id);
		assertEquals(autorizacion.size(), 3);//
	}
	
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndTipoDataFinding() {
		int jugador_id=1;
		Jugador jugador = jugadorService.findById(jugador_id).get();
		TipoAutorizacion tipo = TipoAutorizacion.RESPONSABILIDADLESION;
		Autorizacion autorizacion =autorizacionService.findByJugadorAndTipo(jugador,tipo);
		assertEquals(autorizacion.getTipoAutorizacion(), tipo);
		assertEquals(autorizacion.getJugador(), jugador);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorInitialDataNotFinding() {
		Jugador jugador = new Jugador("22222222B","Calle Alcafran 12","aquinohayquienduerma@gmail.com","Guarroman",LocalDate.of(1997, 8, 19),173,75,Posicion.COLOCADOR,Posicion.OPUESTO);
		jugador.setFirstName("Javier");
		jugador.setLastName("Gutierrez Falcon");
		jugador.setPesoIdeal(70);
		jugador.setImc(22.5);
		jugador.setUser(new User());
		jugador.getUser().setUsername("Superjavi");
		jugador.getUser().setPassword("asdf1234");
		jugador.getUser().setEnabled(true);
		
		Jugador player = jugadorService.save(jugador);
		
		List<Autorizacion> autorizacion=autorizacionService.findByJugador(player.getId());
		assertEquals(autorizacion.size(), 0);//
	}
	
	@Test
	@Transactional
	public void testSaveAutorizacion() {
		Optional<Jugador> jugador= jugadorService.findById(4);
		Jugador player= jugador.get();
		Autorizacion autorizacion = new Autorizacion(player,LocalDate.of(2020, 11, 5), TipoAutorizacion.TRANSPORTE);	

		Autorizacion aut = autorizacionService.save(autorizacion);

		assertNotNull(aut);
		
		assertEquals(aut.getJugador(), player);
		assertEquals(aut.getTipoAutorizacion(), TipoAutorizacion.TRANSPORTE);
	}

}


