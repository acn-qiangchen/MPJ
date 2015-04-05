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
import com.tink.mpj.business.ejb.PurchaseFacade;
import com.tink.mpj.persistence.model.Purchase;
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
public class PurchaseController {

    public PurchaseController() {
        pagingInfo = new PagingInfo();
        converter = new PurchaseConverter();
    }
    private Purchase purchase = null;
    private List<Purchase> purchaseItems = null;
    private PurchaseFacade jpaController = null;
    private PurchaseConverter converter = null;
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

    public PurchaseFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PurchaseFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "purchaseJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPurchaseItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPurchaseItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Purchase getPurchase() {
        if (purchase == null) {
            purchase = (Purchase) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPurchase", converter, null);
        }
        if (purchase == null) {
            purchase = new Purchase();
        }
        return purchase;
    }

    public String listSetup() {
        reset(true);
        return "purchase_list";
    }

    public String createSetup() {
        reset(false);
        purchase = new Purchase();
        return "purchase_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(purchase);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Purchase was successfully created.");
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
        return scalarSetup("purchase_detail");
    }

    public String editSetup() {
        return scalarSetup("purchase_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        purchase = (Purchase) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPurchase", converter, null);
        if (purchase == null) {
            String requestPurchaseString = JsfUtil.getRequestParameter("jsfcrud.currentPurchase");
            JsfUtil.addErrorMessage("The purchase with id " + requestPurchaseString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String purchaseString = converter.getAsString(FacesContext.getCurrentInstance(), null, purchase);
        String currentPurchaseString = JsfUtil.getRequestParameter("jsfcrud.currentPurchase");
        if (purchaseString == null || purchaseString.length() == 0 || !purchaseString.equals(currentPurchaseString)) {
            String outcome = editSetup();
            if ("purchase_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit purchase. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(purchase);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Purchase was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPurchase");
        Long id = new Long(idAsString);
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
                JsfUtil.addSuccessMessage("Purchase was successfully deleted.");
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

    public List<Purchase> getPurchaseItems() {
        if (purchaseItems == null) {
            getPagingInfo();
            purchaseItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return purchaseItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "purchase_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "purchase_list";
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
        purchase = null;
        purchaseItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Purchase newPurchase = new Purchase();
        String newPurchaseString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPurchase);
        String purchaseString = converter.getAsString(FacesContext.getCurrentInstance(), null, purchase);
        if (!newPurchaseString.equals(purchaseString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
