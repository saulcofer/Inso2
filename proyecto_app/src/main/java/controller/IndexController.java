/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

public class IndexController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private Usuario user;
    
    
    @PostConstruct
    public void init(){
        user = new Usuario();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    public String verificarUsuario() throws IOException{
        String url;
        Usuario aUser = usuarioEJB.verificarUsuario(user);
        if(aUser!=null){
            url= FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/faces/private/principal.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", aUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            url="private/principal.xhtml";
        }else{
            url="public/index.xhtml";
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Credenciales no válidas",""));
        }
        
        return url;
    }
    

}
