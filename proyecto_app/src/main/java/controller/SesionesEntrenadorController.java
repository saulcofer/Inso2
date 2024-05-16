/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.SesionFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Sesion;
import modelo.Usuario;

/**
 *
 * @author jvega
 */
@Named
@ViewScoped
public class SesionesEntrenadorController implements Serializable{
    private List<Sesion> listasesiones;    
    private Sesion sesion;
    private Usuario user;
    private String accion;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
    @PostConstruct
    public void init(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionAñadir(){
        this.setAccion("R");
        this.sesion = new Sesion();
    }
    
    public void insertarSesion(){
        List<Usuario> usuarios = new ArrayList<>();
        sesion.setUsuarios(usuarios);
        sesion.getUsuarios().add(user);
        user.getSesiones().add(sesion); 
        sesionEJB.create(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionEliminar(Sesion sesion){
        this.setAccion("E");
        this.sesion = sesion;
    }
    
    public void eliminarSesion(){
        for(Usuario usuario : sesion.getUsuarios()) {
            usuario.getSesiones().remove(sesion);
        }

        sesionEJB.remove(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionModificar(Sesion sesion){
        this.setAccion("M");
        this.sesion = sesion;
    }
    
    public void modificarSesion(){
        sesionEJB.edit(this.sesion);
        listasesiones = user.getSesiones();
    }

    public List<Sesion> getListasesiones() {
        return listasesiones;
    }

    public void setListasesiones(List<Sesion> listasesiones) {
        this.listasesiones = listasesiones;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public SesionFacadeLocal getSesionEJB() {
        return sesionEJB;
    }

    public void setSesionEJB(SesionFacadeLocal sesionEJB) {
        this.sesionEJB = sesionEJB;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
}
