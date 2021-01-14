package org.springframework.samples.petclinic.web.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.model.auxiliares.DataAutorizacion;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.auxiliares.DataPruebaCondicion;
import org.springframework.samples.petclinic.model.auxiliares.EjercicioIndividualDTO;
import org.springframework.samples.petclinic.model.auxiliares.EntrenamientoConAsistencia;
import org.springframework.samples.petclinic.model.auxiliares.EquipoCAP;
import org.springframework.samples.petclinic.model.auxiliares.EquipoCategoria;
import org.springframework.samples.petclinic.model.auxiliares.EquipoTablaEquipos;
import org.springframework.samples.petclinic.model.auxiliares.JugadorAut;
import org.springframework.samples.petclinic.model.auxiliares.JugadorCAP;
import org.springframework.samples.petclinic.model.auxiliares.JugadorDTO;
import org.springframework.samples.petclinic.model.auxiliares.JugadorPartidoViaje;
import org.springframework.samples.petclinic.model.auxiliares.JugadorWithEquipo;
import org.springframework.samples.petclinic.model.auxiliares.JugadoresInEquipo;
import org.springframework.samples.petclinic.model.auxiliares.JugadoresInEquipoSinUser;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
import org.springframework.samples.petclinic.model.auxiliares.PartidoPuntos;
import org.springframework.samples.petclinic.model.auxiliares.PruebasSinJugador;
import org.springframework.samples.petclinic.model.auxiliares.RealizaEjercicioDTO;
import org.springframework.samples.petclinic.model.ediciones.EntrenamientoEdit;
import org.springframework.samples.petclinic.model.ediciones.EquipoEdit;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;
import org.springframework.samples.petclinic.model.ediciones.JugadorEditNumCamiseta;
import org.springframework.samples.petclinic.model.ediciones.MaterialDTO;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.EntrenamientoStats;
import org.springframework.samples.petclinic.model.estadisticas.EquipoStats;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasDeUnJugadorStats;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesStats;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.samples.petclinic.model.estadisticas.PartidoStats;

public class BaseConverterMethods extends BaseMockControllerTest {

	public DataPosicion convertPartidoToPartidoStats(List<JugadorPartidoStats> jugadores) {

		DataPosicion stats = new DataPosicion();
		stats.setData(jugadores);

		return stats;
	}

	public DataPruebaCondicion convertPruebaToDataPrueba(List<PruebasSinJugador> pruebas) {

		DataPruebaCondicion data = new DataPruebaCondicion();
		data.setData(pruebas);

		return data;
	}

	public EjercicioIndividualDTO converterEntityToDTO(EjercicioIndividual entity) {
		return new EjercicioIndividualDTO(entity.getId(), entity.getTipoEjercicio(), entity.getNombre(),
				entity.getDescripcion());
	}

	public List<EjercicioIndividualDTO> converListEntityToListDTO(List<EjercicioIndividual> entities) {
		List<EjercicioIndividualDTO> dtos = new ArrayList<EjercicioIndividualDTO>();

		for (int i = 0; i < entities.size(); i++) {
			EjercicioIndividual entity = entities.get(i);
			EjercicioIndividualDTO dto = converterEntityToDTO(entity);
			dtos.add(dto);
		}

		return dtos;

	}

	public EntrenamientoStats convertEntrenamientoToEntrenamientoStats(Entrenamiento entrenamiento) {

		return new EntrenamientoStats(entrenamiento.getId(), entrenamiento.getFecha(), entrenamiento.getHora(),
				entrenamiento.getEquipo().getCategoria(), entrenamiento.getSaquesAcertados(),
				entrenamiento.getSaquesTotales(), entrenamiento.getPorcentajeSaques(),
				entrenamiento.getRecepcionesAcertadas(), entrenamiento.getRecepcionesTotales(),
				entrenamiento.getPorcentajeRecepciones(), entrenamiento.getColocacionesAcertadas(),
				entrenamiento.getColocacionesTotales(), entrenamiento.getPorcentajeColocaciones(),
				entrenamiento.getDefensasAcertadas(), entrenamiento.getDefensasTotales(),
				entrenamiento.getPorcentajeDefensas(), entrenamiento.getBloqueosAcertados(),
				entrenamiento.getBloqueosTotales(), entrenamiento.getPorcentajeBloqueos(),
				entrenamiento.getRematesAcertados(), entrenamiento.getRematesTotales(),
				entrenamiento.getPorcentajeRemates(), entrenamiento.getFintasAcertadas(),
				entrenamiento.getFintasTotales(), entrenamiento.getPorcentajeFintas(),
				entrenamiento.getNumAtaquesRapidosAcertados(), entrenamiento.getNumAtaquesRapidosTotales(),
				entrenamiento.getPorcentajeAtaquesRapidos());
	}

