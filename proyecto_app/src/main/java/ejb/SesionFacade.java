/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Sesion;
import modelo.Usuario;

/**
 *
 * @author jvega
 */
@Stateless
public class SesionFacade extends AbstractFacade<Sesion> implements SesionFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SesionFacade() {
        super(Sesion.class);
    }
    
    public List<Sesion> obtenerSesionesUsuario() {
   
        String consulta = "SELECT * FROM sesiones";
        Query query = em.createQuery(consulta);
        List<Sesion> resultado = query.getResultList();
        
        return resultado;

    }
    
}
