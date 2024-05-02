/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import ejb.MenuFacadeLocal;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Menu;
import modelo.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author alvar
 */
@Named
@SessionScoped

public class MenuController implements Serializable{
    
    private MenuModel modelo;
    
    private List<Menu> menusUsuario;
    
    @EJB
    private MenuFacadeLocal menuEJB;

    
    @PostConstruct
    public void init(){
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        menusUsuario=menuEJB.obtenerMenusUsuario(user);
        modelo= new DefaultMenuModel();
        obtenerMenu(menusUsuario);
    }
    
    public void obtenerMenu(List<Menu> menuUser){
        DefaultSubMenu subMenu = null;
        DefaultMenuItem item = null;
        boolean sigSubMenu = false;
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        
        for (Menu menu : menuUser) {
            if (menu.getTipo().equals("S")) {
                if (sigSubMenu) {
                    modelo.getElements().add(subMenu);
                    sigSubMenu = false;
                }
                subMenu = DefaultSubMenu.builder().label(menu.getNombre()).build();
                sigSubMenu = true;
            } else {
                item = DefaultMenuItem.builder().value(menu.getNombre()).url(contextPath+menu.getUrl()).build();        // url de la web destino
                if (menu.getIdMenu() > 0) {
                    subMenu.getElements().add(item);
                } else {
                    modelo.getElements().add(item);
                }
            }
        }
        modelo.getElements().add(subMenu);
    }
    
    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }
    
}
