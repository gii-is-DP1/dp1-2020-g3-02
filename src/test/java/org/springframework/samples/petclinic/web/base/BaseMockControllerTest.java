package org.springframework.samples.petclinic.web.base;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.component.EntrenadorValidator;
import org.springframework.samples.petclinic.component.EntrenamientoValidator;
import org.springframework.samples.petclinic.component.EstadisticoValidator;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.component.PartidoValidator;
import org.springframework.samples.petclinic.component.PersonalesValidator;
import org.springframework.samples.petclinic.component.UserValidator;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.converter.EntrenamientoConverter;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.converter.JugadorPartidoStatsConverter;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.converter.PersonalConverter;
import org.springframework.samples.petclinic.converter.UserConverter;
import org.springframework.samples.petclinic.converter.ViajeConverter;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.SistemaJuegoService;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.impl.AuthoritiesService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.validation.Errors;

public class BaseMockControllerTest {

	// SERVICES

	@MockBean
	protected PartidoService partidoService;

	@MockBean
	protected EquipoService equipoService;

	@MockBean
	protected EstadisticaPersonalPartidoService estadisticaPersonalPartidoService;
	
	@MockBean
	protected EstadisticaPersonalEntrenamientoService estadisticaPersonalEntrenamientoService;

	@MockBean
	protected JugadorService jugadorService;

	@MockBean
	protected EntrenadorService entrenadorService;
	
	@MockBean
	protected EntrenamientoService entrenamientoService;

	@MockBean
	protected UserService userService;

	@MockBean
	protected AuthoritiesService authoritiesService;

	@MockBean
	protected ViajeService viajeService;
	
	@MockBean
	protected PersonalesService personalesService;

	@MockBean
	protected MaterialService materialService;
	
	@MockBean
	protected EstadisticoService estadisticoService;
	
	@MockBean
	protected NumCamisetaService numCamisetaService;
	
	@MockBean
	protected SustitucionService sustitucionService;
	
	@MockBean
	protected SistemaJuegoService sistemaService;

	// CONVERTERS

	@MockBean
	protected PartidoConverter partidoConverter;
	
	@MockBean
	protected EntrenamientoConverter entrenamientoConverter;

	@MockBean
	protected JugadorPartidoStatsConverter jugadorPartidoStatsConverter;

	@MockBean
	protected DataPosicionConverter dataPosicionConverter;

	@MockBean
	protected EstadisticasConverter estadisticasConverter;

	@MockBean
	protected ViajeConverter viajeConverter;
	
	@MockBean
	protected UserConverter userConverter;
	
	@MockBean
	protected PersonalConverter personalesConverter;
	
	@MockBean
	protected JugadorConverter jugadorConverter;

	// VALIDATORS
	
	@MockBean
	protected JugadorValidator jugadorValidator;

	@MockBean
	protected EntrenadorValidator entrenadorValidator;
	
	@MockBean
	protected EntrenamientoValidator entrenamientoValidator;

	@MockBean
	protected PartidoValidator partidoValidator;
	
	@MockBean
	protected PersonalesValidator personalesValidator;
	
	@MockBean

	protected EstadisticoValidator estadisticoValidator;
	@MockBean
	protected UserValidator userValidator;


	// Invalidación de validators
	protected void doNothingValidators() {
		doNothing().when(jugadorValidator).validate(any(Object.class), any(Errors.class));
		when(jugadorValidator.supports(any(Class.class))).thenReturn(true);
		
		doNothing().when(entrenadorValidator).validate(any(Object.class), any(Errors.class));
		when(entrenadorValidator.supports(any(Class.class))).thenReturn(true);

		doNothing().when(partidoValidator).validate(any(Object.class), any(Errors.class));
		when(partidoValidator.supports(any(Class.class))).thenReturn(true);
		
		doNothing().when(entrenamientoValidator).validate(any(Object.class), any(Errors.class));
		when(entrenamientoValidator.supports(any(Class.class))).thenReturn(true);
		
		doNothing().when(personalesValidator).validate(any(Object.class), any(Errors.class));
		when(personalesValidator.supports(any(Class.class))).thenReturn(true);
		

		doNothing().when(estadisticoValidator).validate(any(Object.class), any(Errors.class));
		when(estadisticoValidator.supports(any(Class.class))).thenReturn(true);

		doNothing().when(userValidator).validate(any(Object.class), any(Errors.class));
		when(userValidator.supports(any(Class.class))).thenReturn(true);

	}

	// Metodos Given

	/** Metodos UserService por defecto */
	protected void givenUserService(User user) {
		given(this.userService.findByUsername(any(String.class))).willReturn(user);
	}

