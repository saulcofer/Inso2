/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Instalacion;
import modelo.Rol;

/**
 *
 * @author jvega
 */
@Stateless
public class InstalacionFacade extends AbstractFacade<Instalacion> implements InstalacionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstalacionFacade() {
        super(Instalacion.class);
    }
    
    @Override
    public List<Instalacion> findInstalaciones(){
        List<Instalacion> instalaciones = findAll();
        Iterator<Instalacion> iterator = instalaciones.iterator();
        
        while(iterator.hasNext()){
            Instalacion instalacion = iterator.next();
            if(instalacion.getTipo().equals("M")){
                iterator.remove();
            }
        }
        
        return instalaciones;
    }
    
    @Override
    public List<Instalacion> findMateriales(){
        List<Instalacion> materiales = findAll();
        Iterator<Instalacion> iterator = materiales.iterator();
        
        while(iterator.hasNext()){
            Instalacion material = iterator.next();
            if(material.getTipo().equals("I")){
                iterator.remove();
            }
        }
        
        return materiales;
    }
    
    
    @Override
    public int crearInstalacion(Instalacion ins){
        String consulta = "FROM Instalacion ins WHERE ins.nombre=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1",ins.getNombre());
        List<Instalacion>resultado = query.getResultList();
        
        if(resultado.size()==0){
            create(ins);
            return 1;
        }else{
            return 0;
        }
        
    }
}
