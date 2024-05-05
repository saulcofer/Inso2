/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import modelo.Sesion;

/**
 *
 * @author alvar
 */
@Local
public interface PublicacionFacadeLocal {

    void create(Sesion publicacion);

    void edit(Sesion publicacion);

    void remove(Sesion publicacion);

    Sesion find(Object id);

    List<Sesion> findAll();

    List<Sesion> findRange(int[] range);

    int count();
    
}
