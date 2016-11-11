/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.RolBean;

/**
 *
 * @author DMART
 */
@Stateless
public class RolBeanFacade extends AbstractFacade<RolBean> {

    @PersistenceContext(unitName = "SistemaDeTicketsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolBeanFacade() {
        super(RolBean.class);
    }
    
}
