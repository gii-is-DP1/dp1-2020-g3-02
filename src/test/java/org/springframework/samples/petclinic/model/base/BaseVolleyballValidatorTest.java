package org.springframework.samples.petclinic.model.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.service.CapitanService;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.PruebaCondicionFisicaService;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Clase que genera datos correctos para las validaciones de Validators
 *
 */
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BaseVolleyballValidatorTest {
	
	// Todos los servicios necesarios para crear objetos validables
	@Autowired
	private CapitanService capitanService;
	
	@Autowired
	private EjercicioIndividualService ejercicioIndividualService;
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private EstadisticoService estadisticoService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private PersonalesService personalesService;
	
	@Autowired
	private PruebaCondicionFisicaService pruebaCondicionFisicaService;
	
	@Autowired
	private SustitucionService sustitucionService;
	
	/**
	 * Crea un capitan con todos sus campos rellenos con un valor válido
	 * 
	 * @return Capitan
	 */
	@Transactional(readOnly = true)
	protected Capitan getCapitanCorrecto() {
		Integer id = 1;
		Optional<Capitan> capitan = capitanService.findById(id);
		return capitan.get();
	}
	
	/**
	 * Crea un ejercicio individual con todos sus campos rellenos con un valor válido
	 * 
	 * @return EjercicioIndividual
	 */
	@Transactional(readOnly = true)
	protected EjercicioIndividual getEjercicioIndividualCorrecto() {
		Integer id = 1;
		Optional<EjercicioIndividual> ejercicio = ejercicioIndividualService.findById(id);
		return ejercicio.get();
	}
	
	/**
	 * Crea un entrenador con todos sus campos rellenos con un valor válido
	 * 
	 * @return Entrenador
	 */
	@Transactional(readOnly = true)
	protected Entrenador getEntrenadorCorrecto() {
		Integer id = 1;
		Optional<Entrenador> entrenador = entrenadorService.findById(id);
		return entrenador.get();
	}
	
	/**
	 * Crea un entrenamiento con todos sus campos rellenos con un valor válido
	 * 
	 * @return Entrenamiento
	 */
	@Transactional(readOnly = true)
	protected Entrenamiento getEntrenamientoCorrecto() {
		Integer id = 1;
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(id);
		return entrenamiento.get();
	}
	
	/**
	 * Crea un equipo con todos sus campos rellenos con un valor válido
	 * 
	 * @return Equipo
	 */
	@Transactional(readOnly = true)
	protected Equipo getEquipoCorrecto() {
		Integer id = 1;
		Optional<Equipo> equipo = equipoService.findById(id);
		return equipo.get();
	}
	
	/**
	 * Crea un estadistico con todos sus campos rellenos con un valor válido
	 * 
	 * @return Estadistico
	 */
	@Transactional(readOnly = true)
	protected Estadistico getEstadisticoCorrecto() {
		Integer id = 1;
		Optional<Estadistico> estadistico = estadisticoService.findById(id);
		return estadistico.get();
	}

	/**
	 * Crea un jugador con todos sus campos rellenos con un valor válido
	 * 
	 * @return Jugador
	 */
	@Transactional(readOnly = true)
	protected Jugador getJugadorCorrecto() {
		Integer id = 1;
		Optional<Jugador> jugador = jugadorService.findById(id);
		return jugador.get();
	}
	
	/**
	 * Crea un material con todos sus campos rellenos con un valor válido
	 * 
	 * @return Material
	 */
	@Transactional(readOnly = true)
	protected Material getMaterialCorrecto() {
		Integer id = 1;
		Optional<Material> material = materialService.findById(id);
		return material.get();
	}
	
	/**
	 * Crea un partido con todos sus campos rellenos con un valor válido
	 * 
	 * @return Partido
	 */
	@Transactional(readOnly = true)
	protected Partido getPartidoCorrecto() {
		Integer id = 1;
		Optional<Partido> partido = partidoService.findById(id);
		return partido.get();
	}
	
	/**
	 * Crea un vehículo personal con todos sus campos rellenos con un valor válido
	 * 
	 * @return Personales
	 */
	@Transactional(readOnly = true)
	protected Personales getPersonalesCorrecto() {
		Integer id = 1;
		Optional<Personales> personal = personalesService.findById(id);
		return personal.get();
	}
	
	/**
	 * Crea una prueba de condicion fisica con todos sus campos rellenos con un valor válido
	 * 
	 * @return PruebaCondicionFisica
	 */
	@Transactional(readOnly = true)
	protected PruebaCondicionFisica getPruebaCondicionFisicaCorrecto() {
		Integer id = 1;
		Optional<PruebaCondicionFisica> pruebaCondicionFisica = pruebaCondicionFisicaService.findById(id);
		return pruebaCondicionFisica.get();
	}
	
	/**
	 * Crea una sustitucion con todos sus campos rellenos con un valor válido
	 * 
	 * @return Sustitucion
	 */
	@Transactional(readOnly = true)
	protected Sustitucion getSustitucionCorrecto() {
		Integer id = 1;
		Optional<Sustitucion> sustitucion = sustitucionService.findById(id);
		return sustitucion.get();
	}
	
	
	protected String buildCadenaError(String field, String value, String mensaje) {
		return "Field error in object '' on field '" + field + "': rejected value [" + value + "]; codes [error." + field + ",error.java.lang.String,error]; arguments []; default message [" + mensaje +"]";
	}
	
}
