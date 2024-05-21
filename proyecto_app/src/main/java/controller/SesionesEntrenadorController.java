/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InstalacionFacadeLocal;
import ejb.SesionFacadeLocal;
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
 * @author jvega
 */
@Named
@ViewScoped
public class SesionesEntrenadorController implements Serializable{
    private List<Sesion> listasesiones;    
    private Sesion sesion;
    private Usuario user;
    private String accion;
    private List<Instalacion>listainstalaciones;
    private List<Instalacion>listamaterial;
    private Instalacion instalacion;
    private Instalacion material;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
    @EJB
    private InstalacionFacadeLocal instalacionEJB;
    
    @PostConstruct
    public void init(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listasesiones = user.getSesiones();
        listainstalaciones=instalacionEJB.findInstalaciones();
        listamaterial=instalacionEJB.findMateriales();       
    }
    
    public void establecerSesionAñadir(){
        this.setAccion("R");
        this.sesion = new Sesion();
        this.instalacion=null;
        this.material=null;
    }
    
    public void insertarSesion(){
        System.out.println("entra");
        List<Usuario> usuarios = new ArrayList<>();
        List<Instalacion> instalaciones = new ArrayList<>();
        sesion.setInstalaciones(instalaciones);
        sesion.setUsuarios(usuarios);
        sesion.getUsuarios().add(user);
        user.getSesiones().add(sesion);
        System.out.println(sesion);
        System.out.println(instalacion);
        System.out.println(material);
        if(instalacion!=null){
            sesion.getInstalaciones().add(instalacion);
            instalacion.getSesiones().add(sesion);
        }
        if(material!=null){
            sesion.getInstalaciones().add(material);
            material.getSesiones().add(sesion);
        }
        sesionEJB.create(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionEliminar(Sesion sesion){
        System.out.println(instalacion);
        System.out.println(material);
        this.setAccion("E");
        this.sesion = sesion;
        this.instalacion=getInstalacion2(sesion);
        this.material=getMaterial2(sesion);
    }
    
    public void eliminarSesion(){
        for(Usuario usuario : sesion.getUsuarios()) {
            usuario.getSesiones().remove(sesion);
        }

        sesionEJB.remove(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionModificar(Sesion sesion){
        this.setAccion("M");
        this.sesion = sesion;
        this.instalacion=getInstalacion2(sesion);
        this.material=getMaterial2(sesion);
    }
    
    public void modificarSesion(){
        List<Instalacion> instalaciones = new ArrayList<>();
        sesion.setInstalaciones(instalaciones);
        sesion.getInstalaciones().add(this.instalacion);
        System.out.println(sesion.getInstalaciones());
        sesionEJB.edit(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public Instalacion getInstalacion2(Sesion ses){
        for (Instalacion instalacion : ses.getInstalaciones()) {
            if ("I".equals(instalacion.getTipo())) {
                return instalacion;
            }
    }    
        Instalacion instalacion=null;
        return instalacion;
    }
    
    public String getInstalacion(Sesion ses){
        String instalacion2="No tiene instalación asignada";
        if(getInstalacionLista(ses.getInstalaciones())!=null){
            instalacion2=getInstalacionLista(ses.getInstalaciones()).getNombre()+" ("+getInstalacionLista(ses.getInstalaciones()).getDescripcion()+")";
        }    
        
        return instalacion2;
    }
    
    public Instalacion getInstalacionLista(List<Instalacion> lista){
        for (Instalacion instalacion : lista) {
            if ("I".equals(instalacion.getTipo())) {
                return instalacion;
            }
    }    
        Instalacion instalacion=null;
        return instalacion;
    }
    
    public String getMaterial(Sesion ses){
        String material2="No tiene material asignado";
        if(getMaterialLista(ses.getInstalaciones())!=null){
            material2=getMaterialLista(ses.getInstalaciones()).getNombre()+" ("+getMaterialLista(ses.getInstalaciones()).getDescripcion()+")";
        }    
        
        return material2;
    }
    
    public Instalacion getMaterialLista(List<Instalacion> lista){
        for (Instalacion instalacion : lista) {
            if ("M".equals(instalacion.getTipo())) {
                return instalacion;
            }
    }    
        Instalacion instalacion=null;
        return instalacion;
    }
    
    public Instalacion getMaterial2(Sesion ses){
        for (Instalacion instalacion : ses.getInstalaciones()) {
            if ("M".equals(instalacion.getTipo())) {
                return instalacion;
            }
    }    
        Instalacion instalacion=null;
        return instalacion;
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Instalacion> getListainstalaciones() {
        return listainstalaciones;
    }

    public void setListainstalaciones(List<Instalacion> listainstalaciones) {
        this.listainstalaciones = listainstalaciones;
    }

    public List<Instalacion> getListamaterial() {
        return listamaterial;
    }

    public void setListamaterial(List<Instalacion> listamaterial) {
        this.listamaterial = listamaterial;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(Instalacion instalacion) {
        this.instalacion = instalacion;
    }

    public Instalacion getMaterial() {
        return material;
    }

    public void setMaterial(Instalacion material) {
        this.material = material;
    }

    public InstalacionFacadeLocal getInstalacionEJB() {
        return instalacionEJB;
    }

    public void setInstalacionEJB(InstalacionFacadeLocal instalacionEJB) {
        this.instalacionEJB = instalacionEJB;
    }

    
}