	public EntrenamientoConAsistencia convertEntrenamientoToEntrenamientoConAsistencia(Entrenamiento entrenamiento) {

		EntrenamientoConAsistencia entrenamientoConAsistencia = new EntrenamientoConAsistencia();

		entrenamientoConAsistencia.setId(entrenamiento.getId());
		entrenamientoConAsistencia.setEquipo(entrenamiento.getEquipo().getCategoria());
		entrenamientoConAsistencia.setFecha(entrenamiento.getFecha().toString());
		entrenamientoConAsistencia.setHora(entrenamiento.getHora());
		entrenamientoConAsistencia
				.setAsistencia(entrenamiento.getJugadores().stream().map(x -> x.getId()).collect(Collectors.toList()));
		return entrenamientoConAsistencia;
	}

	public EntrenamientoEdit convertEntrenamientoToEntrenamientoEdit(Entrenamiento entrenamiento) {

		EntrenamientoEdit entrenamientoEdit = new EntrenamientoEdit();

		entrenamientoEdit.setId(entrenamiento.getId());
		entrenamientoEdit.setEquipo(entrenamiento.getEquipo().getCategoria());
		entrenamientoEdit.setFecha(entrenamiento.getFecha().toString());
		entrenamientoEdit.setHora(entrenamiento.getHora());
		return entrenamientoEdit;
	}

	public EquipoStats convertEquipoToEquipoStats(Equipo equipo) {
		return new EquipoStats(equipo.getId(), equipo.getCategoria(), equipo.getSaquesAcertados(),
				equipo.getSaquesTotales(), equipo.getPorcentajeSaques(), equipo.getRecepcionesAcertadas(),
				equipo.getRecepcionesTotales(), equipo.getPorcentajeRecepciones(), equipo.getColocacionesAcertadas(),
				equipo.getColocacionesTotales(), equipo.getPorcentajeColocaciones(), equipo.getDefensasAcertadas(),
				equipo.getDefensasTotales(), equipo.getPorcentajeDefensas(), equipo.getBloqueosAcertados(),
				equipo.getBloqueosTotales(), equipo.getPorcentajeBloqueos(), equipo.getRematesAcertados(),
				equipo.getRematesTotales(), equipo.getPorcentajeRemates(), equipo.getFintasAcertadas(),
				equipo.getFintasTotales(), equipo.getPorcentajeFintas(), equipo.getNumAtaquesRapidosAcertados(),
				equipo.getNumAtaquesRapidosTotales(), equipo.getPorcentajeAtaquesRapidos());
	}

	public EquipoTablaEquipos convertEquipoToEquipoTablaEquipo(Equipo equipo) {
		String fed;
		if (equipo.getFederacion()) {
			fed = "Federado";
		} else {
			fed = "No federado";
		}
		return new EquipoTablaEquipos(equipo.getId(), equipo.getSistemaJuego(), equipo.getCategoria(),
				equipo.getNumAmarillas(), equipo.getNumRojas(), equipo.getLiga(), fed);
	}

	public EquipoEdit convertEquipoToEquipoEdit(Equipo equipo) {
		return new EquipoEdit(equipo.getId(), equipo.getCategoria(), equipo.getSistemaJuego(), equipo.getLiga(),
				equipo.getFederacion());
	}

	public EquipoCategoria convertEquipoToEquipoCategoria(Equipo equipo) {
		return new EquipoCategoria(equipo.getId(), equipo.getCategoria());
	}

	public EquipoCAP convertEquipoToEquipoCAP(Equipo equipo, List<JugadorCAP> jugadores) {
		return new EquipoCAP(equipo.getId(), equipo.getCategoria(), jugadores);
	}

