package org.springframework.samples.petclinic.web.base;

import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasDeUnJugadorStats;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesStats;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;

public class BaseConverterMethods extends BaseMockControllerTest {

	protected JugadorPartidoStats convertJugadorToJugadorPartidoStats(Jugador jugador) {

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

	public PartidoEdit convertPartidoToPartidoEdit(Partido partido) {

		PartidoEdit partidoEdit = new PartidoEdit();

		partidoEdit.setId(partido.getId());
		partidoEdit.setEquipo(partido.getEquipo().getCategoria());
		partidoEdit.setFecha(partido.getFecha().toString());
		partidoEdit.setHora(partido.getHora());
		return partidoEdit;
	}

	protected EstadisticasDeUnJugadorStats convertEstadisticasPersonalesToJugadorStats(
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

	protected EstadisticasPersonalesStats convertEstadisticasToEstadisticasStats(
			EstadisticaPersonalPartido estadistica) {
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

}
