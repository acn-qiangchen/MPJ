/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.persistence.model;

import com.tink.mpj.common.constraint.Email;
import com.tink.mpj.common.constraint.Required;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shearer
 */
@Entity
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Required
    private String planId;
    private String kbn;
    private String unitKbn;
    @Min(0)
    private int unitValue;
    private int totalValue;
    private int duration;
    private int effFrom;
    private int effTo;
    
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getKbn() {
        return kbn;
    }

    public void setKbn(String kbn) {
        this.kbn = kbn;
    }

    public String getUnitKbn() {
        return unitKbn;
    }

    public void setUnitKbn(String unitKbn) {
        this.unitKbn = unitKbn;
    }

    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getEffFrom() {
        return effFrom;
    }

    public void setEffFrom(int effFrom) {
        this.effFrom = effFrom;
    }

    public int getEffTo() {
        return effTo;
    }

    public void setEffTo(int effTo) {
        this.effTo = effTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tink.mpj.persistence.model.Plan[ id=" + id + " ]";
    }
    
}
