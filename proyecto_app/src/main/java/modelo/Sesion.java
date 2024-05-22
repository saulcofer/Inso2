/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
 * @author alvar
 */

@Entity
@Table(name="sesiones")
public class Sesion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSesion;
    
    @Column(name="Titulo")
    private String titulo;
    
    @Column(name="Cuerpo")
    private String cuerpo;
        
    @Column(name="Fecha")
    private Date fecha;
    
    @Column(name="Valoracion_media")
    private float valoracion;
    
    @Column(name="Comentarios")
    private String comentarios;
    
    
    @JoinTable(
        name = "usuarios_sesiones",
        joinColumns = @JoinColumn(name = "IdSesion", nullable = false),
        inverseJoinColumns = @JoinColumn(name="IdUser", nullable = false) // pendiente de añadir columnas adicionales
    )    
    @ManyToMany
    private List<Usuario> usuarios;
    
    @JoinTable(
        name = "instalaciones_sesiones",
        joinColumns = @JoinColumn(name = "IdSesion", nullable = false),
        inverseJoinColumns = @JoinColumn(name="IdInstalacion", nullable = false) // pendiente de añadir columnas adicionales
    )    
    @ManyToMany
    private List<Instalacion> instalaciones;

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(List<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }

    
    
    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.idSesion;
        hash = 23 * hash + Objects.hashCode(this.titulo);
        hash = 23 * hash + Objects.hashCode(this.cuerpo);
        hash = 23 * hash + Objects.hashCode(this.fecha);
        hash = 23 * hash + Float.floatToIntBits(this.valoracion);
        hash = 23 * hash + Objects.hashCode(this.comentarios);
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
        final Sesion other = (Sesion) obj;
        if (this.idSesion != other.idSesion) {
            return false;
        }
        if (Float.floatToIntBits(this.valoracion) != Float.floatToIntBits(other.valoracion)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.cuerpo, other.cuerpo)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
    
    
    
}
