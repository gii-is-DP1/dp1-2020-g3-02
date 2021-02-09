package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EstadisticaPersonalEntrenamientoServiceTests {
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EstadisticaPersonalEntrenamientoService eEntrenamientoService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAllInitialDataFinding() {
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento = eEntrenamientoService.findAll();
		assertNotNull(eEntrenamiento);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataFinding() {
		int id = 1;
		Optional<EstadisticaPersonalEntrenamiento> eEntrenamiento = eEntrenamientoService.findById(id);
		assertNotNull(eEntrenamiento);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdInitialDataNotFinding() {
		int id = 50;
		Optional<EstadisticaPersonalEntrenamiento> eEntrenamiento = eEntrenamientoService.findById(id);
		assertEquals(eEntrenamiento, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorDataFinding() {
		Integer jugadorId = 1;
	
		List<EstadisticaPersonalEntrenamiento> ePartido = eEntrenamientoService.findByJugador(jugadorId);
		assertNotNull(ePartido);
		assertEquals(ePartido.get(0).getJugador().getId(), jugadorId);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPartidoDataFinding() {
		Integer partidoId = 2;
		List<EstadisticaPersonalEntrenamiento> ePartido = eEntrenamientoService.findByEntrenamiento(partidoId);
		assertNotNull(ePartido);
		assertEquals(ePartido.get(0).getEntrenamiento().getId(), partidoId);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndEntrenamientoDataFinding() {
		Integer jugadorId = 1;
		Integer entrenamientoId = 2;
		EstadisticaPersonalEntrenamiento ePartido = eEntrenamientoService.findByJugadorAndEntrenamiento(jugadorId,entrenamientoId);
		assertNotNull(ePartido);
		assertEquals(ePartido.getEntrenamiento().getId(), entrenamientoId);
		assertEquals(ePartido.getJugador().getId(), jugadorId);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByJugadorAndPartidoDataNotFinding() {
		Integer jugadorId = 1;
		Integer entrenamientoId = 9;
		EstadisticaPersonalEntrenamiento ePartido = eEntrenamientoService.findByJugadorAndEntrenamiento(jugadorId,entrenamientoId);
		assertNull(ePartido);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualInitialDataFinding() {
		double percent = 0.8;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento = eEntrenamientoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertNotNull(eEntrenamiento);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualInitialDataNotFinding() {
		double percent = 0.2;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento = eEntrenamientoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=eEntrenamientoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(eEntrenamiento.size(), 15);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeRecepcionesLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeColocacionesLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 15);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeColocacionesLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeDefensasLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 15);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeDefensasLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeBloqueosLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 18);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeBloqueosLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeRematesLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 15);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeRematesLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeFintasLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 18);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeFintasLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualInitialDataFinding() {
		double percentIntroducido=50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento=new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 15);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualInitialDataNotFinding() {
		double percentIntroducido=-50;
		double percent=percentIntroducido/100;
		List<EstadisticaPersonalEntrenamiento> eEntrenamiento =new ArrayList<EstadisticaPersonalEntrenamiento>(eEntrenamientoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent));
		assertEquals(eEntrenamiento.size(), 0);
	}
	
	@Test
	@Transactional
	public void testSaveEstadisticaPersonalPartido() {
		
		EstadisticaPersonalEntrenamiento estadistica = new EstadisticaPersonalEntrenamiento();
		Jugador jugador = jugadorService.findById(1).get();
		Entrenamiento entrenamiento = entrenamientoService.findById(1).get();
		estadistica.setJugador(jugador);
		estadistica.setEntrenamiento(entrenamiento);
		
		EstadisticaPersonalEntrenamiento statEntrenamiento = eEntrenamientoService.save(estadistica);
		
		assertNotNull(statEntrenamiento);
		assertEquals(statEntrenamiento.getJugador(), jugador);
		assertEquals(statEntrenamiento.getEntrenamiento(), entrenamiento);
	}
	

}
