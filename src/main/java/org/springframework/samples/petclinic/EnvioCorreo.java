package org.springframework.samples.petclinic;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Entrenamiento;
import org.springframework.samples.petclinic.model.LineaMaterial;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EntrenamientoService;
import org.springframework.samples.petclinic.service.EnvioEmailService;
import org.springframework.samples.petclinic.service.LineaMaterialService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EnvioCorreo {
	
	@Autowired
	private EnvioEmailService enviarEmailService;
	
	@Autowired
	private EntrenamientoService entrenamientoService;
	
	@Autowired
	private EntrenadorService entrenadorService;
	
	@Autowired
	private LineaMaterialService lineaMaterialService;
	
	@Scheduled(cron = "0 30 23 * * *", zone="GMT+1")
	public void correo() {
		LocalDate fecha = LocalDate.now();
		List<Entrenamiento> entrenamientosHoy = entrenamientoService.findByFechaOrderByHora(fecha);
		if(entrenamientosHoy.size()>0) {
		List<Entrenador> entrenadores = entrenadorService.findAll();
		String contenido = "";
		
		for(int i =0; i< entrenamientosHoy.size(); i++) {
			contenido = contenido+"\n - Entrenamiento con fecha y hora: "+entrenamientosHoy.get(i).getFecha() + " "+entrenamientosHoy.get(i).getHora()+
					" y del equipo: "+ entrenamientosHoy.get(i).getEquipo().getCategoria()+ "\n\n";
			List<LineaMaterial> lineas = lineaMaterialService.findByEntrenamiento(entrenamientosHoy.get(i).getId());
			for(int j=0; j< lineas.size();j++) {
				contenido = contenido+ " 		- " + lineas.get(j).getMaterial().getTipo() + " usados una cantidad de "
						+ lineas.get(j).getCantidad()+"\n"; 
			}
		}
		System.out.println(contenido);
		for(Entrenador entrenador: entrenadores) {
			enviarEmailService.sendEmail(entrenador.getEmail(), "Cantidad de material usada hoy en los entrenamientos", contenido);
		}
		}
	}
	 

}
