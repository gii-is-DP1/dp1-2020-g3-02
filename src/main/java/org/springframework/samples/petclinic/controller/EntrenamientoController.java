package org.springframework.samples.petclinic.controller;

import java.security.Principal;
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
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.EntrenamientoConverter;
import org.springframework.samples.petclinic.converter.PartidoConverter;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.estadisticas.EntrenamientoStats;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.EstadisticaPersonalEntrenamientoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	private PartidoConverter partidoConverter;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EntrenamientoConverter entrenamientoConverter;
	
	@GetMapping("/showentrenamientos")
	public ModelAndView listadoJugadores() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ENTRENAMIENTOS);
		mav.addObject("entrenamientos", entrenamientoService.findAll());
		return mav;
	}
	 
	@GetMapping("/showentrenamiento")
	public Entrenamiento entrenamiento(int id) {
		Optional<Entrenamiento> entrenamiento = entrenamientoService.findById(id);
		return entrenamiento.get();
	}
	
	
	@GetMapping("/showestadisiticasJugadores")
	public ModelAndView vistaEstadísticas(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
        String username =  principal.getName(); 
        User  user = userService.findByUsername(username);
        Jugador jugador = jugadorService.findByUser(user);
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_JUGADOR_POR_ENTRENAMIENTO);
		mav.addObject("estadisticas", estadisService.findByJugador(jugador.getId()));
		
		return mav;
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
	
	@RequestMapping(value = "findestadisticasEntrenamientos/{categoria}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
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
	
	@GetMapping("/showestadisiticasEntrenamientoTodosJugadores")
	public ModelAndView vistaEstadísticasEntrenamientoJugadores(int id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_JUGADORES);
		mav.addObject("estadisticas", estadisService.findByEntrenamiento(id));
		
		return mav;
	}
	
	@GetMapping("/showestadisiticasEntrenamientoJugador")
	public ModelAndView vistaEstadísticasEntrenamientoJugador(int jugador_id, int entrenamiento_id) {
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_ESTADISTICAS_ENTRENAMIENTO_JUGADOR);
		mav.addObject("estadisticas", estadisService.findByJugadorAndEntrenamiento(jugador_id, entrenamiento_id));
		return mav;
	}
	
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	
	@GetMapping("/entrenamientoform")
	public String redirectEntrenamientoForm(@RequestParam(name="id",required=false) Integer id, Model model) {
		Optional<Entrenamiento> entrenamiento = Optional.of(new Entrenamiento());
		if(id != 0) {
			entrenamiento = entrenamientoService.findById(id);
		}
		model.addAttribute("entrenamiento", entrenamiento);
		return ViewConstant.VIEWS_ENTRENAMIENTO_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping("/addentrenamiento")
	public String addJugador(@ModelAttribute(name="Entrenamiento") Entrenamiento entrenamiento, Model model, final BindingResult result) {
		
		//LOG.info("addpartido() -- PARAMETROS: "+ form.getJugador().toString());
		
//		ValidationUtils.invokeValidator(jugadorFormValidator, form, result);
//		
//		if (result.hasErrors()) {
//			model.addAttribute("formJugador", form);
//			return ViewConstant.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
//		}else {
		
		LOG.info("Se procede a guardar el entrenamiento");
		try {
			Entrenamiento training = entrenamientoService.save(entrenamiento);
			LOG.info("Entrenamiento guardado con éxito: " + training);
		} catch (Exception e) {
			LOG.error("No se ha podido crear el entrenamiento");
		}
		
		return "redirect:/entrenamientos/showEntrenamientos";
	//}
	}
	
}