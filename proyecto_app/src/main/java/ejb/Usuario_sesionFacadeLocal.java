/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import modelo.Usuario_sesion;

/**
 *
 * @author jvega
 */
@Local
public interface Usuario_sesionFacadeLocal {

    void create(Usuario_sesion usuario_sesion);

    void edit(Usuario_sesion usuario_sesion);

    void remove(Usuario_sesion usuario_sesion);

    Usuario_sesion find(Object id);

    List<Usuario_sesion> findAll();

    List<Usuario_sesion> findRange(int[] range);

    int count();
    
}
