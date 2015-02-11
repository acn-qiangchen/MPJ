/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.business.ejb;

import com.tink.mpj.persistence.model.Ticket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shearer
 */
@Stateless
public class TicketFacade extends AbstractFacade<Ticket> {
    @PersistenceContext(unitName = "MPJ01")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }
    
}
