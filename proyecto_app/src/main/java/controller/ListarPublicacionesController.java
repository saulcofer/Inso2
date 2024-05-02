/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PublicacionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import modelo.Publicacion;

/**
 *
 * @author alvar
 */

@Named
@RequestScoped

public class ListarPublicacionesController implements Serializable{
    
    private List<Publicacion> publicaciones;
    
    @EJB
    private PublicacionFacadeLocal pubEJB;
    
    private Publicacion pub_target;
            
    @PostConstruct
    public void init(){
        publicaciones = pubEJB.findAll();
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public PublicacionFacadeLocal getPubEJB() {
        return pubEJB;
    }

    public void setPubEJB(PublicacionFacadeLocal pubEJB) {
        this.pubEJB = pubEJB;
    }

    public Publicacion getPub_target() {
        return pub_target;
    }

    public void setPub_target(Publicacion pub_target) {
        this.pub_target = pub_target;
    }
 
}
