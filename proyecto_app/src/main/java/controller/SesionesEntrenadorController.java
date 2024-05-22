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
    private String[] selected_instalaciones;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
    @EJB
    private InstalacionFacadeLocal instalacionEJB;
    
    @PostConstruct
    public void init(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listasesiones = user.getSesiones();
        listainstalaciones=instalacionEJB.findAll();   
    }
    
    public void establecerSesionAñadir(){
        this.setAccion("R");
        this.sesion = new Sesion();
        String nuevas_instalaciones[]=new String[0];
        this.selected_instalaciones=nuevas_instalaciones;
    }
    
    public void insertarSesion(){
        List<Usuario> usuarios = new ArrayList<>();
        List<Instalacion> instalaciones = obtenerInstalaciones(this.selected_instalaciones);
        sesion.setInstalaciones(instalaciones);
        sesion.setUsuarios(usuarios);
        sesion.getUsuarios().add(user);
        user.getSesiones().add(sesion);
        sesionEJB.create(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionEliminar(Sesion sesion){
        this.setAccion("E");
        this.sesion = sesion;
    }
    
    public void eliminarSesion(){
        for(Usuario usuario : sesion.getUsuarios()) {
            usuario.getSesiones().remove(sesion);
        }
        for(Instalacion instalacion : sesion.getInstalaciones()) {
            instalacion.getSesiones().remove(sesion);
        }

        sesionEJB.remove(this.sesion);
        listasesiones = user.getSesiones();
    }
    
    public void establecerSesionModificar(Sesion sesion){
        this.setAccion("M");
        this.sesion = sesion;
        this.selected_instalaciones=obtenerInstalaciones_names(this.sesion.getInstalaciones());
    }
    
    public void modificarSesion(){
        List<Instalacion> instalacionesActuales = sesion.getInstalaciones();
        List<Instalacion> nuevasInstalaciones = obtenerInstalaciones(this.selected_instalaciones);
        // Eliminar instalaciones que ya no están seleccionadas
        instalacionesActuales.removeIf(ins -> !nuevasInstalaciones.contains(ins));

        // Añadir nuevas instalaciones seleccionadas
        for (Instalacion nuevaInstalacion : nuevasInstalaciones) {
            if (!instalacionesActuales.contains(nuevaInstalacion)) {
                instalacionesActuales.add(nuevaInstalacion);
                System.out.println("Instalacion Añadida");
            }
        }

        sesion.setInstalaciones(instalacionesActuales);

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
        String instalacion2="No tiene instalaciones asignadas";
        if(!getInstalacionLista(ses.getInstalaciones()).equals("")){
            instalacion2=getInstalacionLista(ses.getInstalaciones());
        }    
        
        return instalacion2;
    }
    
    public String getInstalacionLista(List<Instalacion> lista){
        String listainstalaciones2="";

        // Recorre la lista y añade los nombres de las instalaciones, separados por comas.
        for (Instalacion instalacion : lista) {
            listainstalaciones2=listainstalaciones2+" ";
            listainstalaciones2=listainstalaciones2+instalacion.getNombre();
            listainstalaciones2=listainstalaciones2+" ("+instalacion.getDescripcion()+")";
        }

    // Convierte el StringBuilder a String y lo devuelve.
        return listainstalaciones2;

    }
    
   

    public List<Instalacion> obtenerInstalaciones(String[] names){
        List<Instalacion> result = new ArrayList<>();
        int i;
        boolean found;
        
        for(Instalacion ins : this.listainstalaciones){
            i=0;
            found=false;
            while(!found && i<names.length){
                if(names[i].equals(ins.getNombre())){
                    result.add(ins);
                    found=true;
                }
                i++;
            }
        }
        return result;
    }
    
    public String[] obtenerInstalaciones_names (List<Instalacion>instalaciones){
        String[] result = new String[instalaciones.size()];

        for(int i=0;i<instalaciones.size();i++){
            result[i] = instalaciones.get(i).getNombre();
        }
        return result;
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

    public InstalacionFacadeLocal getInstalacionEJB() {
        return instalacionEJB;
    }

    public void setInstalacionEJB(InstalacionFacadeLocal instalacionEJB) {
        this.instalacionEJB = instalacionEJB;
    }

    public String[] getSelected_instalaciones() {
        return selected_instalaciones;
    }

    public void setSelected_instalaciones(String[] selected_instalaciones) {
        this.selected_instalaciones = selected_instalaciones;
    }

    
}
