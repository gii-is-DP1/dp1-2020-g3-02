package org.springframework.samples.petclinic.enumerate;

public enum Posicion {
	
	PUNTA,OPUESTO,COLOCADOR,CENTRAL,LIBERO;
	
	public static Posicion fromNombre(String nombre) {
		for(Posicion a : Posicion.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
	
}
