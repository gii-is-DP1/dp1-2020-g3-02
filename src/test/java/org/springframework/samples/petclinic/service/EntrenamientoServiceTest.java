package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.auxiliares.EntrenamientoConAsistencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
	public class EntrenamientoServiceTest {

		@Autowired
		private EntrenamientoService entrenamientoService;

		@Autowired
		private EquipoService equipoService;
		
		@Autowired
		private JugadorService jugadorService;
		
		@Test
		@Transactional(readOnly = true)
		public void testFindAllInitialData() {
			List<Entrenamiento>entrenamiento=new ArrayList<Entrenamiento>(entrenamientoService.findAll());
			assertEquals(entrenamiento.size(), 15);
			
		}



		@Test
		@Transactional(readOnly = true)
		public void testFindByIdInitialDataFinding() {
			int id=3;
			Optional<Entrenamiento>entrenamiento=entrenamientoService.findById(id);
			assertNotNull(entrenamiento);
		}
		
		@Test
		@Transactional(readOnly = true)
		public void testFindByIdInitialNotDataFinding() {
			int id=1000;
			Optional<Entrenamiento>entrenamiento=entrenamientoService.findById(id);
			assertEquals(entrenamiento, Optional.empty());
		}
		
		@Test
		@Transactional(readOnly = true)
		public void testFindByEquipoOrderByFechaInitialDataFinding() {
			Equipo equipo = equipoService.findById(2).get();
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEquipoOrderByFecha(equipo);
			assertEquals(entrenamiento.size(), 6);
		}

		@Test
		@Transactional(readOnly = true)
		public void testFindByEquipoOrderByFechaInitialDataNotFinding() {
			Equipo equipo = equipoService.findById(3).get();
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEquipoOrderByFecha(equipo);
			assertEquals(entrenamiento, Lists.emptyList());
		}

		@Test
		@Transactional(readOnly = true)
		public void testFindByFechaOrderByHoraInitialDataFinding() {
			LocalDate date = LocalDate.parse("06/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			List<Entrenamiento> entrenamiento = entrenamientoService.findByFechaOrderByHora(date);
			assertEquals(entrenamiento.size(), 3);
		}

		@Test
		@Transactional(readOnly = true)
		public void testFindByFechaOrderByHoraInitialDataNotFinding() {
			LocalDate date = LocalDate.parse("06/11/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			List<Entrenamiento> entrenamiento = entrenamientoService.findByFechaOrderByHora(date);
			assertEquals(entrenamiento, Lists.emptyList());
		}
		@Test
		@Transactional(readOnly = true)
		public void testFindByFechaAfterInitialDataFinding() {
			LocalDate date = LocalDate.parse("06/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			List<Entrenamiento> entrenamiento = entrenamientoService.findByFechaAfter(date);
			assertEquals(entrenamiento.size(), 12);
		}

		@Test
		@Transactional(readOnly = true)
		public void testFindByFechaAfterInitialDataNotFinding() {
			LocalDate date = LocalDate.parse("06/11/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			List<Entrenamiento> entrenamiento = entrenamientoService.findByFechaAfter(date);
			assertEquals(entrenamiento, Lists.emptyList());
		}
		@Test
		@Transactional(readOnly = true)
		public void testFindByEquipoAndFechaAndHoraBetweenInitialDataFinding() {
			Equipo equipo = equipoService.findById(1).get();
			LocalDate date = LocalDate.parse("07/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String hora1 ="12:00";
			String hora2 ="13:00";
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, date, hora1, hora2);
			assertEquals(entrenamiento.size(), 1);
		}

		@Test
		@Transactional(readOnly = true)
		public void testFindByEquipoAndFechaAndHoraBetweenInitialDataNotFinding() {
			Equipo equipo = equipoService.findById(3).get();
			LocalDate date = LocalDate.parse("07/11/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String hora1 ="12:00";
			String hora2 ="13:00";
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, date, hora1, hora2);
			assertEquals(entrenamiento, Lists.emptyList());
		}
		@Test
		@Transactional(readOnly = true)
		public void testFindByEquipoInitialDataFinding() {
			Equipo equipo = equipoService.findById(2).get();
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEquipo(equipo);
			assertEquals(entrenamiento.size(), 6);
		}

		@Test
		@Transactional(readOnly = true)
		public void testFindByEquipoInitialDataNotFinding() {
			Equipo equipo = equipoService.findById(3).get();
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEquipo(equipo);
			assertEquals(entrenamiento, Lists.emptyList());
		}
		@Test
		@Transactional
		public void testDeleteEntrenamiento() {
			int equipo_id = 1;
			entrenamientoService.deleteById(equipo_id);
			Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(equipo_id);
			assertEquals(entrenamiento, Optional.empty());
			
		}
		@Test
		@Transactional
		public void testObtenerEntrenamientosAsistidos() {
			List<Equipo> equipos = new ArrayList<>();
			equipos.add(equipoService.findByCategoria("Senior"));
			Jugador jugador = jugadorService.findById(1).get();
			Entrenamiento entr = entrenamientoService.findById(1).get();
			List<EntrenamientoConAsistencia> entrenamiento = entrenamientoService.obtenerEntrenamientosAsistidos(equipos, jugador, entr);
			assertEquals(entrenamiento.size(), 1);
			
		}
		@Test
		@Transactional
		public void testDeleteAllEntrenamientosByEquipo() {
			int equipo_id = 1;
			entrenamientoService.deleteAllInEquipo(equipo_id);
			Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(equipo_id);
			assertEquals(entrenamiento, Optional.empty());
			
		}
		@Test
		@Transactional(readOnly = true)
		public void testFindByEntrenamientoFinalizadoFalseOrderByFechaInitialDataFinding() {
			
			List<Entrenamiento> entrenamiento = entrenamientoService.findByEntrenamientoFinalizadoFalseOrderByFecha();
			assertEquals(entrenamiento.size(), 4);
		}

}