	public EstadisticasPersonalesStats convertEstadisticasToEstadisticasStats(EstadisticaPersonalPartido estadistica) {
		return new EstadisticasPersonalesStats(estadistica.getJugador().getId(),
				estadistica.getJugador().getFirstName() + ", " + estadistica.getJugador().getLastName(),
				estadistica.getPartido().getFecha(), estadistica.getSaquesAcertados(), estadistica.getSaquesTotales(),
				estadistica.getPorcentajeSaques(), estadistica.getRecepcionesAcertadas(),
				estadistica.getRecepcionesTotales(), estadistica.getPorcentajeRecepciones(),
				estadistica.getColocacionesAcertadas(), estadistica.getColocacionesTotales(),
				estadistica.getPorcentajeColocaciones(), estadistica.getDefensasAcertadas(),
				estadistica.getDefensasTotales(), estadistica.getPorcentajeDefensas(),
				estadistica.getBloqueosAcertados(), estadistica.getBloqueosTotales(),
				estadistica.getPorcentajeBloqueos(), estadistica.getRematesAcertados(), estadistica.getRematesTotales(),
				estadistica.getPorcentajeRemates(), estadistica.getFintasAcertadas(), estadistica.getFintasTotales(),
				estadistica.getPorcentajeFintas(), estadistica.getNumAtaquesRapidosAcertados(),
				estadistica.getNumAtaquesRapidosTotales(), estadistica.getPorcentajeAtaquesRapidos(),
				estadistica.getPartido().getId());
	}

	public EstadisticasDeUnJugadorStats convertEstadisticasPersonalesToJugadorStats(
			EstadisticaPersonalPartido estadisiticas) {

		return new EstadisticasDeUnJugadorStats(estadisiticas.getId(), estadisiticas.getPartido().getFecha(),
				estadisiticas.getPartido().getHora(), estadisiticas.getSaquesAcertados(),
				estadisiticas.getSaquesTotales(), estadisiticas.getPorcentajeSaques(),
				estadisiticas.getRecepcionesAcertadas(), estadisiticas.getRecepcionesTotales(),
				estadisiticas.getPorcentajeRecepciones(), estadisiticas.getColocacionesAcertadas(),
				estadisiticas.getColocacionesTotales(), estadisiticas.getPorcentajeColocaciones(),
				estadisiticas.getDefensasAcertadas(), estadisiticas.getDefensasTotales(),
				estadisiticas.getPorcentajeDefensas(), estadisiticas.getBloqueosAcertados(),
				estadisiticas.getBloqueosTotales(), estadisiticas.getPorcentajeBloqueos(),
				estadisiticas.getRematesAcertados(), estadisiticas.getRematesTotales(),
				estadisiticas.getPorcentajeRemates(), estadisiticas.getFintasAcertadas(),
				estadisiticas.getFintasTotales(), estadisiticas.getPorcentajeFintas(),
				estadisiticas.getNumAtaquesRapidosAcertados(), estadisiticas.getNumAtaquesRapidosTotales(),
				estadisiticas.getPorcentajeAtaquesRapidos());
	}

	public EstadisticasDeUnJugadorStats convertEstadisticasPersonalesToJugadorStats(
			EstadisticaPersonalEntrenamiento estadisiticas) {

		return new EstadisticasDeUnJugadorStats(estadisiticas.getId(), estadisiticas.getEntrenamiento().getFecha(),
				estadisiticas.getEntrenamiento().getHora(), estadisiticas.getSaquesAcertados(),
				estadisiticas.getSaquesTotales(), estadisiticas.getPorcentajeSaques(),
				estadisiticas.getRecepcionesAcertadas(), estadisiticas.getRecepcionesTotales(),
				estadisiticas.getPorcentajeRecepciones(), estadisiticas.getColocacionesAcertadas(),
				estadisiticas.getColocacionesTotales(), estadisiticas.getPorcentajeColocaciones(),
				estadisiticas.getDefensasAcertadas(), estadisiticas.getDefensasTotales(),
				estadisiticas.getPorcentajeDefensas(), estadisiticas.getBloqueosAcertados(),
				estadisiticas.getBloqueosTotales(), estadisiticas.getPorcentajeBloqueos(),
				estadisiticas.getRematesAcertados(), estadisiticas.getRematesTotales(),
				estadisiticas.getPorcentajeRemates(), estadisiticas.getFintasAcertadas(),
				estadisiticas.getFintasTotales(), estadisiticas.getPorcentajeFintas(),
				estadisiticas.getNumAtaquesRapidosAcertados(), estadisiticas.getNumAtaquesRapidosTotales(),
				estadisiticas.getPorcentajeAtaquesRapidos());
	}

