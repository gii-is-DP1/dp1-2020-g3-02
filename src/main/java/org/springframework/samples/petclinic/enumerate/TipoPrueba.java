package org.springframework.samples.petclinic.enumerate;

public enum TipoPrueba {
	ABDOMINAL,FLEXIBILIDAD,VELOCIDAD,SALTOVERTICAL,AGILIDAD,RESISTENCIA,PULSACIONESMINIMAS;
	
	public static TipoPrueba fromNombre(String nombre) {
		for(TipoPrueba a : TipoPrueba.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
