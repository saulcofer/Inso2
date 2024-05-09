/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Instalacion_sesion;

/**
 *
 * @author jvega
 */
@Stateless
public class Instalacion_sesionFacade extends AbstractFacade<Instalacion_sesion> implements Instalacion_sesionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Instalacion_sesionFacade() {
        super(Instalacion_sesion.class);
    }
    
}
