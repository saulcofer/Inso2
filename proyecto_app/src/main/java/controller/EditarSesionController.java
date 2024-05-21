/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InstalacionFacadeLocal;
import ejb.SesionFacadeLocal;
import ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Instalacion;
import modelo.Sesion;
import modelo.Usuario;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped
public class EditarSesionController implements Serializable{
    private List<Sesion> listasesiones;
    private List<Usuario> listaEntrenadores;
    private List<Instalacion> listaInstalaciones;
    private Sesion sesion;
    private int selected_entrenador;
    private int[] selected_instalaciones;
    private String accion;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private InstalacionFacadeLocal instalacionEJB;
    
    @PostConstruct
    public void init(){
        listasesiones = sesionEJB.findAll();
        listaInstalaciones = instalacionEJB.findAll();
    }

    
    public void establecerSesionAñadir(){
        this.listaEntrenadores = usuarioEJB.findAllEntrenadores();
        this.setAccion("R");
        this.sesion = new Sesion();
        System.out.println(this.sesion);
    }

    public void insertarSesion(){
        Usuario entrenador = obtenerEntrenador(this.selected_entrenador);
        List<Instalacion> instalaciones = obtenerInstalaciones(this.selected_instalaciones);
        List<Usuario> usuarios = new ArrayList<>();
        
        sesion.setUsuarios(usuarios);
        sesion.getUsuarios().add(entrenador);
        
        sesion.setInstalaciones(instalaciones);
        
        entrenador.getSesiones().add(sesion);
        sesionEJB.create(this.sesion);
        listasesiones = sesionEJB.findAll();
    }
    
    
    public Usuario obtenerEntrenador(int id){
        Usuario result=null;
        for(Usuario us : this.listaEntrenadores){
            if(us.getIdUser()==id){
                result=us;
            }
        }
        return result;
    }
    
    public String obtenerUsernameEntrenador(List<Usuario> usuarios){
        Usuario result=null;
        for(Usuario us : usuarios){
            if(us.getRol().getDescripcion().equals("Entrenador")){
                result=us;break;
            }
        }
        return result.getUsername();
    }
    
     public List<Instalacion> obtenerInstalaciones(int[] ids){
        List<Instalacion> result = new ArrayList<>();
        int i;
        boolean found;
        
        for(Instalacion ins : this.listaInstalaciones){
            i=0;
            found=false;
            while(!found && i<ids.length){
                if(ids[i]==ins.getIdInstalacion()){
                    result.add(ins);
                    found=true;
                }
                i++;
            }
        }
        return result;
    }
    
    public void establecerSesionEliminar(Sesion sesion){
        this.setAccion("E");
        this.sesion = sesion;
    }
    
    public void eliminarSesion(){
//        for(Usuario usuario : sesion.getUsuarios()) {
//            usuario.getSesiones().remove(sesion);
//        }
//
//        sesionEJB.remove(this.sesion);
//        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionModificar(Sesion sesion){
        this.setAccion("M");
        this.sesion = sesion;
    }
    
    public void modificarSesion(){
//        sesionEJB.edit(this.sesion);
//        listasesiones = user.getSesiones();
    }

    public List<Sesion> getListasesiones() {
        return listasesiones;
    }

    public void setListasesiones(List<Sesion> listasesiones) {
        this.listasesiones = listasesiones;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public SesionFacadeLocal getSesionEJB() {
        return sesionEJB;
    }

    public void setSesionEJB(SesionFacadeLocal sesionEJB) {
        this.sesionEJB = sesionEJB;
    }

    public int getSelected_entrenador() {
        return selected_entrenador;
    }

    public void setSelected_entrenador(int selected_entrenador) {
        this.selected_entrenador = selected_entrenador;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Usuario> getListaEntrenadores() {
        return listaEntrenadores;
    }

    public void setListaEntrenadores(List<Usuario> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public List<Instalacion> getListaInstalaciones() {
        return listaInstalaciones;
    }

    public void setListaInstalaciones(List<Instalacion> listaInstalaciones) {
        this.listaInstalaciones = listaInstalaciones;
    }

    public int[] getSelected_instalaciones() {
        return selected_instalaciones;
    }

    public void setSelected_instalaciones(int[] selected_instalaciones) {
        this.selected_instalaciones = selected_instalaciones;
    }


    public InstalacionFacadeLocal getInstalacionEJB() {
        return instalacionEJB;
    }

    public void setInstalacionEJB(InstalacionFacadeLocal instalacionEJB) {
        this.instalacionEJB = instalacionEJB;
    }

}
