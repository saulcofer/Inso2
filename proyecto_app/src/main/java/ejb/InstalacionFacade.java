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
import modelo.Instalacion;

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
