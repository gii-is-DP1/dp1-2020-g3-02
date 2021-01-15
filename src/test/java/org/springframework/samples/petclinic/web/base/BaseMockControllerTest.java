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
import org.springframework.samples.petclinic.component.PartidoValidator;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.converter.JugadorPartidoStatsConverter;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.converter.ViajeConverter;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.PartidoService;
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
	protected JugadorService jugadorService;

	@MockBean
	protected EntrenadorService entrenadorService;

	@MockBean
	protected UserService userService;

	@MockBean
	protected AuthoritiesService authoritiesService;

	@MockBean
	protected ViajeService viajeService;

	@MockBean
	protected MaterialService materialService;

	// CONVERTERS

	@MockBean
	protected PartidoConverter partidoConverter;

	@MockBean
	protected JugadorPartidoStatsConverter jugadorPartidoStatsConverter;

	@MockBean
	protected DataPosicionConverter dataPosicionConverter;

	@MockBean
	protected EstadisticasConverter estadisticasConverter;

	@MockBean
	protected ViajeConverter viajeConverter;

	// VALIDATORS

	@MockBean
	protected EntrenadorValidator entrenadorValidator;

	@MockBean
	protected PartidoValidator partidoValidator;

	// Invalidación de validators
	protected void doNothingValidators() {
		doNothing().when(entrenadorValidator).validate(any(Object.class), any(Errors.class));
		when(entrenadorValidator.supports(any(Class.class))).thenReturn(true);

		doNothing().when(partidoValidator).validate(any(Object.class), any(Errors.class));
		when(partidoValidator.supports(any(Class.class))).thenReturn(true);
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
	}

	/** Metodos EntrenadorService por defecto */
	protected void givenEntrenadorService(Entrenador entrenador) {
		given(this.entrenadorService.findById(any(Integer.class))).willReturn(Optional.of(entrenador));
		given(this.entrenadorService.findByUser(any(User.class))).willReturn(entrenador);
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

	/** Metodos PartidoService por defecto */
	protected void givenViajeService(Viaje viaje) {
		given(this.viajeService.findById(any(Integer.class))).willReturn(Optional.of(viaje));
		given(this.viajeService.findByJugadorAndPartidoAndTipoViaje(any(Jugador.class), any(Partido.class),
				any(TipoViaje.class))).willReturn(viaje);
	}

}
