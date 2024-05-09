/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Usuario_sesion;

/**
 *
 * @author jvega
 */
@Stateless
public class Usuario_sesionFacade extends AbstractFacade<Usuario_sesion> implements Usuario_sesionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuario_sesionFacade() {
        super(Usuario_sesion.class);
    }
    
}
