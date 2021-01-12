package org.springframework.samples.petclinic.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.constant.ValidationConstant;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.ediciones.EntrenamientoEdit;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class EntrenamientoValidator implements Validator {
	
private static final Log LOG = LogFactory.getLog(PartidoValidator.class);
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EquipoService equipoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return EntrenamientoEdit.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		EntrenamientoEdit entrenamiento = (EntrenamientoEdit) target;
		
		if(StringUtils.isEmpty(entrenamiento.getFecha())) {
			LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": fecha");
			errors.rejectValue("fecha", "error", ValidationConstant.VALOR_OBLIGATORIO);
		} else if (!(Pattern.matches("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$", entrenamiento.getFecha()))){
			LOG.warn(ValidationConstant.FECHA_FORMATO_ERRONEO);
			errors.rejectValue("fecha", "error", ValidationConstant.FECHA_FORMATO_ERRONEO);
		} else {
			LocalDate fecha = LocalDate.parse(entrenamiento.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			if(fecha.isBefore(LocalDate.now().plusDays(1))) {
				LOG.warn(ValidationConstant.FECHA_ANTERIOR_ERROR);
				errors.rejectValue("fecha", "error", ValidationConstant.FECHA_ANTERIOR_ERROR);
			}
			
			if(StringUtils.isEmpty(entrenamiento.getHora())) {
				LOG.warn(ValidationConstant.VALOR_OBLIGATORIO + ": hora");
				errors.rejectValue("hora", "error", ValidationConstant.VALOR_OBLIGATORIO);
			} else if(!Pattern.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", entrenamiento.getHora())) {
				LOG.warn(ValidationConstant.HORA_FORMATO_ERRONEO);
				errors.rejectValue("hora", "error", ValidationConstant.HORA_FORMATO_ERRONEO);
			} else {
				LocalDate date = LocalDate.parse(entrenamiento.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				String horaAnterior = horaMasMenosNHoras(entrenamiento.getHora(), -1);
				String horaPosterior = horaMasMenosNHoras(entrenamiento.getHora(), 1);
				Equipo equipo = equipoService.findByCategoria(entrenamiento.getEquipo());
				if(entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, date, horaAnterior, entrenamiento.getHora()).size() != 0) {
					List<Entrenamiento> partidos = entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, date, horaAnterior, entrenamiento.getHora());
					if(entrenamiento.getId() == null) {
						LOG.warn(ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_ANTERIOR);
						errors.rejectValue("hora", "error", ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_ANTERIOR);
					}else if (entrenamiento.getId() != partidos.get(0).getId()) {
						LOG.warn(ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_ANTERIOR);
						errors.rejectValue("hora", "error", ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_ANTERIOR);
					}
					
				} else if(entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, date, entrenamiento.getHora(), horaPosterior).size() != 0) {
					List<Entrenamiento> partidos = entrenamientoService.findByEquipoAndFechaAndHoraBetween(equipo, date, entrenamiento.getHora(), horaPosterior);
					if(entrenamiento.getId() == null) {
						LOG.warn(ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_POSTERIOR);
						errors.rejectValue("hora", "error", ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_POSTERIOR);
					}else if (entrenamiento.getId() != partidos.get(0).getId()) {
						LOG.warn(ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_POSTERIOR);
						errors.rejectValue("hora", "error", ValidationConstant.HORA_ENTRENAMIENTO_COINCIDEN_POSTERIOR);
					}				
				}
			}
		}
	}
	
	// FUNCIONES AUXILIARES
		/** 
		 * 
		 * @param hora		recibe en un string una hora no vacía
		 * @param tiempo	recibe en un entero no nulo positivo o negativo lo que necesitemos sumar o restar a la hora
		 * @return horaFin	devuelve la hora calculada como un string
		 * 
		 */  
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

}
