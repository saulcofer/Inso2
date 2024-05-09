/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.SesionFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Sesion;
import modelo.Usuario;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped

public class AltaPublicacionController implements Serializable{
    
    @Inject
    private Usuario user;
    
    @Inject
    private Sesion ses;
    
    private String categoria_elegida;
    
    @EJB
    private SesionFacadeLocal pubEJB;
    
    @PostConstruct
    public void init(){
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        categorias=catEJB.findAll();
    }
    
    
    public void insertarPublicacion(){
        try{
            ses.setPersona(user.getPersona());
            ses.setCategoria(obtenerCategoria(categoria_elegida));  // No es posible pasaar el objeto directamente de la vista al controlador     
            ses.setFecha(new Date());
            pubEJB.create(ses);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Publicacion registrada con éxito",""));
        }catch(Exception e){
            System.out.println("Error al insertar el usuario "+e.getMessage());
        }
    }  
    
    public Categoria obtenerCategoria(String cat_target){
        Categoria result=null;
        for(Categoria cat : categorias){
            if(cat.getNombre().equals(cat_target)){
                result=cat;
            }
        }
        return result;
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Sesion getPub() {
        return ses;
    }

    public void setPub(Sesion pub) {
        this.ses = pub;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public PublicacionFacadeLocal getPubEJB() {
        return pubEJB;
    }

    public void setPubEJB(PublicacionFacadeLocal pubEJB) {
        this.pubEJB = pubEJB;
    }

    public CategoriaFacadeLocal getCatEJB() {
        return catEJB;
    }

    public void setCatEJB(CategoriaFacadeLocal catEJB) {
        this.catEJB = catEJB;
    }

    public String getCategoria_elegida() {
        return categoria_elegida;
    }

    public void setCategoria_elegida(String categoria_elegida) {
        this.categoria_elegida = categoria_elegida;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.user);
        hash = 23 * hash + Objects.hashCode(this.ses);
        hash = 23 * hash + Objects.hashCode(this.categoria_elegida);
        hash = 23 * hash + Objects.hashCode(this.categorias);
        hash = 23 * hash + Objects.hashCode(this.pubEJB);
        hash = 23 * hash + Objects.hashCode(this.catEJB);
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
        final AltaPublicacionController other = (AltaPublicacionController) obj;
        if (!Objects.equals(this.categoria_elegida, other.categoria_elegida)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.ses, other.ses)) {
            return false;
        }
        if (!Objects.equals(this.categorias, other.categorias)) {
            return false;
        }
        if (!Objects.equals(this.pubEJB, other.pubEJB)) {
            return false;
        }
        if (!Objects.equals(this.catEJB, other.catEJB)) {
            return false;
        }
        return true;
    }




    
    
}