	public JugadorDTO convertParcialJugadorToJugadorDTO(Jugador jugador) {
		return new JugadorDTO(jugador.getId(), jugador.getFirstName(), jugador.getLastName(), null, jugador.getDni(),
				jugador.getDireccion(), jugador.getEmail(), jugador.getLocalidad(), jugador.getFechaNacimiento(),
				jugador.getAltura(), jugador.getPeso(), jugador.getPesoIdeal(), jugador.getImc(),
				jugador.getPosicionPrincipal(), jugador.getPosicionSecundaria(), jugador.getEstadoActual());
	}

	public List<JugadorDTO> convertParcialListJugadorToListJugadorDTO(List<Jugador> jugadores) {
		List<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
		for (int i = 0; i < jugadores.size(); i++) {
			JugadorDTO jugadorDTO = convertParcialJugadorToJugadorDTO(jugadores.get(i));
			jugadoresDTO.add(jugadorDTO);
		}
		return jugadoresDTO;
	}

	public JugadorStats convertJugadorToJugadorStats(Jugador jugador) {
		return new JugadorStats(jugador.getId(), jugador.getFirstName(), jugador.getLastName(),
				jugador.getSaquesAcertados(), jugador.getSaquesTotales(), jugador.getPorcentajeSaques(),
				jugador.getRecepcionesAcertadas(), jugador.getRecepcionesTotales(), jugador.getPorcentajeRecepciones(),
				jugador.getColocacionesAcertadas(), jugador.getColocacionesTotales(),
				jugador.getPorcentajeColocaciones(), jugador.getDefensasAcertadas(), jugador.getDefensasTotales(),
				jugador.getPorcentajeDefensas(), jugador.getBloqueosAcertados(), jugador.getBloqueosTotales(),
				jugador.getPorcentajeBloqueos(), jugador.getRematesAcertados(), jugador.getRematesTotales(),
				jugador.getPorcentajeRemates(), jugador.getFintasAcertadas(), jugador.getFintasTotales(),
				jugador.getPorcentajeFintas(), jugador.getNumAtaquesRapidosAcertados(),
				jugador.getNumAtaquesRapidosTotales(), jugador.getPorcentajeAtaquesRapidos());
	}

	public JugadorEdit convertJugadorToJugadorEdit(Jugador jugador) {
		return new JugadorEdit(jugador.getId(), jugador.getFirstName(), jugador.getLastName(), jugador.getDni(),
				jugador.getDireccion(), jugador.getEmail(), jugador.getLocalidad(), jugador.getFechaNacimiento(),
				jugador.getAltura(), jugador.getPeso(), jugador.getPosicionPrincipal(), jugador.getPosicionSecundaria(),
				jugador.getEstadoActual());
	}

	public JugadorEditNumCamiseta convertJugadorToJugadorEditNumCamiseta(Jugador jugador, int numCamiseta) {
		return new JugadorEditNumCamiseta(jugador.getId(), jugador.getFirstName(), jugador.getLastName(),
				jugador.getDni(), jugador.getDireccion(), jugador.getEmail(), jugador.getLocalidad(),
				jugador.getFechaNacimiento(), jugador.getAltura(), jugador.getPeso(), jugador.getPosicionPrincipal(),
				jugador.getPosicionSecundaria(), jugador.getEstadoActual(), numCamiseta);
	}

