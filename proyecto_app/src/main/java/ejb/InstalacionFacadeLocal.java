/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import modelo.Instalacion;

/**
 *
 * @author jvega
 */
@Local
public interface InstalacionFacadeLocal {

    void create(Instalacion instalacion);

    void edit(Instalacion instalacion);

    void remove(Instalacion instalacion);

    Instalacion find(Object id);

    List<Instalacion> findAll();

    List<Instalacion> findRange(int[] range);

    int count();
    
    List<Instalacion> findInstalaciones();
    
    List<Instalacion> findMateriales();
    int crearInstalacion(Instalacion usuario);
    
}
