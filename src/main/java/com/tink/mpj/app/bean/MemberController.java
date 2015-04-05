/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.app.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import com.tink.mpj.app.bean.util.JsfUtil;
import com.tink.mpj.app.bean.util.PagingInfo;
import com.tink.mpj.business.ejb.MemberFacade;
import com.tink.mpj.persistence.model.Member;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author shearer
 */
public class MemberController {

    public MemberController() {
        pagingInfo = new PagingInfo();
        converter = new MemberConverter();
    }
    private Member member = null;
    private List<Member> memberItems = null;
    private MemberFacade jpaController = null;
    private MemberConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "MPJ01")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public MemberFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (MemberFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "memberJpa");
        }
        return jpaController;
    }

    public SelectItem[] getMemberItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getMemberItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Member getMember() {
        if (member == null) {
            member = (Member) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMember", converter, null);
        }
        if (member == null) {
            member = new Member();
        }
        return member;
    }

    public String listSetup() {
        reset(true);
        return "member_list";
    }

    public String createSetup() {
        reset(false);
        member = new Member();
        return "member_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(member);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Member was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("member_detail");
    }

    public String editSetup() {
        return scalarSetup("member_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        member = (Member) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMember", converter, null);
        if (member == null) {
            String requestMemberString = JsfUtil.getRequestParameter("jsfcrud.currentMember");
            JsfUtil.addErrorMessage("The member with id " + requestMemberString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String memberString = converter.getAsString(FacesContext.getCurrentInstance(), null, member);
        String currentMemberString = JsfUtil.getRequestParameter("jsfcrud.currentMember");
        if (memberString == null || memberString.length() == 0 || !memberString.equals(currentMemberString)) {
            String outcome = editSetup();
            if ("member_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit member. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(member);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Member was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentMember");
        long id = Long.parseLong(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Member was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Member> getMemberItems() {
        if (memberItems == null) {
            getPagingInfo();
            memberItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return memberItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "member_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "member_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        member = null;
        memberItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Member newMember = new Member();
        String newMemberString = converter.getAsString(FacesContext.getCurrentInstance(), null, newMember);
        String memberString = converter.getAsString(FacesContext.getCurrentInstance(), null, member);
        if (!newMemberString.equals(memberString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
