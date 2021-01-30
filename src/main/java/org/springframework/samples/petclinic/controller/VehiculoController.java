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
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.model.Personales;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.PersonalesService;
import org.springframework.samples.petclinic.service.ViajeService;
import org.springframework.samples.petclinic.service.impl.AuthoritiesService;
import org.springframework.samples.petclinic.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/eliminarVehiculo/{id}")
    public String eliminarVehiculo(@PathVariable Integer id){
		viajeService.deleteAll(viajeService.findByPersonal(personalService.findById(id).get()));
		personalService.deleteById(id);
		return "redirect:/personales/showvehiculos";
    }
	
	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}

	
}
