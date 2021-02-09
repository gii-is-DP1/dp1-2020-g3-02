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
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.Estadistico;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.JugadorDTO;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesEntrenamientoStats;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
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
@RequestMapping("/estadisticasEntrenamiento")
public class EstadisticasEntrenamientoController {

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
	private EntrenamientoService entrenamientoService;

	@Autowired
	private SustitucionService sustitucionService;

	@Autowired
	private EstadisticasConverter estadisticasConverter;

	@Autowired
	private SistemaJuegoService sistemaService;

	@Autowired
	private EstadisticaPersonalPartidoService estadisticaPersonalPartidoService;

	@Autowired
	private EstadisticaPersonalEntrenamientoService estadisticaPersonalEntrenamientoService;

	@Autowired
	private EstadisticoService estadisticoService;

	private static final Log LOG = LogFactory.getLog(EstadisticasEntrenamientoController.class);


	@GetMapping("/estadisticasEntrenamientoForm/{entrenamientoId}")
	public String formularioEstadisticasEntrenamiento(@PathVariable("entrenamientoId") int entrenamientoId,
			Model model) {

		model.addAttribute("entrenamiento", entrenamientoService.findById(entrenamientoId).get());
		return ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_FORM;
	}


	@RequestMapping(value = "/tablaIntroducirEstadisticasEntrenamiento/{entrenamientoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadorDTO>> tablaIntroducirEstadisticasEntrenamiento(
			@PathVariable("entrenamientoId") int entrenamientoId) {
		try {
			Entrenamiento entrenamiento = entrenamientoService.findById(entrenamientoId).get();
			Set<Jugador> jugadores = new HashSet<Jugador>();
			jugadores.addAll(entrenamiento.getJugadores());

			List<JugadorDTO> jugadoresDTO = new ArrayList<JugadorDTO>();
			for (Jugador jugador : jugadores) {
				JugadorDTO jugadorDTO = new JugadorDTO();
				jugadorDTO = jugadorConverter.convertParcialJugadorToJugadorDTO(jugador);
				jugadorDTO = obtenerDatosEstadisticosJugadorEntrenamiento(jugadorDTO, jugador.getId(), entrenamientoId);
				jugadorDTO.setNumCamiseta(jugador.getId());
				jugadoresDTO.add(jugadorDTO);
			}

			DataTableResponse<JugadorDTO> data = new DataTableResponse<JugadorDTO>(jugadoresDTO);
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/obtenerEstadisticasJugadoresEntrenamiento/{entrenamientoId}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstadisticasPersonalesEntrenamientoStats>> obtenerEstadisticasJugadoresEntrenamiento(
			@PathVariable("entrenamientoId") int entrenamientoId) {
		try {

			List<EstadisticaPersonalEntrenamiento> estadisticasPersonalesPartidos = estadisticaPersonalEntrenamientoService
					.findByEntrenamiento(entrenamientoId);

			List<EstadisticasPersonalesEntrenamientoStats> estadisticasPersonalesStats = new ArrayList<EstadisticasPersonalesEntrenamientoStats>();

			for (int i = 0; i < estadisticasPersonalesPartidos.size(); i++) {
				EstadisticasPersonalesEntrenamientoStats estadisticasPersonalesEntrenamientoStats = estadisticasConverter
						.convertEstadisticasToEstadisticasEntrenamientoStats(estadisticasPersonalesPartidos.get(i));
				estadisticasPersonalesStats.add(estadisticasPersonalesEntrenamientoStats);
			}

			return new ResponseEntity<List<EstadisticasPersonalesEntrenamientoStats>>(estadisticasPersonalesStats,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<EstadisticasPersonalesEntrenamientoStats>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/saveComandosEntrenamiento/{entrenamientoId}", method = RequestMethod.POST)
	public ResponseEntity<String> saveComandosEntrenamiento(HttpServletRequest request,
			@PathVariable("entrenamientoId") int entrenamientoId) {
		try {
			Principal principal = request.getUserPrincipal();
			Estadistico estadistico = estadisticoService.findByUser(userService.findByUsername(principal.getName()));

			Entrenamiento entrenamiento = entrenamientoService.findById(entrenamientoId).get();
			List<Jugador> jugadores = new ArrayList<Jugador>(
					entrenamiento.getJugadores().stream().collect(Collectors.toList()));

			Map<Integer, EstadisticaPersonalEntrenamiento> estadisticasMap = new HashMap<Integer, EstadisticaPersonalEntrenamiento>();
			Set<Integer> dorsales = new HashSet<Integer>(); // Al ser un entrenamiento ahora contará como set de ids
			for (Jugador jugador : jugadores) {
				EstadisticaPersonalEntrenamiento estadistica = new EstadisticaPersonalEntrenamiento();
				Integer id = jugador.getId();

				dorsales.add(id);

				EstadisticaPersonalEntrenamiento stat = estadisticaPersonalEntrenamientoService
						.findByJugadorAndEntrenamiento(jugador.getId(), entrenamientoId);
				if (stat != null) {
					BeanUtils.copyProperties(stat, estadistica);
					estadisticasMap.put(id, estadistica);
				}
			}

			String data = request.getParameter("comandoIntroducido").trim();
			String error = "Errores de Sintaxis: ";

			String[] dataActions = data.split(" ");

			Set<Integer> dorsalesEditSet = new HashSet<Integer>();
			for (int i = 0; i < dataActions.length; i++) {
				boolean correccion = dataActions[i].startsWith("%");

				if (!correccion) {
					String[] dataActionsParts = dataActions[i].split(",");
					error = gestionErroresComandosEntrenamiento(error, dataActions, i, dorsales, correccion);
					if (error.equals("Errores de Sintaxis: ")) {
						Integer dorsal = Integer.valueOf(dataActionsParts[0]);
						String accion = dataActionsParts[1];
						String acierto = dataActionsParts[2];

						dorsalesEditSet.add(dorsal);

						if (estadisticasMap.containsKey(dorsal)) {
							EstadisticaPersonalEntrenamiento estadistica = estadisticasMap.get(dorsal);

							estadistica = setEstadisticaCorrecta(estadistica, accion, acierto, correccion);

							estadisticasMap.put(dorsal, estadistica);
						} else {
							EstadisticaPersonalEntrenamiento estadistica = new EstadisticaPersonalEntrenamiento();

							estadistica = setEstadisticaCorrecta(estadistica, accion, acierto, correccion);

							estadisticasMap.put(dorsal, estadistica);
						}
					}
				} else {
					dataActions[i].replace("%", "");
					error = gestionErroresComandos(error, dataActions, i, dorsales, correccion);
					String cadenaCorreccion = dataActions[i].replace("%", "");
					String[] dataActionsParts = cadenaCorreccion.split(",");
					error = gestionErroresComandos(error, dataActions, i, dorsales, correccion);
					if (error.equals("Errores de Sintaxis: ")) {
						Integer dorsal = Integer.valueOf(dataActionsParts[0]);
						String accion = dataActionsParts[1];
						String acierto = dataActionsParts[2];

						dorsalesEditSet.add(dorsal);

						if (estadisticasMap.containsKey(dorsal)) {
							EstadisticaPersonalEntrenamiento estadistica = estadisticasMap.get(dorsal);

							estadistica = setEstadisticaCorrecta(estadistica, accion, acierto, correccion);

							estadisticasMap.put(dorsal, estadistica);
						} else {
							EstadisticaPersonalEntrenamiento estadistica = new EstadisticaPersonalEntrenamiento();

							estadistica = setEstadisticaCorrecta(estadistica, accion, acierto, correccion);

							estadisticasMap.put(dorsal, estadistica);
						}
					}
				}
			}

			if (!error.equals("Errores de Sintaxis: ")) {
				LOG.info(data);
				LOG.warn(error);
				return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
			}

			List<Integer> dorsalesEdit = new ArrayList<Integer>(dorsalesEditSet);
			for (Integer dorsal : dorsalesEdit) {
				EstadisticaPersonalEntrenamiento estadistica = estadisticasMap.get(dorsal);
				Jugador jugador = jugadorService.findById(dorsal).get();

				estadistica.setJugador(jugador);
				estadistica.setEstadistico(estadistico);
				estadistica.setEntrenamiento(entrenamiento);

				EstadisticaPersonalEntrenamiento estadisticaSave = estadisticaPersonalEntrenamientoService
						.save(estadistica);
				LOG.info("Estadística guardada con éxito: " + estadisticaSave.getId());
			}

			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			String data = request.getParameter("comandoIntroducido").trim();
			return new ResponseEntity<String>(data, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/rellenarDatosEntrenamiento", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> rellenarDatosEntrenamiento(HttpServletRequest request) {
		try {
			int entrenamientoId = Integer.parseInt(request.getParameter("entrenamientoId"));

			Entrenamiento entrenamiento = entrenamientoService.findById(entrenamientoId).get();
			Set<Jugador> jugadores = entrenamiento.getJugadores();

			Enumeration<String> parameters = request.getParameterNames();
			List<String> camposNegativos = new ArrayList<String>();

			while (parameters.hasMoreElements()) {

				String clave = parameters.nextElement();

				if (!(clave.equals("entrenamientoId") || clave.equals("hour") || clave.equals("_csrf"))) {
					String accion = clave.split(",")[0];
					int jugadorId = Integer.parseInt(clave.split(",")[1]);
					Jugador jugador = jugadorService.findById(jugadorId).get();
					Integer dato = 0;
					try {
						dato = Integer.parseInt(request.getParameter(clave));
					} catch (Exception e) {
						LOG.error("Hueco en blanco");

					}

					EstadisticaPersonalEntrenamiento estadisticas = estadisticaPersonalEntrenamientoService
							.findByJugadorAndEntrenamiento(jugadorId, entrenamientoId);
					if (estadisticas == null) {
						estadisticas = new EstadisticaPersonalEntrenamiento();
						estadisticas.setEntrenamiento(entrenamiento);
						estadisticas.setJugador(jugador);
					}

					if (dato < 0) {
						camposNegativos.add(clave);
					}

					else if (accion.equals("faltas")) {
						estadisticas.setNumFaltasTotales(dato);
					}

					else if (accion.startsWith("a")) {
						if (accion.contains("saque")) {
							estadisticas.setSaquesTotales(
									estadisticas.getSaquesTotales() + dato - estadisticas.getSaquesAcertados());
							estadisticas.setSaquesAcertados(dato);
						} else if (accion.contains("recepcion")) {
							estadisticas.setRecepcionesTotales(estadisticas.getRecepcionesTotales() + dato
									- estadisticas.getRecepcionesAcertadas());
							estadisticas.setRecepcionesAcertadas(dato);
						} else if (accion.contains("colocacion")) {
							estadisticas.setColocacionesTotales(estadisticas.getColocacionesTotales() + dato
									- estadisticas.getColocacionesAcertadas());
							estadisticas.setColocacionesAcertadas(dato);
						} else if (accion.contains("defensa")) {
							estadisticas.setDefensasTotales(
									estadisticas.getDefensasTotales() + dato - estadisticas.getDefensasAcertadas());
							estadisticas.setDefensasAcertadas(dato);
						} else if (accion.contains("bloqueo")) {
							estadisticas.setBloqueosTotales(
									estadisticas.getBloqueosTotales() + dato - estadisticas.getBloqueosAcertados());
							estadisticas.setBloqueosAcertados(dato);
						} else if (accion.contains("remate")) {
							estadisticas.setRematesTotales(
									estadisticas.getRematesTotales() + dato - estadisticas.getRematesAcertados());
							estadisticas.setRematesAcertados(dato);
						} else if (accion.contains("finta")) {
							estadisticas.setFintasTotales(
									estadisticas.getFintasTotales() + dato - estadisticas.getFintasAcertadas());
							estadisticas.setFintasAcertadas(dato);
						} else if (accion.contains("ataque")) {
							estadisticas.setNumAtaquesRapidosTotales(estadisticas.getNumAtaquesRapidosTotales() + dato
									- estadisticas.getNumAtaquesRapidosAcertados());
							estadisticas.setNumAtaquesRapidosAcertados(dato);
						}
					} else {
						if (accion.contains("saque")) {
							estadisticas.setSaquesTotales(estadisticas.getSaquesAcertados() + dato);
						} else if (accion.contains("recepcion")) {
							estadisticas.setRecepcionesTotales(estadisticas.getRecepcionesAcertadas() + dato);
						} else if (accion.contains("colocacion")) {
							estadisticas.setColocacionesTotales(estadisticas.getColocacionesAcertadas() + dato);
						} else if (accion.contains("defensa")) {
							estadisticas.setDefensasTotales(estadisticas.getDefensasAcertadas() + dato);
						} else if (accion.contains("bloqueo")) {
							estadisticas.setBloqueosTotales(estadisticas.getBloqueosAcertados() + dato);
						} else if (accion.contains("remate")) {
							estadisticas.setRematesTotales(estadisticas.getRematesAcertados() + dato);
						} else if (accion.contains("finta")) {
							estadisticas.setFintasTotales(estadisticas.getFintasAcertadas() + dato);
						} else if (accion.contains("ataque")) {
							estadisticas
									.setNumAtaquesRapidosTotales(estadisticas.getNumAtaquesRapidosAcertados() + dato);
						}
					}

					estadisticaPersonalEntrenamientoService.save(estadisticas);

				}

			}

			return new ResponseEntity<List<String>>(camposNegativos, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción actualizando las estadísticas del entrenamiento");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/finalizarEntrenamiento", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity finalizarEntrenamiento(HttpServletRequest request) {
		try {
			int entrenamientoId = Integer.parseInt(request.getParameter("entrenamientoId"));
			Entrenamiento entrenamiento = entrenamientoService.findById(entrenamientoId).get();
			entrenamiento.setEntrenamientoFinalizado(true);
			Entrenamiento entrenamiento_ = entrenamientoService.save(entrenamiento);

			estadisticaPersonalEntrenamientoService.saveEstadisticasByEntrenamiento(entrenamientoId);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción actualizando el jugador en partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	// MÉTODOS PRIVADOS

	// Método para traer a la tabla las estadísticas personales de un jugador
	// concreto
	private JugadorDTO obtenerDatosEstadisticosJugadorEntrenamiento(JugadorDTO jugadorDTO, int jugadorId,
			int entrenamientoId) {
		EstadisticaPersonalEntrenamiento estadisticaJugador = estadisticaPersonalEntrenamientoService
				.findByJugadorAndEntrenamiento(jugadorId, entrenamientoId);

		if (estadisticaJugador != null) {
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
		}

		return jugadorDTO;
	}

	// Gestionar los errores cometidos en la sintaxis de los comandos introducidos
	private String gestionErroresComandos(String error, String[] dataActions, int i, Set<Integer> dorsales,
			boolean correccion) {
		String[] dataActionsParts = dataActions[i].split(",");
		if (dataActionsParts.length < 3 || dataActionsParts.length > 3) {
			error += "Comas(,) en posición " + (i + 1);
			error += "; ";
		} else {
			String dorsal = dataActionsParts[0].replace("%", "");
			String accion = dataActionsParts[1];
			String acierto = dataActionsParts[2];

			// Elemento 1
			if (!Pattern.matches("^[0-9]+", dorsal)) {
				error += "Dorsal no números en posición " + (i + 1);
				error += "; ";
			} else if (!dorsales.contains(Integer.valueOf(dorsal))) {
				error += "Dorsal no en juego en posición " + (i + 1);
				error += "; ";
			}

			// Elemento 2
			if (!correccion) {
				if (!accion.equalsIgnoreCase("s") && !accion.equalsIgnoreCase("r") && !accion.equalsIgnoreCase("c")
						&& !accion.equalsIgnoreCase("d") && !accion.equalsIgnoreCase("b")
						&& !accion.equalsIgnoreCase("a") && !accion.equalsIgnoreCase("f")
						&& !accion.equalsIgnoreCase("ar") && !accion.equalsIgnoreCase("ft")
						&& !accion.equalsIgnoreCase("ta") && !accion.equalsIgnoreCase("tr")) {
					error += "Acción incorrecta en posición " + (i + 1);
					error += "; ";
				}
			} else {
				if (!accion.equalsIgnoreCase("s") && !accion.equalsIgnoreCase("r") && !accion.equalsIgnoreCase("c")
						&& !accion.equalsIgnoreCase("d") && !accion.equalsIgnoreCase("b")
						&& !accion.equalsIgnoreCase("a") && !accion.equalsIgnoreCase("f")
						&& !accion.equalsIgnoreCase("ar")) {
					error += "Acción incorrecta en posición " + (i + 1);
					error += "; ";
				}
			}

			// Elemento 3
			if (acierto.length() != 1 && (!acierto.equals("+") && !acierto.equals("-"))) {
				error += "+/- incorrecto en posición " + (i + 1);
				error += "; ";
			}
		}
		return error;
	}

	// Gestionar los errores cometidos en la sintaxis de los comandos introducidos
	private String gestionErroresComandosEntrenamiento(String error, String[] dataActions, int i, Set<Integer> dorsales,
			boolean correccion) {
		String[] dataActionsParts = dataActions[i].split(",");
		if (dataActionsParts.length < 3 || dataActionsParts.length > 3) {
			error += "Comas(,) en posición " + (i + 1);
			error += "; ";
		} else {
			String dorsal = dataActionsParts[0].replace("%", "");
			String accion = dataActionsParts[1];
			String acierto = dataActionsParts[2];

			// Elemento 1
			if (!Pattern.matches("^[0-9]+", dorsal)) {
				error += "Identificador no números en posición " + (i + 1);
				error += "; ";
			} else if (!dorsales.contains(Integer.valueOf(dorsal))) {
				error += "Identificador no en juego en posición " + (i + 1);
				error += "; ";
			}

			// Elemento 2
			if (!correccion) {
				if (!accion.equalsIgnoreCase("s") && !accion.equalsIgnoreCase("r") && !accion.equalsIgnoreCase("c")
						&& !accion.equalsIgnoreCase("d") && !accion.equalsIgnoreCase("b")
						&& !accion.equalsIgnoreCase("a") && !accion.equalsIgnoreCase("f")
						&& !accion.equalsIgnoreCase("ar") && !accion.equalsIgnoreCase("ft")) {
					error += "Acción incorrecta en posición " + (i + 1);
					error += "; ";
				}
			} else {
				if (!accion.equalsIgnoreCase("s") && !accion.equalsIgnoreCase("r") && !accion.equalsIgnoreCase("c")
						&& !accion.equalsIgnoreCase("d") && !accion.equalsIgnoreCase("b")
						&& !accion.equalsIgnoreCase("a") && !accion.equalsIgnoreCase("f")
						&& !accion.equalsIgnoreCase("ar")) {
					error += "Acción incorrecta en posición " + (i + 1);
					error += "; ";
				}
			}

			// Elemento 3
			if (acierto.length() != 1 && (!acierto.equals("+") && !acierto.equals("-"))) {
				error += "+/- incorrecto en posición " + (i + 1);
				error += "; ";
			}
		}
		return error;
	}

	// Metodo para saber que estadistica settear segun la acción y el signo
	private EstadisticaPersonalEntrenamiento setEstadisticaCorrecta(EstadisticaPersonalEntrenamiento estadistica,
			String accion, String acierto, boolean correccion) {
		if (!correccion) {
			if (accion.equalsIgnoreCase("s")) {
				if (acierto.equals("+")) {
					estadistica.setSaquesTotales(estadistica.getSaquesTotales() + 1);
					estadistica.setSaquesAcertados(estadistica.getSaquesAcertados() + 1);
				} else {
					estadistica.setSaquesTotales(estadistica.getSaquesTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("r")) {
				if (acierto.equals("+")) {
					estadistica.setRecepcionesTotales(estadistica.getRecepcionesTotales() + 1);
					estadistica.setRecepcionesAcertadas(estadistica.getRecepcionesAcertadas() + 1);
				} else {
					estadistica.setRecepcionesTotales(estadistica.getRecepcionesTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("c")) {
				if (acierto.equals("+")) {
					estadistica.setColocacionesTotales(estadistica.getColocacionesTotales() + 1);
					estadistica.setColocacionesAcertadas(estadistica.getColocacionesAcertadas() + 1);
				} else {
					estadistica.setColocacionesTotales(estadistica.getColocacionesTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("d")) {
				if (acierto.equals("+")) {
					estadistica.setDefensasTotales(estadistica.getDefensasTotales() + 1);
					estadistica.setDefensasAcertadas(estadistica.getDefensasAcertadas() + 1);
				} else {
					estadistica.setDefensasTotales(estadistica.getDefensasTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("b")) {
				if (acierto.equals("+")) {
					estadistica.setBloqueosTotales(estadistica.getBloqueosTotales() + 1);
					estadistica.setBloqueosAcertados(estadistica.getBloqueosAcertados() + 1);
				} else {
					estadistica.setBloqueosTotales(estadistica.getBloqueosTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("a")) {
				if (acierto.equals("+")) {
					estadistica.setRematesTotales(estadistica.getRematesTotales() + 1);
					estadistica.setRematesAcertados(estadistica.getRematesAcertados() + 1);
				} else {
					estadistica.setRematesTotales(estadistica.getRematesTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("f")) {
				if (acierto.equals("+")) {
					estadistica.setFintasTotales(estadistica.getFintasTotales() + 1);
					estadistica.setFintasAcertadas(estadistica.getFintasAcertadas() + 1);
				} else {
					estadistica.setFintasTotales(estadistica.getFintasTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("ar")) {
				if (acierto.equals("+")) {
					estadistica.setNumAtaquesRapidosTotales(estadistica.getNumAtaquesRapidosTotales() + 1);
					estadistica.setNumAtaquesRapidosAcertados(estadistica.getNumAtaquesRapidosAcertados() + 1);
				} else {
					estadistica.setNumAtaquesRapidosTotales(estadistica.getNumAtaquesRapidosTotales() + 1);
				}
			} else if (accion.equalsIgnoreCase("ft")) {
				if (acierto.equals("+")) {
					estadistica.setNumFaltasTotales(estadistica.getNumFaltasTotales() + 1);
				} else {
					if (!(estadistica.getNumFaltasTotales() <= 0)) {
						estadistica.setNumFaltasTotales(estadistica.getNumFaltasTotales() - 1);
					}
				}
			}
		} else {
			if (accion.equalsIgnoreCase("s")) {
				if (estadistica.getSaquesTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setSaquesTotales(estadistica.getSaquesTotales() - 1);
						if (estadistica.getSaquesAcertados() > 0)
							estadistica.setSaquesAcertados(estadistica.getSaquesAcertados() - 1);
					} else {
						if (estadistica.getSaquesTotales() != estadistica.getSaquesAcertados()) {
							estadistica.setSaquesTotales(estadistica.getSaquesTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("r")) {
				if (estadistica.getRecepcionesTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setRecepcionesTotales(estadistica.getRecepcionesTotales() - 1);
						if (estadistica.getRecepcionesAcertadas() > 0)
							estadistica.setRecepcionesAcertadas(estadistica.getRecepcionesAcertadas() - 1);
					} else {
						if (estadistica.getRecepcionesTotales() != estadistica.getRecepcionesAcertadas()) {
							estadistica.setRecepcionesTotales(estadistica.getRecepcionesTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("c")) {
				if (estadistica.getColocacionesTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setColocacionesTotales(estadistica.getColocacionesTotales() - 1);
						if (estadistica.getColocacionesAcertadas() > 0)
							estadistica.setColocacionesAcertadas(estadistica.getColocacionesAcertadas() - 1);
					} else {
						if (estadistica.getColocacionesTotales() != estadistica.getColocacionesAcertadas()) {
							estadistica.setColocacionesTotales(estadistica.getColocacionesTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("d")) {
				if (estadistica.getDefensasTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setDefensasTotales(estadistica.getDefensasTotales() - 1);
						if (estadistica.getDefensasAcertadas() > 0)
							estadistica.setDefensasAcertadas(estadistica.getDefensasAcertadas() - 1);
					} else {
						if (estadistica.getDefensasTotales() != estadistica.getDefensasAcertadas()) {
							estadistica.setDefensasTotales(estadistica.getDefensasTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("b")) {
				if (estadistica.getBloqueosTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setBloqueosTotales(estadistica.getBloqueosTotales() - 1);
						if (estadistica.getBloqueosAcertados() > 0)
							estadistica.setBloqueosAcertados(estadistica.getBloqueosAcertados() - 1);
					} else {
						if (estadistica.getBloqueosTotales() != estadistica.getBloqueosAcertados()) {
							estadistica.setBloqueosTotales(estadistica.getBloqueosTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("a")) {
				if (estadistica.getRematesTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setRematesTotales(estadistica.getRematesTotales() - 1);
						if (estadistica.getRematesAcertados() > 0)
							estadistica.setRematesAcertados(estadistica.getRematesAcertados() - 1);
					} else {
						if (estadistica.getRematesTotales() != estadistica.getRematesAcertados()) {
							estadistica.setRematesTotales(estadistica.getRematesTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("f")) {
				if (estadistica.getFintasTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setFintasTotales(estadistica.getFintasTotales() - 1);
						if (estadistica.getFintasAcertadas() > 0)
							estadistica.setFintasAcertadas(estadistica.getFintasAcertadas() - 1);
					} else {
						if (estadistica.getFintasTotales() != estadistica.getFintasAcertadas()) {
							estadistica.setFintasTotales(estadistica.getFintasTotales() - 1);
						}
					}
				}
			} else if (accion.equalsIgnoreCase("ar")) {
				if (estadistica.getNumAtaquesRapidosTotales() > 0) {
					if (acierto.equals("+")) {
						estadistica.setNumAtaquesRapidosTotales(estadistica.getNumAtaquesRapidosTotales() - 1);
						if (estadistica.getNumAtaquesRapidosAcertados() > 0)
							estadistica.setNumAtaquesRapidosAcertados(estadistica.getNumAtaquesRapidosAcertados() - 1);
					} else {
						if (estadistica.getNumAtaquesRapidosTotales() != estadistica.getNumAtaquesRapidosAcertados()) {
							estadistica.setNumAtaquesRapidosTotales(estadistica.getNumAtaquesRapidosTotales() - 1);
						}
					}
				}
			}
		}

		return estadistica;
	}



}