	public List<JugadorAut> convertListJugadorToListJugadorAut(List<Jugador> listajug) {
		List<JugadorAut> listafin = new ArrayList<JugadorAut>();
		for (int i = 0; i < listajug.size(); i++) {
			List<TipoAutorizacion> listaut = new ArrayList<TipoAutorizacion>();
			for (int j = 0; j < listajug.get(i).getAutorizacion().size(); j++) {
				listaut.add(listajug.get(i).getAutorizacion().get(j).getTipoAutorizacion());
			}
			;
			JugadorAut jugador = new JugadorAut(listajug.get(i).getId(), listajug.get(i).getFirstName(),
					listajug.get(i).getLastName(), listaut);
			listafin.add(jugador);

		}
		return listafin;
	}

	public List<JugadorWithEquipo> convertListJugadorToListJugadorWithEquipo(List<Jugador> listajugadores) {
		List<JugadorWithEquipo> listaJugadorWithEquipos = new ArrayList<JugadorWithEquipo>();
		for (int i = 0; i < listajugadores.size(); i++) {
			List<String> equipos = new ArrayList<String>();
			for (int j = 0; j < listajugadores.get(i).getEquipos().size(); j++) {
				equipos.add(listajugadores.get(i).getEquipos().get(j).getCategoria());
			}
			;
			JugadorWithEquipo jugadorWithEquipo = new JugadorWithEquipo(listajugadores.get(i).getId(),
					listajugadores.get(i).getFirstName(), listajugadores.get(i).getLastName(),
					listajugadores.get(i).getDni(), listajugadores.get(i).getDireccion(),
					listajugadores.get(i).getEmail(), listajugadores.get(i).getLocalidad(),
					listajugadores.get(i).getFechaNacimiento(), listajugadores.get(i).getAltura(),
					listajugadores.get(i).getPeso(), listajugadores.get(i).getPosicionPrincipal(),
					listajugadores.get(i).getPosicionSecundaria(), listajugadores.get(i).getEstadoActual(), equipos,
					listajugadores.get(i).getUser());

			listaJugadorWithEquipos.add(jugadorWithEquipo);

		}
		return listaJugadorWithEquipos;
	}

	public JugadoresInEquipo convertJugadorToJugadorInEquipo(Jugador jugador) {

		JugadoresInEquipo jugadorInEquipo = new JugadoresInEquipo(jugador.getId(), jugador.getFirstName(),
				jugador.getLastName(), jugador.getAltura(), jugador.getPeso(), jugador.getPesoIdeal(),
				jugador.getPosicionPrincipal(), jugador.getPosicionSecundaria(), jugador.getImc(), jugador.getUser());

		return jugadorInEquipo;

	}

	public List<JugadoresInEquipo> convertListJugadorToListJugadorInEquipo(List<Jugador> listajugadores) {
		List<JugadoresInEquipo> listaJugadoresInEquipo = new ArrayList<JugadoresInEquipo>();
		for (int i = 0; i < listajugadores.size(); i++) {

			listaJugadoresInEquipo.add(convertJugadorToJugadorInEquipo(listajugadores.get(i)));

		}
		return listaJugadoresInEquipo;
	}

	public JugadoresInEquipoSinUser convertJugadorToJugadorInEquipoSinUser(Jugador jugador, int numCamiseta) {

		JugadoresInEquipoSinUser jugadorInEquipo = new JugadoresInEquipoSinUser(jugador.getId(), jugador.getFirstName(),
				jugador.getLastName(), jugador.getAltura(), jugador.getPeso(), jugador.getPesoIdeal(),
				jugador.getPosicionPrincipal(), jugador.getPosicionSecundaria(), jugador.getImc(), jugador.getEmail(),
				numCamiseta);

		return jugadorInEquipo;

	}

	public List<JugadoresInEquipoSinUser> convertListJugadorToListJugadorInEquipoSinUser(List<Jugador> listajugadores,
			List<Integer> numerosCamiseta) {
		List<JugadoresInEquipoSinUser> listaJugadoresInEquipo = new ArrayList<JugadoresInEquipoSinUser>();
		for (int i = 0; i < listajugadores.size(); i++) {

			listaJugadoresInEquipo
					.add(convertJugadorToJugadorInEquipoSinUser(listajugadores.get(i), numerosCamiseta.get(i)));

		}
		return listaJugadoresInEquipo;
	}