	/** Metodos AuthoritiesService por defecto */
	protected void givenAuthoritiesService() {
		given(this.authoritiesService.hasAuthority(any(String.class), any(String.class))).willReturn(true);
	}

	/** Metodos JugadorService por defecto */
	protected void givenJugadorService(Jugador jugador) {
		given(this.jugadorService.findById(any(Integer.class))).willReturn(Optional.of(jugador));
		given(this.jugadorService.findByUser(any(User.class))).willReturn(jugador);
		given(this.jugadorService.findAll()).willReturn(Lists.newArrayList(jugador));
		given(this.jugadorService.findPrivilegio(any(TipoPrivilegio.class))).willReturn(Lists.newArrayList(jugador));
		given(this.jugadorService.findAuto(any(TipoAutorizacion.class))).willReturn(Lists.newArrayList(jugador));
		given(this.jugadorService.save(any(Jugador.class))).willReturn(jugador);
	}

	/** Metodos EntrenadorService por defecto */
	protected void givenEntrenadorService(Entrenador entrenador) {
		given(this.entrenadorService.findById(any(Integer.class))).willReturn(Optional.of(entrenador));
		given(this.entrenadorService.findByUser(any(User.class))).willReturn(entrenador);
	}
	
	/** Metodos EntrenamientoService por defecto */
	protected void givenEntrenamientoService(Entrenamiento entrenamiento) {
		given(this.entrenamientoService.findById(any(Integer.class))).willReturn(Optional.of(entrenamiento));
		given(this.entrenamientoService.findByFechaAfter(any(LocalDate.class))).willReturn(Lists.newArrayList(entrenamiento));
	}

	/** Metodos EquipoService por defecto */
	protected void givenEquipoService(Equipo equipo) {
		given(this.equipoService.findById(any(Integer.class))).willReturn(Optional.of(equipo));
		given(this.equipoService.findAll()).willReturn(Lists.newArrayList(equipo));
		given(this.equipoService.findByCategoria(any(String.class))).willReturn(equipo);
	}

	/** Metodos PartidoService por defecto */
	protected void givenPartidoService(Partido partido) {
		given(this.partidoService.findById(any(Integer.class))).willReturn(Optional.of(partido));
		given(this.partidoService.findByFechaAfter(any(LocalDate.class))).willReturn(Lists.newArrayList(partido));
	}

	/** Metodos EstadisticaPersonalPartidoService por defecto */
	protected void givenEstadisticaPersonalPartidoService(EstadisticaPersonalPartido estadisticaPersonalPartido) {
		given(this.estadisticaPersonalPartidoService.findById(any(Integer.class)))
				.willReturn(Optional.of(estadisticaPersonalPartido));
		given(this.estadisticaPersonalPartidoService.findByJugador(any(Integer.class)))
				.willReturn(Lists.newArrayList(estadisticaPersonalPartido));
		given(this.estadisticaPersonalPartidoService.findByPartido(any(Integer.class)))
				.willReturn(Lists.newArrayList(estadisticaPersonalPartido));
	}
	
	/** Metodos EstadisticaPersonalEntrenamientoService por defecto */
	protected void givenEstadisticaPersonalEntrenamientoService(EstadisticaPersonalEntrenamiento estadisticaPersonalEntrenamiento) {
		given(this.estadisticaPersonalEntrenamientoService.findById(any(Integer.class)))
				.willReturn(Optional.of(estadisticaPersonalEntrenamiento));
		given(this.estadisticaPersonalEntrenamientoService.findByJugador(any(Integer.class)))
				.willReturn(Lists.newArrayList(estadisticaPersonalEntrenamiento));
		given(this.estadisticaPersonalEntrenamientoService.findByEntrenamiento(any(Integer.class)))
				.willReturn(Lists.newArrayList(estadisticaPersonalEntrenamiento));
	}

	/** Metodos PartidoService por defecto */
	protected void givenViajeService(Viaje viaje) {
		given(this.viajeService.findById(any(Integer.class))).willReturn(Optional.of(viaje));
		given(this.viajeService.findByJugadorAndPartidoAndTipoViaje(any(Jugador.class), any(Partido.class),
				any(TipoViaje.class))).willReturn(viaje);
	}
	
	/** Metodos PersonalesService por defecto */
	protected void givenPersonalesService(Personales personales) {
		given(this.personalesService.findById(any(Integer.class))).willReturn(Optional.of(personales));
//		given(this.personalesService.findByJugador(any(Integer.class)));
	}
	/** Metodos EstadisticoService por defecto */
	protected void givenEstadisticoService(Estadistico estadistico) {
		given(this.estadisticoService.findById(any(Integer.class))).willReturn(Optional.of(estadistico));
		given(this.estadisticoService.findByFirstName(any(String.class))).willReturn(Lists.newArrayList(estadistico));
	}
}
