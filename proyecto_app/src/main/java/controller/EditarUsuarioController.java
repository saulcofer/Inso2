/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InstalacionFacadeLocal;
import ejb.SesionFacadeLocal;
import ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Instalacion;
import modelo.Sesion;
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
    
    @EJB
    private InstalacionFacadeLocal instalacionEJB;
    
    private String accion; // M=modificar E=eliminar R=añadir , hay que saber el caso para mostrar el widgetBar pertinente
    
    private Usuario usuario;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
    @PostConstruct
    public void init(){
        System.out.println("Se cargan los usuarios de la DB");
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
        if(this.usuario.getRol().getDescripcion().equals("Participante")){
            // Remove all sessions associated with the participant user
            this.usuario.getSesiones().clear();
        } else {
            // The user is a coach, so sessions should be deleted since they can't exist without a coach
            System.out.println("Número de sesiones: " + this.usuario.getSesiones().size());
            System.out.println(this.usuario.getSesiones());

            // Create a copy of the list of sessions to avoid ConcurrentModificationException
            List<Sesion> sesionesAEliminar = new ArrayList<>(this.usuario.getSesiones());

            for(Sesion sesion: sesionesAEliminar){
                System.out.println("Hola!!!!!");

                // Remove the session from each user in the session
                for(Usuario us : new ArrayList<>(sesion.getUsuarios())) {
                    us.getSesiones().remove(sesion);
                    userEJB.edit(us);
                }

                // Remove the session from each installation in the session
                for(Instalacion instalacion : new ArrayList<>(sesion.getInstalaciones())) {
                    instalacion.getSesiones().remove(sesion);
                    instalacionEJB.edit(instalacion); 
                }

                // Remove the session itself
                sesionEJB.remove(sesion);
            }
        }

        // Remove the user
        userEJB.remove(this.usuario);

        // Refresh the list of users
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
