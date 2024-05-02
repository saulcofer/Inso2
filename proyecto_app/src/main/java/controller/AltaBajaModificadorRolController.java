/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enterpriseJavaBing.PublicacionFacadeLocal;
import enterpriseJavaBing.RolFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Publicacion;
import modelo.Rol;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped

public class AltaBajaModificadorRolController implements Serializable{
    
    private List<Rol> roles;

    @EJB
    private RolFacadeLocal rolEJB;
    
    private String accion; // M=modificar E=eliminar R=añadir , hay que saber el caso para mostrar el widgetBar pertinente
    
    private Rol rol;
    
    @PostConstruct
    public void init(){
        roles = rolEJB.findAll();
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public RolFacadeLocal getRolEJB() {
        return rolEJB;
    }

    public void setRolEJB(RolFacadeLocal rolEJB) {
        this.rolEJB = rolEJB;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
        
    public void establecerRolAñadir(){
        this.setAccion("R");
        this.rol = new Rol();
    }
    
    public void insertarRol(){
        rolEJB.create(this.rol);
        roles = rolEJB.findAll();
    }
    
    public void establecerRolEliminar(Rol aRol){
        this.rol=aRol;
        this.setAccion("E");
    }
    
    public void eliminarRol(){
        rolEJB.remove(this.rol);
        roles = rolEJB.findAll();
    }
    
    public void establecerRolModificar(Rol aRol){
        this.rol=aRol;
        this.setAccion("M");
    }
    
    public void modificarRol(){
        rolEJB.edit(this.rol);
        roles = rolEJB.findAll();
    }
}
