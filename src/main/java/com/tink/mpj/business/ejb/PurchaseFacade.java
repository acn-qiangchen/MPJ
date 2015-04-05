/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.business.ejb;

import com.tink.mpj.persistence.model.Purchase;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shearer
 */
@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> {
    @PersistenceContext(unitName = "MPJ01")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseFacade() {
        super(Purchase.class);
    }
    
}
