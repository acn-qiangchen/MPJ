/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.faces.component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

/**
 *
 * @author shearer
 */
//@Named
//@ViewScoped
// Component Backing Bean, not just faces backing bean
// and has to extend javax.faces.component.UINamingContainer.
@FacesComponent(value = "inputDate", namespace = "http://xmlns.jcp.org/jsf/component/mpj")
public class ComponentInputDate extends UINamingContainer{

    private static final DateFormat HTML5_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private String minDate = "";
    private String maxDate = "";
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        // Extract the minimum date from the interface
        java.util.Date attrsMin = (java.util.Date) getAttributes().get("min");
        if (attrsMin != null) {
            // Convert the date to an HTML5 date
            minDate = HTML5_FORMAT.format(attrsMin);
        }

        // Extract the maximum date from the interface
        java.util.Date attrsMax = (java.util.Date) getAttributes().get("max");
        if (attrsMax != null) {
            // Convert the date to an HTML5 date
            maxDate = HTML5_FORMAT.format(attrsMax);
        }

        super.encodeBegin(context);
    }

    /**
     * Gets the minimum date selectable in the date picker.
     *
     * @return Date formatted using the {@link inputDate#HTML5_FORMAT}
     */
    public String getMinDate() {
        return minDate;
    }

    /**
     * Gets the maximum date selectable in the date picker.
     *
     * @return Date formatted using the {@link inputDate#HTML5_FORMAT}
     */

     public String getMaxDate() {
         return maxDate;
    }
}
