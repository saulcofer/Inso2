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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alvar
 */
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // autogenerado
    private int idUser;
    
    @Column(name="Username")
    private String username;
    
    @Column(name="Password")
    private String password;
    
    @Column(name="UltimaConexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaConexion;
        
    // CascadType.MERGE/REMOVE para que se actualice/borre la fila de Persona cuando se actualice/borre el usuario en la llamada a edit/remove
    @JoinColumn(name="IdPerson")
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Persona persona;
    
    @JoinColumn(name="IdRol")
    @ManyToOne
    private Rol rol;
    
    @JoinTable(
        name = "usuarios_sesiones",
        joinColumns = @JoinColumn(name = "IdUser", nullable = false),
        inverseJoinColumns = @JoinColumn(name="IdSesion", nullable = false) // pendiente de añadir columnas adicionales
    )
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Sesion> sesiones;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idUser;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.ultimaConexion);
        hash = 29 * hash + Objects.hashCode(this.persona);
        hash = 29 * hash + Objects.hashCode(this.rol);
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
        final Usuario other = (Usuario) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.persona != other.persona) {
            return false;
        }
        if (this.rol != other.rol) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.ultimaConexion, other.ultimaConexion)) {
            return false;
        }
        return true;
    }

    public int getIdUsuario() {
        return idUser;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUser = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona unaPersona) {
        this.persona = unaPersona;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol aRol) {
        this.rol = aRol;
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }
    
  
}
