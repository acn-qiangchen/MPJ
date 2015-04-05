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
import com.tink.mpj.business.ejb.TicketFacade;
import com.tink.mpj.persistence.model.Ticket;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author shearer
 */
@Named(value = "ticketAdmin")
@RequestScoped
public class TicketController {

    private Ticket ticket = null;
    private List<Ticket> ticketItems = null;
    @Inject
    private TicketFacade ejb;
    @Inject
    private TicketConverter converter;
    @Inject
    private PagingInfo pagingInfo;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "MPJ01")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(ejb.count());
        }
        return pagingInfo;
    }

    public SelectItem[] getTicketItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejb.findAll(), false);
    }

    public SelectItem[] getTicketItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAll(), true);
    }

    public Ticket getTicket() {
        if (ticket == null) {
            ticket = (Ticket) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTicket", converter, null);
        }
        if (ticket == null) {
            ticket = new Ticket();
        }
        return ticket;
    }

    public String listSetup() {
        reset(true);
        return "ticket_list";
    }

    public String createSetup() {
        reset(false);
        ticket = new Ticket();
        return "ticket_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            ejb.create(ticket);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ticket was successfully created.");
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
        return scalarSetup("ticket_detail");
    }

    public String editSetup() {
        return scalarSetup("ticket_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        ticket = (Ticket) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTicket", converter, null);
        if (ticket == null) {
            String requestTicketString = JsfUtil.getRequestParameter("jsfcrud.currentTicket");
            JsfUtil.addErrorMessage("The ticket with id " + requestTicketString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String ticketString = converter.getAsString(FacesContext.getCurrentInstance(), null, ticket);
        String currentTicketString = JsfUtil.getRequestParameter("jsfcrud.currentTicket");
        if (ticketString == null || ticketString.length() == 0 || !ticketString.equals(currentTicketString)) {
            String outcome = editSetup();
            if ("ticket_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit ticket. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            ejb.edit(ticket);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ticket was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTicket");
        Long id = new Long(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            ejb.remove(ejb.find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Ticket was successfully deleted.");
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

    public List<Ticket> getTicketItems() {
        if (ticketItems == null) {
            getPagingInfo();
            ticketItems = ejb.findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return ticketItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "ticket_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "ticket_list";
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
        ticket = null;
        ticketItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Ticket newTicket = new Ticket();
        String newTicketString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTicket);
        String ticketString = converter.getAsString(FacesContext.getCurrentInstance(), null, ticket);
        if (!newTicketString.equals(ticketString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
