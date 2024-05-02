/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import modelo.Menu;
import modelo.Usuario;

/**
 *
 * @author alvar
 */
@Local
public interface MenuFacadeLocal {

    void create(Menu menus);

    void edit(Menu menus);

    void remove(Menu menus);

    Menu find(Object id);

    List<Menu> findAll();

    List<Menu> findRange(int[] range);

    int count();
    
    List<Menu> obtenerMenusUsuario(Usuario user);
    
}
