package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.samples.petclinic.model.auxiliares.EquipoCAP;
import org.springframework.samples.petclinic.model.auxiliares.EquipoCategoria;
import org.springframework.samples.petclinic.model.auxiliares.EquipoTablaEquipos;
import org.springframework.samples.petclinic.model.auxiliares.JugadorCAP;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

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
			List<Jugador> jugadores = equipo.getJugadores();
			List<Integer> numerosCamiseta = new ArrayList<Integer>();
			for(int i=0;i<jugadores.size();i++) {
				numerosCamiseta.add(numCamisetaService.findByEquipoAndJugador(id, jugadores.get(i).getId()).getNumero());
			}
			data.setData(jugadorConverter.convertListJugadorToListJugadorInEquipoSinUser(equipo.getJugadores(),numerosCamiseta));
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

	@RequestMapping(value = "/getallteams", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findEquipos() {
		try {
			List<String> equipos = equipoService.findCategoria();
			return new ResponseEntity<List<String>>(equipos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}	
	}

	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	@RequestMapping(value = "/eliminarequipo/{id}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
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

	@RequestMapping(value = "/findEstadisticasEquipo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
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

				JugadorPartidoStats stats = jugadorPartidoStatsConverter.convertJugadorToJugadorPartidoStats(equipo.get().getJugadores().get(i));
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

	@RequestMapping(value = "/updateequipo", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> updateEquipo(HttpServletRequest request, @ModelAttribute(name="equipo") Equipo equipoAux, BindingResult result) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));

			LOG.info("Buscamos el equipo con id=" + id);
			Equipo equipo = equipoService.findById(id).get();

			equipoAux.setNumAmarillas(equipo.getNumAmarillas());
			equipoAux.setNumRojas(equipo.getNumRojas());

			ValidationUtils.invokeValidator(equipoValidator, equipoAux, result);

			//equipoEdit edit = equipoConverter.convertequipoToequipoEdit(equipo);

			if (result.hasErrors()) {
				LOG.warn("Se han obtenido " + result.getErrorCount() + " errores de validación");
				ResponseEntity<List<ObjectError>> re = new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
				return re;
			}

			equipo = seleccionarAtributosEquipo(equipo, request);

			LOG.info("Se procede a actualizar el equipo");
			Equipo team = equipoService.updateEquipo(equipo);
			LOG.info("Se ha actualizado el equipo con éxito: " + team);
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
		} catch(Exception e) {
			LOG.error("No se ha podido guardar el equipo");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}

	}

	private Equipo seleccionarAtributosEquipo(Equipo equipo, HttpServletRequest request) {
		equipo.setCategoria(request.getParameter("categoria").trim());
		equipo.setSistemaJuego(Sistema.valueOf(request.getParameter("sistemajuego").trim()));
		equipo.setLiga(request.getParameter("liga").trim());
		if(request.getParameter("federado")!=null) {
			equipo.setFederacion(true);
		}else if(request.getParameter("noFederado")!=null) {
			equipo.setFederacion(false);
		}
		return equipo;
	}

	@RequestMapping(value = "/postequipo", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> addEquipo(HttpServletRequest request, @ModelAttribute(name="equipo") EquipoEdit equipoEdit, BindingResult result) {
		try {
			
			Equipo equipo = new Equipo(request.getParameter("categoria").trim(), Sistema.valueOf(request.getParameter("sistemajuego").trim()), request.getParameter("liga").trim());
			ValidationUtils.invokeValidator(equipoValidator, equipo, result);

			if(result.hasErrors()) {
				LOG.warn("Se han obtenido " + result.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			
			List<Jugador> jugadores = jugadorService.findAll();
			List<Jugador> agregados = new ArrayList<Jugador>();
			for(int i=0; i<jugadores.size(); i++) {
				//Identificamos a cada jugador que debe ser añadido al equipo por el parámetro de la request nombrado con el id de dicho jugador y
				//que tiene el valor "true" si ha sido seleccionado por el usuario.
				String añadido = request.getParameter(String.valueOf(i+1));
				if(añadido.equals("true")) {
					agregados.add(jugadores.get(i));
				}
			}

			//añadimos al equipo los jugadores seleccionados por el usuario
			equipo.setJugadores(agregados);

			if(!StringUtils.isEmpty(request.getParameter("capitan"))) {
				//añadimos como capitán al jugador seleccionado por el usuario
				Jugador capi = jugadorService.findById(Integer.valueOf(request.getParameter("capitan"))).get();
				Capitan aux = capitanService.findByJugador(capi);
				if(aux==null) {
					LOG.info("El jugador elegido como capitán no es capián de ningún otro equipo.");
					Capitan capitan = new Capitan(capi, 0, Actitud.POSITIVA);
					Capitan capitanSave = capitanService.save(capitan);
					equipo.setCapitan(capitanSave);
				}
				else {
					LOG.info("El jugador elegido como capitán ya era capitán de otro equipo.");
					equipo.setCapitan(aux);
				}
			}

			//añadimos como entrenador del equipo al usuario que ha creado el equipo
			Principal principal = request.getUserPrincipal();
			String username =  principal.getName(); 
			User  user = userService.findByUsername(username);
			Entrenador entrenador = entrenadorService.findByUser(user);
			equipo.setEntrenador(entrenador);

			LOG.info("Se procede a guardar el equipo");
			Equipo team = equipoService.save(equipo);
			LOG.info("Se ha guardado el equipo");

			//añadimos números de camiseta por defecto a los jugadores (pueden ser editados por el usuario en la vista de equipo).
			for(int i=0; i<agregados.size(); i++) {
				LOG.info("Se procede a guardar el número de camiseta del jugador número " +(i+1)+ " de un total de " + agregados.size() + " jugadores");
				NumCamiseta num = new NumCamiseta(team,agregados.get(i),i+1);
				NumCamiseta number = numCamisetaService.save(num);
				LOG.info("Se ha guardado el número de camiseta del jugador número " +(i+1)+ " de un total de " + agregados.size() + " jugadores");
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

	@RequestMapping(value = "/findEquipoCAP/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<EquipoCAP> findEquipoCAP(@PathVariable("id") int id) {
		try {
			Equipo equipo = equipoService.findById(id).get();
			List<Jugador> players = equipo.getJugadores();
			List<JugadorCAP> jugadores = new ArrayList<JugadorCAP>();
			for(Jugador player:players) {
				JugadorCAP j = jugadorConverter.convertJugadorToJugadorCAP(player);
				jugadores.add(j);
			}
			EquipoCAP data = equipoConverter.convertEquipoToEquipoCAP(equipo,jugadores);

			return new ResponseEntity<EquipoCAP>(data, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<EquipoCAP>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(value = "/setCapitanEquipo/{idEquipo}/{idJugador}", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Equipo> setCapitanEquipo(@PathVariable("idEquipo") int idEquipo, @PathVariable("idJugador") int idJugador) {
		try {
			Optional<Equipo> equipo = equipoService.findById(idEquipo);
			Equipo team = equipo.get();
			if(team.getCapitan()==null || !(idJugador==team.getCapitan().getJugador().getId())) {
				Optional<Jugador> jugador = jugadorService.findById(idJugador);
				Jugador player = jugador.get();

				//Cojo los equipos en los que está el jugador y de los cuales es capitán.
				List<Equipo> equipos = new ArrayList<Equipo>();
				if(team.getCapitan()!=null) {
					equipos = equipoService.findByCapitan(team.getCapitan());
				}

				if(equipos.size()>1) {
					equipoService.deleteCapitan(team);
					if(capitanService.findByJugador(player) == null) {
						Capitan capitan = new Capitan();
						capitan.setJugador(player);
						capitan.setNtiemposmuertos(0);
						capitan.setActitud(Actitud.POSITIVA);
						capitanService.saveCapitan(capitan);
						team.setCapitan(capitan);
					} else {
						team.setCapitan(capitanService.findByJugador(player));
					}
					equipoService.updateCapitan(team);
				} else if(equipos.size()==1) {
					Integer idCapitan = team.getCapitan().getId();
					equipoService.deleteCapitan(team);
					capitanService.deleteByIdSiExiste(idCapitan);
					if(capitanService.findByJugador(player) == null) {
						Capitan capitan = new Capitan();
						capitan.setJugador(player);
						capitan.setNtiemposmuertos(0);
						capitan.setActitud(Actitud.POSITIVA);
						capitanService.saveCapitan(capitan);
						team.setCapitan(capitan);
					} else {
						team.setCapitan(capitanService.findByJugador(player));
					}
					equipoService.updateCapitan(team);
				} else {
					Capitan capitan = new Capitan();
					capitan.setJugador(player);
					capitan.setNtiemposmuertos(0);
					capitan.setActitud(Actitud.POSITIVA);
					capitanService.saveCapitan(capitan);
					team.setCapitan(capitan);
					equipoService.updateCapitan(team);
				}
			}


			return new ResponseEntity<Equipo>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Equipo>(HttpStatus.BAD_REQUEST);
		}	
	}

}
