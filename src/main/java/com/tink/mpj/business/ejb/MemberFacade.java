/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.business.ejb;

import com.tink.mpj.persistence.model.Member;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author shearer
 */
@Stateless
public class MemberFacade extends AbstractFacade<Member> {
    @PersistenceContext(unitName = "MPJ01")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberFacade() {
        super(Member.class);
    }
    
    public Member findbyEmail(String email){
        Query query = em.createNamedQuery("queryByEmail");
        query.setParameter("email", email);
        List<Member> result = query.getResultList();
        if(result.size() > 0){
            return result.get(0);
        }
        
        return new Member();
    }
    
}
