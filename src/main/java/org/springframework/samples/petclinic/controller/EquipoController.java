package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.EquipoValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.converter.EquipoConverter;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.converter.JugadorPartidoStatsConverter;
import org.springframework.samples.petclinic.enumerate.Actitud;
import org.springframework.samples.petclinic.enumerate.Sistema;
import org.springframework.samples.petclinic.model.Capitan;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.NumCamiseta;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.EquipoCategoria;
import org.springframework.samples.petclinic.model.auxiliares.EquipoTablaEquipos;
import org.springframework.samples.petclinic.model.auxiliares.JugadorDTO;
import org.springframework.samples.petclinic.model.auxiliares.JugadoresInEquipoSinUser;
import org.springframework.samples.petclinic.model.ediciones.EquipoEdit;
import org.springframework.samples.petclinic.model.estadisticas.EquipoStats;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.samples.petclinic.service.CapitanService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.PartidoService;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/equipos")
public class EquipoController {
	
	private static final Log LOG = LogFactory.getLog(EquipoController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Autowired
	private PartidoService partidoService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EquipoValidator equipoValidator;
	
	@Autowired
	private JugadorConverter jugadorConverter;
	
	@Autowired
	private CapitanService capitanService;
	
	@Autowired
	private EquipoConverter equipoConverter;
	
	@Autowired
	private JugadorPartidoStatsConverter jugadorPartidoStatsConverter;
	
	@Autowired
	private DataPosicionConverter dataPosicionConverter;
	
	@Autowired
	private NumCamisetaService numCamisetaService;
	
	@GetMapping("/showequipos")
	public String listadoEquipos(Model model) {
		
		
		model.addAttribute("equipos", equipoService.findAll());
		model.addAttribute("jugadores", jugadorService.findAll());
		return ViewConstant.VIEW_EQUIPOS;
	}
	
	@RequestMapping(value = "/findEquipos", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EquipoTablaEquipos>> listadoDeEquipos(HttpServletRequest request) {
		try {
			List<EquipoTablaEquipos> listaDeEquipos = new ArrayList<EquipoTablaEquipos>();
			List<Equipo> equipos = equipoService.findAll();
			
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
		        
			}
			
			List<Equipo> equiposFiltrados = equipos.stream().filter(x->categorias.contains(x.getCategoria())).collect(Collectors.toList());
					
			for(int i = 0; i<equiposFiltrados.size();i++) {
				EquipoTablaEquipos equipoTablaEquipos = equipoConverter.convertEquipoToEquipoTablaEquipo(equiposFiltrados.get(i));
				listaDeEquipos.add(equipoTablaEquipos);
			}
			DataTableResponse<EquipoTablaEquipos> data = new DataTableResponse<EquipoTablaEquipos>();
			data.setData(listaDeEquipos);
			
			return new ResponseEntity<DataTableResponse<EquipoTablaEquipos>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EquipoTablaEquipos>>(HttpStatus.BAD_REQUEST);
		}	
	}

	
	@GetMapping("/showequipo/{id}")
	public ModelAndView equipo(@PathVariable("id") int id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String username = "";
									
		if(principal == null) {
			username = "";
//			ModelAndView mav = new ModelAndView("/login");
//			return mav;
		}else {
			username =  principal.getName();
		}
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_EQUIPO);
		mav.addObject("username", username);
		Optional<Equipo> eq = equipoService.findById(id);
		Equipo equipo = eq.get();
		mav.addObject("equipo", equipo);
		mav.addObject("jugadores", jugadorConverter.convertListJugadorToListJugadorInEquipo(equipo.getJugadores()));
		return mav;
	}
	
