package org.springframework.samples.petclinic.web.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.Posicion;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.User;
import org.springframework.validation.Errors;

public class BaseControllerTest extends BaseUserControllerTest {

	protected Jugador getJugadorCorrecto() {
		List<TipoAutorizacion> tiposAutorizaciones = new ArrayList<TipoAutorizacion>();
		tiposAutorizaciones.add(TipoAutorizacion.EXCURSIONES);
		tiposAutorizaciones.add(TipoAutorizacion.RESPONSABILIDADLESION);
		tiposAutorizaciones.add(TipoAutorizacion.TRANSPORTE);
		tiposAutorizaciones.add(TipoAutorizacion.USOIMAGEN);
		
		Jugador jugador = new Jugador();
		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador);
		
		Capitan capitan = getCapitanCorrecto(jugador);
		
		Entrenador entrenador = new Entrenador();
		
		Equipo equipo = new Equipo();
		equipo = getEquipoCorrecto(jugadores, capitan, entrenador);
		List<Equipo> equipos = new ArrayList<Equipo>();
		equipos.add(equipo);
		
		entrenador = getEntrenadorCorrecto(equipos);
		equipo.setEntrenador(entrenador);
		
		NumCamiseta numCamiseta = getNumCamisetaCorrecto(1, jugador, equipo);
		List<NumCamiseta> numCamisetas = new ArrayList<NumCamiseta>();
		numCamisetas.add(numCamiseta);
		
