/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.CategoriaPermisoBean;

/**
 *
 * @author DMART
 */
@Stateless
public class CategoriaPermisoBeanFacade extends AbstractFacade<CategoriaPermisoBean> {

    @PersistenceContext(unitName = "SistemaDeTicketsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaPermisoBeanFacade() {
        super(CategoriaPermisoBean.class);
    }
    
}
