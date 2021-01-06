package org.springframework.samples.petclinic.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.model.RealizaEjercicio;
import org.springframework.samples.petclinic.model.auxiliares.RealizaEjercicioDTO;
import org.springframework.stereotype.Component;

@Component
public class RealizaEjercicioConverter {
	
	public RealizaEjercicioDTO converterEntityToDTO(RealizaEjercicio entity) {
		return new RealizaEjercicioDTO(entity.getJugador().getFirstName() + " " + entity.getJugador().getLastName(), 
				entity.getJugador().getPosicionPrincipal(), 
				entity.getEjercicioIndividual().getNombre(), 
				entity.getFecha().getDayOfMonth()+"/"+entity.getFecha().getMonthValue()+"/"+entity.getFecha().getYear());
	}
	
	public List<RealizaEjercicioDTO> converListEntityToListDTO(List<RealizaEjercicio> entities){
		List<RealizaEjercicioDTO> dtos = new ArrayList<RealizaEjercicioDTO>();
		
		for(int i=0;i<entities.size();i++) {
			RealizaEjercicio entity = entities.get(i);
			RealizaEjercicioDTO dto = converterEntityToDTO(entity);
			dtos.add(dto);
		}
		
		return dtos;
		
	}

}
