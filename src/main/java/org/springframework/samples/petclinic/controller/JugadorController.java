package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.JugadorValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.JugadorConverter;
import org.springframework.samples.petclinic.converter.enumerate.EstadoConverter;
import org.springframework.samples.petclinic.converter.enumerate.PosicionConverter;
import org.springframework.samples.petclinic.converter.enumerate.PrivilegioConverter;
import org.springframework.samples.petclinic.converter.enumerate.TipoPrivilegioConverter;
import org.springframework.samples.petclinic.enumerate.Estado;
import org.springframework.samples.petclinic.enumerate.TipoAutorizacion;
import org.springframework.samples.petclinic.enumerate.TipoPrivilegio;
import org.springframework.samples.petclinic.model.Autorizacion;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Privilegio;
import org.springframework.samples.petclinic.model.auxiliares.DataAutorizacion;
import org.springframework.samples.petclinic.model.auxiliares.JugadorAut;
import org.springframework.samples.petclinic.model.auxiliares.JugadorPriv;
import org.springframework.samples.petclinic.model.ediciones.JugadorEdit;
import org.springframework.samples.petclinic.model.ediciones.JugadorEditNumCamiseta;
import org.springframework.samples.petclinic.model.ediciones.PrivilegioEdit;
import org.springframework.samples.petclinic.model.estadisticas.JugadorStats;
import org.springframework.samples.petclinic.service.AutorizacionService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalPartidoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.NumCamisetaService;
import org.springframework.samples.petclinic.service.PrivilegioService;
import org.springframework.samples.petclinic.service.impl.UserService;
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
@RequestMapping("/jugadores")
public class JugadorController {

	private static final Log LOG = LogFactory.getLog(JugadorController.class);
	public static final String TEMPLATE_MODAL_GESTION_JUGADOR = "/jugadores/modalGestionJugador";


	@Autowired
	private JugadorValidator jugadorFormValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private JugadorService jugadorService;

	@Autowired
	private AutorizacionService autorizacionService;

	@Autowired
	private PrivilegioService privilegioService;

	@Autowired
	private EstadisticaPersonalPartidoService estadisService;

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private NumCamisetaService numCamisetaService;

	@Autowired
	private EstadoConverter estadoConverter;

	@Autowired
	private PosicionConverter posicionConverter;

	@Autowired
	private JugadorConverter jugadorConverter;

	@Autowired
	private JugadorValidator jugadorValidator;

	@Autowired
	private TipoPrivilegioConverter tipoPrivilegioConverter;

	@Autowired
	private PrivilegioConverter privilegioConverter;


	//	@InitBinder("jugador")
	//	public void initJugadorBinder(WebDataBinder dataBinder) {
	//	dataBinder.setValidator(new JugadorValidator());
	//	}




