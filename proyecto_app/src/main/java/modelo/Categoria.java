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
@Table(name="categorias")
public class Categoria implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // autogenerado
    private int idCategoria;

    @Column(name="Nombre")
    private String nombre;

    @Column(name="Estado")
    private boolean estado = true;

    // Getters
    public int getIdCategoria() {
        return idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idCategoria;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + (this.estado ? 1 : 0);
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
        final Categoria other = (Categoria) obj;
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    // Setters
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
