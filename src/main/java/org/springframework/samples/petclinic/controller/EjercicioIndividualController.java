package org.springframework.samples.petclinic.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.EjercicioIndividualValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.EjercicioIndividualConverter;
import org.springframework.samples.petclinic.converter.RealizaEjercicioConverter;
import org.springframework.samples.petclinic.converter.enumerate.TipoEjercicioConverter;
import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.EjercicioIndividualDTO;
import org.springframework.samples.petclinic.model.auxiliares.RealizaEjercicioDTO;
import org.springframework.samples.petclinic.service.EjercicioIndividualService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.RealizaEjercicioService;
import org.springframework.samples.petclinic.service.impl.AuthoritiesService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/ejercicios")
public class EjercicioIndividualController {
	
	private static final Log LOG = LogFactory.getLog(EjercicioIndividualController.class);
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private RealizaEjercicioService realizaEjercicioService;
	
	@Autowired
	private RealizaEjercicioConverter realizaEjercicioConverter;
	
	@Autowired
	private EjercicioIndividualService ejercicioIndividualService;
	
	@Autowired
	private EjercicioIndividualValidator ejercicioIndividualValidator;
	
	@Autowired 
	private EjercicioIndividualConverter ejercicioIndividualConverter;
	
	@Autowired
	private TipoEjercicioConverter tipoEjercicioConverter;
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	
	@GetMapping("/showejercicios")
	public String inicio(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		boolean permisoEntrenador = authoritiesService.hasAuthority("entrenador", principal.getName());
		model.addAttribute("permisoEntrenador", permisoEntrenador);
		return ViewConstant.VIEW_EJERCICIOS;
	}
	
	@RequestMapping(value = "/tablaRealizados", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<RealizaEjercicioDTO>> tablaRealizados(){
		try {
			List<RealizaEjercicio> realizaEjercicios = realizaEjercicioService.findAll();
			DataTableResponse<RealizaEjercicioDTO> data = new DataTableResponse<RealizaEjercicioDTO>(realizaEjercicioConverter.converListEntityToListDTO(realizaEjercicios));
			return new ResponseEntity<DataTableResponse<RealizaEjercicioDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<RealizaEjercicioDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/tablaRecomendados", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EjercicioIndividualDTO>> tablaRecomendados(HttpServletRequest request){
		try {
			Principal principal = request.getUserPrincipal();
			User user = userService.findByUsername(principal.getName());
			Jugador jugador = jugadorService.findByUser(user);
			List<EjercicioIndividual> recomendados = ejercicioIndividualService.findEjerciciosRecomendados(jugador);
			DataTableResponse<EjercicioIndividualDTO> data = new DataTableResponse<EjercicioIndividualDTO>(ejercicioIndividualConverter.converListEntityToListDTO(recomendados));
			return new ResponseEntity<DataTableResponse<EjercicioIndividualDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EjercicioIndividualDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/tablaTipo/{tipo}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<EjercicioIndividualDTO>> tablaTipo(@PathVariable("tipo") String tipo ){
		try {
			List<EjercicioIndividual> ejercicios = ejercicioIndividualService.findByTipoEjercicio(tipoEjercicioConverter.convertToEntityAttribute(tipo));
			DataTableResponse<EjercicioIndividualDTO> data = new DataTableResponse<EjercicioIndividualDTO>(ejercicioIndividualConverter.converListEntityToListDTO(ejercicios));
			return new ResponseEntity<DataTableResponse<EjercicioIndividualDTO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<EjercicioIndividualDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "postEjercicio", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> postEjercicio(HttpServletRequest request, @ModelAttribute(name="ejercicio") EjercicioIndividual ejercicio, BindingResult result){
		try {
			
			if(!StringUtils.isEmpty(request.getParameter("idGestionEjercicio"))) {
				Integer id = Integer.parseInt(request.getParameter("idGestionEjercicio"));
				ejercicio = ejercicioIndividualService.findById(id).get();
			} else {
				ejercicio.setTipoEjercicio(tipoEjercicioConverter.convertToEntityAttribute(request.getParameter("tipoGestionEjercicio")));
			}
			
			ejercicio.setNombre(request.getParameter("nombre").trim());
			ejercicio.setDescripcion(request.getParameter("descripcion").trim());
			
			ValidationUtils.invokeValidator(ejercicioIndividualValidator, ejercicio, result);
			
			if (result.hasErrors()) {
				LOG.warn("Se han obtenido " + result.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			
			LOG.info("Se procede a actualizar el jugador");
			EjercicioIndividual ejercicioSave = ejercicioIndividualService.save(ejercicio);
			LOG.info("Se ha actualizado el jugador con éxito: " + ejercicioSave);
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
		} catch (Exception e) {
			LOG.error("No se ha podido guardar el ejercicio");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}

}
