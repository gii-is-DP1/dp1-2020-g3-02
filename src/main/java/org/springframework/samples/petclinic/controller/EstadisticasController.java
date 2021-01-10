package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.SistemaJuego;
import org.springframework.samples.petclinic.model.Sustitucion;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.JugadorDTO;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesStats;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.EstadisticoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.SistemaJuegoService;
import org.springframework.samples.petclinic.service.SustitucionService;
import org.springframework.samples.petclinic.service.impl.UserService;
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
	private UserService userService;
	
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
	private SistemaJuegoService sistemaService;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisticaPersonalPartidoService;
	
	@Autowired
	private EstadisticoService estadisticoService;
	
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
			List<Jugador> jugadores = new ArrayList<Jugador>();
				jugadores.addAll(partido.getJugadoresJugando());
			
			if(partido.getJugadorLibero() != null) {
				jugadores.add(partido.getJugadorLibero());
			}
			
			List<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
			for(Jugador jugador:jugadores) {
				JugadorDTO jugadorDTO = new JugadorDTO();
				jugadorDTO = jugadorConverter.convertParcialJugadorToJugadorDTO(jugador);
				jugadorDTO = obtenerDatosEstadisticosJugador(jugadorDTO, jugador.getId(), partidoId);
				jugadorDTO.setNumCamiseta(numCamisetaService.findByEquipoAndJugador(partido.getEquipo().getId(), jugador.getId()).getNumero());
				if(partido.getJugadorLibero() != null && jugador.equals(partido.getJugadorLibero())) {
					jugadorDTO.setEsLibero(true);
				}else {
					jugadorDTO.setEsLibero(false);
				}
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
	
	@RequestMapping(value = "saveComandos/{partidoId}", method = RequestMethod.POST)
	public ResponseEntity<String> saveComandos(HttpServletRequest request, @PathVariable("partidoId") int partidoId) {
		try {
			Principal principal = request.getUserPrincipal();
			Estadistico estadistico = estadisticoService.findByUser(userService.findByUsername(principal.getName()));
			
			Partido partido = partidoService.findById(partidoId).get();
			List<Jugador> jugadores = new ArrayList<Jugador>(partido.getJugadoresJugando());
			Jugador libero = partido.getJugadorLibero();
			
			if(libero != null) {
				jugadores.add(libero);
			}
					
			Map<Integer, EstadisticaPersonalPartido> estadisticasMap = new HashMap<Integer, EstadisticaPersonalPartido>();
			Map<Integer, Integer> camisetaIdJugador = new HashMap<Integer, Integer>();
			Set<Integer> dorsales = new HashSet<Integer>();
			for(Jugador jugador:jugadores) {
				EstadisticaPersonalPartido estadistica =  new EstadisticaPersonalPartido();
				Integer numCamiseta = numCamisetaService.findByEquipoAndJugador(partido.getEquipo().getId(), jugador.getId()).getNumero();
				
				dorsales.add(numCamiseta);
				camisetaIdJugador.put(numCamiseta, jugador.getId());
				
				EstadisticaPersonalPartido stat = estadisticaPersonalPartidoService.findByJugadorAndPartido(jugador.getId(), partidoId);
				if(stat != null) {
					BeanUtils.copyProperties(stat, estadistica);
					estadisticasMap.put(numCamiseta, estadistica);
				}
			}
			
			String data = request.getParameter("comandoIntroducido").trim();
			String error = "Errores de Sintaxis: ";
			
			String[] dataActions = data.split(";");
			
			Set<Integer> dorsalesEditSet = new HashSet<Integer>();
			for(int i=0;i<dataActions.length;i++) {
				boolean correccion = dataActions[i].startsWith("%");
				
				if(!correccion) {
					String[] dataActionsParts = dataActions[i].split(",");
					error = gestionErroresComandos(error, dataActions, i, dorsales, correccion);
					if(error.equals("Errores de Sintaxis: ")) {
						Integer dorsal = Integer.valueOf(dataActionsParts[0]);
						String accion = dataActionsParts[1];
						String acierto = dataActionsParts[2];
						
						dorsalesEditSet.add(dorsal);
						
						if(estadisticasMap.containsKey(dorsal)) {
							EstadisticaPersonalPartido estadistica = estadisticasMap.get(dorsal);
							
							estadistica = setEstadisticaCorrecta(estadistica, accion, acierto, correccion);
							
							estadisticasMap.put(dorsal, estadistica);
						} else {
							EstadisticaPersonalPartido estadistica =  new EstadisticaPersonalPartido();
							
							estadistica = setEstadisticaCorrecta(estadistica, accion, acierto, correccion);
							
							estadisticasMap.put(dorsal, estadistica);
						}
					}
				} else {
					dataActions[i].replace("%", "");
					error = gestionErroresComandos(error, dataActions, i, dorsales, correccion);
					//TODO
				}
			}
			
			if(!error.equals("Errores de Sintaxis: ")) {
				LOG.info(data);
				LOG.warn(error);
				return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
			}
			
			List<Integer> dorsalesEdit = new ArrayList<Integer>(dorsalesEditSet);
			for(Integer dorsal:dorsalesEdit) {
				EstadisticaPersonalPartido estadistica = estadisticasMap.get(dorsal);
				Jugador jugador = jugadorService.findById(camisetaIdJugador.get(dorsal)).get();
				
				estadistica.setJugador(jugador);
				estadistica.setEstadistico(estadistico);
				estadistica.setPartido(partido);
				
				EstadisticaPersonalPartido estadisticaSave = estadisticaPersonalPartidoService.save(estadistica);
				LOG.info("Estadística guardada con éxito: " + estadisticaSave.getId());
			}
			
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			String data = request.getParameter("comandoIntroducido").trim();
			return new ResponseEntity<String>(data,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "rellenarDatos", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity rellenarDatos(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			
			int hour = Integer.parseInt(request.getParameter("hour"));
			int minute = Integer.parseInt(request.getParameter("minute"));
			int second = Integer.parseInt(request.getParameter("second"));
			
			Partido partido = partidoService.findById(partidoId).get();
			List<Jugador> jugadores = partido.getJugadores();
			
			partido.setHour(hour);
			partido.setMinute(minute);
			partido.setSecond(second);
			
			Partido partido_ = partidoService.save(partido);
			
			Enumeration<String> parameters = request.getParameterNames();
			
			while (parameters.hasMoreElements()) {
				
				String clave = parameters.nextElement();
				
				if(!(clave.equals("partidoId") || clave.equals("hour") || clave.equals("minute") || clave.equals("second"))) {
					String accion = clave.split(",")[0];
					int jugadorId = Integer.parseInt(clave.split(",")[1]);
					Jugador jugador = jugadorService.findById(jugadorId).get();
					Integer dato = 0;
					try {
						dato = Integer.parseInt(request.getParameter(clave));
					}catch (Exception e) {
						LOG.error("Hueco en blanco");
						
					}
					
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
	
	@RequestMapping(value = "/cambioSistemaJuegoSustitucion", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Sistema> cambioSistemaJuegoSustitucion(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorEnCampoId = Integer.parseInt(request.getParameter("jugadorEnCampo"));
			int jugadorEnBanquilloId = Integer.parseInt(request.getParameter("jugadorEnBanquillo"));
			
			int minutoAplicacion = Integer.parseInt(request.getParameter("minutoAplicacion"));
			Sistema sistemaJuego = Sistema.fromNombre(request.getParameter("sistemajuego"));
			
			LOG.info("Buscamos el partido con el id: " + partidoId);
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugadorEnCampo = jugadorService.findById(jugadorEnCampoId).get();
			Jugador jugadorEnBanquillo = jugadorService.findById(jugadorEnBanquilloId).get();
			
			List<Jugador> jugadoresEnCampo = partido.getJugadoresJugando();
			
			jugadoresEnCampo.add(jugadorEnBanquillo);
			jugadoresEnCampo.remove(jugadorEnCampo);
			
			partido.setJugadoresJugando(jugadoresEnCampo);
			Partido partido_ = partidoService.save(partido);
			Sustitucion sustitucion = new Sustitucion();
			
			sustitucion.setJugadorEntra(jugadorEnCampo);
			sustitucion.setJugadorSale(jugadorEnBanquillo);
			sustitucion.setMinutoSustitucion(minutoAplicacion);
			sustitucion.setPartido(partido);
			
			Sustitucion sustitucio_ = sustitucionService.save(sustitucion);
			
			Integer tam = partido.getSistemasJuego().size()-1;
			
			if(partido.getSistemasJuego().size()!=0 && sistemaJuego.equals(partido.getSistemasJuego().get(tam).getSistema())) {
				return new ResponseEntity<Sistema>(sistemaJuego,HttpStatus.OK);
			}
			
			List<SistemaJuego> sistemasJuegoPartido = partido.getSistemasJuego();
			SistemaJuego sistemaJuegoPartido = new SistemaJuego();
			
			sistemaJuegoPartido.setPartido(partido);
			sistemaJuegoPartido.setSistema(sistemaJuego);
			sistemaJuegoPartido.setMinutoAplicacion(minutoAplicacion);
			SistemaJuego sistemaSave = sistemaService.save(sistemaJuegoPartido);
			sistemasJuegoPartido.add(sistemaSave);
			partido.setSistemasJuego(sistemasJuegoPartido);
			LOG.info("Guardamos el partido con el sistema de juego cambiado");
			partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción eliminando el jugador del partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/cambioSistemaJuego", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sistema> cambioSistemaJuego(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int minutoAplicacion = Integer.parseInt(request.getParameter("minutoAplicacion"));
			Sistema sistemaJuego = Sistema.fromNombre(request.getParameter("sistemajuego"));
			
			LOG.info("Buscamos el partido con el id: " + partidoId);
			Partido partido = partidoService.findById(partidoId).get();
			Integer tam = partido.getSistemasJuego().size()-1;
			
			if(partido.getSistemasJuego().size()!=0 && sistemaJuego.equals(partido.getSistemasJuego().get(tam).getSistema())) {
				return new ResponseEntity<Sistema>(sistemaJuego,HttpStatus.OK);
			}
			
			List<SistemaJuego> sistemasJuegoPartido = partido.getSistemasJuego();
			SistemaJuego sistemaJuegoPartido = new SistemaJuego();
			
			sistemaJuegoPartido.setPartido(partido);
			sistemaJuegoPartido.setSistema(sistemaJuego);
			sistemaJuegoPartido.setMinutoAplicacion(minutoAplicacion);
			SistemaJuego sistemaSave = sistemaService.save(sistemaJuegoPartido);
			sistemasJuegoPartido.add(sistemaSave);
			partido.setSistemasJuego(sistemasJuegoPartido);
			LOG.info("Guardamos el partido con el sistema de juego cambiado");
			partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción eliminando el jugador del partido");
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
			
			List<String> jugadores = partido.getJugadores().stream().filter(x->!x.equals(partido.getJugadorLibero())).filter(x->!partido.getJugadoresJugando().contains(x))
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
	public ResponseEntity<Map<String,JugadorDTO>> realizarSustitucion(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			Integer partidoIdx = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorEnCampoId = Integer.parseInt(request.getParameter("jugadorEnCampo"));
			int jugadorEnBanquilloId = Integer.parseInt(request.getParameter("jugadorEnBanquillo"));
			int minutoSustitucion = Integer.parseInt(request.getParameter("minutoSustitucion"));
			String coinciden = "coinciden";
			Sustitucion sustitucion = new Sustitucion();
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugadorEnCampo = jugadorService.findById(jugadorEnCampoId).get();
			Jugador jugadorEnBanquillo = jugadorService.findById(jugadorEnBanquilloId).get();
			
			if(!(jugadorEnCampo.getPosicionPrincipal().equals(jugadorEnBanquillo.getPosicionPrincipal()) ||
				jugadorEnCampo.getPosicionPrincipal().equals(jugadorEnBanquillo.getPosicionSecundaria()) ||
				jugadorEnCampo.getPosicionSecundaria().equals(jugadorEnBanquillo.getPosicionPrincipal()) ||
				jugadorEnCampo.getPosicionSecundaria().equals(jugadorEnBanquillo.getPosicionSecundaria()))) {
				
				JugadorDTO jugadorDTOEntra = jugadorConverter.convertParcialJugadorToJugadorDTO(jugadorEnBanquillo);
				JugadorDTO jugadorDTOSale = jugadorConverter.convertParcialJugadorToJugadorDTO(jugadorEnCampo);
				Map<String,JugadorDTO> map = new HashMap<String, JugadorDTO>();
				map.put("jugadorEntra", jugadorDTOEntra);
				map.put("jugadorSale", jugadorDTOSale);
				return new ResponseEntity<Map<String,JugadorDTO>>(map,HttpStatus.OK);
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
	
	@RequestMapping(value = "/anadirJugadorJugando", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE) 
	public ResponseEntity anadirJugadorJugando(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
			
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugador = jugadorService.findById(jugadorId).get();
			
			List<Jugador> jugadoresJugando = partido.getJugadoresJugando();
			jugadoresJugando.add(jugador);
			
			partido.setJugadoresJugando(jugadoresJugando);
			
			Partido patido_ = partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el jugador en partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/eliminarJugadorJugando", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarJugadorJugando(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
			
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugador = jugadorService.findById(jugadorId).get();
			
			List<Jugador> jugadoresJugando = partido.getJugadoresJugando();
			jugadoresJugando.remove(jugador);
			
			partido.setJugadoresJugando(jugadoresJugando);
			
			Partido patido_ = partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el jugador en partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/seleccionarLibero", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity seleccionarLibero(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
			
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugador = jugadorService.findById(jugadorId).get();
			
			partido.setJugadorLibero(jugador);
			
			Partido patido_ = partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el jugador en partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/eliminarLibero", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarLibero(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
			
			Partido partido = partidoService.findById(partidoId).get();
			Jugador jugador = jugadorService.findById(jugadorId).get();	
			
			partido.setJugadorLibero(null);
			
			Partido patido_ = partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el jugador en partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/sistemaJuegoEquipo/{partidoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sistema> sistemaJuegoEquipo(@PathVariable("partidoId") int partidoId) {
		try {
			Partido partido = partidoService.findById(partidoId).get();
			
			Sistema sistemaJuego =  partido.getEquipo().getSistemaJuego();
			
			return new ResponseEntity<Sistema>(sistemaJuego, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Sistema>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "/iniciarPartido", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity iniciarPartido(HttpServletRequest request) {
		try {
			int partidoId = Integer.parseInt(request.getParameter("partidoId"));
			Sistema sistema = Sistema.fromNombre(request.getParameter("sistemaJuego"));
			
			Partido partido = partidoService.findById(partidoId).get();
			
			if(partido.getJugadoresJugando().size()!=6) {
				LOG.error("Menos de 6 jugadores en el partido");
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			
			List<SistemaJuego> listaSistemas = new ArrayList<SistemaJuego>();
			SistemaJuego sistemaJuego = new SistemaJuego();
			sistemaJuego.setPartido(partido);
			sistemaJuego.setSistema(sistema);
			sistemaJuego.setMinutoAplicacion(0);
			
			SistemaJuego sistemaJuego_ = sistemaService.save(sistemaJuego);
			listaSistemas.add(sistemaJuego_);
			
			partido.setSistemasJuego(listaSistemas);
			Partido patido_ = partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción iniciando partido");
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
				jugadorDTO.setNumJugadoresJugando(jugadoresJugando.size());
				
				if(partido.getJugadorLibero() != null) {
					jugadorDTO.setYaHayLibero(true);
				}else {
					jugadorDTO.setYaHayLibero(false);
				}
				
				if(jugadoresJugando.contains(jugador)) {
					jugadorDTO.setEnCampo(true);
					jugadoresDTO.add(jugadorDTO);
				}else {
					jugadorDTO.setEnCampo(false);
					jugadoresDTO.add(jugadorDTO);
				}
				
				if(partido.getJugadorLibero() != null && partido.getJugadorLibero().equals(jugador)) {
					jugadorDTO.setEsLibero(true);
				}else {
					jugadorDTO.setEsLibero(false);
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
	
	// Gestionar los errores cometidos en la sintaxis de los comandos introducidos
	private String gestionErroresComandos(String error, String[] dataActions, int i, Set<Integer> dorsales, boolean correccion) {
		String[] dataActionsParts = dataActions[i].split(",");
		if(dataActionsParts.length < 3 || dataActionsParts.length > 3) {
			error += "Comas(,) en posición " + (i+1);
			error += "; ";
		} else {
			String dorsal = dataActionsParts[0];
			String accion = dataActionsParts[1];
			String acierto = dataActionsParts[2];
			
			//Elemento 1
			if(!Pattern.matches("^[0-9]+", dorsal)) {
				error += "Dorsal no números en posición " + (i+1);
				error += "; ";
			} else if(!dorsales.contains(Integer.valueOf(dorsal))) {
				error += "Dorsal no en juego en posición " + (i+1);
				error += "; ";
			}
			
			//Elemento 2
			if(!correccion) {
				if(!accion.equalsIgnoreCase("s") && !accion.equalsIgnoreCase("r") && !accion.equalsIgnoreCase("c") && !accion.equalsIgnoreCase("d") && !accion.equalsIgnoreCase("b") && !accion.equalsIgnoreCase("a") && !accion.equalsIgnoreCase("f") && !accion.equalsIgnoreCase("ar") && !accion.equalsIgnoreCase("ft") && !accion.equalsIgnoreCase("ta") && !accion.equalsIgnoreCase("tr")) {
					error += "Acción incorrecta en posición " + (i+1);
					error += "; ";
				}
			} else {
				if(!accion.equalsIgnoreCase("s") && !accion.equalsIgnoreCase("r") && !accion.equalsIgnoreCase("c") && !accion.equalsIgnoreCase("d") && !accion.equalsIgnoreCase("b") && !accion.equalsIgnoreCase("a") && !accion.equalsIgnoreCase("f") && !accion.equalsIgnoreCase("ar")) {
					error += "Acción incorrecta en posición " + (i+1);
					error += "; ";
				}
			}
			
			//Elemento 3
			if(acierto.length() != 1 && (!acierto.equals("+") && !acierto.equals("-"))) {
				error += "+/- incorrecto en posición " + (i+1);
				error += "; ";
			}
		}
		return error;
	}
	
	// Metodo para saber que estadistica settear segun la acción y el signo
	private EstadisticaPersonalPartido setEstadisticaCorrecta(EstadisticaPersonalPartido estadistica, String accion, String acierto, boolean correccion) {
		if(!correccion) {
			if(accion.equalsIgnoreCase("s")) {
				if(acierto.equals("+")) {
					estadistica.setSaquesTotales(estadistica.getSaquesTotales() + 1);
					estadistica.setSaquesAcertados(estadistica.getSaquesAcertados() + 1);
				} else {
					estadistica.setSaquesTotales(estadistica.getSaquesTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("r")) {
				if(acierto.equals("+")) {
					estadistica.setRecepcionesTotales(estadistica.getRecepcionesTotales() + 1);
					estadistica.setRecepcionesAcertadas(estadistica.getRecepcionesAcertadas() + 1);
				} else {
					estadistica.setRecepcionesTotales(estadistica.getRecepcionesTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("c")) {
				if(acierto.equals("+")) {
					estadistica.setColocacionesTotales(estadistica.getColocacionesTotales() + 1);
					estadistica.setColocacionesAcertadas(estadistica.getColocacionesAcertadas() + 1);
				} else {
					estadistica.setColocacionesTotales(estadistica.getColocacionesTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("d")) {
				if(acierto.equals("+")) {
					estadistica.setDefensasTotales(estadistica.getDefensasTotales() + 1);
					estadistica.setDefensasAcertadas(estadistica.getDefensasAcertadas() + 1);
				} else {
					estadistica.setDefensasTotales(estadistica.getDefensasTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("b")) {
				if(acierto.equals("+")) {
					estadistica.setBloqueosTotales(estadistica.getBloqueosTotales() + 1);
					estadistica.setBloqueosAcertados(estadistica.getBloqueosAcertados() + 1);
				} else {
					estadistica.setBloqueosTotales(estadistica.getBloqueosTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("a")) {
				if(acierto.equals("+")) {
					estadistica.setRematesTotales(estadistica.getRematesTotales() + 1);
					estadistica.setRematesAcertados(estadistica.getRematesAcertados() + 1);
				} else {
					estadistica.setRematesTotales(estadistica.getRematesTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("f")) {
				if(acierto.equals("+")) {
					estadistica.setFintasTotales(estadistica.getFintasTotales() + 1);
					estadistica.setFintasAcertadas(estadistica.getFintasAcertadas() + 1);
				} else {
					estadistica.setFintasTotales(estadistica.getFintasTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("ar")) {
				if(acierto.equals("+")) {
					estadistica.setNumAtaquesRapidosTotales(estadistica.getNumAtaquesRapidosTotales() + 1);
					estadistica.setNumAtaquesRapidosAcertados(estadistica.getNumAtaquesRapidosAcertados() + 1);
				} else {
					estadistica.setNumAtaquesRapidosTotales(estadistica.getNumAtaquesRapidosTotales() + 1);
				}
			} else if(accion.equalsIgnoreCase("ft")) {
				if(acierto.equals("+")) {
					estadistica.setNumFaltasTotales(estadistica.getNumFaltasTotales() + 1);
				} else {
					if(!(estadistica.getNumFaltasTotales() <= 0)) {
						estadistica.setNumFaltasTotales(estadistica.getNumFaltasTotales() - 1);
					}
				}
			} else if(accion.equalsIgnoreCase("ta")) {
				if(acierto.equals("+")) {
					estadistica.setNumAmarillas(estadistica.getNumAmarillas() + 1);
					if(estadistica.getNumAmarillas() % 2 == 0) {
						estadistica.setNumRojas(estadistica.getNumRojas() + 1);
					}
				} else {
					if(!(estadistica.getNumAmarillas() <=0)) {
						estadistica.setNumFaltasTotales(estadistica.getNumFaltasTotales() - 1);
					}
				}
			} else if(accion.equalsIgnoreCase("tr")) {
				if(acierto.equals("+")) {
					estadistica.setNumRojas(estadistica.getNumRojas() + 1);
				} else {
					if(!(estadistica.getNumFaltasTotales() <= 0)) {
						estadistica.setNumRojas(estadistica.getNumRojas() - 1);
					}
				}
			}
		} else {
			//TODO
		}
		
		return estadistica;
	}
	
}
