/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.RolFacadeLocal;
import ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.registry.infomodel.User;
import modelo.Persona;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped
public class UsuarioController implements Serializable{
    private Usuario usuario;
    private Persona persona;
    private Rol rol;
    private String rol_elegido;
    private List<Rol> roles;
    private char tipo;
    
    
    @EJB
    private UsuarioFacadeLocal userEJB;
    
    @EJB
    private RolFacadeLocal rolEJB;

    @PostConstruct
    public void init(){
        usuario = new Usuario();
        persona = new Persona();
        roles = rolEJB.getRoles();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public UsuarioFacadeLocal getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UsuarioFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }

    public RolFacadeLocal getRolEJB() {
        return rolEJB;
    }

    public void setRolEJB(RolFacadeLocal rolEJB) {
        this.rolEJB = rolEJB;
    }
    
    public Rol getRol(String tipoUsuario){        
        for (int i=0;i<=roles.size();i++){
            if(roles.get(i).getTipoUsuario().equals(tipoUsuario)){
                return roles.get(i);
            }
        } 
        return null;
    }

    public String getRol_elegido() {
        return rol_elegido;
    }

    public void setRol_elegido(String rol_elegido) {
        this.rol_elegido = rol_elegido;
    }
    
    public Rol obtenerRol(String rol_target){
        Rol result=null;
        for(Rol rol : this.roles){
            if(rol.getDescripcion().equals(rol_target)){
                result=rol;
            }
        }
        return result;
    }
    
    public void establecerUsuario(){
        this.usuario=usuario;
    }
    
    
    public void insertarUsuario(){
        String url;
        try{
            rol = obtenerRol(this.rol_elegido);
            usuario.setRol(rol);
            usuario.setPersona(persona);
            int resp = userEJB.crearUsuario(usuario);
            if(resp==0){
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"El nombre de usuario ya existe",""));
            }else{
                url= FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/faces/private/principal.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            }
        }catch(Exception e){
            System.out.println("Error al insertar el usuario "+e.getMessage());
        }
    }
    
   
    public void cargarDatosUsuario() {
        // Suponiendo que tienes algún mecanismo para obtener el nombre de usuario actualmente autenticado
        String nombreUsuario = obtenerNombreUsuarioAutenticado();

        // Buscar el usuario por su nombre de usuario
        usuario = userEJB.buscarPorNombreUsuario(nombreUsuario);

        // Si encontramos al usuario, podemos obtener la persona asociada
        if (usuario != null) {
            persona = usuario.getPersona();
        }
    }

    // Método para obtener el nombre de usuario del usuario actualmente autenticado
    private String obtenerNombreUsuarioAutenticado() {
        // Aquí debes implementar la lógica para obtener el nombre de usuario del usuario autenticado
        // Podría ser desde la sesión, desde un servicio de autenticación, etc.
        // Por ejemplo:
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getRemoteUser();
    }
    
}
