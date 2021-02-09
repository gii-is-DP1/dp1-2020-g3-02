package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.EntrenamientoValidator;
import org.springframework.samples.petclinic.component.LineaMaterialValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.DataPosicionConverter;
import org.springframework.samples.petclinic.converter.EntrenamientoConverter;
import org.springframework.samples.petclinic.converter.EquipoConverter;
import org.springframework.samples.petclinic.converter.EstadisticasConverter;
import org.springframework.samples.petclinic.converter.JugadorPartidoStatsConverter;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.EstadisticaPersonalEntrenamiento;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Viaje;
import org.springframework.samples.petclinic.model.auxiliares.DataPosicion;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.EntrenamientoConAsistencia;
import org.springframework.samples.petclinic.model.ediciones.EntrenamientoEdit;
import org.springframework.samples.petclinic.model.estadisticas.EntrenamientoStats;
import org.springframework.samples.petclinic.model.estadisticas.EstadisticasDeUnJugadorStats;
import org.springframework.samples.petclinic.model.estadisticas.JugadorPartidoStats;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

	private static final Log LOG = LogFactory.getLog(EntrenamientoController.class);

	@Autowired
	private EstadisticaPersonalEntrenamientoService estadisService;

	@Autowired
	private UserService userService;

	@Autowired
	private JugadorService jugadorService;

	@Autowired
	private EntrenadorService entrenadorService;

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private PartidoService partidoService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private LineaMaterialService lineaMaterialService;

	@Autowired
	private PartidoConverter partidoConverter;

	@Autowired
	private EquipoConverter equipoConverter;

	@Autowired
	private EntrenamientoService entrenamientoService;

	@Autowired
	private EntrenamientoConverter entrenamientoConverter;

	@Autowired
	private EstadisticasConverter estadisticasConverter;

	@Autowired
	private JugadorPartidoStatsConverter jugadorPartidoStatsConverter;

	@Autowired
	private DataPosicionConverter dataPosicionConverter;

	@Autowired
	private EntrenamientoValidator entrenamientoValidator;

	@Autowired
	private LineaMaterialValidator lineaMaterialValidator;

	@Autowired
	private ViajeService viajeService;

	@GetMapping("/showentrenamientos")
	public ModelAndView listadoEntrenamientos(HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ENTRENAMIENTOS);
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList()).get(0).getAuthority().equals("jugador")) {
			String username =  principal.getName(); 
			User  user = userService.findByUsername(username);
			Jugador jugador = jugadorService.findByUser(user);
			mav.addObject("jugador", jugador);

		}
		return mav;
	}

	@GetMapping("/showestadisiticasJugadores")
	public ModelAndView vistaEstadísticas(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String username =  principal.getName(); 
		User  user = userService.findByUsername(username);
		Jugador jugador = jugadorService.findByUser(user);
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_ENTRENAMIENTO);
		mav.addObject("estadisticas", estadisService.findByJugador(jugador.getId()));


		List<String> categorias = new ArrayList<String>();
		categorias.addAll(jugador.getEquipos().stream().map(x->x.getCategoria()).sorted().collect(Collectors.toList()));
		mav.addObject("categorias", categorias);
		return mav;
	}

	@RequestMapping(value = "/findestadisticasJugador/{categoria}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EstadisticasDeUnJugadorStats>> graficoEstadisticasTodosLosEntrenamientosDeUnJugador(@PathVariable("categoria") String categoria, HttpServletRequest request) {
		try {

			List<String> categorias = new ArrayList<String>();

			Principal principal = request.getUserPrincipal();
			String username =  principal.getName(); 
			User  user = userService.findByUsername(username);
			Jugador jugador = jugadorService.findByUser(user);

			categorias.addAll(jugador.getEquipos().stream().map(x->x.getCategoria()).collect(Collectors.toList()));

			List<EstadisticaPersonalEntrenamiento> estadisticasJugador = new ArrayList<EstadisticaPersonalEntrenamiento>();
			List<EstadisticasDeUnJugadorStats> estadisticas = new ArrayList<EstadisticasDeUnJugadorStats>();


			Equipo equipo = equipoService.findByCategoria(categoria);
			estadisticasJugador = estadisService.findByJugador(jugador.getId());


			for (int i = 0; i < estadisticasJugador.size();i++) {
				if(estadisticasJugador.get(i).getEntrenamiento().getEquipo().equals(equipo)) {
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

	@GetMapping("/showestadisiticasEntrenamiento")
	public ModelAndView vistaEstadísticasEntrenamiento(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO);
		mav.addObject("estadisticas", entrenamientoService.findAll());

		List<String> categorias = new ArrayList<String>();
		Principal principal = request.getUserPrincipal();
		String username =  principal.getName(); 
		User  user = userService.findByUsername(username);
		Entrenador entrenador = entrenadorService.findByUser(user);
		categorias.addAll(entrenador.getEquipos().stream().map(x->x.getCategoria()).sorted().collect(Collectors.toList()));
		mav.addObject("categorias", categorias);
		return mav;
	}

	@RequestMapping(value = "/findestadisticasEntrenamientos/{categoria}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EntrenamientoStats>> graficoEstadisticasTodosLosEntrenamientos(@PathVariable("categoria") String categoria, HttpServletRequest request) {
		try {

			List<String> categorias = new ArrayList<String>();

			Principal principal = request.getUserPrincipal();
			String username =  principal.getName(); 
			User  user = userService.findByUsername(username);
			Entrenador entrenador = entrenadorService.findByUser(user);

			categorias.addAll(entrenador.getEquipos().stream().map(x->x.getCategoria()).collect(Collectors.toList()));

			List<Entrenamiento> entrenamientos = new ArrayList<Entrenamiento>();
			List<EntrenamientoStats> entrenamientosStats = new ArrayList<EntrenamientoStats>();

			if(categoria.equals("todo")) {
				for (int i=0; i<categorias.size();i++) {
					Equipo equipo = equipoService.findByCategoria(categorias.get(i));
					List<Entrenamiento> entrenamientosEquipo = entrenamientoService.findByEquipo(equipo);
					entrenamientos.addAll(entrenamientosEquipo);
				}
			}else {
				Equipo equipo = equipoService.findByCategoria(categoria);
				entrenamientos = entrenamientoService.findByEquipo(equipo);
				entrenamientos.removeAll( entrenamientoService.findByEntrenamientoFinalizadoFalseOrderByFecha());
			}

			for (int i = 0; i < entrenamientos.size();i++) {
				EntrenamientoStats entrenamientoStats = entrenamientoConverter.convertEntrenamientoToEntrenamientoStats(entrenamientos.get(i));
				entrenamientosStats.add(entrenamientoStats);
			}

			DataTableResponse<EntrenamientoStats> data = new DataTableResponse<EntrenamientoStats>();
			data.setData(entrenamientosStats);
			return new ResponseEntity<DataTableResponse<EntrenamientoStats>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EntrenamientoStats>>(HttpStatus.BAD_REQUEST);
		}	
	}

	@GetMapping("/showestadisiticasEntrenamientoTodosJugadores/{id}")
	public ModelAndView vistaEstadísticasEntrenamientoJugadores(HttpServletRequest request, @PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_JUGADORES);
		List<EstadisticaPersonalEntrenamiento> estadisitcas = estadisService.findByEntrenamiento(id);

		if(estadisitcas.size()>0) {
			mav.addObject("estadisticas", estadisitcas);
		}else {
			return vistaEstadísticasEntrenamiento(request);
		}
		return mav;
	}

	@GetMapping("/showestadisiticasEntrenamientoJugador/{id_jugador}/{id_entrenamiento}")
	public ModelAndView vistaEstadísticasEntrenamientoJugador(@PathVariable("id_jugador") int jugador_id, @PathVariable("id_entrenamiento") int entrenamiento_id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_JUGADOR);
		mav.addObject("estadisticas", estadisService.findByJugadorAndEntrenamiento(jugador_id, entrenamiento_id));
		return mav;
	}


	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	@RequestMapping(value = "/findEntrenamientos", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EntrenamientoConAsistencia>> listadoDeEntrenamientos(HttpServletRequest request) {
		try {
			List<EntrenamientoConAsistencia> entrenamientosSinEquipo = new ArrayList<EntrenamientoConAsistencia>();
			List<Entrenamiento> entrenamientos = entrenamientoService.findByEntrenamientoFinalizadoFalseOrderByFecha(); 

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

			List<Entrenamiento> entrenamientosFiltrados = entrenamientos.stream().filter(x->categorias.contains(x.getEquipo().getCategoria())).collect(Collectors.toList());

			for(int i = 0; i<entrenamientosFiltrados.size();i++) {
				EntrenamientoConAsistencia entrenamientoSinEquipo = entrenamientoConverter.convertEntrenamientoToEntrenamientoConAsistencia(entrenamientosFiltrados.get(i));
				entrenamientosSinEquipo.add(entrenamientoSinEquipo);
			}
			DataTableResponse<EntrenamientoConAsistencia> data = new DataTableResponse<EntrenamientoConAsistencia>();
			data.setData(entrenamientosSinEquipo);

			return new ResponseEntity<DataTableResponse<EntrenamientoConAsistencia>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EntrenamientoConAsistencia>>(HttpStatus.BAD_REQUEST);
		}	
	}

	@PostMapping("/removeEntrenamiento/{id}")
	public ResponseEntity removeEntrenamiento(@PathVariable("id") int id, Model model) {
		try {
			LOG.info("Se procede a borrar el entrenamiento con id: " + id);

			entrenamientoService.deleteById(id);

			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error al eliminar el partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/postentrenamiento")
	public ResponseEntity<List<ObjectError>> addEntrenamiento(HttpServletRequest request, @ModelAttribute(name="entrenamiento") EntrenamientoEdit entrenamientoEdit, BindingResult result) {
		try {
			Entrenamiento entrenamiento = new Entrenamiento();
			if(!request.getParameter("id").isEmpty()) {
				int id = Integer.parseInt(request.getParameter("id"));
				LOG.info("Estamos editando el entrenamiento con el id: " + id);
				Optional<Entrenamiento> entrenamiento0 = entrenamientoService.findById(id); 
				entrenamiento = entrenamiento0.get();
			}else if(request.getParameter("equipo").trim() != null) {
				LOG.info("Estamos creando un entrenamiento nuevo para el equipo con categoría: " + request.getParameter("equipo").trim());
				Equipo equipo = equipoService.findByCategoria(request.getParameter("equipo").trim());
				entrenamiento.setEquipo(equipo);
				entrenamiento.setSistemaJuego(equipo.getSistemaJuego());
			}

			EntrenamientoEdit edit = new EntrenamientoEdit(entrenamiento.getId(), entrenamiento.getEquipo().getCategoria(), request.getParameter("fecha"), request.getParameter("hora").trim());

			ValidationUtils.invokeValidator(entrenamientoValidator, edit, result);

			if(result.hasErrors()) {
				LOG.warn("Se han encontrado " + result.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}

			entrenamiento.setHora(request.getParameter("hora").trim());
			if(!request.getParameter("fecha").isEmpty()) {
				entrenamiento.setFecha(LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}

			LOG.info("Procedemos a guardar el entrenamiento");
			Entrenamiento training = entrenamientoService.save(entrenamiento);

			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);

		} catch (Exception e) {
			LOG.error("Error al guardar el entrenamiento");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "findeditentrenamiento/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntrenamientoEdit> findPartido(@PathVariable("id") int id) {
		try {
			LOG.info("Buscamos el entrenamiento con el id: " + id);
			Optional<Entrenamiento> entrenamiento0 = entrenamientoService.findById(id);
			Entrenamiento entrenamiento = entrenamiento0.get();
			EntrenamientoEdit edit = entrenamientoConverter.convertEntrenamientoToEntrenamientoEdit(entrenamiento);
			return new ResponseEntity<EntrenamientoEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando el entrenamiento para editar");
			return new ResponseEntity<EntrenamientoEdit>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(value = "/addAsisteJugador/{entrenamiento_id}/{jugador_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EntrenamientoConAsistencia>> addJuegaJuegador(@PathVariable("entrenamiento_id") int entrenamiento_id , @PathVariable("jugador_id") int jugador_id) {
		try {
			Entrenamiento entrenamiento = entrenamientoService.findById(entrenamiento_id).get();
			EntrenamientoConAsistencia entrenamientoConAsistencia = entrenamientoConverter.convertEntrenamientoToEntrenamientoConAsistencia(entrenamiento);
			Jugador jugador = jugadorService.findById(jugador_id).get();

			List<Equipo> equiposJugador = jugador.getEquipos();

			List<EntrenamientoConAsistencia> entrenamientosAtendidos = entrenamientoService.obtenerEntrenamientosAsistidos(equiposJugador, jugador, entrenamiento);

			if(entrenamientosAtendidos.size() == 1) {
				entrenamientosAtendidos.add(entrenamientoConAsistencia);
				return new ResponseEntity<List<EntrenamientoConAsistencia>>(entrenamientosAtendidos, HttpStatus.OK);
			}

			Set<Jugador> jugadores = entrenamiento.getJugadores();
			jugadores.add(jugador);
			entrenamiento.setJugadores(jugadores);
			entrenamientoService.save(entrenamiento);

			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción añadiendo el jugador al partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/eliminarAsisteJugador/{entrenamiento_id}/{jugador_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarJuegaJugador(@PathVariable("entrenamiento_id") int entrenamiento_id , @PathVariable("jugador_id") int jugador_id) {
		try {
			LOG.info("Buscamos el entrenamiento con el id: " + entrenamiento_id);
			Entrenamiento entrenamiento = entrenamientoService.findById(entrenamiento_id).get();

			LOG.info("Filtramos los jugadores para encontrar dentro del entrenamiento todos los que no tienen el id: " + jugador_id);
			Set<Jugador> jugadores = entrenamiento.getJugadores().stream().filter(x->x.getId()!= jugador_id).collect(Collectors.toSet());
			entrenamiento.setJugadores(jugadores);
			LOG.info("Actualizamos el partido de id=" + entrenamiento_id + " sin el jugador con id=" + jugador_id);
			entrenamientoService.save(entrenamiento);

			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("Excepción eliminando el jugador del partido");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/findJugadorPosicionEntrenamiento/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataPosicion> graficoJugadorPosicionEntrenamiento(@PathVariable("id") int id) {
		try {
			Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(id);
			List<JugadorPartidoStats> listaJugadorStats = new ArrayList<JugadorPartidoStats>();
			List<Jugador> jugadores = entrenamiento.get().getJugadores().stream().collect(Collectors.toList());
			for (int i = 0; i<entrenamiento.get().getJugadores().size();i++) {

				JugadorPartidoStats stats = jugadorPartidoStatsConverter.convertJugadorToJugadorPartidoStats(jugadores.get(i));
				listaJugadorStats.add(stats);
			}


			DataPosicion data = dataPosicionConverter.convertPartidoToPartidoStats(listaJugadorStats);
			return new ResponseEntity<DataPosicion>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataPosicion>(HttpStatus.BAD_REQUEST);
		}	
	}
	@RequestMapping(value = "/confirmacionLlegada/{viaje_id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity confirmarLlegadaAEntrenamientoJugador(@PathVariable("viaje_id") int viaje_id ) {
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

	private void linea(TipoMaterial tipo, int cantidad,Entrenamiento entr,BindingResult result,Map<String,String> mape) {
		List<LineaMaterial> lineas= new ArrayList<>();

		if(cantidad>getStockTotal(tipo) || cantidad < 0) {
			Material mm= materialService.findByTipo(tipo).get(0);
			LineaMaterial lin1= new LineaMaterial(mm, entr,cantidad);
			lineas.add(lin1);
		}else {
			int stocker = 0;
			for(Material mm : materialService.findByTipo(tipo)) {
				if(cantidad>stocker && mm.getStock()!=0) {
					if(cantidad>mm.getStock()) {
						if((cantidad-stocker)<mm.getStock()) {
							LineaMaterial lin1= new LineaMaterial(mm, entr, cantidad-stocker);
							lineas.add(lin1);
							stocker+=cantidad-stocker;
						}else {
							LineaMaterial lin1= new LineaMaterial(mm, entr, mm.getStock());
							lineas.add(lin1);
							stocker+=mm.getStock();
						}
					}else {
						LineaMaterial lin1= new LineaMaterial(mm, entr,cantidad);
						lineas.add(lin1);
						stocker+=cantidad;
					}

				}

			}

		}
		for(LineaMaterial linea:lineas) {
			Errors errors = new BeanPropertyBindingResult(linea, "");
			lineaMaterialValidator.validate(linea, errors);

			if(errors.hasErrors()) {
				mape.put("cantidad"+tipo.name(), errors.getAllErrors().get(0).getDefaultMessage());
			}
		}
		if(mape.isEmpty()) {
			lineaMaterialService.saveAll(lineas);
		}

	}
	@RequestMapping(value = "/updatematerial", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateMaterial(HttpServletRequest request, @ModelAttribute(name="lineaMaterial") LineaMaterial linea, BindingResult result) {
		try {
			Map<String, String> mape = new HashMap<String,String>();
			Entrenamiento entr= entrenamientoService.findById(Integer.parseInt(request.getParameter("id"))).get();


			linea(TipoMaterial.BALONMEDICINAL, Integer.parseInt(request.getParameter("cantidad1")), entr,result,mape);
			linea(TipoMaterial.BALONDEJUEGO, Integer.parseInt(request.getParameter("cantidad2")), entr,result,mape);
			linea(TipoMaterial.RED, Integer.parseInt(request.getParameter("cantidad3")), entr,result,mape);
			linea(TipoMaterial.POSTE, Integer.parseInt(request.getParameter("cantidad4")), entr,result,mape);
			linea(TipoMaterial.CONOBAJO, Integer.parseInt(request.getParameter("cantidad5")), entr,result,mape);
			linea(TipoMaterial.CONOALTO, Integer.parseInt(request.getParameter("cantidad6")), entr,result,mape);
			linea(TipoMaterial.CONOMEDIO, Integer.parseInt(request.getParameter("cantidad7")), entr,result,mape);
			linea(TipoMaterial.CUERDA, Integer.parseInt(request.getParameter("cantidad8")), entr,result,mape);
			linea(TipoMaterial.CINTA, Integer.parseInt(request.getParameter("cantidad9")), entr,result,mape); 


			//si el map esta vacio devuelva ok si el map tiene errorres devuelva el map
			if(mape.isEmpty()) {

				return new ResponseEntity<Map<String, String>>(HttpStatus.OK);
			}else {

				return new ResponseEntity<Map<String, String>>(mape,HttpStatus.BAD_REQUEST);


			} 
		} catch (Exception e) {
			LOG.error("Error al guardar la linea de material");
			return new ResponseEntity<Map<String, String>>(HttpStatus.BAD_REQUEST);

		}

	}
	private int getStockTotal(TipoMaterial tipo) {
		int stock =0;

		Material materialAceptable= materialService.findByTipoAndEstado(tipo, EstadoMaterial.ACEPTABLE);
		Material materialBueno= materialService.findByTipoAndEstado(tipo, EstadoMaterial.BUENO);
		Material materialNuevo= materialService.findByTipoAndEstado(tipo, EstadoMaterial.NUEVO);

		if(materialAceptable!=null) {
			stock+=materialAceptable.getStock();
		}
		if(materialBueno!=null) {
			stock+=materialBueno.getStock();
		}
		if(materialNuevo!=null) {
			stock+=materialNuevo.getStock();
		}


		return stock;
	}
}