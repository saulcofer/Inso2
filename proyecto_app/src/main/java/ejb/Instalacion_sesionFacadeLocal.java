/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import modelo.Instalacion_sesion;

/**
 *
 * @author jvega
 */
@Local
public interface Instalacion_sesionFacadeLocal {

    void create(Instalacion_sesion instalacion_sesion);

    void edit(Instalacion_sesion instalacion_sesion);

    void remove(Instalacion_sesion instalacion_sesion);

    Instalacion_sesion find(Object id);

    List<Instalacion_sesion> findAll();

    List<Instalacion_sesion> findRange(int[] range);

    int count();
    
}
