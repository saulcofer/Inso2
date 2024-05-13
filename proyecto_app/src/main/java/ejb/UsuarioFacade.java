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
import javax.persistence.TypedQuery;
import modelo.Usuario;

/**
 *
 * @author jvega
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario verificarUsuario(Usuario us){
        String consulta = "FROM Usuario u WHERE u.username=:param1 and u.password=:param2";
        Query query = em.createQuery(consulta);
        query.setParameter("param1",us.getUsername());
        query.setParameter("param2",us.getPassword());
        List<Usuario>resultado = query.getResultList();
        
        if(resultado.size()!=0){
            return resultado.get(0);
        }else{
            return null;
        }
    }
    
    @Override
    public int crearUsuario(Usuario usuario){
        String consulta = "FROM Usuario u WHERE u.username=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1",usuario.getUsername());
        List<Usuario>resultado = query.getResultList();
        
        if(resultado.size()==0){
            create(usuario);
            return 1;
        }else{
            return 0;
        }
        
    }

    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
        query.setParameter("username", nombreUsuario);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            // Manejar la excepción si el usuario no es encontrado
            return null; // O lanzar una excepción personalizada, según lo que necesites
        }
    }
}
