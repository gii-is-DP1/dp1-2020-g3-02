package org.springframework.samples.petclinic.enumerate;

public enum Estado {
	
	EN_FORMA, LESIONADO;
	
	public static Estado fromNombre(String nombre) {
		for(Estado a : Estado.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
	
}
