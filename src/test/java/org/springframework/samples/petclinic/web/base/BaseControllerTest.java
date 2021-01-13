package org.springframework.samples.petclinic.web.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

public class BaseControllerTest extends BaseUserControllerTest {

	protected static final Integer ID = 1;

	protected static final String CATEGORIA_EQUIPO = "Senior";

	protected static final String HORA = "14:00";

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

		jugador.setId(ID);
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
		Entrenador entrenador = new Entrenador(getUserEntrenador(), equipos, "messirve@gmail.com",
				LocalDate.parse("28/11/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		entrenador.setId(ID);
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
		for (TipoAutorizacion tipo : tipos) {
			autorizaciones.add(getAutorizacionCorrecta(tipo, jugador));
		}

		if (autorizaciones.size() != 0) {
			return autorizaciones;
		} else {
			return null;
		}
	}

	protected NumCamiseta getNumCamisetaCorrecto(Integer num, Jugador jugador, Equipo equipo) {
		NumCamiseta numCamiseta = new NumCamiseta(equipo, jugador, num);
		numCamiseta.setId(ID);
		return numCamiseta;
	}

	protected Capitan getCapitanCorrecto(Jugador jugador) {
		Capitan capitan = new Capitan(jugador, 0, Actitud.POSITIVA);
		capitan.setId(ID);
		return capitan;
	}

	protected Equipo getEquipoCorrecto(List<Jugador> jugadores, Capitan capitan, Entrenador entrenador) {
		Equipo equipo = new Equipo();

		equipo.setId(ID);
		equipo.setEntrenador(entrenador);
		equipo.setJugadores(jugadores);
		equipo.setCapitan(capitan);
		equipo.setCategoria(CATEGORIA_EQUIPO);
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

		partido.setId(ID);
		partido.setFecha(LocalDate.now());
		partido.setHora(HORA);
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
		estadistico.setId(ID);
		estadistico.setFirstName("Manolo");
		estadistico.setLastName("Rodríguez");

		return estadistico;
	}

	protected EstadisticaPersonalPartido getEstadisticaPersonalPartidoCorrecta(Jugador jugador, Partido partido,
			Estadistico estadistico) {
		EstadisticaPersonalPartido estadistica = new EstadisticaPersonalPartido();

		estadistica.setId(ID);
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
		EstadisticaPersonalPartido estadisticaPersonalPartido = getEstadisticaPersonalPartidoCorrecta(jugador, partido,
				estadistico);

		// Invalidación de validators
		doNothingValidators();

		// Servicios

		// UserService
		givenUserService(getUserJugador());

		// AuthoritiesService
		givenAuthoritiesService();

		// JugadorService
		givenJugadorService(jugador);

		// EntrenadorService
		givenEntrenadorService(entrenador);

		// EquipoService
		givenEquipoService(equipo);

		// PartidoService
		givenPartidoService(partido);

		// EstadisticaPersonalPartidoService
		givenEstadisticaPersonalPartidoService(estadisticaPersonalPartido);

		// Converters
		given(this.jugadorPartidoStatsConverter
				.convertJugadorToJugadorPartidoStats(any(Jugador.class)))
						.willReturn(convertJugadorToJugadorPartidoStats(jugador));
		given(this.partidoConverter
				.convertPartidoToPartidoEdit(any(Partido.class)))
						.willReturn(convertPartidoToPartidoEdit(partido));
		given(this.estadisticasConverter
				.convertEstadisticasPersonalesToJugadorStats(any(EstadisticaPersonalPartido.class)))
						.willReturn(convertEstadisticasPersonalesToJugadorStats(estadisticaPersonalPartido));
		given(this.estadisticasConverter.convertEstadisticasToEstadisticasStats(any(EstadisticaPersonalPartido.class)))
				.willReturn(convertEstadisticasToEstadisticasStats(estadisticaPersonalPartido));
	}

}
