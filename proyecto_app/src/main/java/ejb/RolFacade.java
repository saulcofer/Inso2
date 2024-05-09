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
import modelo.Rol;

/**
 *
 * @author jvega
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    /**
     * Returns all roles stored in the database, excepting Admin role
     * @return 
     */
    public List<Rol> getRoles(){
        List<Rol> roles = findAll();
        Iterator<Rol> iterator = roles.iterator();
        
        while(iterator.hasNext()){
            Rol rol = iterator.next();
            if(rol.getDescripcion().equals("Admin")){
                iterator.remove();
            }
        }
        
        System.out.println("Test");
        return roles;
    }
}
