//package org.springframework.samples.petclinic.controller;
//
//import java.security.Principal;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.component.MaterialValidator;
//import org.springframework.samples.petclinic.constant.ViewConstant;
//import org.springframework.samples.petclinic.service.MaterialService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/materiales")
//public class MaterialController {
//	private static final Log LOG = LogFactory.getLog(MaterialController.class);
//
//	@Autowired
//	private MaterialValidator materialValidator;
//
//	@Autowired
//	private MaterialService materialService;
//
//
//
//	@GetMapping("/showmateriales")
//	public ModelAndView listadoMaterial(HttpServletRequest request) {
//		Principal principal = request.getUserPrincipal();
//		String tipo = "";
//
//		if(principal == null) {
//			tipo = "";
//			//			ModelAndView mav = new ModelAndView("/login");
//			//			return mav;
//		}else {
//			tipo =  principal.getName(); 
//
//		}
//		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_MATERIALES);
//		mav.addObject("tipo", tipo);
//		mav.addObject("materiales", materialService.findAll());
//		return mav;
//	}
//}