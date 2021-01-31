package org.springframework.samples.petclinic.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.PersonalesValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.PersonalConverter;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.model.auxiliares.PersonalEdit;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.ViajeService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/personales")
public class VehiculoController {
	@Autowired
	private PersonalesValidator personalValidator;
	
	@Autowired
	private ViajeService viajeService;
	
	@Autowired
	private PersonalesService personalService;
	
	@Autowired
	private PersonalConverter personalConverter;
	
	@Autowired
	private JugadorService jugadorService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private UserService userService;
	
	
	private static final Log LOG = LogFactory.getLog(VehiculoController.class);
	
	@GetMapping("/showvehiculos")
	public ModelAndView listadoVehiculos(HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		boolean autoridad = authoritiesService.hasAuthority("jugador", username);
		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_VEHICULO);
		if(autoridad == true) {
			Jugador jugador=jugadorService.findByUser(userService.findByUsername(username));
			mav.addObject("jugador", jugador);
		}
		
		mav.addObject("personales", personalService.findAll());
		return mav;
	}
	
	@GetMapping("/vehiculoform")
	public String redirectVehiculoForm(@RequestParam(name="id",required=true) int id, Model model,HttpServletRequest request) {
		Personales personal= new Personales();
		String username = request.getUserPrincipal().getName();
		boolean autoridad = authoritiesService.hasAuthority("jugador", username);
		
		if(autoridad == false) {
			return "redirect:/login";
		}else { 
		if(id != 0) {
			personal = personalService.findById(id).get();
			
		}}
		model.addAttribute("personal", personal);
		return ViewConstant.VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/postvehiculo")
	public ResponseEntity<List<ObjectError>> postVehiculo(@ModelAttribute(name="personal") Personales personal, BindingResult bindResult,
			Model model, HttpServletRequest request) {
			
		
			String username = request.getUserPrincipal().getName();
			Jugador jugador=jugadorService.findByUser(userService.findByUsername(username));
			boolean autoridad = authoritiesService.hasAuthority("jugador", username);
			if(autoridad == false){
				return new ResponseEntity<List<ObjectError>>(HttpStatus.FORBIDDEN);
			}
			try {
				if(!request.getParameter("id").isEmpty()) {
					int id = Integer.parseInt(request.getParameter("id"));
					LOG.info("Estamos editando el personal con el id: " + id);
					Optional<Personales> personalO = personalService.findById(id);
					personal = personalO.get();
					personal.setPropietario(request.getParameter("propietario"));
				}else{
					LOG.info("Estamos creando un vehículo nuevo ");
					personal = new Personales();
					personal.setPropietario(request.getParameter("propietario"));
					personal.setJugador(jugador);
				}
				
				ValidationUtils.invokeValidator(personalValidator, personal, bindResult);
				if (bindResult.hasErrors()) {
					LOG.warn("Se han encontrado " + bindResult.getErrorCount() + " errores de validación");
					return new ResponseEntity<List<ObjectError>>(bindResult.getAllErrors(), HttpStatus.BAD_REQUEST);
				}
				Personales personalSave = personalService.save(personal);
				LOG.info("Se ha guardado el vehículo con éxito: " + personalSave);
				return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			} catch (Exception e) {
				LOG.error("No se ha podido guardar el vehículo");
				return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
			}
		
		
	}
	
	@RequestMapping(value = "findeditvehiculo/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonalEdit> findPersonal(@PathVariable("id") int id) {
		try {
			LOG.info("Buscamos el vehículo con el id: " + id);
			Optional<Personales> personalO = personalService.findById(id);
			Personales personal = personalO.get();
			PersonalEdit edit = personalConverter.convertPersonalToPersonalEdit(personal);
			return new ResponseEntity<PersonalEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Excepción encontrando el vehículo para editar");
			return new ResponseEntity<PersonalEdit>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	@PostMapping("/eliminarVehiculo/{id}")
    public ResponseEntity<ObjectError> eliminarVehiculo(@PathVariable Integer id){
		try{
		LOG.info("Procedemos al borrado del vehículo y sus dependencias con el id: " + id);
		personalService.deleteById(id);
		LOG.info("Se ha borrado el vehículo con el id: " + id);
		return new ResponseEntity<ObjectError>(HttpStatus.OK);
		
		}
		catch(Exception e){
			LOG.info("Procedemos al borrado del vehículo y sus dependencias con el id: " + id);
			LOG.info("No se ha podido borrar correctamente el vehículo con el id: " + id);
			return new ResponseEntity<ObjectError>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	
}
