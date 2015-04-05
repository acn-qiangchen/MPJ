/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.app.bean;

import com.tink.mpj.persistence.model.Purchase;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author shearer
 */
public class PurchaseConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        PurchaseController controller = (PurchaseController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "purchase");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Purchase) {
            Purchase o = (Purchase) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.tink.mpj.persistence.model.Purchase");
        }
    }
    
}