	public DataAutorizacion convertListJugadoresAutorizaciones(List<JugadorAut> jugadores) {

		DataAutorizacion dataaut = new DataAutorizacion();

		dataaut.setData(jugadores);

		return dataaut;
	}

	public JugadorCAP convertJugadorToJugadorCAP(Jugador j) {
		String nombre = j.getFirstName() + " " + j.getLastName();
		return new JugadorCAP(j.getId(), nombre);
	}

	public JugadorPartidoStats convertJugadorToJugadorPartidoStats(Jugador jugador) {

		JugadorPartidoStats partidoStats = new JugadorPartidoStats();

		partidoStats.setFirstName(jugador.getFirstName());
		partidoStats.setLastName(jugador.getLastName());
		partidoStats.setPrincipal(jugador.getPosicionPrincipal());
		partidoStats.setSecundaria(jugador.getPosicionSecundaria());
		partidoStats.setPorcentajeSaques(jugador.getPorcentajeSaques());
		partidoStats.setPorcentajeRecepciones(jugador.getPorcentajeRecepciones());
		partidoStats.setPorcentajeColocaciones(jugador.getPorcentajeColocaciones());
		partidoStats.setPorcentajeRemates(jugador.getPorcentajeRemates());
		partidoStats.setPorcentajeBloqueos(jugador.getPorcentajeBloqueos());
		partidoStats.setPorcentajeFintas(jugador.getPorcentajeFintas());
		partidoStats.setPorcentajeDefensas(jugador.getPorcentajeDefensas());
		partidoStats.setPorcentajeAtaquesRapidos(jugador.getPorcentajeAtaquesRapidos());

		return partidoStats;
	}

	public MaterialDTO convertMaterialToMaterialDTO(Material material) {
		return new MaterialDTO(material.getId(), material.getDescripcion(), material.getTipo(), material.getStock(),
				material.getEstado()

		);
	}

	public List<MaterialDTO> convertListEntityToListDTO(List<Material> materiales) {
		List<MaterialDTO> dtos = new ArrayList<MaterialDTO>();

		for (Material material : materiales) {
			MaterialDTO dto = new MaterialDTO(material.getId(), material.getDescripcion(), material.getTipo(),
					material.getStock(), material.getEstado());
			dtos.add(dto);
		}

		return dtos;

	}

	public PartidoEdit convertPartidoToPartidoEdit(Partido partido) {

		PartidoEdit partidoEdit = new PartidoEdit();

		partidoEdit.setId(partido.getId());
		partidoEdit.setEquipo(partido.getEquipo().getCategoria());
		partidoEdit.setFecha(partido.getFecha().toString());
		partidoEdit.setHora(partido.getHora());
		return partidoEdit;
	}

	public PartidoConAsistencia convertPartidoToPartidoConAsistencia(Partido partido) {

		PartidoConAsistencia partidoConAsistencia = new PartidoConAsistencia();

		partidoConAsistencia.setId(partido.getId());
		partidoConAsistencia.setEquipo(partido.getEquipo().getCategoria());
		partidoConAsistencia.setFecha(partido.getFecha().toString());
		partidoConAsistencia.setHora(partido.getHora());
		partidoConAsistencia
				.setAsistencia(partido.getJugadores().stream().map(x -> x.getId()).collect(Collectors.toList()));
		return partidoConAsistencia;
	}

	public PartidoStats convertPartidoToPartidoStats(Partido partido) {

		return new PartidoStats(partido.getId(), partido.getFecha(), partido.getHora(),
				partido.getEquipo().getCategoria(), partido.getSaquesAcertados(), partido.getSaquesTotales(),
				partido.getPorcentajeSaques(), partido.getRecepcionesAcertadas(), partido.getRecepcionesTotales(),
				partido.getPorcentajeRecepciones(), partido.getColocacionesAcertadas(),
				partido.getColocacionesTotales(), partido.getPorcentajeColocaciones(), partido.getDefensasAcertadas(),
				partido.getDefensasTotales(), partido.getPorcentajeDefensas(), partido.getBloqueosAcertados(),
				partido.getBloqueosTotales(), partido.getPorcentajeBloqueos(), partido.getRematesAcertados(),
				partido.getRematesTotales(), partido.getPorcentajeRemates(), partido.getFintasAcertadas(),
				partido.getFintasTotales(), partido.getPorcentajeFintas(), partido.getNumAtaquesRapidosAcertados(),
				partido.getNumAtaquesRapidosTotales(), partido.getPorcentajeAtaquesRapidos(), partido.getTiempo51(),
				partido.getTiempo42(), partido.getTiempo62());
	}

