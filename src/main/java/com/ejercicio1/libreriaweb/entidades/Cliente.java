package com.ejercicio1.libreriaweb.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private boolean alta;

    public Cliente() {
        this.alta = true;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the documento
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Long documento) {
        this.setDocumento((long) documento);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the alta
     */
    public Boolean getAlta() {
        return isAlta();
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Boolean alta) {
        this.setAlta((boolean) alta);
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + getId() + ", documento=" + getDocumento() + ", nombre=" + getNombre() + ", apellido=" + getApellido() + ", telefono=" + getTelefono() + ", alta=" + isAlta() + '}';
    }

    /**
     * @return the alta
     */
    public boolean isAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(long documento) {
        this.documento = documento;
    }

}
