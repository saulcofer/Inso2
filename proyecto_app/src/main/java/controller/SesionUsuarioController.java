/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.SesionFacadeLocal;
import java.io.Serializable;
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
 * @author saulcofer
 */

@Named
@ViewScoped
public class SesionUsuarioController implements Serializable{
    
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

    public SesionFacadeLocal getSesionEJB() {
        return sesionEJB;
    }

    public void setSesionEJB(SesionFacadeLocal sesionEJB) {
        this.sesionEJB = sesionEJB;
    }
    
    public void establecerSesionValorar(Sesion sesion){
        this.sesion = sesion;
    }
    
    
    public void valorarSesionUsuario(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.sesion = sesion;
        sesionEJB.edit(this.sesion);
        listasesiones = user.getSesiones();
        
        if(sesion.getUsuarios().isEmpty())
        {
            //Sesion vacia
            sesion.getValoracion();
        }else{
            //Sesion con participantes
            
            
        }
        
    }
    
    public void inscribirseEnSesion(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        sesion.getUsuarios().add(user);
        user.getSesiones().add(sesion); 
        listasesiones = user.getSesiones();
    }
    
}