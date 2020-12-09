package org.springframework.samples.petclinic.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.MaterialValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.MaterialConverter;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.ediciones.MaterialEdit;
import org.springframework.samples.petclinic.service.MaterialService;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/materiales")
public class MaterialController {
	private static final Log LOG = LogFactory.getLog(MaterialController.class);

	@Autowired
	private MaterialValidator materialValidator;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private MaterialConverter materialConverter;



	@GetMapping("/showmateriales")
	public ModelAndView listadoMaterial() {

		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_MATERIALES);
		mav.addObject("materiales", materialService.findAll());
		return mav;
	}


	@PostMapping("/addmaterial")
	public String addMaterial(@Valid @ModelAttribute(name="material") Material material, BindingResult bindResult, Model model) {

		LOG.info("addmaterial() -- PARAMETROS: "+ material.toString());

		ValidationUtils.invokeValidator(materialValidator, material, bindResult);

		if (bindResult.hasErrors()) {
			model.addAttribute("material", material);
			return ViewConstant.VIEWS_MATERIALES_CREATE_OR_UPDATE_FORM;
		}
		Material materialSave = materialService.saveMaterial(material);
		return "redirect:/materiales/showmateriales";

	}
	@RequestMapping(value = "findeditmaterial/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialEdit> editarMaterial(@PathVariable("id") int id) {
		try {
			Optional<Material> materialO = materialService.findById(id);
			Material material = materialO.get();
			MaterialEdit edit = materialConverter.convertMaterialToMaterialEdit(material);
			return new ResponseEntity<MaterialEdit>(edit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MaterialEdit>(HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(value = "updatematerial", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> updateMaterial(HttpServletRequest request, @ModelAttribute(name="material") Material material, BindingResult result) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			Optional<Material> materialO = materialService.findById(id);
			material = materialO.get();


			if(!request.getParameter("stock").isEmpty()) {
				material.setStock(Integer.parseInt(request.getParameter("stock")));
			} else {
				material.setStock(null);
			}

			ValidationUtils.invokeValidator(materialValidator, material, result);


			MaterialEdit edit = materialConverter.convertMaterialToMaterialEdit(material);

			if (result.hasErrors()) {
				ResponseEntity<List<ObjectError>> re = new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
				return re;
			}
			Material materiall = materialService.updateMaterial(material);
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}


	
}