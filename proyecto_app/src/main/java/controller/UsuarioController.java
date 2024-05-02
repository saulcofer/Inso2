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
    
    public Rol getRol(char tipoUsuario){        
        for (int i=0;i<=roles.size();i++){
            if(roles.get(i).getTipoUsuario()==tipoUsuario){
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
    
    
    public void insertarUsuario(){
        try{
            rol = obtenerRol(this.rol_elegido);
            usuario.setRol(rol);
            usuario.setPersona(persona);
            userEJB.create(usuario);
        }catch(Exception e){
            System.out.println("Error al insertar el usuario "+e.getMessage());
        }
    }
    
}
