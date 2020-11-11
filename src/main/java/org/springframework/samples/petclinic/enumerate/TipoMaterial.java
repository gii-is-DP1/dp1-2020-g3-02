package org.springframework.samples.petclinic.enumerate;

public enum TipoMaterial {
	BALONMEDICINAL,BALONDEJUEGO,RED,POSTE,CONOBAJO,CONOMEDIO,CONOALTO,CUERDA,CINTA;
	
	public static TipoMaterial fromNombre(String nombre) {
		for(TipoMaterial a : TipoMaterial.values()) {
			if(a.name().equals(nombre)) {
				return a;
			}
		}
		return null;
	}
}
