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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private List<Usuario> listaParticipantes;
    private List<Instalacion> listaInstalaciones;
    private Sesion sesion;
    private int selected_entrenador;
    private String[] selected_instalaciones;
    private String[] selected_participantes;
    private String accion;
    private Usuario entrenador;
    private List<Usuario> participantes;
    
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
        listaEntrenadores = usuarioEJB.findAllEntrenadores();
        listaParticipantes = usuarioEJB.findAllParticipantes();
    }

    
    public void establecerSesionAñadir(){
        this.setAccion("R");
        this.sesion = new Sesion();
        // para resetear selectMenus de la vista
        this.selected_entrenador=-1;
        this.selected_instalaciones=null;
    }

    public void insertarSesion(){
        Usuario entrenador = obtenerEntrenador(this.selected_entrenador);
        List<Instalacion> instalaciones = obtenerInstalaciones(this.selected_instalaciones);
        List<Usuario> usuarios = new ArrayList<>();
        
        sesion.setUsuarios(usuarios);
        sesion.getUsuarios().add(entrenador);
        
        for(Instalacion ins:instalaciones){
            ins.getSesiones().add(this.sesion);
            instalacionEJB.edit(ins);
        }
        sesion.setInstalaciones(instalaciones);
        entrenador.getSesiones().add(sesion);
        
        usuarioEJB.edit(entrenador);
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
    
    public Usuario obtenerEntrenador_lista(List<Usuario> usuarios){
        Usuario result=null;
        for(Usuario us : usuarios){
            if(us.getRol().getDescripcion().equals("Entrenador")){
                result=us;break;
            }
        }
        return result;
    }
    
    public String[] obtenerParticipantes_names(List<Usuario> usuarios){
        String[] result;
        int j;
        if(usuarios.size()>1){
            // Hay participantes (por defecto hay un usuario de tipo entrenador en todas las sesiones)
            result= new String[usuarios.size()-1];
            j=0;
            for(int i=0;i<usuarios.size();i++){
                if(usuarios.get(i).getRol().getDescripcion().equals("Participante")){
                    result[j]=usuarios.get(i).getUsername();
                    j++;
                }
            } 
        }else{
            result = new String[0]; // lista vacía si no hay usuarios
        }
        return result;
    }
    
    public List<Usuario> obtenerParticipantes(String[] usernames) {
        List<Usuario> result = new ArrayList<>();
        for (String username : usernames) {
            Usuario usuario = usuarioEJB.buscarPorNombreUsuario(username);
            if (usuario != null) {
                result.add(usuario);
            }
        }
        return result;
    }

    public List<Usuario> obtenerParticipantes_lista(List<Usuario> usuarios) {
        List<Usuario> participantes = new ArrayList<>();
        for (Usuario us : usuarios) {
            if (us.getRol().getDescripcion().equals("Participante")) {
                participantes.add(us);
            }
        }
        return participantes;
    }
    
     public List<Instalacion> obtenerInstalaciones(String[] names){
        List<Instalacion> result = new ArrayList<>();
        int i;
        boolean found;
        
        for(Instalacion ins : this.listaInstalaciones){
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
    
    public void establecerSesionEliminar(Sesion sesion){
        this.setAccion("E");
        this.sesion = sesion;
        this.entrenador = obtenerEntrenador_lista(sesion.getUsuarios());
        this.participantes=this.obtenerParticipantes_lista(sesion.getUsuarios());
    }
    
    public void eliminarSesion(){
        for(Usuario usuario : sesion.getUsuarios()) {
            usuario.getSesiones().remove(sesion);
            usuarioEJB.edit(usuario);
        }
        for(Instalacion instalacion : sesion.getInstalaciones()) {
            instalacion.getSesiones().remove(sesion);
            instalacionEJB.edit(instalacion);
        }

        sesionEJB.remove(this.sesion);
        listasesiones = sesionEJB.findAll();
    }
    
    public void establecerSesionModificar(Sesion sesion){
        this.setAccion("M");
        this.sesion = sesion;
        System.out.println(sesion.getUsuarios());
        
        if(this.obtenerEntrenador_lista(this.sesion.getUsuarios())!=null){
            this.selected_entrenador=this.obtenerEntrenador_lista(this.sesion.getUsuarios()).getIdUser();
        }
        this.selected_instalaciones=obtenerInstalaciones_names(this.sesion.getInstalaciones());
        this.selected_participantes=obtenerParticipantes_names(this.sesion.getUsuarios());
    }

    public void modificarSesion() {
        // Obtener el nuevo entrenador
        Usuario nuevoEntrenador = obtenerEntrenador(this.selected_entrenador);
        List<Instalacion> nuevasInstalaciones = obtenerInstalaciones(this.selected_instalaciones);
        List<Usuario> usuarios = sesion.getUsuarios();

        // Actualizar el entrenador
        Usuario entrenadorActual = obtenerEntrenador_lista(usuarios);
        if (!entrenadorActual.getUsername().equals(nuevoEntrenador.getUsername())) {
            entrenadorActual.getSesiones().remove(this.sesion);
            usuarioEJB.edit(entrenadorActual);
            usuarios.remove(entrenadorActual);            
            usuarios.add(nuevoEntrenador);
        }

        // Actualizar participantes
        List<Usuario> nuevosParticipantes = obtenerParticipantes(this.selected_participantes);
        List<Usuario> participantesActuales = obtenerParticipantes_lista(usuarios);


        // Eliminar participantes que ya no están seleccionados
        for (Usuario participanteActual : new ArrayList<>(participantesActuales)) {
            if (!nuevosParticipantes.contains(participanteActual)) {
                usuarios.remove(participanteActual);
                participanteActual.getSesiones().remove(this.sesion);
                usuarioEJB.edit(participanteActual);
            }
        }

        // Agregar nuevos participantes seleccionados
        for (Usuario nuevoParticipante : nuevosParticipantes) {
            if (!participantesActuales.contains(nuevoParticipante)) {
                usuarios.add(nuevoParticipante);
                nuevoParticipante.getSesiones().add(this.sesion);
                usuarioEJB.edit(nuevoParticipante);
            }
        }

        // Obtener la lista de instalaciones actuales de la sesión
        List<Instalacion> instalacionesActuales = sesion.getInstalaciones();
        // Eliminar la sesión de las instalaciones que no están en las nuevas instalaciones
        for (Instalacion instalacion : instalacionesActuales) {
            if (!nuevasInstalaciones.contains(instalacion)) {
                instalacion.getSesiones().remove(sesion);
                instalacionEJB.edit(instalacion);
            }
        }

        // Eliminar las instalaciones que no están en las nuevas instalaciones
        instalacionesActuales.removeIf(ins -> !nuevasInstalaciones.contains(ins));
        // Agregar nuevas instalaciones y la sesión correspondiente si no está ya presente
        for (Instalacion nuevaInstalacion : nuevasInstalaciones) {
            if (!instalacionesActuales.contains(nuevaInstalacion)) {
                instalacionesActuales.add(nuevaInstalacion);
                nuevaInstalacion.getSesiones().add(sesion);
                instalacionEJB.edit(nuevaInstalacion);
            }
        }

        sesion.setInstalaciones(instalacionesActuales);
        sesion.setUsuarios(usuarios);
        nuevoEntrenador.getSesiones().add(this.sesion);
        usuarioEJB.edit(nuevoEntrenador);
        sesionEJB.edit(this.sesion);
        listasesiones = sesionEJB.findAll();
    }
    
    
    public boolean sameElements(List<Instalacion> list1, List<Instalacion> list2) {
        if (list1 == null || list2 == null) {
            return false;
        }
        Set<Instalacion> set1 = new HashSet<>(list1);
        Set<Instalacion> set2 = new HashSet<>(list2);
        return set1.equals(set2);
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

    public String[] getSelected_instalaciones() {
        return selected_instalaciones;
    }

    public void setSelected_instalaciones(String[] selected_instalaciones) {
        this.selected_instalaciones = selected_instalaciones;
    }


    public InstalacionFacadeLocal getInstalacionEJB() {
        return instalacionEJB;
    }

    public void setInstalacionEJB(InstalacionFacadeLocal instalacionEJB) {
        this.instalacionEJB = instalacionEJB;
    }

    public String[] getSelected_participantes() {
        return selected_participantes;
    }

    public void setSelected_participantes(String[] selected_participantes) {
        this.selected_participantes = selected_participantes;
    }

    public List<Usuario> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(List<Usuario> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public Usuario getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Usuario entrenador) {
        this.entrenador = entrenador;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
    
}
