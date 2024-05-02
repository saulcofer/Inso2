/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enterpriseJavaBing.CategoriaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Categoria;

/**
 *
 * @author alvar
 */

@Named
@ViewScoped

public class AltaCategoriaController implements Serializable{
    
    @Inject
    private Categoria categoria;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @PostConstruct
    public void init(){
        categoria = new Categoria();
        categoria.setNombre("Introduzca al mejor trapero vivo: ");
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }
    
    public void insertarCategoria(){
        try{
            categoriaEJB.create(categoria);
        }catch(Exception e){
            System.out.println("Error al insertar la categoria "+e.getMessage());
        }
    }
}
