/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.SesionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Sesion;

/**
 *
 * @author saulcofer
 */
@Named
@ViewScoped

public class ListarSesionesController implements Serializable{
       
    private List<Sesion> sesiones;
    
    @EJB
    private SesionFacadeLocal sesEJB;
    
    private Sesion ses_target;
    
    private Sesion sesion;

    @PostConstruct
    public void init(){
        sesiones = sesEJB.findAll();
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public SesionFacadeLocal getSesEJB() {
        return sesEJB;
    }

    public void setSesEJB(SesionFacadeLocal sesEJB) {
        this.sesEJB = sesEJB;
    }

    public Sesion getSes_target() {
        return ses_target;
    }

    public void setSes_target(Sesion ses_target) {
        this.ses_target = ses_target;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.sesiones);
        hash = 53 * hash + Objects.hashCode(this.sesEJB);
        hash = 53 * hash + Objects.hashCode(this.ses_target);
        hash = 53 * hash + Objects.hashCode(this.sesion);
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
        final ListarSesionesController other = (ListarSesionesController) obj;
        if (!Objects.equals(this.sesiones, other.sesiones)) {
            return false;
        }
        if (!Objects.equals(this.sesEJB, other.sesEJB)) {
            return false;
        }
        if (!Objects.equals(this.ses_target, other.ses_target)) {
            return false;
        }
        if (!Objects.equals(this.sesion, other.sesion)) {
            return false;
        }
        return true;
    }

}
