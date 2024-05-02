/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alvar
 */

@Entity
@Table(name="publicaciones")
public class Publicacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublicacion;
    
    @Column(name="Titulo")
    private String titulo;
    
    @Column(name="Cuerpo")
    private String cuerpo;
    
    @Column(name="ComentarioProfesor")
    private String comentario_profe; 
    
    @Column(name="Valoracion")
    private int valoracion;
    
    @Column(name="Fecha")
    private Date fecha;
    
    @JoinColumn(name="IdPersona")
    @ManyToOne
    private Persona persona;
    
    @JoinColumn(name="IdCategoria")
    @ManyToOne
    private Categoria categoria;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
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

    public String getComentario_profe() {
        return comentario_profe;
    }

    public void setComentario_profe(String comentario_profe) {
        this.comentario_profe = comentario_profe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idPublicacion;
        hash = 13 * hash + Objects.hashCode(this.titulo);
        hash = 13 * hash + Objects.hashCode(this.cuerpo);
        hash = 13 * hash + Objects.hashCode(this.comentario_profe);
        hash = 13 * hash + this.valoracion;
        hash = 13 * hash + Objects.hashCode(this.fecha);
        hash = 13 * hash + Objects.hashCode(this.persona);
        hash = 13 * hash + Objects.hashCode(this.categoria);
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
        final Publicacion other = (Publicacion) obj;
        if (this.idPublicacion != other.idPublicacion) {
            return false;
        }
        if (this.valoracion != other.valoracion) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.cuerpo, other.cuerpo)) {
            return false;
        }
        if (!Objects.equals(this.comentario_profe, other.comentario_profe)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.persona, other.persona)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }


}
