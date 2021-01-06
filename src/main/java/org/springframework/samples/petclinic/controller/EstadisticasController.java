package org.springframework.samples.petclinic.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.JugadorDTO;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesStats;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/estadisticas")
public class EstadisticasController {
	
	@Autowired
	private JugadorConverter jugadorConverter;
	
	@Autowired
	private NumCamisetaService numCamisetaService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private SustitucionService sustitucionService;
	
	@Autowired
	private EstadisticasConverter estadisticasConverter;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisticaPersonalPartidoService;
	
	private static final Log LOG = LogFactory.getLog(EstadisticasController.class);
	
	@GetMapping("/estadisticasPartidoForm/{partidoId}")
	public String formularioEstadisticasPartido(@PathVariable("partidoId") int partidoId, Model model) {
		
		model.addAttribute("partido", partidoService.findById(partidoId).get());
		return ViewConstant.VIEW_ESTADISTICAS_PARTIDO_FORM;
	}
	
	@RequestMapping(value = "/tablaIntroducirEstadisticas/{partidoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadorDTO>> tablaIntroducirEstadisticas(@PathVariable("partidoId") int partidoId) {
		try {
			Partido partido = partidoService.findById(partidoId).get();
			List<Jugador> jugadores = partido.getJugadoresJugando();
			
			List<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
			for(Jugador jugador:jugadores) {
				JugadorDTO jugadorDTO = new JugadorDTO();
				jugadorDTO = jugadorConverter.convertParcialJugadorToJugadorDTO(jugador);
				jugadorDTO = obtenerDatosEstadisticosJugador(jugadorDTO, jugador.getId(), partidoId);
				jugadorDTO.setNumCamiseta(numCamisetaService.findByEquipoAndJugador(partido.getEquipo().getId(), jugador.getId()).getNumero());
				
				jugadoresDTO.add(jugadorDTO);
			}
			
			DataTableResponse<JugadorDTO> data = new DataTableResponse<JugadorDTO>(jugadoresDTO);
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/obtenerEstadisticasJugadores/{partidoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstadisticasPersonalesStats>> obtenerEstadisticasJugadores(@PathVariable("partidoId") int partidoId) {
		try {
			
			List<EstadisticaPersonalPartido> estadisticasPersonalesPartidos = estadisticaPersonalPartidoService.findByPartido(partidoId);
			
			List<EstadisticasPersonalesStats> estadisticasPersonalesStats = new ArrayList<EstadisticasPersonalesStats>();
			
			
			for (int i = 0; i < estadisticasPersonalesPartidos.size();i++) {
				EstadisticasPersonalesStats estadisticaPersonalesStats = estadisticasConverter.convertEstadisticasToEstadisticasStats(estadisticasPersonalesPartidos.get(i));
				estadisticasPersonalesStats.add(estadisticaPersonalesStats);
			}
			
			return new ResponseEntity<List<EstadisticasPersonalesStats>>(estadisticasPersonalesStats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<EstadisticasPersonalesStats>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "rellenarDatos", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity rellenarDatos(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			
			Partido partido = partidoService.findById(partidoId).get();
			List<Jugador> jugadores = partido.getJugadores();
			
			Enumeration<String> parameters = request.getParameterNames();
			
			while (parameters.hasMoreElements()) {
				
				String clave = parameters.nextElement();
				
				if(!clave.equals("partidoId")) {
					String accion = clave.split(",")[0];
					int jugadorId = Integer.parseInt(clave.split(",")[1]);
					Jugador jugador = jugadorService.findById(jugadorId).get();
					
					Integer dato = Integer.parseInt(request.getParameter(clave));
					if (dato < 0) {
						return new ResponseEntity(HttpStatus.BAD_REQUEST);
					}
					EstadisticaPersonalPartido estadisticas = estadisticaPersonalPartidoService.findByJugadorAndPartido(jugadorId, partidoId);
					if(estadisticas == null) {
						estadisticas = new EstadisticaPersonalPartido();
						estadisticas.setPartido(partido);
						estadisticas.setJugador(jugador);
					}
					
					
					if(accion.startsWith("a")) {
						if(accion.contains("saque")) {
							estadisticas.setSaquesTotales(estadisticas.getSaquesTotales()+dato-estadisticas.getSaquesAcertados());
							estadisticas.setSaquesAcertados(dato);
						}else if(accion.contains("recepcion")){
							estadisticas.setRecepcionesTotales(estadisticas.getRecepcionesTotales()+dato-estadisticas.getRecepcionesAcertadas());
							estadisticas.setRecepcionesAcertadas(dato);
						}else if(accion.contains("colocacion")){
							estadisticas.setColocacionesTotales(estadisticas.getColocacionesTotales()+dato-estadisticas.getColocacionesAcertadas());
							estadisticas.setColocacionesAcertadas(dato);
						}else if(accion.contains("defensa")){
							estadisticas.setDefensasTotales(estadisticas.getDefensasTotales()+dato-estadisticas.getDefensasAcertadas());
							estadisticas.setDefensasAcertadas(dato);
						}else if(accion.contains("bloqueo")){
							estadisticas.setBloqueosTotales(estadisticas.getBloqueosTotales()+dato-estadisticas.getBloqueosAcertados());
							estadisticas.setBloqueosAcertados(dato);
						}else if(accion.contains("remate")){
							estadisticas.setRematesTotales(estadisticas.getRematesTotales()+dato-estadisticas.getRematesAcertados());
							estadisticas.setRematesAcertados(dato);
						}else if(accion.contains("finta")){
							estadisticas.setFintasTotales(estadisticas.getFintasTotales()+dato-estadisticas.getFintasAcertadas());
							estadisticas.setFintasAcertadas(dato);
						}else if(accion.contains("ataque")){
							estadisticas.setNumAtaquesRapidosTotales(estadisticas.getNumAtaquesRapidosTotales()+dato-estadisticas.getNumAtaquesRapidosAcertados());
							estadisticas.setNumAtaquesRapidosAcertados(dato);
						}
					}else {
						if(accion.contains("saque")) {
							estadisticas.setSaquesTotales(estadisticas.getSaquesAcertados()+dato);
						}else if(accion.contains("recepcion")){
							estadisticas.setRecepcionesTotales(estadisticas.getRecepcionesAcertadas()+dato);
						}else if(accion.contains("colocacion")){
							estadisticas.setColocacionesTotales(estadisticas.getColocacionesAcertadas()+dato);
						}else if(accion.contains("defensa")){
							estadisticas.setDefensasTotales(estadisticas.getDefensasAcertadas()+dato);
						}else if(accion.contains("bloqueo")){
							estadisticas.setBloqueosTotales(estadisticas.getBloqueosAcertados()+dato);
						}else if(accion.contains("remate")){
							estadisticas.setRematesTotales(estadisticas.getRematesAcertados()+dato);
						}else if(accion.contains("finta")){
							estadisticas.setFintasTotales(estadisticas.getFintasAcertadas()+dato);
						}else if(accion.contains("ataque")){
							estadisticas.setNumAtaquesRapidosTotales(estadisticas.getNumAtaquesRapidosAcertados()+dato);
						}
					}
					
					estadisticaPersonalPartidoService.save(estadisticas);
				}
				
				
			}
//			for (int i = 0; i<; i++) {
//				if (request.getParameterMap().)
//			}
			
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el viaje");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/jugadoresEnCampo/{partidoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> jugadoresEnCampo(@PathVariable("partidoId") int partidoId) {
		try {
			Partido partido = partidoService.findById(partidoId).get();
			
			List<String> jugadores = partido.getJugadoresJugando().stream().filter(x->!x.equals(partido.getJugadorLibero())).map(x->x.getFirstName()+", " +x.getLastName()+ " "
			+ x.getNumCamisetas().stream().filter(y->y.getEquipo().getId().equals(partido.getEquipo().getId()))
			.map(z->z.getNumero()).collect(Collectors.toList()).get(0)+";"+x.getId())
			.collect(Collectors.toList());
			
			return new ResponseEntity<List<String>>(jugadores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "/jugadoresEnBanquillo/{partidoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> jugadoresEnBanquillo(@PathVariable("partidoId") int partidoId) {
		try {
			Partido partido = partidoService.findById(partidoId).get();
			
			List<String> jugadores = partido.getJugadores().stream().filter(x->!partido.getJugadoresJugando().contains(x))
			.map(x->x.getFirstName()+", " +x.getLastName()+ " "
			+ x.getNumCamisetas().stream().filter(y->y.getEquipo().getId().equals(partido.getEquipo().getId()))
			.map(z->z.getNumero()).collect(Collectors.toList()).get(0)+";"+x.getId())
			.collect(Collectors.toList());
			
			return new ResponseEntity<List<String>>(jugadores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "realizarsustitucion", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity realizarSustitucion(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorEnCampoId = Integer.parseInt(request.getParameter("jugadorEnCampo"));
			int jugadorEnBanquilloId = Integer.parseInt(request.getParameter("jugadorEnBanquillo"));
			int minutoSustitucion = Integer.parseInt(request.getParameter("minutoSustitucion"));
			
			Sustitucion sustitucion = new Sustitucion();
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugadorEnCampo = jugadorService.findById(jugadorEnCampoId).get();
			Jugador jugadorEnBanquillo = jugadorService.findById(jugadorEnBanquilloId).get();
			
			if(jugadorEnCampo.getPosicionPrincipal().equals(jugadorEnBanquillo.getPosicionPrincipal()) ||
				jugadorEnCampo.getPosicionPrincipal().equals(jugadorEnBanquillo.getPosicionSecundaria()) ||
				jugadorEnCampo.getPosicionSecundaria().equals(jugadorEnBanquillo.getPosicionPrincipal()) ||
				jugadorEnCampo.getPosicionSecundaria().equals(jugadorEnBanquillo.getPosicionSecundaria())) {
				
				
				
				
				return new ResponseEntity("coinciden",HttpStatus.OK);
			}
			
			List<Jugador> jugadoresEnCampo = partido.getJugadoresJugando();
			
			jugadoresEnCampo.add(jugadorEnBanquillo);
			jugadoresEnCampo.remove(jugadorEnCampo);
			
			partido.setJugadoresJugando(jugadoresEnCampo);
			Partido partido_ = partidoService.save(partido);
			
			sustitucion.setJugadorEntra(jugadorEnCampo);
			sustitucion.setJugadorSale(jugadorEnBanquillo);
			sustitucion.setMinutoSustitucion(minutoSustitucion);
			sustitucion.setPartido(partido);
			
			Sustitucion sustitucio_ = sustitucionService.save(sustitucion);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el viaje");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/tablaSustituciones/{partidoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadorDTO>> tablaSustituciones(@PathVariable("partidoId") int partidoId) {
		try {
			Partido partido = partidoService.findById(partidoId).get();
			
			List<Jugador> jugadores = partido.getJugadores();
			List<Jugador> jugadoresJugando = partido.getJugadoresJugando();
			
			List<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
			
			
			for(Jugador jugador:jugadores) {
				JugadorDTO jugadorDTO = new JugadorDTO();
				jugadorDTO = jugadorConverter.convertParcialJugadorToJugadorDTO(jugador);
				jugadorDTO.setNumCamiseta(numCamisetaService.findByEquipoAndJugador(partido.getEquipo().getId(), jugador.getId()).getNumero());
				
				if(jugadoresJugando.contains(jugador)) {
					jugadorDTO.setEnCampo(true);
					jugadoresDTO.add(jugadorDTO);
				}else {
					jugadorDTO.setEnCampo(false);
					jugadoresDTO.add(jugadorDTO);
				}
				
			}
			
			DataTableResponse<JugadorDTO> data = new DataTableResponse<JugadorDTO>(jugadoresDTO);
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	//Método para traer a la tabla las estadísticas personales de un jugador concreto
	private JugadorDTO obtenerDatosEstadisticosJugador(JugadorDTO jugadorDTO, int jugadorId, int partidoId) {
		EstadisticaPersonalPartido estadisticaJugador = estadisticaPersonalPartidoService.findByJugadorAndPartido(jugadorId, partidoId);
		
		if(estadisticaJugador != null) {
			jugadorDTO.setSaquesAcertados(estadisticaJugador.getSaquesAcertados());
			jugadorDTO.setSaquesTotales(estadisticaJugador.getSaquesTotales());
			jugadorDTO.setPorcentajeSaques(estadisticaJugador.getPorcentajeSaques());
			jugadorDTO.setRecepcionesAcertadas(estadisticaJugador.getRecepcionesAcertadas());
			jugadorDTO.setRecepcionesTotales(estadisticaJugador.getRecepcionesTotales());
			jugadorDTO.setPorcentajeRecepciones(estadisticaJugador.getPorcentajeRecepciones());
			jugadorDTO.setColocacionesAcertadas(estadisticaJugador.getColocacionesAcertadas());
			jugadorDTO.setColocacionesTotales(estadisticaJugador.getColocacionesTotales());
			jugadorDTO.setPorcentajeColocaciones(estadisticaJugador.getPorcentajeColocaciones());
			jugadorDTO.setDefensasAcertadas(estadisticaJugador.getDefensasAcertadas());
			jugadorDTO.setDefensasTotales(estadisticaJugador.getDefensasTotales());
			jugadorDTO.setPorcentajeDefensas(estadisticaJugador.getPorcentajeDefensas());
			jugadorDTO.setBloqueosAcertados(estadisticaJugador.getBloqueosAcertados());
			jugadorDTO.setBloqueosTotales(estadisticaJugador.getBloqueosTotales());
			jugadorDTO.setPorcentajeBloqueos(estadisticaJugador.getPorcentajeBloqueos());
			jugadorDTO.setRematesAcertados(estadisticaJugador.getRematesAcertados());
			jugadorDTO.setRematesTotales(estadisticaJugador.getRematesTotales());
			jugadorDTO.setPorcentajeRemates(estadisticaJugador.getPorcentajeRemates());
			jugadorDTO.setFintasAcertadas(estadisticaJugador.getFintasAcertadas());
			jugadorDTO.setFintasTotales(estadisticaJugador.getFintasTotales());
			jugadorDTO.setPorcentajeFintas(estadisticaJugador.getPorcentajeFintas());
			jugadorDTO.setNumAtaquesRapidosAcertados(estadisticaJugador.getNumAtaquesRapidosAcertados());
			jugadorDTO.setNumAtaquesRapidosTotales(estadisticaJugador.getNumAtaquesRapidosTotales());
			jugadorDTO.setPorcentajeAtaquesRapidos(estadisticaJugador.getPorcentajeAtaquesRapidos());
			jugadorDTO.setNumFaltasTotales(estadisticaJugador.getNumFaltasTotales());
			jugadorDTO.setNumAmarillas(estadisticaJugador.getNumAmarillas());
			jugadorDTO.setNumRojas(estadisticaJugador.getNumRojas());
		}
		
		return jugadorDTO;
	}
	
}
