/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InstalacionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Instalacion;
import modelo.Usuario;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped
public class EditarInstalacionController implements Serializable{
    private List<Instalacion> listaInstalaciones;    
    private Instalacion ins;
    private Usuario user;
    private String accion;
    
    @EJB
    private InstalacionFacadeLocal instalacionEJB;
    
    @PostConstruct
    public void init(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listaInstalaciones = instalacionEJB.findAll();
    }
    
    public void establecerInstalacionAñadir(){
        this.setAccion("R");
        this.ins = new Instalacion();
    }
    
    public void insertarInstalacion(){
        int resp=instalacionEJB.crearInstalacion(this.ins);
        if(resp==0){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"El nombre de instalación debe ser único",""));
        }else{
            listaInstalaciones = instalacionEJB.findAll();
        }
    }
    
    public void establecerInstalacionEliminar(Instalacion ins){
        this.setAccion("E");
        this.ins = ins;
    }
    
    public void eliminarInstalacion(){
        // Falta eliminar la instalación de objetos sesiones
        instalacionEJB.remove(this.ins);
        listaInstalaciones = instalacionEJB.findAll();
    }
    
    public void establecerInstalacionModificar(Instalacion ins){
        this.setAccion("M");
        this.ins = ins;
    }
    
    public void modificarInstalacion(){
        instalacionEJB.edit(this.ins);
        listaInstalaciones = instalacionEJB.findAll();
    }

    public List<Instalacion> getListaInstalaciones() {
        return listaInstalaciones;
    }

    public void setListaInstalaciones(List<Instalacion> listaInstalaciones) {
        this.listaInstalaciones = listaInstalaciones;
    }

    public Instalacion getIns() {
        return ins;
    }

    public void setIns(Instalacion ins) {
        this.ins = ins;
    }

    public InstalacionFacadeLocal getInstalacionEJB() {
        return instalacionEJB;
    }

    public void setInstalacionEJB(InstalacionFacadeLocal instalacionEJB) {
        this.instalacionEJB = instalacionEJB;
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
