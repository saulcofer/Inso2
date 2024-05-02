/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alvar
 */

@Entity
@Table(name="roles")
public class Rol implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // autogenerado
    private int idRol;
    
    @Column(name="TipoUsuario")
    private char tipoUsuario;
    
    @Column(name="Descripcion")
    private String descripcion;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idRol;
        hash = 17 * hash + this.tipoUsuario;
        hash = 17 * hash + Objects.hashCode(this.descripcion);
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
        final Rol other = (Rol) obj;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.tipoUsuario != other.tipoUsuario) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public char getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(char tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
       
}