	public PartidoPuntos convertPartidoToPartidoPuntos(Partido partido) {
		return new PartidoPuntos(partido.getFecha(), partido.getNumPuntosSet1(), partido.getNumPuntosSet2(),
				partido.getNumPuntosSet3(), partido.getNumPuntosSet4(), partido.getNumPuntosSet5(),
				partido.getNumPuntosTotales());
	}

	public List<PartidoPuntos> convertListPartidoToListPartidoPuntos(List<Partido> partidos) {
		List<PartidoPuntos> partidospuntos = new ArrayList<PartidoPuntos>();
		for (int i = 0; i < partidos.size(); i++) {
			partidospuntos.add(convertPartidoToPartidoPuntos(partidos.get(i)));
		}
		return partidospuntos;
	}

	public PruebasSinJugador convertPruebaToPruebaSinJugador(PruebaCondicionFisica prueba) {

		PruebasSinJugador pruebaSinJugador = new PruebasSinJugador();

		pruebaSinJugador.setId(prueba.getId());
		pruebaSinJugador.setDato(prueba.getDato());
		pruebaSinJugador.setFecha(prueba.getFecha().toString());
		pruebaSinJugador.setTipoPrueba(prueba.getTipoPrueba());

		return pruebaSinJugador;
	}

	public RealizaEjercicioDTO converterEntityToDTO(RealizaEjercicio entity) {
		return new RealizaEjercicioDTO(entity.getJugador().getFirstName() + " " + entity.getJugador().getLastName(),
				entity.getJugador().getPosicionPrincipal(), entity.getEjercicioIndividual().getNombre(),
				entity.getFecha().getDayOfMonth() + "/" + entity.getFecha().getMonthValue() + "/"
						+ entity.getFecha().getYear());
	}

	public List<RealizaEjercicioDTO> converListEntityToListDTORealizaEjercicio(List<RealizaEjercicio> entities) {
		List<RealizaEjercicioDTO> dtos = new ArrayList<RealizaEjercicioDTO>();

		for (int i = 0; i < entities.size(); i++) {
			RealizaEjercicio entity = entities.get(i);
			RealizaEjercicioDTO dto = converterEntityToDTO(entity);
			dtos.add(dto);
		}

		return dtos;

	}

	public JugadorPartidoViaje convertViajeToJugadorPartidoViaje(Viaje viaje) {
		JugadorPartidoViaje jugadorPartidoViaje = new JugadorPartidoViaje();

		jugadorPartidoViaje.setViajeId(viaje.getId());
		jugadorPartidoViaje.setFecha(viaje.getPartido().getFecha());
		jugadorPartidoViaje.setHora(viaje.getPartido().getHora());
		jugadorPartidoViaje.setHaLlegado(viaje.isHaLlegado());
		jugadorPartidoViaje.setNombre(viaje.getJugador().getFirstName() + ", " + viaje.getJugador().getLastName());
		if (viaje.getJugador().getNumCamisetas().stream()
				.filter(x -> x.getEquipo().equals(viaje.getPartido().getEquipo())).map(x -> x.getNumero())
				.collect(Collectors.toList()).size() > 0) {
			jugadorPartidoViaje.setNumCamiseta(viaje.getJugador().getNumCamisetas().stream()
					.filter(x -> x.getEquipo().equals(viaje.getPartido().getEquipo())).map(x -> x.getNumero())
					.collect(Collectors.toList()).get(0));
		}

		if (viaje.getAutobus() != null) {
			jugadorPartidoViaje.setAutobusId(viaje.getAutobus().getId());
		}

		if (viaje.getPersonal() != null) {
			jugadorPartidoViaje.setPropietario(viaje.getPersonal().getPropietario());
		} else {
			jugadorPartidoViaje.setPropietario(null);
		}
		return jugadorPartidoViaje;
	}
}
