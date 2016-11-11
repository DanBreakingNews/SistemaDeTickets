/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.UsuarioBean;

/**
 *
 * @author DMART
 */
@Stateless
public class UsuarioBeanFacade extends AbstractFacade<UsuarioBean> {

    @PersistenceContext(unitName = "SistemaDeTicketsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioBeanFacade() {
        super(UsuarioBean.class);
    }
    
}
