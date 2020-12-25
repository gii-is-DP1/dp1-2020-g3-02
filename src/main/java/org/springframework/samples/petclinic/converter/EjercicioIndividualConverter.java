package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.model.EjercicioIndividual;
import org.springframework.samples.petclinic.model.auxiliares.EjercicioIndividualDTO;
import org.springframework.stereotype.Component;

@Component
public class EjercicioIndividualConverter {
	
	public EjercicioIndividualDTO converterEntityToDTO(EjercicioIndividual entity) {
		return new EjercicioIndividualDTO(entity.getId(), entity.getTipoEjercicio(), entity.getNombre(), entity.getDescripcion());
	}
	
	public List<EjercicioIndividualDTO> converListEntityToListDTO(List<EjercicioIndividual> entities){
		List<EjercicioIndividualDTO> dtos = new ArrayList<EjercicioIndividualDTO>();
		
		for(int i=0;i<entities.size();i++) {
			EjercicioIndividual entity = entities.get(i);
			EjercicioIndividualDTO dto = converterEntityToDTO(entity);
			dtos.add(dto);
		}
		
		return dtos;
		
	}

}
