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
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import modelo.Sesion;

/**
 *
 * @author alvar
 */

@Named
@RequestScoped

public class ListarPublicacionesController implements Serializable{
    
    private List<Sesion> publicaciones;
    
    @EJB
    private PublicacionFacadeLocal pubEJB;
    
    private Sesion pub_target;
            
    @PostConstruct
    public void init(){
        publicaciones = pubEJB.findAll();
    }

    public List<Sesion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Sesion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public PublicacionFacadeLocal getPubEJB() {
        return pubEJB;
    }

    public void setPubEJB(PublicacionFacadeLocal pubEJB) {
        this.pubEJB = pubEJB;
    }

    public Sesion getPub_target() {
        return pub_target;
    }

    public void setPub_target(Sesion pub_target) {
        this.pub_target = pub_target;
    }
 
}
