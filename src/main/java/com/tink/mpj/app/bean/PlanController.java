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
import com.tink.mpj.business.ejb.PlanFacade;
import com.tink.mpj.persistence.model.Plan;
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
@Named(value = "planAdmin")
@RequestScoped
public class PlanController {

    public PlanController() {
//        pagingInfo = new PagingInfo();
//        converter = new PlanConverter();
    }
    
    private Plan plan = null;
    private List<Plan> planItems = null;
    
    @Inject
    private PlanFacade ejb;
    @Inject
    private PlanConverter converter;
    @Inject
    private PagingInfo pagingInfo;
    @Resource
    private UserTransaction utx = null;
    //@PersistenceUnit(unitName = "MPJ01")
    //private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(ejb.count());
        }
        return pagingInfo;
    }

//    public PlanFacade getJpaController() {
//        if (jpaController == null) {
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            jpaController = (PlanFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "planJpa");
//        }
//        return jpaController;
//    }

    public SelectItem[] getPlanItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejb.findAll(), false);
    }

    public SelectItem[] getPlanItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAll(), true);
    }

    public Plan getPlan() {
        if (plan == null) {
            plan = (Plan) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPlan", converter, null);
        }
        if (plan == null) {
            plan = new Plan();
        }
        return plan;
    }

    public String listSetup() {
        reset(true);
        return "plan_list";
    }

    public String createSetup() {
        reset(false);
        plan = new Plan();
        return "plan_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            ejb.create(plan);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Plan was successfully created.");
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
        return scalarSetup("plan_detail");
    }

    public String editSetup() {
        return scalarSetup("plan_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        plan = (Plan) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPlan", converter, null);
        if (plan == null) {
            String requestPlanString = JsfUtil.getRequestParameter("jsfcrud.currentPlan");
            JsfUtil.addErrorMessage("The plan with id " + requestPlanString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String planString = converter.getAsString(FacesContext.getCurrentInstance(), null, plan);
        String currentPlanString = JsfUtil.getRequestParameter("jsfcrud.currentPlan");
        if (planString == null || planString.length() == 0 || !planString.equals(currentPlanString)) {
            String outcome = editSetup();
            if ("plan_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit plan. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            ejb.edit(plan);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Plan was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPlan");
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
                JsfUtil.addSuccessMessage("Plan was successfully deleted.");
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

    public List<Plan> getPlanItems() {
        if (planItems == null) {
            getPagingInfo();
            planItems = ejb.findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return planItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "plan_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "plan_list";
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
        plan = null;
        planItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Plan newPlan = new Plan();
        String newPlanString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPlan);
        String planString = converter.getAsString(FacesContext.getCurrentInstance(), null, plan);
        if (!newPlanString.equals(planString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
