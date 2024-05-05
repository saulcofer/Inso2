/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author jvega
 */
@Entity
@Table(name="instalaciones_sesiones")
public class Instalacion_sesion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // autogenerado
    private int id;
    
    @JoinColumn(name="IdSesion")
    @ManyToMany
    private Sesion sesion;
    
    @JoinColumn(name="IdInstalacion")
    @ManyToMany
    private Instalacion instalacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(Instalacion instalacion) {
        this.instalacion = instalacion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.sesion);
        hash = 37 * hash + Objects.hashCode(this.instalacion);
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
        final Instalacion_sesion other = (Instalacion_sesion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sesion, other.sesion)) {
            return false;
        }
        if (!Objects.equals(this.instalacion, other.instalacion)) {
            return false;
        }
        return true;
    }
    
    
}
