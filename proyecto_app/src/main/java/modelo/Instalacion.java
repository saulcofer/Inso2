/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author jvega
 */
@Entity
@Table(name="instalaciones")
public class Instalacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // autogenerado
    private int idInstalacion;

    @Column(name="Nombre")
    private String nombre;

    @Column(name="Descripcion")
    private String descripcion;
    
    @Column(name="Tipo")
    private String tipo;
        
    @Column(name="Aforo_min")
    private int aforo_min;
            
    @Column(name="Aforo_max")
    private int aforo_max;

    
    @JoinTable(
        name = "instalaciones_sesiones",
        joinColumns = @JoinColumn(name = "IdInstalacion", nullable = false),
        inverseJoinColumns = @JoinColumn(name="IdSesion", nullable = false)
    )
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Sesion> sesiones;
    
    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAforo_min() {
        return aforo_min;
    }

    public void setAforo_min(int aforo_min) {
        this.aforo_min = aforo_min;
    }

    public int getAforo_max() {
        return aforo_max;
    }

    public void setAforo_max(int aforo_max) {
        this.aforo_max = aforo_max;
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }
    
 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idInstalacion;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        hash = 67 * hash + Objects.hashCode(this.tipo);
        hash = 67 * hash + this.aforo_min;
        hash = 67 * hash + this.aforo_max;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instalacion other = (Instalacion) obj;
        if (this.idInstalacion != other.idInstalacion) {
            return false;
        }
        if (this.aforo_min != other.aforo_min) {
            return false;
        }
        if (this.aforo_max != other.aforo_max) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }
    
    
}
