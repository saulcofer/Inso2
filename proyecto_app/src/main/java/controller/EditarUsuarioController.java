/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    
    private String accion; // M=modificar E=eliminar R=añadir , hay que saber el caso para mostrar el widgetBar pertinente
    
    private Usuario usuario;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
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
        if(this.usuario.getRol().getDescripcion().equals("Participante")){
            // quitar filas usuarios-sesiones
            this.usuario.getSesiones().clear();
        }else{
            // el usuario es entrenador, por tanto se borra la sesión (no puede haber sesión sin entrenador)
            System.out.println("Número de sesiones: "+this.usuario.getSesiones().size());
            for(Sesion sesion: this.usuario.getSesiones()){
                System.out.println("Hola!!!!!");
                for(Usuario us : sesion.getUsuarios()) {
                    // quitar filas usuarios-sesiones
                    us.getSesiones().remove(sesion);
                }
                for(Instalacion instalacion : sesion.getInstalaciones()) {
                    // quitar filas tabla instalaciones-sesiones
                    instalacion.getSesiones().remove(sesion);
                }
                sesionEJB.remove(sesion);
            }

        }       
        userEJB.remove(this.usuario);
        usuarios = userEJB.findAll();
    }

//public void eliminarUsuario() {
//    if (this.usuario.getRol().getDescripcion().equals("Participante")) {
//        // quitar filas usuarios-sesiones
//        this.usuario.getSesiones().clear();
//    } else {
//        // el usuario es entrenador, por tanto se borra la sesión (no puede haber sesión sin entrenador)
//        System.out.println("Número de sesiones: " + this.usuario.getSesiones().size());
//
//        // Crear una lista temporal para almacenar las sesiones a eliminar
//        List<Sesion> sesionesAEliminar = new ArrayList<>(this.usuario.getSesiones());
//
//        for (Sesion ses : sesionesAEliminar) {
//            System.out.println("Hola!!!!!");
//
//            // Crear una lista temporal para los usuarios en la sesión
//            List<Usuario> usuariosEnSesion = new ArrayList<>(ses.getUsuarios());
//            for (Usuario us : usuariosEnSesion) {
//                // quitar filas usuarios-sesiones
//                us.getSesiones().remove(ses);
//            }
//
//            // Crear una lista temporal para las instalaciones en la sesión
//            List<Instalacion> instalacionesEnSesion = new ArrayList<>(ses.getInstalaciones());
//            for (Instalacion instalacion : instalacionesEnSesion) {
//                // quitar filas tabla instalaciones-sesiones
//                instalacion.getSesiones().remove(ses);
//            }
//
//            // Eliminar la sesión del EJB
//            sesionEJB.remove(ses);
//        }
//
//        // Limpiar la lista de sesiones del usuario actual
//        this.usuario.getSesiones().clear();
//    }
//
//    // Eliminar el usuario
//    userEJB.remove(this.usuario);
//    usuarios = userEJB.findAll();
//}

    
    public void establecerUsuarioModificar(Usuario aUser){
        this.usuario=aUser;
        this.setAccion("M");
    }
    
    public void modificarUsuario(){
        userEJB.edit(this.usuario);
        usuarios = userEJB.findAll();
    }
}
