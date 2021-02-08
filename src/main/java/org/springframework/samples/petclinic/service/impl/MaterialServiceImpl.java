package org.springframework.samples.petclinic.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.repository.MaterialRepository;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.samples.petclinic.service.MaterialService;
import org.springframework.samples.petclinic.service.base.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("materialService")
public class MaterialServiceImpl extends AbstractService<Material> implements MaterialService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(MaterialServiceImpl.class);

	@Autowired
	@Qualifier("materialRepository")
	private MaterialRepository materialRepository;

	@Autowired
	private LineaMaterialService lineaMaterialService;

	@Autowired
	private PartidoServiceImpl partidoService;

	@Autowired
	private EntrenamientoServiceImpl entrenamientoService;

	@Override
	@Transactional(readOnly = true)
	public Optional<Material> findById(int id) {
		return materialRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> findByTipo(TipoMaterial tipo) {
		return materialRepository.findByTipo(tipo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> findByDescripcion(String descripcion) {
		return materialRepository.findByDescripcion(descripcion);
	}

	@Override
	public List<Material> findByStock(int stock) {
		return materialRepository.findByStock(stock);
	}

	public int getStockTotal(TipoMaterial tipo) {
		int stock =0;

		Material materialAceptable= findByTipoAndEstado(tipo, EstadoMaterial.ACEPTABLE);
		Material materialBueno= findByTipoAndEstado(tipo, EstadoMaterial.BUENO);
		Material materialNuevo= findByTipoAndEstado(tipo, EstadoMaterial.NUEVO);

		if(materialAceptable!=null) {
			stock+=materialAceptable.getStock();
		}
		if(materialBueno!=null) {
			stock+=materialBueno.getStock();
		}
		if(materialNuevo!=null) {
			stock+=materialNuevo.getStock();
		}


		return stock;
	}
	private String horaMasMenosNHoras(String hora, int tiempo) {
		String[] splitHora = hora.split(":");
		Integer horaInt = Integer.valueOf(splitHora[0]);
		Integer minutosInt = Integer.valueOf(splitHora[1]);
		Integer horaAux = horaInt + tiempo;
		Integer minutosAux = 0;

		if(tiempo > 0) {
			minutosAux = minutosInt - 1;
			if(minutosAux < 0) {
				minutosAux = minutosAux + 60;
				horaAux = horaAux - 1;
			}
		} else {
			minutosAux = minutosInt + 1;
			if(minutosAux > 59) {
				minutosAux = minutosAux - 60;
				horaAux = horaAux + 1;
			}
		}

		if(horaAux < 0) {
			horaAux = horaAux + 24;
		} else if(horaAux > 23) {
			horaAux = horaAux - 24;
		}

		String horaString = (horaAux < 10)?"0"+horaAux:horaAux.toString();
		String minutosString = (minutosAux < 10)?"0"+minutosAux:minutosAux.toString();

		String horaFin = horaString+":"+minutosString;

		return horaFin;
	}
	@Override
	public  int porcentajeUso(TipoMaterial material){


		List<LineaMaterial> lineasdndeseusaelmaterial= new ArrayList<>();
		int stock=getStockTotal(material); //se me guarda el stock total de ese tipo de material en caso de cono alto se guarda un 9

		List<Integer> finlcomun=new ArrayList<>();
		int finl=0;
		if(stock==0) {
			finl=100;
		}else {

			lineasdndeseusaelmaterial.addAll(lineaMaterialService.findByTipoAndEstado(material, EstadoMaterial.ACEPTABLE));
			lineasdndeseusaelmaterial.addAll(lineaMaterialService.findByTipoAndEstado(material, EstadoMaterial.BUENO));
			lineasdndeseusaelmaterial.addAll(lineaMaterialService.findByTipoAndEstado(material, EstadoMaterial.NUEVO));


			for(int i=0;i<lineasdndeseusaelmaterial.size();i++) {
				Equipo equipo =lineasdndeseusaelmaterial.get(i).getEntrenamiento().getEquipo();
				LocalDate fecha = lineasdndeseusaelmaterial.get(i).getEntrenamiento().getFecha();
				String hora1= lineasdndeseusaelmaterial.get(i).getEntrenamiento().getHora();
				String hora2=horaMasMenosNHoras(lineasdndeseusaelmaterial.get(i).getEntrenamiento().getHora(), 1);
				List<Entrenamiento> entrSolapados= new ArrayList<>();
				List<LineaMaterial> entrSolapadosMismoTipo= new ArrayList<>();
				//
				entrSolapados.addAll(entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2));
				for(Entrenamiento entr:entrSolapados) {
					entrSolapadosMismoTipo.addAll(lineaMaterialService.findByMaterialAndEntrenamiento(lineasdndeseusaelmaterial.get(i).getMaterial(), entr));
				}


				int usados=0;

				for(LineaMaterial linea: entrSolapadosMismoTipo) {
					usados+=linea.getCantidad();
				}
				finlcomun.add(usados*100/stock);


			}
			if(finlcomun.isEmpty() ) {
				finl=0;
			}else {
				int porcent=0;
				for(int i :finlcomun) {
					porcent+=i;
				}
				finl=porcent/finlcomun.size();
			}


		}


		if(finl>100) {
			return 100;
		}else {
			return finl;
		}

	}

	@Override
	public Material findByTipoAndEstado(TipoMaterial tipo, EstadoMaterial estado) {
		Material material = materialRepository.findByTipoAndEstado(tipo, estado);
		return material;
	}




}