	@GetMapping("/showjugadores")
	public ModelAndView listadoJugadores(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String username = "";

		if(principal == null) {
			username = "";
			//			ModelAndView mav = new ModelAndView("/login");
			//			return mav;
		}else {
			username =  principal.getName(); 

		}
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADOR);
		mav.addObject("username", username);
		mav.addObject("estadoLesionado", Estado.LESIONADO);
		mav.addObject("jugadores", jugadorConverter.convertListJugadorToListJugadorWithEquipo(jugadorService.findAll()));
		return mav;

	}

	@RequestMapping(value = "getallteamsjugador/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findEquiposJugador(@PathVariable("id") int id) {
		try {
			Optional<Jugador> jug = jugadorService.findById(id);
			Jugador j = jug.get();
			List<Equipo> e = j.getEquipos();
			List<String> data = new ArrayList<String>();

			for(Equipo team:e) {
				data.add(team.getCategoria());
			}
			return new ResponseEntity<List<String>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(value = "getprivjugadorteam/{id}/{equipo}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JugadorPriv>> findPrivJugadorEquipo(@PathVariable("id") int id, @PathVariable("equipo") String equipo) {
		try {
			Optional<Jugador> jug = jugadorService.findById(id);
			Jugador j = jug.get();
			Equipo e = equipoService.findByCategoria(equipo);
			List<JugadorPriv> data = new ArrayList<JugadorPriv>();

			JugadorPriv player = new JugadorPriv();
			Set<Privilegio> Spri = j.getPrivilegios();
			List<TipoPrivilegio> tpri = Spri.stream().filter(x -> x.getEquipo().getId().equals(e.getId())).map(y -> y.getTipoPrivilegio()).collect(Collectors.toList());
			if(tpri.contains(TipoPrivilegio.PARTIDOS)) {
				player.setPartidos(true);
			} else {
				player.setPartidos(false);
			}
			if(tpri.contains(TipoPrivilegio.ENTRENAMIENTOS)) {
				player.setEntrenamientos(true);
			} else {
				player.setEntrenamientos(false);
			}
			data.add(player);

			return new ResponseEntity<List<JugadorPriv>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<JugadorPriv>>(HttpStatus.BAD_REQUEST);
		}	
	}

	@GetMapping("/showjugadorespriv")
	public String listadoJugadoresPrivilegios(Model model) {
		model.addAttribute("entrenamientos",jugadorService.findPrivilegio(TipoPrivilegio.ENTRENAMIENTOS));
		model.addAttribute("partidos",jugadorService.findPrivilegio(TipoPrivilegio.PARTIDOS));
		model.addAttribute("jugadorespriv", jugadorService.findAll());
		model.addAttribute("listpriv", new ArrayList<TipoPrivilegio>(Arrays.asList(TipoPrivilegio.ENTRENAMIENTOS, TipoPrivilegio.PARTIDOS)));

		return ViewConstant.VIEW_JUGADORES_PRIVILEGIOS;
	}

	@RequestMapping(value = "/updateprivilegio", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> editarPrivilegio(HttpServletRequest request, @ModelAttribute(name="privilegiojugador") PrivilegioEdit privilegioedit, BindingResult result) {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();
			Equipo equipo = equipoService.findByCategoria(request.getParameter("equipo").trim());			

			LOG.info("Buscamos los privilegios del jugador con id = " + jugador.getId() + " en el equipo " + equipo.getCategoria());
			Set<Privilegio> Spri = jugador.getPrivilegios();
			List<Privilegio> pri = Spri.stream().filter(x -> x.getEquipo().getId().equals(equipo.getId())).collect(Collectors.toList());
			List<TipoPrivilegio> tpri = Spri.stream().filter(x -> x.getEquipo().getId().equals(equipo.getId())).map(y -> y.getTipoPrivilegio()).collect(Collectors.toList());

			List<TipoPrivilegio> privilegios = new ArrayList<TipoPrivilegio>();

			if(Boolean.parseBoolean(request.getParameter("partidos").trim())) {
				if(!(tpri.contains(TipoPrivilegio.PARTIDOS))) {
					privilegios.add(tipoPrivilegioConverter.convertToEntityAttribute("PARTIDOS"));
				}
				//				privilegios.add(tipoPrivilegioConverter.convertToEntityAttribute(request.getParameter("descripcionPartidos").trim()));
			} else {
				if(tpri.contains(TipoPrivilegio.PARTIDOS)) {
					List<Privilegio> auxP = pri.stream().filter(y -> y.getTipoPrivilegio().equals(TipoPrivilegio.PARTIDOS)).collect(Collectors.toList());
					privilegioService.deleteAll(auxP);
				}
			}

			if(Boolean.parseBoolean(request.getParameter("entrenamientos").trim())) {
				if(!(tpri.contains(TipoPrivilegio.ENTRENAMIENTOS))) {
					privilegios.add(tipoPrivilegioConverter.convertToEntityAttribute("ENTRENAMIENTOS"));
				}
				//				privilegios.add(tipoPrivilegioConverter.convertToEntityAttribute(request.getParameter("descripcionEntrenamientos").trim()));
			} else {
				if(tpri.contains(TipoPrivilegio.ENTRENAMIENTOS)) {
					List<Privilegio> auxP = pri.stream().filter(y -> y.getTipoPrivilegio().equals(TipoPrivilegio.ENTRENAMIENTOS)).collect(Collectors.toList());
					privilegioService.deleteAll(auxP);
				}
			}

			List<Privilegio> priv = privilegioConverter.convertListPrivilegiosEditToListPrivilegios(privilegios, jugador, equipo);
			for(Privilegio privi:priv) {
				Privilegio p = privilegioService.updatePrivilegio(privi);
			}
			return new ResponseEntity<List<ObjectError>>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}	
	}

	//	@GetMapping("/addprivilegio/{id}/{tipoPrivilegio}")
	//	public String addPrivilegioJugador(@PathVariable("id") int id, @PathVariable("tipoPrivilegio") TipoPrivilegio priv) {
	//		Privilegio privilegio= new Privilegio(); 
	//		Optional<Jugador> jug = jugadorService.findById(id);
	//		Jugador jugador= jug.get();
	//		privilegio.setJugador(jugador);
	//		privilegio.setTipoPrivilegio(priv);
	//		Privilegio privilege = privilegioService.savePrivilegio(privilegio);
	//		
	//		return "redirect:/jugadores/showjugadorespriv";
	//	}

	@GetMapping("/showjugadoresaut")
	public ModelAndView listadoJugadoresAutorizacion() {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_JUGADORES_AUTORIZACION);
		mav.addObject("transporte",jugadorService.findAuto(TipoAutorizacion.TRANSPORTE));
		mav.addObject("usoimagen",jugadorService.findAuto(TipoAutorizacion.USOIMAGEN));
		mav.addObject("lesion",jugadorService.findAuto(TipoAutorizacion.RESPONSABILIDADLESION));
		mav.addObject("excursiones",jugadorService.findAuto(TipoAutorizacion.EXCURSIONES));
		mav.addObject("jugadoresaut", jugadorService.findAll());
		mav.addObject("listaut", new ArrayList<TipoAutorizacion>(Arrays.asList(TipoAutorizacion.TRANSPORTE, TipoAutorizacion.USOIMAGEN, TipoAutorizacion.USOIMAGEN, TipoAutorizacion.EXCURSIONES)));

		return mav;
	}

	@RequestMapping(value = "/tablajugadoresaut", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataAutorizacion> tablaJugadoresAutorizacion(){
		try {
			List<Jugador> jugadores = jugadorService.findAll();
			List<JugadorAut> listajugadoraut= jugadorConverter.convertListJugadorToListJugadorAut(jugadores);
			DataAutorizacion data= jugadorConverter.convertListJugadoresAutorizaciones(listajugadoraut);
			return new ResponseEntity<DataAutorizacion>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataAutorizacion>(HttpStatus.BAD_REQUEST);
		}	
	}

	//@PostMapping("/eliminarautorizacion/{id}/{tipoAutorizacion}")
	@RequestMapping(value = "/eliminarautorizacion/{id}/{tipoAutorizacion}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity eliminarAutorizacion(@PathVariable("id") int id , @PathVariable("tipoAutorizacion") TipoAutorizacion autor) {
		try {
			LOG.info("Buscamos el jugador con id=" + id);
			Optional<Jugador> player = jugadorService.findById(id);
			Jugador jug= player.get();
			Autorizacion ar= autorizacionService.findByJugadorAndTipo(jug, autor);

			LOG.info("Se procede a eliminar la autorización");
			autorizacionService.deleteByIdSiExiste(ar.getId());
			LOG.info("Se ha eliminado la autorización con éxito");

			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("No se ha podido eliminar la autorización");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	//@PostMapping("/addautorizacion/{id}/{tipoAutorizacion}")
	@RequestMapping(value = "/addautorizacion/{id}/{tipoAutorizacion}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Autorizacion> addAutorizacion(@PathVariable("id") int id , @PathVariable("tipoAutorizacion") TipoAutorizacion autor) {
		try {
			Autorizacion autorizacion= new Autorizacion(); 
			LOG.info("Buscamos el jugador con id=" + id);
			Optional<Jugador> jug = jugadorService.findById(id);
			Jugador jugador= jug.get();
			autorizacion.setFecha(LocalDate.now());
			autorizacion.setJugador(jugador);
			autorizacion.setTipoAutorizacion(autor);

			LOG.info("Se procede a añadir la autorización");
			Autorizacion autorization= autorizacionService.save(autorizacion);
			LOG.info("Se ha guardado la autorización con éxito: " + autorization);

			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			LOG.error("No se ha podido añadir la autorización");
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "findestadisticasjugador/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<JugadorStats> graficoEstadisticasJugador(@PathVariable("id") int id) {
		try {
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();
			JugadorStats stats = jugadorConverter.convertJugadorToJugadorStats(jugador);
			return new ResponseEntity<JugadorStats>(stats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JugadorStats>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(value = "findeditjugador/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<JugadorEdit> editarJugador(@PathVariable("id") int id) {
		try {
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();
			JugadorEdit edit = jugadorConverter.convertJugadorToJugadorEdit(jugador);
			return new ResponseEntity<JugadorEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JugadorEdit>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(value = "findEditjugadorNumCamiseta/{jugadorID}/{equipoID}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<JugadorEditNumCamiseta> editarJugadorNumCamiseta(@PathVariable("jugadorID") int jugadorID, @PathVariable("equipoID") int equipoID) {
		try {
			Jugador jugador = jugadorService.findById(jugadorID).get();
			int num = numCamisetaService.findByEquipoAndJugador(equipoID, jugadorID).getNumero();
			JugadorEditNumCamiseta edit = jugadorConverter.convertJugadorToJugadorEditNumCamiseta(jugador, num);
			return new ResponseEntity<JugadorEditNumCamiseta>(edit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JugadorEditNumCamiseta>(HttpStatus.BAD_REQUEST);
		}	
	}

	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	@GetMapping("/jugadorform")
	public String redirectJugadorForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Jugador jugador = new Jugador();
		if(id != 0) {
			jugador = jugadorService.findById(id).get();
		}
		model.addAttribute("jugador", jugador);
		return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}


	@PostMapping("/addjugador")
	public String addJugador(@ModelAttribute(name="jugador") Jugador jugador, Model model, BindingResult result) {

		LOG.info("addjugador() -- PARAMETROS: "+ jugador);

		ValidationUtils.invokeValidator(jugadorValidator, jugador, result);

		if (result.hasErrors()) {
			LOG.warn("Se han obtenido " + result.getErrorCount() + " errores de validación");
			model.addAttribute("jugador", jugador);
			return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		}

		try {
			LOG.info("Se procede a guardar el jugador");
			Jugador player = jugadorService.save(jugador);
			LOG.info("Se ha guardado el jugador con éxito: " + player);
		} catch (Exception e) {
			LOG.error("No se ha podido guardar el jugador");
		}

		return "redirect:/home";
	}

	@RequestMapping(value = "updatejugador", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> updateJugador(HttpServletRequest request, @ModelAttribute(name="jugador") Jugador jugadorAux, BindingResult result) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));

			LOG.info("Buscamos el jugador con id=" + id);
			Optional<Jugador> jugadorO = jugadorService.findById(id);
			Jugador jugador = jugadorO.get();

			jugadorAux.setDni(jugador.getDni());
			jugadorAux.setEmail(jugador.getEmail());
			jugadorAux.setDireccion(jugador.getDireccion());
			jugadorAux.setLocalidad(jugador.getLocalidad());
			jugadorAux.setFechaNacimiento(jugador.getFechaNacimiento());
			jugadorAux = seleccionarAtributosJugador(jugadorAux, request);

			ValidationUtils.invokeValidator(jugadorValidator, jugadorAux, result);

			//JugadorEdit edit = jugadorConverter.convertJugadorToJugadorEdit(jugador);

			if (result.hasErrors()) {
				LOG.warn("Se han obtenido " + result.getErrorCount() + " errores de validación");
				ResponseEntity<List<ObjectError>> re = new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
				return re;
			}

			jugador = seleccionarAtributosJugador(jugador, request);

			LOG.info("Se procede a actualizar el jugador");
			Jugador player = jugadorService.updateJugador(jugador);
			LOG.info("Se ha actualizado el jugador con éxito: " + player);
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
		} catch(Exception e) {
			LOG.error("No se ha podido guardar el jugador");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}

	}

	private Jugador seleccionarAtributosJugador(Jugador jugador, HttpServletRequest request) {
		jugador.setFirstName(request.getParameter("firstName").trim());
		jugador.setLastName(request.getParameter("lastName").trim());
		if(!request.getParameter("altura").isEmpty()) {
			jugador.setAltura(Integer.parseInt(request.getParameter("altura")));
		} else {
			jugador.setAltura(null);
		}
		if(!request.getParameter("peso").isEmpty()) {
			jugador.setPeso(Integer.valueOf(request.getParameter("peso")));
		} else {
			jugador.setPeso(null);
		}
		jugador.setEstadoActual(estadoConverter.convertToEntityAttribute(request.getParameter("estadoActual")));
		jugador.setPosicionPrincipal(posicionConverter.convertToEntityAttribute(request.getParameter("posicionPrincipal")));
		jugador.setPosicionSecundaria(posicionConverter.convertToEntityAttribute(request.getParameter("posicionSecundaria")));
		return jugador;
	}

}