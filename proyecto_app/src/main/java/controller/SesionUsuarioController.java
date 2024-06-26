/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.SesionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Persona;
import modelo.Sesion;
import modelo.Usuario;

/**
 *
 * @author saulcofer
 */

@Named
@ViewScoped
public class SesionUsuarioController implements Serializable{
    
    private List<Sesion> listasesiones;    
    private Sesion sesion;
    private Usuario user;
    private Persona persona;
    private String accion;
    private float nuevaVal;
    private String nuevoComentario;
    
    @EJB
    private SesionFacadeLocal sesionEJB;
    
    @PostConstruct
    public void init(){
        user = new Usuario();
        persona = new Persona();
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        listasesiones = user.getSesiones();
        System.out.println("Se activa el controlador de Usuario con username: "+user.getUsername());
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
    
    public String getNuevoComentario() {
        return nuevoComentario;
    }

    public void setNuevoComentario(String nuevoComentario) {
        this.nuevoComentario = nuevoComentario;
    }

    
    public float getNuevaVal() {
        return nuevaVal;
    }

    public void setNuevaVal(float nuevaVal) {
        this.nuevaVal = nuevaVal;
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

    public SesionFacadeLocal getSesionEJB() {
        return sesionEJB;
    }

    public void setSesionEJB(SesionFacadeLocal sesionEJB) {
        this.sesionEJB = sesionEJB;
    }
    
    public void establecerSesionValorar(Sesion sesion){
        this.sesion = sesion;
    }
    
    
    public void valorarSesionUsuario(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.sesion = sesion;
        listasesiones = user.getSesiones();
        
        if(sesion.getUsuarios().size()==1)
        {
            //Sesion vacia de valoracion
            sesion.getValoracion();
            sesion.setComentarios(user.getUsername()+":"+nuevoComentario+"  ");
        }else{
            //Sesion con participantes
            float val = nuevaVal;
            float num,result;
            
            if(sesion.getUsuarios().size()==2){
                result = nuevaVal;
            }else{
                result = calcularNuevaMedia(sesion.getValoracion(),sesion.getUsuarios().size()-1,nuevaVal);
            }
            
            this.sesion.setValoracion(result);
            if(!nuevoComentario.equals("")){
                if(sesion.getComentarios()==null){
                    sesion.setComentarios(user.getUsername()+":"+nuevoComentario+"  ");
                }else{
                    sesion.setComentarios(sesion.getComentarios()+user.getUsername()+":"+nuevoComentario+"  ");
                }
                
            }
        }
        sesionEJB.edit(this.sesion);
    }
    
    public static float calcularNuevaMedia(float mediaAnterior, int numeroParticipantes, float nuevaNota) {
        // Calcula la suma total de todas las evaluaciones anteriores
        float sumaTotalAnterior = mediaAnterior * numeroParticipantes;
        
        // Suma la nueva nota a la suma total anterior
        float sumaTotalNueva = sumaTotalAnterior + nuevaNota;
        
        // Calcula el nuevo número total de participantes, que incluye al nuevo evaluador
        int nuevoNumeroParticipantes = numeroParticipantes + 1;
        
        // Calcula la nueva media
        float nuevaMedia = sumaTotalNueva / nuevoNumeroParticipantes;
        
        return nuevaMedia;
    }
    
    public void inscribirseEnSesion(){
        user=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        System.out.println(user.getPersona().getNombre());
        
        int i=0;
        boolean found=false;
        
        while (!found && i<this.sesion.getUsuarios().size()){
            if(user.getUsername().equals(this.sesion.getUsuarios().get(i).getUsername())){
                found=true;
            }
            i++;
        }
        
        if(!found){
            System.out.println("Entra");
            sesion.getUsuarios().add(user);
            user.getSesiones().add(sesion); 
            listasesiones = user.getSesiones();
        }else{
            System.out.println("No entra");
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Ya estas inscrito",""));
        }
        

    }
    
}
