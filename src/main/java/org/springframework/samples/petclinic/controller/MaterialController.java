package org.springframework.samples.petclinic.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.component.MaterialValidator;
import org.springframework.samples.petclinic.constant.ViewConstant;
import org.springframework.samples.petclinic.converter.MaterialConverter;
import org.springframework.samples.petclinic.converter.enumerate.EstadoMaterialConverter;
import org.springframework.samples.petclinic.converter.enumerate.TipoMaterialConverter;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.model.auxiliares.DataTableResponse;
import org.springframework.samples.petclinic.model.auxiliares.MaterialEstados;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private TipoMaterialConverter tipoMaterialConverter;
	
	@Autowired
	private EstadoMaterialConverter estadoMaterialConverter;
	

	@GetMapping("/showmateriales")
	public ModelAndView listadoMaterial() {

		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_MATERIALES);
		mav.addObject("materiales", materialService.findAll());
		mav.addObject("listestados", new ArrayList<EstadoMaterial>(Arrays.asList(EstadoMaterial.ACEPTABLE, EstadoMaterial.BUENO, EstadoMaterial.DAÑADO,EstadoMaterial.INSERVIBLE,EstadoMaterial.NUEVO)));


		return mav;
	}
	@GetMapping("/showmaterialesentr")
	public ModelAndView listadoMaterialesEntrenamiento() {

		ModelAndView mav = new ModelAndView(ViewConstant.VIEW_MATERIALES_ENTRENAMIENTO);
		mav.addObject("materiales", materialService.findAll());

		return mav;
	}

	@RequestMapping(value = "/tablamaterial", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<DataTableResponse<MaterialEstados>> tablaMaterial(){
		try {
			List<Material> materiales = materialService.findAll();

			TipoMaterial[] tipos= TipoMaterial.values();
			List<MaterialEstados> listaMateriales = new ArrayList<>();

			for (int i = 0; i < tipos.length; i++) {
				final int val = i;

				List<Integer> inservible = materiales.stream().filter(x->x.getTipo() == tipos[val] && x.getEstado().equals(EstadoMaterial.INSERVIBLE)).map(x->x.getStock()).collect(Collectors.toList());
				List<Integer> nuevo = materiales.stream().filter(x->x.getTipo() == tipos[val] && x.getEstado().equals(EstadoMaterial.NUEVO)).map(x->x.getStock()).collect(Collectors.toList());
				List<Integer> bueno = materiales.stream().filter(x->x.getTipo() == tipos[val] && x.getEstado().equals(EstadoMaterial.BUENO)).map(x->x.getStock()).collect(Collectors.toList());
				List<Integer> aceptable = materiales.stream().filter(x->x.getTipo() == tipos[val] && x.getEstado().equals(EstadoMaterial.ACEPTABLE)).map(x->x.getStock()).collect(Collectors.toList());
				List<Integer> dañado = materiales.stream().filter(x->x.getTipo() == tipos[val] && x.getEstado().equals(EstadoMaterial.DAÑADO)).map(x->x.getStock()).collect(Collectors.toList());

				MaterialEstados m = new MaterialEstados();
				m.setTipo(tipos[val]);

				Map<EstadoMaterial,Integer> map = new HashMap<>();

				map.put(EstadoMaterial.INSERVIBLE, (inservible.size()!=0)? inservible.get(0):0);
				map.put(EstadoMaterial.NUEVO, (nuevo.size()!=0)? nuevo.get(0):0);
				map.put(EstadoMaterial.BUENO, (bueno.size()!=0)? bueno.get(0):0);
				map.put(EstadoMaterial.ACEPTABLE, (aceptable.size()!=0)? aceptable.get(0):0);
				map.put(EstadoMaterial.DAÑADO, (dañado.size()!=0)? dañado.get(0):0);
				m.setEstados(map);
				int hola= calcularPorcentajeUso(tipos[i]);
				m.setPorcentaje(hola);
				listaMateriales.add(m);

			}

			//List<MaterialDTO> dtos = materialConverter.convertListEntityToListDTO(materiales);
			DataTableResponse<MaterialEstados> data = new DataTableResponse<MaterialEstados>(listaMateriales);
			return new ResponseEntity<DataTableResponse<MaterialEstados>> (data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<DataTableResponse<MaterialEstados>> (HttpStatus.BAD_REQUEST);
		}	
	}

	//	
	//	@RequestMapping(value = "getporcentajes/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	//	public ResponseEntity<Integer> Porcentajos(@PathVariable("id") int id) {
	//        try {
	//        	
	//            int materialito = materialService.porcentajeUso(id);
	//           
	//            return new ResponseEntity <Integer >(materialito, HttpStatus.OK);
	//        } catch (Exception e) {
	//            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
	//        }
	//    }

//	@PostMapping("/addmaterial")
//	public String addMaterial(@Valid @ModelAttribute(name="material") Material material, BindingResult bindResult, Model model) {
//
//		LOG.info("addmaterial() -- PARAMETROS: "+ material.toString());
//
//		ValidationUtils.invokeValidator(materialValidator, material, bindResult);
//
//		if (bindResult.hasErrors()) {
//			model.addAttribute("material", material);
//			return ViewConstant.VIEWS_MATERIALES_CREATE_OR_UPDATE_FORM;
//		}
//		Material materialSave = materialService.saveMaterial(material);
//		return "redirect:/materiales/showmateriales";
//
//	}
//	@RequestMapping(value = "findeditmaterial/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
//	public ResponseEntity<MaterialDTO> editarMaterial(@PathVariable("id") int id) {
//		try {
//			Optional<Material> materialO = materialService.findById(id);
//			Material material = materialO.get();
//			MaterialDTO edit = materialConverter.convertMaterialToMaterialDTO(material);
//			return new ResponseEntity<MaterialDTO>(edit, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<MaterialDTO>(HttpStatus.BAD_REQUEST);
//		}	
//	}

	@RequestMapping(value = "/updatematerial", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> updateMaterial(HttpServletRequest request, @ModelAttribute(name="material") Material material, BindingResult result) {
		try {
			

			
			TipoMaterial tipo = tipoMaterialConverter.convertToEntityAttribute(request.getParameter("tipo"));
			EstadoMaterial estadoAnterior =estadoMaterialConverter.convertToEntityAttribute(request.getParameter("EstadoAnterior"));
			EstadoMaterial estadoNuevo =estadoMaterialConverter.convertToEntityAttribute(request.getParameter("EstadoNuevo"));
			
			Material lalalaAntiguo= materialService.findByTipoAndEstado(tipo, estadoAnterior);
			Material lalalaNuevo= materialService.findByTipoAndEstado(tipo, estadoNuevo);
			
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			lalalaAntiguo.setStock(lalalaAntiguo.getStock()-cantidad);
			
			ValidationUtils.invokeValidator(materialValidator, lalalaAntiguo, result);

			if(result.hasErrors()) {
				LOG.warn("Se han encontrado " + result.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			
			if(lalalaNuevo == null) {
				Material materialNuevo = new Material();
				materialNuevo.setStock(cantidad);
				materialNuevo.setTipo(tipo);
				materialNuevo.setEstado(estadoNuevo);
				materialNuevo.setDescripcion(tipo.toString());
				materialService.save(materialNuevo);
				materialService.save(lalalaAntiguo);
			}else {
				lalalaNuevo.setStock(lalalaNuevo.getStock()+cantidad);
				materialService.save(lalalaAntiguo);
				materialService.save(lalalaNuevo);
			}
			
			
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("Error al guardar el material");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/postmaterial", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> addMaterial(HttpServletRequest request, @ModelAttribute(name="material") Material material, BindingResult result) {
		try {
			TipoMaterial tipo = tipoMaterialConverter.convertToEntityAttribute(request.getParameter("tipo"));
			Material lalala= materialService.findByTipoAndEstado(tipo, EstadoMaterial.NUEVO);
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			if(lalala == null) {
				Material materialNuevo = new Material();
				materialNuevo.setStock(cantidad);
				materialNuevo.setTipo(tipo);
				materialNuevo.setEstado(EstadoMaterial.NUEVO);
				materialNuevo.setDescripcion(tipo.toString());
				materialService.save(materialNuevo);
			}else {
				lalala.setStock(lalala.getStock()+cantidad);
				materialService.save(lalala);
			}
			
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("Error al guardar el material");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/eliminarmaterial")
	public String eliminarMaterial(@RequestParam(name="id",required=true) int id, Model model) {

		LOG.info("Se procede al borrado del material con id=" + id);
		materialService.deleteByIdSiExiste(id);

		return "redirect:/materiales/showmateriales";

	}
//	@PostMapping("/removeMaterial/{id}")
//	public ResponseEntity removeMaterial(@PathVariable("id") int id, Model model) {
//		try {
//			LOG.info("Se procede a borrar el material con id: " + id);
//			
//			materialService.deleteById(id);
//			
//			return new ResponseEntity(HttpStatus.OK);
//		} catch (Exception e) {
//			LOG.error("Error al eliminar el material");
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
//		
//	}
	@RequestMapping(value = "/removeMaterial", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObjectError>> removeMaterial(HttpServletRequest request, @ModelAttribute(name="material") Material material, BindingResult result) {
		try {
			TipoMaterial tipo = tipoMaterialConverter.convertToEntityAttribute(request.getParameter("tipo"));
			EstadoMaterial estado =estadoMaterialConverter.convertToEntityAttribute(request.getParameter("EstadodelMat"));
			
			Material lalala = materialService.findByTipoAndEstado(tipo, estado);
			
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			if(lalala == null) {
				Material materialNuevo = new Material();
				materialNuevo.setStock(cantidad);
				materialNuevo.setTipo(tipo);
				materialNuevo.setEstado(estado);
				materialNuevo.setDescripcion(tipo.toString());
				
			}else {
				lalala.setStock(lalala.getStock()-cantidad);
			}
	
			
			ValidationUtils.invokeValidator(materialValidator, lalala, result);

			if(result.hasErrors()) {
				LOG.warn("Se han encontrado " + result.getErrorCount() + " errores de validación");
				return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			
			
			materialService.save(lalala);
			
			return new ResponseEntity<List<ObjectError>>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("Error al guardar el material");
			return new ResponseEntity<List<ObjectError>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/navbar")
	public String navbar() {
		return ViewConstant.VIEW_NAVBAR;
	}
	private Integer calcularPorcentajeUso( TipoMaterial material){

		int materialito = materialService.porcentajeUso(material);

		return materialito;
	}


}