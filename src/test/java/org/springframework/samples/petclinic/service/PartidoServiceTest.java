package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidoServiceTest {
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisticasService;
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaOrderByHoraDataFinding() {
		LocalDate date = LocalDate.parse("06/11/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		List<Partido> partidos = partidoService.findByFechaOrderByHora(date);
		assertEquals(partidos.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoAndFechaAndHoraBetweenDataFinding() {
		Equipo equipo = equipoService.findByCategoria("Senior");
		LocalDate fecha = LocalDate.parse("06/11/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String hora1 = "17:00";
		String hora2 = "19:00";
		List<Partido> partidos = partidoService.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2);
		assertEquals(partidos.size(), 1);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoAndFechaAndHoraBetweenDataNotFinding() {
		Equipo equipo = equipoService.findByCategoria("Senior");
		LocalDate fecha = LocalDate.parse("06/11/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String hora1 = "18:00";
		String hora2 = "20:00";
		List<Partido> partidos = partidoService.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaOrderByHoraDataNotFinding() {
		LocalDate date = LocalDate.parse("06/11/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		List<Partido> partidos = partidoService.findByFechaOrderByHora(date);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdDataFinding() {
		int partido_id = 1;
		Optional<Partido> partido = partidoService.findById(partido_id);
		assertNotEquals(partido, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByIdDataNotFinding() {
		int partido_id = 1000;
		Optional<Partido> partido = partidoService.findById(partido_id);
		assertEquals(partido, Optional.empty());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeDefensasLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeDefensasLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeBloqueosLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeBloqueosLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeRematesLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeFintasLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeFintasLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByNumFaltasTotalesGreaterThanEqualDataFinding() {
		int faults=0;
		List<Partido> partidos=new ArrayList<Partido>(partidoService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindByNumFaltasTotalesGreaterThanEqualDataNotFinding() {
		int faults=100;
		List<Partido> partidos=new ArrayList<Partido>(partidoService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(partidos.size(), 0);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testFindAll() {
		List<Partido> partidos=new ArrayList<Partido>(partidoService.findAll());
		assertEquals(partidos.size(), 6);
	}
	
	@Test
	@Transactional
	public void testSavePartido() {
		Partido match = new Partido();
		Equipo equipo = equipoService.findByCategoria("Senior");
		match.setFecha(LocalDate.parse("25/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		match.setHora("22:00");
		match.setEquipo(equipo);
		
		Partido partido = partidoService.savePartido(match);
		
		assertNotNull(partido);
	}
	
	@Test
	@Transactional
	public void testDeletePartido() {
		int partido_id = 1;
		partidoService.deleteById(partido_id);
		Optional<Partido> partido = partidoService.findById(partido_id);
		assertEquals(partido, Optional.empty());
		
	}

}
