package ec.edu.ups.sd64.eva.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Universidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private int codigo;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String serial;
    
 // Getters y setters
    
    
	public int getCodigo() {
		return codigo;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Universidad [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", direccion="
				+ direccion + "]";
	}

	
    

}
