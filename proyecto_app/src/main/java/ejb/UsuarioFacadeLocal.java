/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import modelo.Usuario;

/**
 *
 * @author jvega
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario verificarUsuario(Usuario us);
    
    Usuario buscarPorNombreUsuario(String nombreUsuario);
        
    int crearUsuario(Usuario usuario);

    public List<Usuario> findAllEntrenadores();
    
    public List<Usuario> findAllParticipantes();
}
