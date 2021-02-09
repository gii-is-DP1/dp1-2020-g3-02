package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidoServiceTest {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private JugadorService jugadorService;

	//AssertJ assertion
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaOrderByHoraDataFinding() {
		LocalDate date = LocalDate.parse("06/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		List<Partido> partidos = partidoService.findByFechaOrderByHora(date);
		PartidoAssert.assertThat(partidos.get(0)).hasFecha(date.toString());
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 1);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaOrderByHoraDataNotFinding() {
		LocalDate date = LocalDate.parse("06/11/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		List<Partido> partidos = partidoService.findByFechaOrderByHora(date);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	//AsserJ assertion
	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaAfterDataFinding() {
		LocalDate date = LocalDate.parse("06/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		List<Partido> partidos = partidoService.findByFechaAfter(date);
		PartidoAssert.assertThat(partidos.get(0)).hasFecha(partidos.get(0).getFecha().toString());
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 17);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByFechaAfterDataNotFinding() {
		LocalDate date = LocalDate.parse("06/11/3000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		List<Partido> partidos = partidoService.findByFechaOrderByHora(date);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	//AssertJ assertion
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoAndFechaAndHoraBetweenDataFinding() {
		Equipo equipo = equipoService.findByCategoria("Senior");
		LocalDate fecha = LocalDate.parse("06/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String hora1 = "17:00";
		String hora2 = "19:00";
		List<Partido> partidos = partidoService.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2);
		PartidoAssert.assertThat(partidos.get(0))
		.isInEquipo(equipo)
		.isHoraBefore(hora2)
		.isHoraAfter(hora1)
		.hasFecha(fecha.toString());
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
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional
	public void testDeletePartido() {
		int partido_id = 1;
		partidoService.deleteById(partido_id);
		Optional<Partido> partido = partidoService.findById(partido_id);
		assertEquals(partido, Optional.empty());
	}	

	//AsserJ assertion
	@Test
	@Transactional(readOnly = true)
	public void testFindByEquipoDataFinding() {
		Equipo equipo = equipoService.findAll().get(0);
		List<Partido> partidos = partidoService.findByEquipo(equipo);
		PartidoAssert.assertThat(partidos.get(0)).isInEquipo(equipo);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 6);
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
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeSaquesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeSaquesLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRecepcionesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeRecepcionesLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeColocacionesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeDefensasLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeDefensasLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeDefensasLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeColocacionesLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeBloqueosLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeBloqueosLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeBloqueosLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeRematesLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeRematesLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeFintasLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeFintasLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeFintasLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualDataFinding() {
		double percentIntroducido=100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByPorcentajeAtaquesRapidosLessThanEqualDataNotFinding() {
		double percentIntroducido=-100;
		double percent=percentIntroducido/100;
		List<Partido> partidos = partidoService.findByPorcentajeAtaquesRapidosLessThanEqual(percent);
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByNumFaltasTotalesGreaterThanEqualDataFinding() {
		int faults=0;
		List<Partido> partidos=new ArrayList<Partido>(partidoService.findByNumFaltasTotalesGreaterThanEqual(faults));

		PartidoAssert.assertThat(partidos.get(0)).hasFaltasTotalesGreaterThan(faults);
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindByNumFaltasTotalesGreaterThanEqualDataNotFinding() {
		int faults=100;
		List<Partido> partidos=new ArrayList<Partido>(partidoService.findByNumFaltasTotalesGreaterThanEqual(faults));
		assertEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 0);
	}

	@Test
	@Transactional(readOnly = true)
	public void testFindAll() {
		List<Partido> partidos=new ArrayList<Partido>(partidoService.findAll());
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 18);
	}


	//AssertJ assertion
	@Test
	@Transactional
	public void testSavePartido() {
		Partido match = new Partido();
		Equipo equipo = equipoService.findByCategoria("Senior");
		LocalDate fecha = LocalDate.parse("25/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String hora = "22:00";
		match.setFecha(fecha);
		match.setHora(hora);
		match.setEquipo(equipo);

		Partido partido = partidoService.save(match);

		PartidoAssert.assertThat(partido)
		.hasFecha(fecha.toString())
		.hasHora(hora)
		.hasNotPuntosTotales()
		.isInEquipo(equipo);

		assertEquals(partido.getEquipo(), equipo);
		assertEquals(partido.getFecha(), fecha);

		assertNotEquals(partido,Optional.empty());
	}

	//AssertJ assertion
	@Test
	@Transactional(readOnly = true)
	public void testfindByPartidoFinalizadoFalseOrderByFechaDataFinding() {
		List<Partido> partidos = partidoService.findByPartidoFinalizadoFalseOrderByFecha();
		PartidoAssert.assertThat(partidos.get(0)).hasFecha(partidos.get(0).getFecha().toString());
		assertNotEquals(partidos,Lists.emptyList());
		assertEquals(partidos.size(), 4);
	}

	@Test
	@Transactional
	public void testdeleteAllInEquipo() {
		int equipo_id = 1;

		partidoService.deleteAllInEquipo(equipo_id);

		assertEquals(partidoService.findByEquipo(equipoService.findById(equipo_id).get()).size(), 0);
	}
	
	@Test
	@Transactional
	public void testObtenerPartidosConfrontados() {
		Jugador jugador = jugadorService.findById(1).get();
		List<Equipo> equiposJugador = jugador.getEquipos();
		Partido partido = partidoService.findById(12).get();
		
		partido.setFecha(LocalDate.parse("14/12/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		partido.setHora("12:00");
		
		List<PartidoConAsistencia> partidosConAsistencia = partidoService.obtenerPartidosConfrontados(equiposJugador, jugador, partido);

		assertNotEquals(partidosConAsistencia, 0);
	}


}
