/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.SesionFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Sesion;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped

public class EditarPublicacionController implements Serializable{
    
    @Inject
    private ListarPublicacionesController listPubCon;
    
    @EJB
    private PublicacionFacadeLocal pubEJB;
    
    private Sesion pub;
        
    @PostConstruct
    public void init(){
        pub = listPubCon.getPub_target();
    }

    public ListarPublicacionesController getListPubCon() {
        return listPubCon;
    }

    public void setListPubCon(ListarPublicacionesController listPubCon) {
        this.listPubCon = listPubCon;
    }

    public Sesion getPub() {
        return pub;
    }

    public void setPub(Sesion pub) {
        this.pub = pub;
    }
    
    public void actualizarPub(){
        pubEJB.edit(pub);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Publicación actualizada con éxito",""));
    }
}
