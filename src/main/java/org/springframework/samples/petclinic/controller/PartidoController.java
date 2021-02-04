package org.springframework.samples.petclinic.controller;

import java.security.Principal;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.PartidoValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.JugadorPartidoStatsConverter;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.converter.PersonalConverter;
import org.springframework.samples.petclinic.converter.ViajeConverter;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoViaje;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.model.Autobus;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalPartido;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.JugadorPartidoViaje;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
import org.springframework.samples.petclinic.model.auxiliares.PartidoPuntos;
import org.springframework.samples.petclinic.model.auxiliares.PersonalEdit;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasDeUnJugadorStats;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasPersonalesStats;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.samples.petclinic.model.estadisticas.PartidoStats;
import org.springframework.samples.petclinic.service.AutorizacionService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/partidos")
public class PartidoController {
		
	private static final Log LOG = LogFactory.getLog(PartidoController.class);
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private PartidoConverter partidoConverter;
	
	@Autowired
	private JugadorPartidoStatsConverter jugadorPartidoStatsConverter;
	
	@Autowired
	private EstadisticaPersonalPartidoService estadisticaPersonalPartidoService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PartidoValidator partidoValidator;
	
	@Autowired
	private DataPosicionConverter dataPosicionConverter;
	
	@Autowired
	private EstadisticasConverter estadisticasConverter;
	
	@Autowired
	private ViajeService viajeService;
	
	@Autowired
	private PersonalesService personalService;
	
	@Autowired
	private PersonalConverter personalConverter;
	
	@Autowired
	private AutorizacionService autorizacionService;
	
	@Autowired
	private ViajeConverter viajeConverter;
	
	private Autobus bus;
	
	@PostConstruct
	private void init() {
		bus = new Autobus();
		bus.setId(1);
	}
	