		Partido partido = getPartidoCorrecto(jugadores, equipo);
		List<Partido> partidos = new ArrayList<Partido>();
		partidos.add(partido);
		
		
		jugador.setId(1);
		jugador.setFirstName("Gonzalo");
		jugador.setLastName("Lallena");
		jugador.setUser(getUserJugador());
		jugador.setDni("11111111A");
		jugador.setDireccion("Callejón Lorem");
		jugador.setEmail("arribaespaña@gmail.com");
		jugador.setLocalidad("Sevilla");
		jugador.setFechaNacimiento(LocalDate.parse("28/11/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		jugador.setAltura(178);
		jugador.setPeso(78);
		jugador.setPesoIdeal(75);
		jugador.setImc(22.2);
		jugador.setPosicionPrincipal(Posicion.COLOCADOR);
		jugador.setPosicionSecundaria(Posicion.OPUESTO);
		jugador.setEstadoActual(Estado.EN_FORMA);
		jugador.setNumAmarillas(1);
		jugador.setNumFaltasTotales(8);
		jugador.setNumRojas(0);
		jugador.setAutorizacion(getAutorizacionesCorrectas(tiposAutorizaciones, jugador));
		jugador.setNumCamisetas(numCamisetas);
		jugador.setEquipos(equipos);
		jugador.setPartidos(partidos);
		
		return jugador;
	}
	
	protected Entrenador getEntrenadorCorrecto(List<Equipo> equipos) {
		Entrenador entrenador = new Entrenador(getUserEntrenador(), equipos, "messirve@gmail.com", LocalDate.parse("28/11/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		entrenador.setId(1);
		entrenador.setFirstName("Roberto");
		entrenador.setLastName("Velázquez");
		return entrenador;
	}
	
	protected Autorizacion getAutorizacionCorrecta(TipoAutorizacion tipo, Jugador jugador) {
		Autorizacion autorizacion = new Autorizacion();
		autorizacion.setJugador(jugador);
		autorizacion.setFecha(LocalDate.now());
		autorizacion.setTipoAutorizacion(tipo);
		return autorizacion;
	}
	
	protected List<Autorizacion> getAutorizacionesCorrectas(List<TipoAutorizacion> tipos, Jugador jugador) {
		List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();
		for(TipoAutorizacion tipo:tipos) {
			autorizaciones.add(getAutorizacionCorrecta(tipo, jugador));
		}
		
		if(autorizaciones.size() != 0) {
			return autorizaciones;
		} else {
			return null;
		}	
	}
	
	protected NumCamiseta getNumCamisetaCorrecto(Integer num, Jugador jugador, Equipo equipo) {
		NumCamiseta numCamiseta = new NumCamiseta(equipo, jugador, num);
		numCamiseta.setId(1);
		return numCamiseta;
	}
	
	protected Capitan getCapitanCorrecto(Jugador jugador) {
		Capitan capitan = new Capitan(jugador, 0, Actitud.POSITIVA);
		return capitan;
	}
	
	protected Equipo getEquipoCorrecto(List<Jugador> jugadores, Capitan capitan, Entrenador entrenador) {
		Equipo equipo = new Equipo();
		
		equipo.setId(1);
		equipo.setEntrenador(entrenador);
		equipo.setJugadores(jugadores);
		equipo.setCapitan(capitan);
		equipo.setCategoria("Senior");
		equipo.setSistemaJuego(Sistema.CINCO_UNO);
		equipo.setLiga("IMD");
		equipo.setFederacion(false);
		equipo.setNumFaltasTotales(8);
		equipo.setNumAmarillas(1);
		equipo.setNumRojas(0);
		
		return equipo;
	}
	
	protected Partido getPartidoCorrecto(List<Jugador> jugadores, Equipo equipo) {
		Partido partido = new Partido();
		
		partido.setId(1);
		partido.setFecha(LocalDate.now());
		partido.setHora("14:00");
		partido.setJugadores(jugadores);
		partido.setJugadoresJugando(jugadores);
		partido.setJugadorLibero(null);
		partido.setEquipo(equipo);
		
		return partido;
	}
	
	protected Estadistico getEstadisticoCorrecto() {
		Estadistico estadistico = new Estadistico();
		estadistico.setUser(getUserEstadistico());
		estadistico.setEmail("chucknorristopone@gmail.com");
		estadistico.setFechaNacimiento(LocalDate.parse("28/11/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		estadistico.setId(1);
		estadistico.setFirstName("Manolo");
		estadistico.setLastName("Rodríguez");
		
		return estadistico;
	}
	
	protected EstadisticaPersonalPartido getEstadisticaPersonalPartidoCorrecta(Jugador jugador, Partido partido, Estadistico estadistico) {
		EstadisticaPersonalPartido estadistica = new EstadisticaPersonalPartido();
		
		estadistica.setId(1);
		estadistica.setEstadistico(estadistico);
		estadistica.setJugador(jugador);
		estadistica.setPartido(partido);
		
		return estadistica;
	}

	@BeforeEach
	void setup() {
		
		Jugador jugador = getJugadorCorrecto();
		Equipo equipo = jugador.getEquipos().get(0);
		Entrenador entrenador = equipo.getEntrenador();
		Partido partido = jugador.getPartidos().get(0);
		Estadistico estadistico = getEstadisticoCorrecto();
		EstadisticaPersonalPartido estadisticaPersonalPartido = getEstadisticaPersonalPartidoCorrecta(jugador, partido, estadistico);
		
		//Invalidación de validators
		doNothing().when(entrenadorValidator).validate(any(Object.class), any(Errors.class));
		when(entrenadorValidator.supports(any(Class.class))).thenReturn(true);
		doNothing().when(partidoValidator).validate(any(Object.class), any(Errors.class));
		when(partidoValidator.supports(any(Class.class))).thenReturn(true);
		
		given(this.userService.findByUsername(any(String.class))).willReturn(getUserJugador());
		
		given(this.jugadorService.findById(any(Integer.class))).willReturn(Optional.of(jugador));
		given(this.jugadorService.findByUser(any(User.class))).willReturn(jugador);
		
		given(this.entrenadorService.findById(any(Integer.class))).willReturn(Optional.of(entrenador));
		given(this.entrenadorService.findByUser(any(User.class))).willReturn(entrenador);
		
		given(this.estadisticaPersonalPartidoService.findById(any(Integer.class))).willReturn(Optional.of(estadisticaPersonalPartido));
		given(this.estadisticaPersonalPartidoService.findByJugador(any(Integer.class))).willReturn(Lists.newArrayList(estadisticaPersonalPartido));
	}

}