	@RequestMapping(value = "/findEquipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadoresInEquipoSinUser>> findEquipo(@PathVariable("id") int id, HttpServletRequest request) {
		try {
			Equipo equipo = equipoService.findById(id).get();
			DataTableResponse<JugadoresInEquipoSinUser> data = new DataTableResponse<JugadoresInEquipoSinUser>();
			data.setData(jugadorConverter.convertListJugadorToListJugadorInEquipoSinUser(equipo.getJugadores()));
			return new ResponseEntity<DataTableResponse<JugadoresInEquipoSinUser>>(data, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<DataTableResponse<JugadoresInEquipoSinUser>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "/findEquipoEliminar/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<EquipoCategoria> findEquipoEliminar(@PathVariable("id") int id) {
		try {
			Equipo equipo = equipoService.findById(id).get();
			EquipoCategoria data = equipoConverter.convertEquipoToEquipoCategoria(equipo);
			return new ResponseEntity<EquipoCategoria>(data, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<EquipoCategoria>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping("/equipoform")
	public String redirectEquipoForm(@RequestParam(name="id",required=false) int id, Model model) {
		Optional<Equipo> equipo = Optional.of(new Equipo());
		if(id != 0) {
			equipo = equipoService.findById(id);
		}
		model.addAttribute("equipo", equipo);
		return ViewConstant.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
	}
	
	@RequestMapping(value = "getallteams", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findEquipos() {
		try {
			List<String> equipos = equipoService.findCategoria();
			return new ResponseEntity<List<String>>(equipos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/addequipo")
	public String addEquipo(@Valid @ModelAttribute(name="equipo") Equipo equipo, BindingResult bindResult, Model model) {
		
		LOG.info("addequipo() -- PARAMETROS: "+ equipo.toString());
		
		ValidationUtils.invokeValidator(equipoValidator, equipo, bindResult);
		
		if (bindResult.hasErrors()) {
			LOG.warn("Se han obtenido " + bindResult.getErrorCount() + " errores de validación");
			model.addAttribute("equipo", equipo);
			return ViewConstant.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
		}
		
		try {
			Equipo equipoSave = equipoService.save(equipo);
			LOG.info("Se ha guardado el equipo con éxito: " + equipoSave);
		} catch (Exception e) {
			LOG.error("No se ha podido guardar el equipo");
		}
		
		return "redirect:/equipos/showequipos";
		
	}
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	@RequestMapping(value = "eliminarequipo/{id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> eliminarEquipo(@PathVariable("id") Integer id) {
		try {
			LOG.info("Se procede a borrar el equipo con id: " + id);
			
			equipoService.deleteById(id);
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error al eliminar el equipo");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/addjugador/{jugadorID}/{equipoID}")
	public String agregarJugador(@PathVariable("jugadorID") int jugadorID, @PathVariable("equipoID") int equipoID) {
		
		LOG.info("Se procede a añadir el jugador con id=" + jugadorID + " en el equipo con id=" + equipoID);
		
		Equipo equipo = equipoService.findById(equipoID).get();
		List<Jugador> jugadores = equipo.getJugadores();
		Jugador jugador = jugadorService.findById(jugadorID).get();
		jugadores.add(jugador);
		equipo.setJugadores(jugadores);
		Equipo team = equipoService.save(equipo);
		
		//número de camiseta
		int size = numCamisetaService.findByEquipo(equipoID).stream().map(x->x.getNumero()).collect(Collectors.toList()).size();
		OptionalInt numeroMaxOp = numCamisetaService.findByEquipo(equipoID).stream().map(x->x.getNumero()).mapToInt(Integer::intValue).max();
		int numeroMax;
		if(size==0) {
			numeroMax = 0;
		}
		else {
			numeroMax = numeroMaxOp.getAsInt();
		}
		NumCamiseta num = new NumCamiseta(team,jugador,numeroMax+1);
		NumCamiseta number = numCamisetaService.save(num);
		
		return "redirect:/equipos/showequipo/"+equipoID;
		
	}
	
	@GetMapping("/eliminarjugador/{jugadorID}/{equipoID}")
	public String eliminarJugador(@PathVariable("jugadorID") int jugadorID, @PathVariable("equipoID") int equipoID) {
		
		LOG.info("Se procede a eliminar el jugador con id=" + jugadorID + " del equipo con id=" + equipoID);
		Equipo equipo = equipoService.findById(equipoID).get();
		List<Jugador> jugadores = equipo.getJugadores();
		Jugador jugador = jugadorService.findById(jugadorID).get();
		
		//Eliminar numero de camiseta
		numCamisetaService.deleteByJugadorEquipo(jugador, equipo);
		
		jugadores.remove(jugador);
		equipo.setJugadores(jugadores);
		Equipo team = equipoService.save(equipo);
		
		return "redirect:/equipos/showequipo/"+equipoID;
		
	}
	
	@RequestMapping(value = "findEstadisticasEquipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<EquipoStats> graficoEstadisticasEquipo(@PathVariable("id") int id) {
		try {
			Optional<Equipo> equipoO = equipoService.findById(id);
			Equipo equipo = equipoO.get();
			EquipoStats stats = equipoConverter.convertEquipoToEquipoStats(equipo);
			return new ResponseEntity<EquipoStats>(stats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<EquipoStats>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "findJugadorPosicionEquipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataPosicion> graficoJugadorPosicionEquipo(@PathVariable("id") int id) {
		try {
			Optional<Equipo> equipo = equipoService.findById(id);
			List<JugadorPartidoStats> listaJugadorStats = new ArrayList<JugadorPartidoStats>();
			for (int i = 0; i<equipo.get().getJugadores().size();i++) {
				
				JugadorPartidoStats stats = jugadorPartidoStatsConverter.convertPartidoToPartidoStats(equipo.get().getJugadores().get(i));
				listaJugadorStats.add(stats);
			}
			
			
			DataPosicion data = dataPosicionConverter.convertPartidoToPartidoStats(listaJugadorStats);
			return new ResponseEntity<DataPosicion>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataPosicion>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "findeditequipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<EquipoEdit> editarEquipo(@PathVariable("id") int id) {
		try {
			Equipo equipo = equipoService.findById(id).get();
			EquipoEdit edit = equipoConverter.convertEquipoToEquipoEdit(equipo);
			return new ResponseEntity<EquipoEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<EquipoEdit>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value = "/postequipo", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> addEquipo(HttpServletRequest request, @ModelAttribute(name="equipo") EquipoEdit equipoEdit, BindingResult result) {
		try {
			EquipoEdit edit = new EquipoEdit(null, request.getParameter("categoria").trim(), Sistema.valueOf(request.getParameter("sistemajuego").trim()), request.getParameter("liga").trim(),false);
			
			//ValidationUtils.invokeValidator(partidoValidator, edit, result);
			
			if(result.hasErrors()) {
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			Equipo equipo = new Equipo(request.getParameter("categoria").trim(), Sistema.valueOf(request.getParameter("sistemajuego").trim()), request.getParameter("liga").trim());
			List<Jugador> jugadores = jugadorService.findAll();
			List<Jugador> agregados = new ArrayList<Jugador>();
			for(int i=0; i<jugadores.size(); i++) {
				String añadido = request.getParameter(String.valueOf(i+1));
				if(añadido.equals("true")) {
					agregados.add(jugadores.get(i));
				}
			}
			
			//añadir jugadores
			equipo.setJugadores(agregados);
			
			//añadir capitan
			Optional<Jugador> capi = jugadorService.findById(Integer.valueOf(request.getParameter("capitan")));
			Capitan aux = capitanService.findByJugador(capi.get());
			if(aux==null) {
				Capitan capitan = new Capitan(capi.get(),0, Actitud.POSITIVA);
				Capitan capitanSave = capitanService.save(capitan);
				equipo.setCapitan(capitanSave);
			}
			else {
				equipo.setCapitan(aux);
			}
			
			//añadir entrenador
			Principal principal = request.getUserPrincipal();
			String username =  principal.getName(); 
	        User  user = userService.findByUsername(username);
	        Entrenador entrenador = entrenadorService.findByUser(user);
			equipo.setEntrenador(entrenador);
			
			Equipo team = equipoService.save(equipo);
			
			//añadir numero de camiseta
			for(int i=0; i<agregados.size(); i++) {
				NumCamiseta num = new NumCamiseta(team,agregados.get(i),i+1);
				NumCamiseta number = numCamisetaService.save(num);
			}
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "jugadoresNoEquipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<JugadorDTO>> jugadoresNoEquipo(@PathVariable("id") int id) {
		try {
			List<Jugador> jugadores = equipoService.findJugadoresNoEquipo(id);
			List<JugadorDTO> jugadoresDTO = jugadorConverter.convertParcialListJugadorToListJugadorDTO(jugadores);
			DataTableResponse<JugadorDTO> data = new DataTableResponse<JugadorDTO>();
			data.setData(jugadoresDTO);
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<JugadorDTO>>(HttpStatus.BAD_REQUEST);
		}	
	}

}