	@GetMapping("/showpartidos")
	public ModelAndView listadoPartidos(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_PARTIDOS);
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority().equals("jugador")) {
			String username =  principal.getName(); 
	        User  user = userService.findByUsername(username);
	        Jugador jugador = jugadorService.findByUser(user);
	        Autorizacion autorizacion=autorizacionService.findByJugadorAndTipo(jugador, TipoAutorizacion.TRANSPORTE);
	        if(autorizacion==null) {
	        	mav.addObject("autorizacion", false);
	        }else {
	        	 mav.addObject("autorizacion", true);
	        }
	        mav.addObject("jugador", jugador);
			
		}
		return mav;
	}
	
	
	@GetMapping("/showpartido")
	public Partido partido(int id) {
		Optional<Partido> partido = partidoService.findById(id);
		return partido.get();
	}
	
	public class GetUserWithHTTPServletRequestController {
		 
	    @RequestMapping(value = "/username", method = RequestMethod.GET)
	    @ResponseBody
	    public String currentUserNameSimple(HttpServletRequest request) {
	        Principal principal = request.getUserPrincipal();
	        return principal.getName();
	    }
	}
	
	@GetMapping("/showestadisiticasJugadores")
	public ModelAndView vistaEstadísticas(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
        String username =  principal.getName(); 
        User  user = userService.findByUsername(username);
        Jugador jugador = jugadorService.findByUser(user);
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_PARTIDO);
		mav.addObject("estadisticas", estadisticaPersonalPartidoService.findByJugador(jugador.getId()));
		
		List<String> categorias = new ArrayList<String>();
		categorias.addAll(jugador.getEquipos().stream().map(x->x.getCategoria()).sorted().collect(Collectors.toList()));
        mav.addObject("categorias", categorias);
		
        return mav;
	}
	
	@GetMapping("/showestadisiticasPartido")
	public ModelAndView vistaEstadísticasPartido(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO);
		mav.addObject("estadisticas", partidoService.findAll());
		
		List<String> categorias = new ArrayList<String>();
		Principal principal = request.getUserPrincipal();
		String username =  principal.getName(); 
        User  user = userService.findByUsername(username);
        Entrenador entrenador = entrenadorService.findByUser(user);
        categorias.addAll(entrenador.getEquipos().stream().map(x->x.getCategoria()).sorted().collect(Collectors.toList()));
        mav.addObject("categorias", categorias);
		return mav;
	}
	
	@RequestMapping(value = "findestadisticasJugador/{categoria}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EstadisticasDeUnJugadorStats>> graficoEstadisticasTodosLosPartidosDeUnJugador(@PathVariable("categoria") String categoria, HttpServletRequest request) {
		try {
			
			List<String> categorias = new ArrayList<String>();
			
			Principal principal = request.getUserPrincipal();
			String username =  principal.getName(); 
	        User  user = userService.findByUsername(username);
	        Jugador jugador = jugadorService.findByUser(user);
	        
	        categorias.addAll(jugador.getEquipos().stream().map(x->x.getCategoria()).collect(Collectors.toList()));
			
			List<EstadisticaPersonalPartido> estadisticasJugador = new ArrayList<EstadisticaPersonalPartido>();
			List<EstadisticasDeUnJugadorStats> estadisticas = new ArrayList<EstadisticasDeUnJugadorStats>();
			
		
				Equipo equipo = equipoService.findByCategoria(categoria);
				estadisticasJugador = estadisticaPersonalPartidoService.findByJugador(jugador.getId());
			
			
			for (int i = 0; i < estadisticasJugador.size();i++) {
				if(estadisticasJugador.get(i).getPartido().getEquipo().equals(equipo)) {
					EstadisticasDeUnJugadorStats estadistica = estadisticasConverter.convertEstadisticasPersonalesToJugadorStats(estadisticasJugador.get(i));
					estadisticas.add(estadistica);
				}
				
			}
			
			DataTableResponse<EstadisticasDeUnJugadorStats> data = new DataTableResponse<EstadisticasDeUnJugadorStats>();
			data.setData(estadisticas);
			return new ResponseEntity<DataTableResponse<EstadisticasDeUnJugadorStats>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EstadisticasDeUnJugadorStats>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "findestadisticasPartidos/{categoria}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<PartidoStats>> graficoEstadisticasTodosLosPartidos(@PathVariable("categoria") String categoria, HttpServletRequest request) {
		try {
			
			List<String> categorias = new ArrayList<String>();
			
			Principal principal = request.getUserPrincipal();
			String username =  principal.getName(); 
	        User  user = userService.findByUsername(username);
	        Entrenador entrenador = entrenadorService.findByUser(user);
	        
	        categorias.addAll(entrenador.getEquipos().stream().map(x->x.getCategoria()).collect(Collectors.toList()));
			
			List<Partido> partidos = new ArrayList<Partido>();
			List<PartidoStats> partidosStats = new ArrayList<PartidoStats>();
			
			if(categoria.equals("todo")) {
				for (int i=0; i<categorias.size();i++) {
					Equipo equipo = equipoService.findByCategoria(categorias.get(i));
					List<Partido> partidosEquipo = partidoService.findByEquipo(equipo);
					partidos.addAll(partidosEquipo);
				}
			}else {
				Equipo equipo = equipoService.findByCategoria(categoria);
				partidos = partidoService.findByEquipo(equipo);
			}
			
			for (int i = 0; i < partidos.size();i++) {
				PartidoStats partidoStats = partidoConverter.convertPartidoToPartidoStats(partidos.get(i));
				partidosStats.add(partidoStats);
			}
			
			DataTableResponse<PartidoStats> data = new DataTableResponse<PartidoStats>();
			data.setData(partidosStats);
			return new ResponseEntity<DataTableResponse<PartidoStats>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<PartidoStats>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping("/showestadisiticasPartidoTodosJugadores")
	public ModelAndView vistaEstadísticasPartidoJugadores(int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO_JUGADORES);
		mav.addObject("estadisticas", estadisticaPersonalPartidoService.findByPartido(id));
		
		return mav;
	}
	
	@RequestMapping(value = "/findestadisticasPartidosTodosLosJugadores/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EstadisticasPersonalesStats>> graficoEstadisticasTodosLosPartidos(@PathVariable("id") int id, HttpServletRequest request) {
		try {
			
			
			List<EstadisticaPersonalPartido> estadisticasPersonalesPartidos = estadisticaPersonalPartidoService.findByPartido(id);
			List<EstadisticasPersonalesStats> estadisticasPersonalesStats = new ArrayList<EstadisticasPersonalesStats>();
			
			
			for (int i = 0; i < estadisticasPersonalesPartidos.size();i++) {
				EstadisticasPersonalesStats estadisticaPersonalesStats = estadisticasConverter.convertEstadisticasToEstadisticasStats(estadisticasPersonalesPartidos.get(i));
				estadisticasPersonalesStats.add(estadisticaPersonalesStats);
			}
			
			DataTableResponse<EstadisticasPersonalesStats> data = new DataTableResponse<EstadisticasPersonalesStats>();
			data.setData(estadisticasPersonalesStats);
			return new ResponseEntity<DataTableResponse<EstadisticasPersonalesStats>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EstadisticasPersonalesStats>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping("/showestadisiticasPartidoJugador")
	public ModelAndView vistaEstadísticasPartidoJugador(int jugador_id, int partido_id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO_JUGADOR);
		mav.addObject("estadisticas", estadisticaPersonalPartidoService.findByJugadorAndPartido(jugador_id, partido_id));
		return mav;
	}
	
	@GetMapping("/showJugadorPosicionPartido")
	public ModelAndView vistaJugadorPosicionPartido(int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_PARTIDO_JUGADOR_POSICION);
		mav.addObject("partido", partidoService.findById(id).get());
		return mav;
	}
	
	@RequestMapping(value = "findJugadorPosicionPartido/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataPosicion> graficoJugadorPosicionPartido(@PathVariable("id") int id) {
		try {
			Optional<Partido> partido = partidoService.findById(id);
			List<JugadorPartidoStats> listaJugadorStats = new ArrayList<JugadorPartidoStats>();
			for (int i = 0; i<partido.get().getJugadores().size();i++) {
				
				JugadorPartidoStats stats = jugadorPartidoStatsConverter.convertJugadorToJugadorPartidoStats(partido.get().getJugadores().get(i));
				listaJugadorStats.add(stats);
			}
			
			
			DataPosicion data = dataPosicionConverter.convertPartidoToPartidoStats(listaJugadorStats);
			return new ResponseEntity<DataPosicion>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataPosicion>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	
	@GetMapping("/partidoform")
	public String redirectPartidoForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Optional<Partido> partido = Optional.of(new Partido());
		if(id != 0) {
			partido = partidoService.findById(id);
		}
		model.addAttribute("partido", partido);
		return ViewConstant.VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
	}
	
	@RequestMapping(value = "findeditpartido/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PartidoEdit> findPartido(@PathVariable("id") int id) {
		try {
			LOG.info("Buscamos el partido con el id: " + id);
			Optional<Partido> partidoO = partidoService.findById(id);
			Partido partido = partidoO.get();
			PartidoEdit edit = partidoConverter.convertPartidoToPartidoEdit(partido);
			return new ResponseEntity<PartidoEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando el partido para editar");
			return new ResponseEntity<PartidoEdit>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "/findPartidos", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<PartidoConAsistencia>> listadoDePartidos(HttpServletRequest request) {
		try {
			List<PartidoConAsistencia> partidosSinEquipo = new ArrayList<PartidoConAsistencia>();
			List<Partido> partidos = partidoService.findByFechaAfter(LocalDate.now().minusDays(1));
			
			Principal principal = request.getUserPrincipal();
			List<String> categorias = new ArrayList<String>();
			
			if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority().equals("jugador")) {
				String username =  principal.getName(); 
		        User  user = userService.findByUsername(username);
		        Jugador jugador = jugadorService.findByUser(user);
		        categorias.addAll(jugador.getEquipos().stream().map(x->x.getCategoria()).collect(Collectors.toList()));
		        
			}else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority().equals("entrenador")){
				String username =  principal.getName(); 
		        User  user = userService.findByUsername(username);
		        Entrenador entrenador = entrenadorService.findByUser(user);
		        categorias.addAll(entrenador.getEquipos().stream().map(x->x.getCategoria()).collect(Collectors.toList()));
		        
			}else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority().equals("estadistico")){
				categorias.addAll(equipoService.findAll().stream().map(x->x.getCategoria()).collect(Collectors.toList()));
			}
			
			List<Partido> partidosFiltrados = partidos.stream().filter(x->categorias.contains(x.getEquipo().getCategoria())).collect(Collectors.toList());
					
			for(int i = 0; i<partidosFiltrados.size();i++) {
				PartidoConAsistencia partidoSinEquipo = partidoConverter.convertPartidoToPartidoConAsistencia(partidosFiltrados.get(i));
				partidosSinEquipo.add(partidoSinEquipo);
			}
			DataTableResponse<PartidoConAsistencia> data = new DataTableResponse<PartidoConAsistencia>();
			data.setData(partidosSinEquipo);
			
			return new ResponseEntity<DataTableResponse<PartidoConAsistencia>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<PartidoConAsistencia>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "findjugadorespartidoAutobus/{partido_id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadorPartidoViaje>> findJugadoresPartidoAutobus(@PathVariable("partido_id") int partido_id) {
		try {
			LOG.info("Buscamos el partido con el id: " + partido_id);
			
			Partido partido = partidoService.findById(partido_id).get();
			List<Jugador> jugadores = partido.getJugadores();
			List<JugadorPartidoViaje> jugadoresPartidoViajes = new ArrayList<JugadorPartidoViaje>();
			for (int i=0; i<jugadores.size();i++) {
				Viaje viajeJugador = viajeService.findByJugadorAndPartidoAndTipoViaje(jugadores.get(i), partido, TipoViaje.IDA);
				if(viajeJugador !=null && viajeJugador.getPersonal() == null) {
					JugadorPartidoViaje jugadorPartidoViaje = viajeConverter.convertViajeToJugadorPartidoViaje(viajeJugador);
					jugadoresPartidoViajes.add(jugadorPartidoViaje);
				}
			}
			
			DataTableResponse<JugadorPartidoViaje> data = new DataTableResponse<JugadorPartidoViaje>();
			data.setData(jugadoresPartidoViajes);
			return new ResponseEntity<DataTableResponse<JugadorPartidoViaje>>(data, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando al buscar jugadores del partido");
			return new ResponseEntity<DataTableResponse<JugadorPartidoViaje>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "findjugadorespartidoPersonales/{partido_id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadorPartidoViaje>> findJugadoresPartidoPersonales(@PathVariable("partido_id") int partido_id) {
		try {
			LOG.info("Buscamos el partido con el id: " + partido_id);
			
			Partido partido = partidoService.findById(partido_id).get();
			List<Jugador> jugadores = partido.getJugadores();
			List<JugadorPartidoViaje> jugadoresPartidoViajes = new ArrayList<JugadorPartidoViaje>();
			for (int i=0; i<jugadores.size();i++) {
				Viaje viajeJugador = viajeService.findByJugadorAndPartidoAndTipoViaje(jugadores.get(i), partido, TipoViaje.IDA);
				if(viajeJugador !=null && viajeJugador.getPersonal() != null) {
					JugadorPartidoViaje jugadorPartidoViaje = viajeConverter.convertViajeToJugadorPartidoViaje(viajeJugador);
					jugadoresPartidoViajes.add(jugadorPartidoViaje);
				}
			}
			
			DataTableResponse<JugadorPartidoViaje> data = new DataTableResponse<JugadorPartidoViaje>();
			data.setData(jugadoresPartidoViajes);
			return new ResponseEntity<DataTableResponse<JugadorPartidoViaje>>(data, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando al buscar jugadores del partido");
			return new ResponseEntity<DataTableResponse<JugadorPartidoViaje>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "/confirmacionLlegada/{viaje_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity confirmarLlegadaAPartidoJugador(@PathVariable("viaje_id") int viaje_id ) {
		try {
			LOG.info("Buscamos el viaje con el id: " + viaje_id);
			Viaje viaje = viajeService.findById(viaje_id).get();
			
			viaje.setHaLlegado(true);
			LOG.info("Actualizamos el campo haLlegado de false a true");
			viajeService.save(viaje);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el viaje");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/confirmacionDeLaNoLlegada/{viaje_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity confirmacionDeLaNoLlegada(@PathVariable("viaje_id") int viaje_id ) {
		try {
			LOG.info("Buscamos el viaje con el id: " + viaje_id);
			Viaje viaje = viajeService.findById(viaje_id).get();
			
			viaje.setHaLlegado(false);
			LOG.info("Actualizamos el campo haLlegado de true a false");
			viajeService.save(viaje);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción actualizando el viaje");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(value = "/eliminarjuegaJugador/{partido_id}/{jugador_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarJuegaJugador(@PathVariable("partido_id") int partido_id , @PathVariable("jugador_id") int jugador_id) {
		try {
			LOG.info("Buscamos el partido con el id: " + partido_id);
			Partido partido = partidoService.findById(partido_id).get();
			
			LOG.info("Filtramos los jugadores para encontrar dentro del partido todos los que no tienen el id: " + jugador_id);
			List<Jugador> jugadores = partido.getJugadores().stream().filter(x->x.getId()!= jugador_id).collect(Collectors.toList());
			partido.setJugadores(jugadores);
			LOG.info("Actualizamos el partido de id=" + partido_id + " sin el jugador con id=" + jugador_id);
			partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción eliminando el jugador del partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	

	@RequestMapping(value = "/addjuegaJugador/{partido_id}/{jugador_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PartidoConAsistencia>> addJuegaJuegador(@PathVariable("partido_id") int partido_id , @PathVariable("jugador_id") int jugador_id) {
		try {
			Partido partido = partidoService.findById(partido_id).get();
			PartidoConAsistencia partidoConAsistencia = partidoConverter.convertPartidoToPartidoConAsistencia(partido);
			Jugador jugador = jugadorService.findById(jugador_id).get();
				
			List<Equipo> equiposJugador = jugador.getEquipos();
			
			List<PartidoConAsistencia> partidosConfrontados = partidoService.obtenerPartidosConfrontados(equiposJugador, jugador, partido);
			
			if(partidosConfrontados.size() == 1) {
				partidosConfrontados.add(partidoConAsistencia);
				return new ResponseEntity<List<PartidoConAsistencia>>(partidosConfrontados, HttpStatus.OK);
			}
			
			List<Jugador> jugadores = partido.getJugadores();
			jugadores.add(jugador);
			partido.setJugadores(jugadores);
			partidoService.save(partido);
		
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción añadiendo el jugador al partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/postpartido")
	public ResponseEntity<List<ObjectError>> addPartido(HttpServletRequest request, @ModelAttribute(name="partido") PartidoEdit partidoEdit, BindingResult result) {
		try {
			Partido partido = new Partido();
			if(!request.getParameter("id").isEmpty()) {
				int id = Integer.parseInt(request.getParameter("id"));
				LOG.info("Estamos editando el partido con el id: " + id);
				Optional<Partido> partidoO = partidoService.findById(id);
				partido = partidoO.get();
			}else if(request.getParameter("equipo").trim() != null) {
				LOG.info("Estamos creando un partido nuevo para el equipo con categoría: " + request.getParameter("equipo").trim());
				Equipo equipo = equipoService.findByCategoria(request.getParameter("equipo").trim());
				partido.setEquipo(equipo);
			}
			
			PartidoEdit edit = new PartidoEdit(partido.getId(), partido.getEquipo().getCategoria(), request.getParameter("fecha"), request.getParameter("hora").trim());
			
			ValidationUtils.invokeValidator(partidoValidator, edit, result);
			
			if(result.hasErrors()) {
				LOG.warn("Se han encontrado " + result.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			
			partido.setHora(request.getParameter("hora").trim());
			if(!request.getParameter("fecha").isEmpty()) {
				partido.setFecha(LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
			
			LOG.info("Procedemos a guardar el partido");
			Partido match = partidoService.save(partido);
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("Error al guardar el partido");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/removePartido/{id}")
	public ResponseEntity removePartido(@PathVariable("id") int id, Model model) {
		try {
			LOG.info("Se procede a borrar el partido con id: " + id);
			
			partidoService.deleteById(id);
			
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error al eliminar el partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/findPartidosEquipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<PartidoPuntos>> listadoDePartidosEquipo(@PathVariable("id") int id,HttpServletRequest request) {
		try {
			Equipo equipo = equipoService.findById(id).get();
			List<Partido> partidos = partidoService.findByEquipo(equipo);
			DataTableResponse<PartidoPuntos> data = new DataTableResponse<PartidoPuntos>();
			data.setData(partidoConverter.convertListPartidoToListPartidoPuntos(partidos));
			return new ResponseEntity<DataTableResponse<PartidoPuntos>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<PartidoPuntos>>(HttpStatus.BAD_REQUEST);
		}	
	}
	

	@RequestMapping(value = "findVehiculos/{id}/{tipoViaje}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<PersonalEdit>> findVehiculos(@PathVariable("id") int id, @PathVariable("tipoViaje") String tipoViaje,HttpServletRequest request) {
		
			
		try {
			String username =request.getUserPrincipal().getName();
			User user= userService.findByUsername(username);
			Jugador jugador= jugadorService.findByUser(user);
			Integer jugadorId= jugador.getId();
			
			Set<Personales> sPersonales = personalService.findByJugador(jugadorId);
			Partido partido= partidoService.findById(id).get();
			sPersonales.addAll(viajeService.findPersonalesByPartidoAndTipoViaje(partido, tipoViaje));
			
			List<Personales> lpersonales = sPersonales.stream().collect(Collectors.toList());
			
			PersonalEdit personalEdit = new PersonalEdit();
			List<PersonalEdit> lpersonalEdit= new ArrayList<PersonalEdit>();
			
			
			for (int i = 0; i < lpersonales.size();i++) {
				personalEdit=personalConverter.convertPersonalToPersonalEdit(lpersonales.get(i));
				lpersonalEdit.add(personalEdit);
				
			}
			
			DataTableResponse<PersonalEdit> data = new DataTableResponse<PersonalEdit>();
			data.setData(lpersonalEdit);
			return new ResponseEntity<DataTableResponse<PersonalEdit>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<PersonalEdit>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/postviaje")
	public ResponseEntity<List<ObjectError>> addViaje(HttpServletRequest request, @ModelAttribute(name="personalEdit") PersonalEdit personalEdit, BindingResult result) {
		
		try {
			String username =request.getUserPrincipal().getName();
			User user= userService.findByUsername(username);
			Jugador jugador= jugadorService.findByUser(user);
			
			Partido partido;
			if(!request.getParameter("idPartido").isEmpty()) {
				int idPartido = Integer.parseInt(request.getParameter("idPartido"));
				LOG.info("Estamos creando un viaje para el partido con el id: " + idPartido);
				Optional<Partido> partidoO = partidoService.findById(idPartido);
				partido = partidoO.get();
			} else {
				LOG.info("El id del partido ha llegado vacío");
				return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
			}
			
			String tipoViaje = request.getParameter("tipoViaje");
			Integer propietario = Integer.valueOf(request.getParameter("propietario"));
			
			if(!"IDAYVUELTA".equals(tipoViaje)) {
				final Viaje viaje = setViajePersonal(jugador, partido, TipoViaje.fromNombre(tipoViaje), propietario);
				LOG.info("Procedemos a guardar el viaje de tipo " + tipoViaje);
				viajeService.save(viaje);
			}else if("IDAYVUELTA".equals(tipoViaje)){
				final Viaje viajeIda = setViajePersonal(jugador, partido, TipoViaje.fromNombre("IDA"), propietario);
				final Viaje viajeVuelta = setViajePersonal(jugador, partido, TipoViaje.fromNombre("VUELTA"), propietario);
				
				LOG.info("Procedemos a guardar el viaje de tipo " + "ida");
				viajeService.save(viajeIda);
				LOG.info("Procedemos a guardar el viaje de tipo " + "vuelta");
				viajeService.save(viajeVuelta);
				
			}else {
				LOG.warn("Valor del tipo de viaje inválido");
				return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("Error al guardar el viaje");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping("/postbus")
	public ResponseEntity<List<ObjectError>> addViajeBus(HttpServletRequest request) {
		
		try {
			String username =request.getUserPrincipal().getName();
			User user= userService.findByUsername(username);
			Jugador jugador= jugadorService.findByUser(user);
			
			Partido partido;
			if(!request.getParameter("idPartidoBus").isEmpty()) {
				int idPartido = Integer.parseInt(request.getParameter("idPartidoBus"));
				LOG.info("Estamos creando un viaje para el partido con el id: " + idPartido);
				Optional<Partido> partidoO = partidoService.findById(idPartido);
				partido = partidoO.get();
			}else {
				return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
			}
			
			String tipoViaje = request.getParameter("tipoViaje");
			
			if(!"IDAYVUELTA".equals(tipoViaje)) {
				final Viaje viaje = setViajeBus(jugador, partido, TipoViaje.fromNombre(tipoViaje));
				LOG.info("Procedemos a guardar el viaje de tipo " + tipoViaje);
				viajeService.save(viaje);
			}else if("IDAYVUELTA".equals(request.getParameter("tipoViaje"))){
				final Viaje viajeIda = setViajeBus(jugador, partido, TipoViaje.fromNombre("IDA"));
				final Viaje viajeVuelta = setViajeBus(jugador, partido, TipoViaje.fromNombre("VUELTA"));
				
				LOG.info("Procedemos a guardar el viaje de tipo " + "ida");
				viajeService.save(viajeIda);
				LOG.info("Procedemos a guardar el viaje de tipo " + "vuelta");
				viajeService.save(viajeVuelta);
			}else {
				LOG.warn("Valor del tipo de viaje inválido");
				return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("Error al guardar el viaje");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Método para editar o crear un nuevo viaje con vehículo personal
	private Viaje setViajePersonal(Jugador jugador, Partido partido, TipoViaje tipoViaje, Integer propietario) {
		Viaje viaje = viajeService.findByJugadorAndPartidoAndTipoViaje(jugador, partido, tipoViaje);
		if(viaje!=null) {
			if(viaje.getAutobus()==null) {
				viaje.setPersonal(personalService.findById(propietario).get());
			}else {
				viaje.setAutobus(null);
				viaje.setPersonal(personalService.findById(propietario).get());
			}
		}else {
				viaje = new Viaje();
				viaje.setJugador(jugador);
				viaje.setPartido(partido);
				viaje.setPersonal(personalService.findById(propietario).get());
				viaje.setTipoViaje(tipoViaje);
		}
		return viaje;
	}
	
	// Método para editar o crear un nuevo viaje con un bus
	private Viaje setViajeBus(Jugador jugador, Partido partido, TipoViaje tipoViaje) {
		Viaje viaje = viajeService.findByJugadorAndPartidoAndTipoViaje(jugador, partido, tipoViaje);
		if(viaje!=null) {
			if(viaje.getAutobus()==null) {
				
				viaje.setAutobus(bus);
				viaje.setPersonal(null);
			}
		}else {
			viaje = new Viaje();
			viaje.setJugador(jugador);
			viaje.setPartido(partido);
			viaje.setAutobus(bus);
			viaje.setTipoViaje(tipoViaje);
		}
		return viaje;
	}
	
}