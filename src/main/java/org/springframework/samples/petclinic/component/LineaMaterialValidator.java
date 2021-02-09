package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.enumerate.EstadoMaterial;
import org.springframework.samples.petclinic.enumerate.TipoMaterial;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.model.Material;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.samples.petclinic.service.impl.EntrenamientoServiceImpl;
import org.springframework.samples.petclinic.service.impl.MaterialServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
@AllArgsConstructor
public class LineaMaterialValidator implements Validator {
	private static final Log LOG = LogFactory.getLog(LineaMaterialValidator.class);

	@Autowired
	private LineaMaterialService lineaMaterialService;

	@Autowired
	private MaterialServiceImpl materialService;

	@Autowired
	private EntrenamientoServiceImpl entrenamientoService;

	@Override
	public void validate(Object target, Errors errors) {
		LineaMaterial lineaMaterial = (LineaMaterial) target;

		//Cantidad validation
		if ( lineaMaterial.getCantidad()==null || lineaMaterial.getCantidad()<0) {
			errors.rejectValue("cantidad", "La cantidad no debe ser nula ni menor que 0","La cantidad no debe ser nulo ni menor que 0");
		}
		if ((lineaMaterial.getCantidad()>puedesUsar(lineaMaterial)) && lineaMaterial.getCantidad()<=getStockTotal(lineaMaterial.getMaterial().getTipo())) {
			int stockDisponible = puedesUsar(lineaMaterial);
			errors.rejectValue("cantidad", "La cantidad máxima que puedes usar son "+stockDisponible+" unidades","La cantidad máxima que puedes usar son "+stockDisponible+" unidades");
		}
		if ( lineaMaterial.getCantidad()>getStockTotal(lineaMaterial.getMaterial().getTipo())) {
			int stock = getStockTotal(lineaMaterial.getMaterial().getTipo());
			errors.rejectValue("cantidad", "La cantidad máxima que puedes usar son "+stock+" unidades","La cantidad máxima que puedes usar son "+stock+" unidades");
		}//
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return LineaMaterial.class.isAssignableFrom(clazz);
	}
		
	
	private int puedesUsar(LineaMaterial lineaMaterial) {
		int fin=getStockTotal(lineaMaterial.getMaterial().getTipo());
		TipoMaterial tipo = lineaMaterial.getMaterial().getTipo();

		List<Material> materialesDelMismoTipo= new ArrayList<>();

		for(Material m:materialService.findByTipo(tipo) ){

			if(m.getEstado()!=EstadoMaterial.INSERVIBLE &&m.getEstado()!=EstadoMaterial.DAÑADO) {
				materialesDelMismoTipo.add(m);
			}
		}
		Equipo equipo =lineaMaterial.getEntrenamiento().getEquipo();
		LocalDate fecha = lineaMaterial.getEntrenamiento().getFecha();
		String hora1= lineaMaterial.getEntrenamiento().getHora();
		String hora2=horaMasMenosNHoras(lineaMaterial.getEntrenamiento().getHora(), 1);

		List<Entrenamiento> entrSolapados= new ArrayList<>();
		List<LineaMaterial> lineaDndeSeUsa = new ArrayList<LineaMaterial>();
		//
		entrSolapados.addAll(entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, fecha, hora1, hora2));
		for(Entrenamiento entr:entrSolapados) {


			for(Material material: materialesDelMismoTipo) {
				lineaDndeSeUsa.addAll(lineaMaterialService.findByMaterialAndEntrenamiento(material, entr));
			}
			for(LineaMaterial lin:lineaDndeSeUsa) {
				fin=fin-lin.getCantidad();
			}
		}
		return fin;
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
	private int getStockTotal(TipoMaterial tipo) {
		int stock =0;

		Material materialAceptable= materialService.findByTipoAndEstado(tipo, EstadoMaterial.ACEPTABLE);
		Material materialBueno= materialService.findByTipoAndEstado(tipo, EstadoMaterial.BUENO);
		Material materialNuevo= materialService.findByTipoAndEstado(tipo, EstadoMaterial.NUEVO);

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
}
