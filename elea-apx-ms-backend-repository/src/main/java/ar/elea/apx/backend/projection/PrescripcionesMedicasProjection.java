package ar.elea.apx.backend.projection;

public interface PrescripcionesMedicasProjection {
	String getCodigo();
	String getNombre();
	int getPrescripciones();
	
	void setCodigo(String codigo);
	void setNombre(String nombre);
	void setPrescripciones(int prescripciones);
}
