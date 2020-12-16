package org.springframework.samples.petclinic.controller;

import java.security.Principal;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.PruebaCondicionFisica;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.PartidoConAsistencia;
import org.springframework.samples.petclinic.model.auxiliares.PruebasSinJugador;
import org.springframework.samples.petclinic.model.ediciones.PartidoEdit;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
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
	private EstadisticaPersonalPartidoService estadisService;
	
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
	
	@GetMapping("/showpartidos")
	public ModelAndView listadoPartidos(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_PARTIDOS);
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority().equals("jugador")) {
			String username =  principal.getName(); 
	        User  user = userService.findByUsername(username);
	        Jugador jugador = jugadorService.findByUser(user);
			mav.addObject("idJugador", jugador.getId());
			
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
		mav.addObject("estadisticas", estadisService.findByJugador(jugador.getId()));
		
		return mav;
	}
	
	@GetMapping("/showestadisiticasPartido")
	public ModelAndView vistaEstadísticasPartido() {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO);
		mav.addObject("estadisticas", partidoService.findAll());
		
		return mav;
	}
	
	@GetMapping("/showestadisiticasPartidoTodosJugadores")
	public ModelAndView vistaEstadísticasPartidoJugadores(int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO_JUGADORES);
		mav.addObject("estadisticas", estadisService.findByPartido(id));
		
		return mav;
	}
	
	@GetMapping("/showestadisiticasPartidoJugador")
	public ModelAndView vistaEstadísticasPartidoJugador(int jugador_id, int partido_id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_PARTIDO_JUGADOR);
		mav.addObject("estadisticas", estadisService.findByJugadorAndPartido(jugador_id, partido_id));
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
				
				JugadorPartidoStats stats = jugadorPartidoStatsConverter.convertPartidoToPartidoStats(partido.get().getJugadores().get(i));
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
			Optional<Partido> partidoO = partidoService.findById(id);
			Partido partido = partidoO.get();
			PartidoEdit edit = partidoConverter.convertPartidoToPartidoEdit(partido);
			return new ResponseEntity<PartidoEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
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
	

	@RequestMapping(value = "/eliminarjuegaJugador/{partido_id}/{jugador_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarJuegaJugador(@PathVariable("partido_id") int partido_id , @PathVariable("jugador_id") int jugador_id) {
		try {
			
			Partido partido = partidoService.findById(partido_id).get();
			
			List<Jugador> jugadores = partido.getJugadores().stream().filter(x->x.getId()!= jugador_id).collect(Collectors.toList());
			partido.setJugadores(jugadores);
			partidoService.save(partido);
			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	

	@RequestMapping(value = "/addjuegaJugador/{partido_id}/{jugador_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity addJuegaJuegador(@PathVariable("partido_id") int partido_id , @PathVariable("jugador_id") int jugador_id) {
		try {
			Partido partido = partidoService.findById(partido_id).get();
			Jugador jugador = jugadorService.findById(jugador_id).get();
			
			List<Jugador> jugadores = partido.getJugadores();
			jugadores.add(jugador);
			partido.setJugadores(jugadores);
			partidoService.save(partido);
		
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/postpartido")
	public ResponseEntity<List<ObjectError>> addPartido(HttpServletRequest request, @ModelAttribute(name="partido") PartidoEdit partidoEdit, BindingResult result) {
		try {
			Partido partido = new Partido();
			if(!request.getParameter("id").isEmpty()) {
				int id = Integer.parseInt(request.getParameter("id"));
				Optional<Partido> partidoO = partidoService.findById(id);
				partido = partidoO.get();
			}else if(request.getParameter("equipo").trim() != null) {
				Equipo equipo = equipoService.findByCategoria(request.getParameter("equipo").trim());
				partido.setEquipo(equipo);
			}
			
			PartidoEdit edit = new PartidoEdit(partido.getId(), partido.getEquipo().getCategoria(), request.getParameter("fecha"), request.getParameter("hora").trim());
			
			ValidationUtils.invokeValidator(partidoValidator, edit, result);
			
			if(result.hasErrors()) {
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			
			partido.setHora(request.getParameter("hora").trim());
			if(!request.getParameter("fecha").isEmpty()) {
				partido.setFecha(LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
			
			Partido match = partidoService.savePartido(partido);
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/removePartido/{id}")
	public ResponseEntity removePartido(@PathVariable("id") int id, Model model) {
		try {
			LOG.info("SE PROCEDE A BORRAR EL PARTIDO");
			
			partidoService.deleteById(id);
			
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/* @PostMapping("/updatepartido")
	public String updatePartido(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Optional<Partido> partidoO = partidoService.findById(id);
		Partido partido = partidoO.get();
		
		partido.setHora(request.getParameter("hora").trim());
		partido.setFecha(LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		Partido match = partidoService.savePartido(partido);
		
		return "redirect:/partidos/showpartidos";	
	} */
	
}