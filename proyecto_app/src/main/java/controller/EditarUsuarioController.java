/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped

public class EditarUsuarioController implements Serializable{
    
    private List<Usuario> usuarios;

    @EJB
    private UsuarioFacadeLocal userEJB;
    
    private String accion; // M=modificar E=eliminar R=añadir , hay que saber el caso para mostrar el widgetBar pertinente
    
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuarios = userEJB.findAll();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioFacadeLocal getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UsuarioFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void establecerUsuarioEliminar(Usuario aUser){
        this.usuario=aUser;
        this.setAccion("E");
    }
    
    public void eliminarUsuario(){
        userEJB.remove(this.usuario);
        usuarios = userEJB.findAll();
    }
    
    public void establecerUsuarioModificar(Usuario aUser){
        this.usuario=aUser;
        this.setAccion("M");
    }
    
    public void modificarUsuario(){
        userEJB.edit(this.usuario);
        usuarios = userEJB.findAll();
    }
}
