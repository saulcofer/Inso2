/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped

public class NewTemplateController implements Serializable{
    
    private Usuario sessionUser;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }
    
    public void verificarYMostrar() throws IOException{
        sessionUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        String url;
        
        if(sessionUser==null){
            url =FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/faces/public/permisosinsuficientes.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void destruirSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        String url="/proyecto_app/";   // redirige a la xhtml inicial por defecto en web.xml
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
    
    
    public Usuario getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(Usuario sessionUser) {
        this.sessionUser = sessionUser;
    }
    
}
