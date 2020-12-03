package org.springframework.samples.petclinic.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.component.MaterialValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/materiales")
public class MaterialController {
	private static final Log LOG = LogFactory.getLog(MaterialController.class);

	@Autowired
	private MaterialValidator materialValidator;

	@Autowired
	private MaterialService materialService;



	@GetMapping("/showmateriales")
	public ModelAndView listadoMaterial() {

		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_MATERIALES);
		mav.addObject("materiales", materialService.findAll());
		return mav;
	}
//	@GetMapping("/navbar")
//	public String navbar() {
//		return ViewConstant.VIEW_NAVBAR;
//	}
//	@RequestMapping(value = "getallmaterials", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<String>> findMateriales() {
//		try {
//			List<String> materiales = materialService.find;
//			return new ResponseEntity<List<String>>(equipos, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
//		}	
//	}

}