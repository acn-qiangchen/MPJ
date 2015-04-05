/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.app.bean;

import com.tink.mpj.persistence.model.Member;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author shearer
 */
public class MemberConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        long id = Long.parseLong(string);
        MemberController controller = (MemberController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "member");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Member) {
            Member o = (Member) object;
            return String.valueOf(o.getSeqNo());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.tink.mpj.persistence.model.Member");
        }
    }
    
}
